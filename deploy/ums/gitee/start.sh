#!/bin/bash
nohup java  -Xms128m -Xmx128m -Xmn128m -Dproject.name=auth -jar /data/deploy/lucifer-cloud/auth/auth.jar  --spring.profiles.active=tencent > log &
