package com.lucifer.cloud.boot.blog.msg.consumer;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lucifer.cloud.boot.blog.domin.bo.User;
import com.lucifer.cloud.boot.blog.domin.dto.user.UserConverter;
import com.lucifer.cloud.boot.blog.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 * @author lucifer
 * @date 2023/12/23 14:42
 */
@Slf4j
@Service
public class UserConsumer {

    @Resource
    private UserService userService;

    @KafkaListener(
            id = "BLOG_SAVE_USER",
            groupId = "LOCAL_BLOG_SAVE_USER",
            idIsGroup = false,
            containerFactory = "singleFactory",
            topics ="BLOG_SAVE_USER"
    )
    public void consumerUser(ConsumerRecord<?, ?> consumerRecord, Acknowledgment ack) {
        String name = (String)consumerRecord.key();
        String value = (String) consumerRecord.value();
        JSONObject jsonUser = JSONUtil.parseObj(value);
        log.info("人员注册--博客人员系统接收消息成功。user={}",jsonUser);
        User user = UserConverter.convertJson2User(jsonUser);
        userService.save(user);
        log.info("人员注册--博客人员入库成功。name={}",name);
        ack.acknowledge();
    }

}
