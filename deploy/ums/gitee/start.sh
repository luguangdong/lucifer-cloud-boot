#!/bin/bash
FILEPATH=/data/deploy/lucifer-cloud/ums
JARFILE=ums.jar
ENV=tencent
pid=`ps -ef | grep $JARFILE | grep -v grep | awk '{print $2}'`
echo "旧服务进程pid=$pid"
if [ -n "$pid" ]
then
	kill -9 $pid
	echo "关闭服务 $JARFILE 成功"
	source /etc/profile
  nohup java  -Xms128m -Xmx128m -Xmn128m -jar $FILEPATH/$JARFILE  --spring.profiles.active=$ENV > log &
  pid=`ps -ef | grep $JARFILE | grep -v grep | awk '{print $2}'`
  echo "新服务进程pid=$pid"
  echo "启动服务 $JARFILE 成功"
fi
