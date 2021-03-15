#!/usr/bin/env bash

mvn archetype:generate \
-X \
-DgroupId=com.xx.xx.app \
-DartifactId=app \
-Dpackage=com.xx.xx.app \
-Dversion=0.0.1-SNAPSHOT \
-DarchetypeGroupId=com.xx.template \
-DarchetypeArtifactId=sb-maven-archetype \
-DarchetypeVersion=0.0.1-SNAPSHOT \
-DarchetypeCatalog=local