FROM thakurratanmj/ubuntu_java:latest
RUN mkdir /home/app
RUN cd /home/app
COPY /rest/target/rest*.tar.gz /home/app
#COPY /web/target/web-*.tar.gz /home/app
RUN tar xvf /home/app/rest-*tar.gz -C /home/app
RUN mv /home/app/rest-1.0-SNAPSHOT /home/app/application
#COPY /oauth/target/*.jar /home/app/application
COPY petceptioncert.keystore /
COPY /rest/startup.sh /home/app
#EXPOSE 80
EXPOSE 8080
#EXPOSE 8082
RUN chmod 777 /home/app/startup.sh
CMD ["sh", "/home/app/startup.sh"]