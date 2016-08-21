FROM nginx:latest
RUN rm /etc/nginx/conf.d/default.conf
COPY nginx/default.conf /etc/nginx/conf.d/
COPY petceptioncert.keystore /etc/nginx/conf.d/
EXPOSE 80
EXPOSE 8443
EXPOSE 8082
EXPOSE 8080