echo "-----------------------------------------------Stop the containers----------------------------------------------------------------------"
docker-compose stop

echo "-----------------------------------------------Build the application----------------------------------------------"
docker-compose build

echo "-----------------------------------------------Starting the database ---------------------------------------------"
docker-compose up -d keycloak-db
docker-compose up -d patient-db
docker-compose up -d note-db
docker-compose up -d assessment-db
sleep 15s

echo "-----------------------------------------------Starting the config microservice ----------------------------------"
docker-compose up -d config-service
sleep 15s 

echo "-----------------------------------------------Starting the registration microservice-----------------------------"
docker-compose up -d eureka-server
sleep 15s 

echo "-----------------------------------------------Starting the gateway microservice-----------------------------"
docker-compose up -d gateway-service
sleep 15s 

echo "-----------------------------------------------Starting the authentication microservice-----------------------------"
docker-compose up -d keycloak-service
sleep 15s 

echo "-----------------------------------------------Run the other microservices-----------------------------------------"
docker-compose up -d patient-service
docker-compose up -d note-service
sleep 15s
docker-compose up -d assessment-service

echo "-----------------------------------------------Run the frontend-----------------------------------------"
docker-compose up -d frontend
sleep 60s
echo "Done"
