# Jukepot-server

## Run to debug
1. `./gradlew shadowJar`
2. `docker build -t jukepot-server .`
3. `docker run -it -p 8080:8080 --rm jukepot-server`