FROM openjdk:8-jre-alpine
MAINTAINER Ramkumar RN<ramkumar.rn@ril.com>
ADD target/service70900-1.0.jar service70900-1.0.jar
ENV JAVA_OPTS="-Xms128m -Xmx256m -XX:MaxMetaspaceSize=128m -XX:CompressedClassSpaceSize=256m -Xss256k -Xmn8m -XX:InitialCodeCacheSize=4m -XX:ReservedCodeCacheSize=128m -XX:MaxDirectMemorySize=16m -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=70"
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/service70900-1.0.jar"]
EXPOSE 8080
