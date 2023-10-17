pipeline {
    agent any

    environment{
            harborUser = 'DevOps'
            harborPasswd = 'lucifer1@DevOps'
            gitUrl = "http://192.168.137.200/lucifer-cloud/lucifer-cloud-boot.git"
            gitCredentialsId = "efeff955-9928-4a09-8151-2a450e96269e"
            mvn_home = "/data/maven/maven-3.9.4/"
            publisher_host = "192.168.137.133"
            publisher_hostName = "k8s-master-1"
            deployment = "lucifer-cloud-ums"
            namespace = "kube-lucifer" ;
     }

    // hidden 标签jenkins需要安装 Hidden Parameter 插件
    parameters {
          choice choices: ['*/master', '*/dev'], description: '选择的branch', name: 'branch'
          string name: 'tag', defaultValue: '1.1.1', description:'镜像标签', trim: true
          hidden name: 'harborHost', defaultValue: '192.168.137.199:80', description:'镜像私服地址'
          hidden name: 'harborRepo', defaultValue: 'lucifer_repository', description:'镜像仓库'
          hidden name: 'publisher_remoteDirectory', defaultValue: '/data/deploy/lucifer-cloud/ums/', description:'ssh远程文件夹'
          hidden name: 'publisher_sourceFiles', defaultValue: 'deploy.yaml', description:'ssh远程构建文件'
          hidden name: 'publisher_sourceFiles_env', defaultValue: 'deploy-env.yaml', description:'ssh远程临时环境变量文件'
       }


    // 存放所有任务的合集
    stages {
        // 拉取Git代码
        stage('checkout code from gitlab...') {
            steps {
                checkout scmGit(branches: [[name: "${branch}"]], extensions: [], userRemoteConfigs: [[credentialsId: "${gitCredentialsId}", url: "${gitUrl}"]])
            }
        }

        // 检测代码质量
        stage('code quality...') {
            steps {
                echo '检测代码质量'
            }
        }

        // 构建代码
        stage('mvn build code...') {
            steps {
                sh '${mvn_home}/bin/mvn clean package deploy -DskipTests'
            }
        }

        // 制作自定义镜像并发布Harbor
        stage('make docker image send to harbor...') {
                    steps {
                        sh '''cp ./ums/ums-service/target/*.jar ./
                        docker build -t ${JOB_NAME}:${tag} ./'''

                        sh '''docker login -u ${harborUser} -p ${harborPasswd} ${harborHost}
                        docker tag ${JOB_NAME}:${tag} ${harborHost}/${harborRepo}/${JOB_NAME}:${tag}
                        docker push ${harborHost}/${harborRepo}/${JOB_NAME}:${tag}
                        if docker images | awk -v image="${harborHost}/${harborRepo}/${JOB_NAME}:${tag}" 'NR>1{print $1":"$2}' | grep -q "^${harborHost}/${harborRepo}/${JOB_NAME}"; then
                          docker rmi -f ${harborHost}/${harborRepo}/${JOB_NAME}:${tag}
                        else
                          echo "tag镜像不存在"
                        fi
                        if docker images | awk -v image="${JOB_NAME}:${tag}" 'NR>1{print $1":"$2}' | grep -q "^${JOB_NAME}"; then
                          docker rmi -f ${JOB_NAME}:${tag}
                        else
                          echo "镜像不存在"
                        fi
                        image_ids=$(docker images -f "dangling=true" -q)
                        if [ -n "$image_ids" ]; then
                          for image_id in $image_ids; do
                            docker rmi -f "$image_id"
                          done
                        else
                          echo "虚悬镜像不存在"
                        fi
                        '''
                     }
         }

         // 将deploy.yaml文件传到k8s-master上
         stage('transfer deploy file to remoteDirectory...') {
                     steps {
                        sshPublisher(publishers: [sshPublisherDesc(configName: "${publisher_hostName}", transfers: [sshTransfer(cleanRemote: false, excludes: '',
                        execCommand: '''
                        cd ${publisher_remoteDirectory}
                        export image=${harborHost}/${harborRepo}/${JOB_NAME}:${tag}
                        envsubst < ${publisher_sourceFiles} > ${publisher_sourceFiles_env}
                        rm -rf ${publisher_sourceFiles}
                        mv ${publisher_sourceFiles_env} ${publisher_sourceFiles}
                        ''',
                        execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: "${publisher_remoteDirectory}", remoteDirectorySDF: false, removePrefix: '', sourceFiles: "${publisher_sourceFiles}")], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: true)])
                     }
         }

         // 远程执行k8s-master的kubectl命令
         stage('execute deployment...') {
                      steps {
                         sh '''ssh root@${publisher_host} kubectl apply -f ${publisher_remoteDirectory}${publisher_sourceFiles}
                          ssh root@${publisher_host} kubectl rollout restart deployment ${deployment} -n ${namespace}'''
                      }
         }
    }
}
