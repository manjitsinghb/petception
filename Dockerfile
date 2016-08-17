FROM thakurratanmj/ubuntu_java:latest
MAINTAINER manjitsingh.bits@gmail.com
# set default workdir
RUN mkdir /home/app
RUN cd /home/app
# Add package.json to allow for caching
COPY /web/target/web-*.tar.gz /home/app
RUN tar xvf /root/web-*tar.gz -C /home/app

# Expose the application port and run application
EXPOSE 80
CMD ["java", "-jar","/home/app/web-1.0-SNAPSHOT/web-1.0-SNAPSHOT.jar"]