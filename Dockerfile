FROM adoptopenjdk/openjdk11
EXPOSE 9090

# Copia o jar e o script
ADD /target/forum-0.0.1-SNAPSHOT.jar forum.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# ENTRYPOINT com espera do banco
ENTRYPOINT ["/wait-for-it.sh", "sgbd-mysql:3306", "--", "sh", "-c", "java $JAVA_OPTS -XX:+UseContainerSupport -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dspring.profiles.active=hom -jar forum.jar"]
