# hatch-takehome

Build jars locally
```
./gradlew bootJar
```

Start docker on your machine

Start up the takehome service using docker compose
```
docker-compose up --build
```

Hit continents service on port 8887,

localhost:8887/continent
with body
```
{
    "countries": ["CA"]
}
```
