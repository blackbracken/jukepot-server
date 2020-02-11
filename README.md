# Jukepot-server

## Development
This project is a multi-module project adopts simplified Clean Architecture.

#### module layers
![](https://raw.githubusercontent.com/blackbracken/jukepot-server/master/module-dependency.jpg)

## Run to debug
1. `./gradlew shadowJar`
2. `docker build -t jukepot-server .`
3. `docker run -e "DB_HOST=localdev" -e "DB_DATABASE=jukepot" -e "DB_USER=<user>" -e "DB_PASSWORD=<password>" --add-host=localdev:<local-ip> -p=80:80 jukepot-server`
