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

# Command to run WireMock when the container starts
COPY keystore.jks /wiremock/keystore.jks

CMD ["java", "-jar", "wiremock-standalone.jar", "--https-port", "8443", "--https-keystore", "/wiremock/keystore.jks", "--https-keystore-password", "password", "--root-dir", "/wiremock"]

