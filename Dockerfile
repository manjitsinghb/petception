FROM thakurratanmj/ubuntu_java:latest
MAINTAINER manjitsingh.bits@gmail.com
# set default workdir
RUN mkdir /home/app
RUN cd /home/app
# Add package.json to allow for caching
COPY /web/target/web-*.tar.gz /home/app
COPY /rest/target/*.tar.gz /home/app
COPY /oauth/target/*.tar.gz /home/app
RUN tar xvf /root/web-*tar.gz -C /home/app
COPY startup.sh /home/app
# Expose the application port and run application
EXPOSE 80
EXPOSE 8080
EXPOSE 8082
CMD ["sh", "startup.sh"]