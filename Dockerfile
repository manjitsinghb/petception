FROM thakurratanmj/java8u:latest
RUN mkdir /home/app
RUN cd /home/app
COPY /web/target/web-*.tar.gz /home/app
COPY /rest/target/*.jar /home/app
COPY /oauth/target/*.jar /home/app
RUN tar xvf /home/app/web-*tar.gz -C /home/app
COPY startup.sh /home/app
EXPOSE 80
EXPOSE 8080
EXPOSE 8082
CMD ["sh", "/home/app/startup.sh"]