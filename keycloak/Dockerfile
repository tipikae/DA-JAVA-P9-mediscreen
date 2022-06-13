FROM quay.io/keycloak/keycloak:18.0.0
WORKDIR /opt/keycloak
COPY imports /opt/keycloak/data/import
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev", "--db=mysql", "--import-realm", "--health-enabled=true"]
ENV KC_DB=mysql
ENV KC_DB_URL=jdbc:mysql://keycloak-db:3306/keycloak
ENV KC_DB_USERNAME=tipikae
ENV KC_DB_PASSWORD=231045
ENV KC_HOSTNAME=localhost
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin
EXPOSE 8080
