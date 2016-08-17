FROM thakurratanmj/ubuntu_java:latest
RUN mkdir /home/app
RUN cd /home/app
COPY /web/target/web-*.tar.gz /home/app
RUN tar xvf /home/app/web-*tar.gz -C /home/app
RUN mv /home/app/web-1.0-SNAPSHOT /home/app/application
COPY /rest/target/*.jar /home/app/application
COPY /oauth/target/*.jar /home/app/application
COPY startup.sh /home/app
EXPOSE 80
EXPOSE 8080
EXPOSE 8082
CMD ["sh", "/home/app/startup.sh","&"]