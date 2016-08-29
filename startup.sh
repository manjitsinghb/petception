#!/bin/bash
java -jar /home/app/application/web-1.0-SNAPSHOT.jar &
java -jar /home/app/application/rest-1.0-SNAPSHOT.jar &
java -jar /home/app/application/oauth-1.0-SNAPSHOT.jar &