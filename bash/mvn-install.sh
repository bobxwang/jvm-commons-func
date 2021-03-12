#!/usr/bin/env bash

mvn install:install-file \
-DgroupId=faiwei \
-DartifactId=faiwei \
-Dversion=20200109 \
-Dpackaging=jar \
-Dfile=./faiwei-20200109.jar
