package com.lucifer.cloud.boot.ums.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lucifer
 * @date 2023/7/6 11:24
 */
@Configuration
public class KafkaConfiguration {



    // 指定kafka server的地址，集群配多个，中间，逗号隔开
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    // 重试次数
    @Value("${spring.kafka.producer.retries}")
    private Integer retries;
    // 应答级别:多少个分区副本备份完成时向生产者发送ack确认(可选0、1、all/-1)
    @Value("${spring.kafka.producer.acks}")
    private String acks;
    // 批量发送的消息数量
    @Value("${spring.kafka.producer.batch-size}")
    private Integer batchSize;
    // 32MB的批处理缓冲区
    @Value("${spring.kafka.producer.buffer-memory}")
    private Integer bufferMemory;
    // 消费者组
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    // 当kafka中没有初始offset或offset超出范围时将自动重置offset
    // earliest:重置为分区中最小的offset;
    // latest:重置为分区中最新的offset(消费分区中新产生的数据);
    // none:只要有一个分区不存在已提交的offset,就抛出异常;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    // 批量一次最大拉取数据量(需要开启批量)
    @Value("${spring.kafka.consumer.max-poll-records}")
    private Integer maxPollRecords;
    // 批消费并发量，小于或等于Topic的分区数
    @Value("${spring.kafka.consumer.batch.concurrency}")
    private Integer batchConcurrency;
    // 自动提交(批量消费的话,关闭自动提交,需要在消费后手动提交)
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private Boolean autoCommit;
    // 提交offset延时(接收到消息后多久提交offset)
    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private Integer autoCommitInterval;


    /**
     *  生产者配置信息
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        return props;
    }
    /**
     *  生产者工厂
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    /**
     *  生产者模板
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    /**
     *  消费者配置信息
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        return props;
    }
    /**
     *  消费者批量工厂
     */
    @Bean
    public KafkaListenerContainerFactory<?> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
        //设置并发量，小于或等于Topic的分区数
        factory.setConcurrency(batchConcurrency);
        factory.getContainerProperties().setPollTimeout(10000);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        //设置为批量消费，每个批次数量在Kafka配置参数中设置ConsumerConfig.MAX_POLL_RECORDS_CONFIG
        factory.setBatchListener(true);
        // 缺少Topic后项目启动正常
        factory.setMissingTopicsFatal(false);
        factory.setAutoStartup(true);
        return factory;
    }

    /**
     *  消费者单次工厂
     */
    @Bean
    public KafkaListenerContainerFactory<?> singleFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
        factory.getContainerProperties().setPollTimeout(10000);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setBatchListener(false);
        // 缺少Topic后项目启动正常
        factory.setMissingTopicsFatal(false);
        // 业务环境所有的监听器都关闭 , JOB 开启
        factory.setAutoStartup(true);
        return factory;
    }



}
