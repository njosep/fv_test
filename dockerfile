# Use an official OpenJDK 17 runtime as a parent image
FROM adoptopenjdk:17-jre

# Set the working directory in the container
WORKDIR /app

# Download and install WireMock
RUN apt-get update && apt-get install -y curl
RUN curl -o wiremock-standalone.jar https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock-standalone/2.30.1/wiremock-standalone-2.30.1.jar

# Copy your WireMock mappings and __files directory into the container
COPY automation/wiremock/functional/mappings /app/mappings
COPY automation/wiremock/functional/__files /app/__files

# Expose the port WireMock will listen on
EXPOSE 8080

Yes, you can definitely add the new JVM arguments (like heap size, metaspace size, bytecode verification, etc.) to the existing JAVA_OPTS environment variable without splitting them into multiple ENV commands. All the JVM options can be combined into a single JAVA_OPTS definition.

Here’s how you can update the existing JAVA_OPTS to include all the required options in one place.

Updated Dockerfile with Combined JAVA_OPTS
Dockerfile
Copy code
# Start with a base WireMock image (if using OpenJDK as base)
FROM wiremock/wiremock:3.6

# Set the Java options including Datadog and additional memory and logging
ENV JAVA_OPTS="-javaagent:/app/dd-java-agent.jar \
    -Ddd.logs.injection=true \
    -Ddd.trace.analytics.enabled=true \
    -Ddd.profiling.enabled=true \
    -Ddd.fetch.enabled=true \
    -Xms512m -Xmx2g \
    -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m \
    -noverify \
    -Xlog:gc* -Xlog:class+load=info"
# Command to run WireMock when the container starts
COPY keystore.jks /wiremock/keystore.jks

CMD ["java", "-jar", "wiremock-standalone.jar", "--https-port", "8443", "--https-keystore", "/wiremock/keystore.jks", "--https-keystore-password", "password", "--root-dir", "/wiremock"]

CMD ["sh", "-c", "java $DATADOG_PARAMS -cp /var/wiremock/lib/*:/var/wiremock/extensions/* com.github.tomakehurst.wiremock.standalone.WireMockServerRunner --https-port 8443 --port 8080 --verbose"
