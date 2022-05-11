FROM jboss/keycloak:latest

COPY --chown=jboss ./config.batch /tmp/config.batch

RUN cd $JBOSS_HOME \
  && ./bin/jboss-cli.sh --file=/tmp/config.batch \
  && rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history \
  && rm -rf $JBOSS_HOME/standalone/data \
  && rm -rf $JBOSS_HOME/standalone/tmp \
  && rm -rf /tmp/config.batch

#ENV KEYCLOAK_USER admin
#ENV KEYCLOAK_PASSWORD admin
#ENV DB_VENDOR mysql
#ENV DB_ADDR mysql-kc
#ENV DB_PORT 3306
#ENV DB_DATABASE keycloak
#ENV DB_USER tipikae
#ENV DB_PASSWORD 231045

EXPOSE 8080