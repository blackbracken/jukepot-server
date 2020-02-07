rootProject.name = "jukepot-server"

include(
    ":app",
    ":commonLib",
    ":legacy",
    ":entity",
    ":usecase:register"
)
include("infrastructure:database")
findProject(":infrastructure:database")?.name = "database"
