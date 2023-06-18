# spring-kafka-demo

### Run broker and zookeeper

```
docker compose up -d
```

### Run producer and consumer

```
cd producer

./mvnw spring-boot:run

cd ../consumer

./mvnw spring-boot:run
```

Inboud messages are logged in the consumer service
