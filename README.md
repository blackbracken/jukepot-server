# Jukepot-server

## Play
1. Clone this repo & move to the workspace.
2. `$ ./gradlew shadowJar`
3. `$ docker build -t jukepot-server .`
4. `$ docker run -m1G --cpus 2 -p <PORT>:8080 -it --rm jukepot-server`