FROM java:8

MAINTAINER shuai.yan "953681722@qq.com"

COPY *.jar /app.jar

CMD ["--server.port=8888"]

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app.jar"]