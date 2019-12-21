# Jukepot-server

## Run to debug
1. `./gradlew shadowJar`
2. `docker-compose build`
3. `docker-compose up -d`

## Architecture
```
// TODO: add database

  +--------+
  | domain |
  +---^----+
      |
 use  |
      |         impl
+-----+------+        +---------+        inject
| interactor +--------> usecase <-------+
+------------+        +----^----+       |
                           |            |     +--------------+
                      use  |            +-----+ DI container |
                           |            |     +--------------+
                     +-----+------+     |
                     | controller <-----+
                     +-----^------+
                           |
                      use  |
                           |
                       +---+---+
                       | route |
                       +-------+

```
