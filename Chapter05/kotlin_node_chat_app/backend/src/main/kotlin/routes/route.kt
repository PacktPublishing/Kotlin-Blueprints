fun router() {
    val express = require("express")
    val router = express.Router()


    router.get("/", { req, res ->
        res.render("index")
    })

    return router
}