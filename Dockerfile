FROM thakurratanmj/ubuntu_java:latest
MAINTAINER manjitsingh.bits@gmail.com
# set default workdir
WORKDIR ~
# Add package.json to allow for caching
COPY /web/target/web-*.jar ~/
USER nobody
# Expose the application port and run application
EXPOSE 80
CMD [“java -jar”,”web-*.jar”]