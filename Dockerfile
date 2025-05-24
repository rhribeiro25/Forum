FROM adoptopenjdk/openjdk11
EXPOSE 9090
ADD /target/forum-0.0.1-SNAPSHOT.jar forum.jar
ENTRYPOINT sh -c "java $JAVA_OPTS -XX:+UseContainerSupport -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dspring.profiles.active=hom -Dserver.port=9090 -jar forum.jar"