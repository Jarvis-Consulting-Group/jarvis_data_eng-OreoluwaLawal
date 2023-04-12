FROM openjdk:8-alpine
COPY target/java_apps*.jar /usr/local/app/twitter/lib/twitter.jar
ARG consumerKey
ARG consumerSecret
ARG accessToken
ARG tokenSecret
ENV consumerKey $consumerKey
ENV consumerSecret $consumerSecret
ENV accessToken $accessToken
ENV tokenSecret $tokenSecret
ENTRYPOINT ["java","-jar","/usr/local/app/twitter/lib/twitter.jar"]
