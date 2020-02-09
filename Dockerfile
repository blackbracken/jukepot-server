FROM openjdk:8-jre-alpine

# TODO: fetch jar from GitHub
COPY ./build/libs/jukepot-server-runnable.jar /app/jukepot-server.jar

WORKDIR /app

CMD ["java", \
     "-server", \
     "-XX:+UnlockExperimentalVMOptions", \
     "-XX:+UseCGroupMemoryLimitForHeap", \
     "-XX:InitialRAMFraction=2", \
     "-XX:MinRAMFraction=2", \
     "-XX:MaxRAMFraction=2", \
     "-XX:+UseG1GC", \
     "-XX:MaxGCPauseMillis=100", \
     "-XX:+UseStringDeduplication", \
     "-jar", \
     "jukepot-server.jar"]
