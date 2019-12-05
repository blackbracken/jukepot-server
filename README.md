# Jukepot-server

## Play
1. Clone this repo & move to the workspace.
2. `$ ./gradlew shadowJar`
3. `$ docker build -t jukepot-server .`
4. Write variables for docker-compose into `.env`.
5. `$ docker-compose build`
6. `$ docker-compose up -d`