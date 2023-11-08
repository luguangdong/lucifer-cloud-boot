#!/bin/bash
source /etc/profile
nohup java  -Xms128m -Xmx128m -Xmn128m -Dproject.name=ums -jar /data/deploy/lucifer-cloud/ums/ums.jar  --spring.profiles.active=tencent > log &
