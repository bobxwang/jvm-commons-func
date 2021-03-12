#!/usr/bin/env bash

mvn deploy:deploy-file \
-DgroupId=taobao \
-DartifactId=taobao \
-Dversion=0.0.1-SNAPSHOT \
-Dpackaging=jar \
-Dfile=/Users/wangxiang/Downloads/dingtalk/taobao.jar \
-Durl=http://gg-maven.geely.com/repository/maven-snapshots/ \
-DrepositoryId=maven-snapshots