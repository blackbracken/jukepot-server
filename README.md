# Jukepot-server

## Development

#### route of requests
Look at [here](https://github.com/blackbracken/jukepot-server/blob/master/app/src/main/kotlin/black/bracken/jukepotserver/routes/).

#### module layers
This project adopts multi-module with simplified Clean Architecture.

![](https://raw.githubusercontent.com/blackbracken/jukepot-server/master/module-dependency.jpg)

#### error handling
Requests & responses to/from this are represented a json on REST API mainly.

###### example: requests
```json
{
  "name": "blackbracken",
  "email": "me@example.com",
  "password": "cD1eP6FE"
}
```

###### example: responses
Response HTTP status code is 200 if succeed otherwise 401 or 403.
```json
{
  "reason": "Request parameter didn't validate."
}
```

## Run to debug
TODO: create Makefile

1. `./gradlew shadowJar`
2. `docker build -t jukepot-server .`
3. `docker run -e "DB_HOST=localdev" -e "DB_DATABASE=jukepot" -e "DB_USER=<user>" -e "DB_PASSWORD=<password>" --add-host=localdev:<local-ip> -p=80:80 jukepot-server`
