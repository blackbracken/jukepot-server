# Jukepot-server

## Play
1. Clone this repo & move to the workspace.
2. Write variables for docker-compose into `.env`.
3. Run as written below.
```sh
./gradlew shadowJar
docker-compose build
docker-compose up -d
```