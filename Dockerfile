FROM adoptopenjdk/openjdk13-openj9:jdk-13.0.2_8_openj9-0.18.0-alpine-slim
ARG PORT
COPY build/libs/micronautapp-*-all.jar micronautapp.jar
EXPOSE $PORT
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-XX:+IdleTuningGcOnIdle", "-Xtune:virtualized", "-jar", "micronautapp.jar"]
