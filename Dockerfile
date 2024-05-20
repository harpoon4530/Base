FROM ubuntu:23.10

ENV MAVEN_VERSION "3.6.3"
ENV MAVEN_HOME "/usr/share/maven"
ENV MAVEN_PREFIX "http://archive.apache.org/dist/maven/maven-3"
ENV INSTALL_PREFIX "apt-get install -y --no-install-recommends"

# update
RUN apt update

# Install necessary tools and utilities
RUN $INSTALL_PREFIX zip bzip2 fontconfig curl supervisor nginx wget unzip emacs mysql-server bash-completion dnsutils
#RUN $INSTALL_PREFIX g++ git bazel
# install java
RUN $INSTALL_PREFIX openjdk-21-jdk


# install protoc
# apt install protobuf-compiler
#RUN wget https://github.com/protocolbuffers/protobuf/releases/download/v26.1/protobuf-26.1.zip \
RUN wget https://github.com/protocolbuffers/protobuf/releases/download/v26.1/protoc-26.1-linux-x86_64.zip \
    &&  unzip protoc-26.1-linux-x86_64.zip




# install maven
RUN curl -fsSL $MAVEN_PREFIX/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
  | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# setup supervisord
RUN mkdir -p /var/log/supervisord/
RUN mkdir -p /etc/supervisor/conf.d
RUN mkdir -p /tmp/log/supervisord
# Copy supervisor configs
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# copy files from cwd to docker
RUN wget https://github.com/harpoon4530/Base/archive/refs/heads/main.zip \
    && unzip main.zip  \
    && cd Base-main \
    && mv src/main/resources/prod.properties src/main/resources/db.properties \
    && mvn clean package

# Make port 8080 available to the world outside this container
EXPOSE 8080

WORKDIR /Base-main
#CMD ["java",  "-jar", "./target/base.jar",  ">/tmp/log.txt 2>&1"]
#CMD ["java -jar ./target/base.jar >/tmp/log.txt 2>&1"]
CMD ["/usr/bin/supervisord","-n","-c","/etc/supervisor/conf.d/supervisord.conf"]