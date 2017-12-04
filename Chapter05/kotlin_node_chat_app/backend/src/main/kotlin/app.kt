import kotlin.js.json

external fun require(module: String): dynamic

external val process: dynamic
external val __dirname: dynamic

fun main(args: Array<String>) {

    var numOfUsers = 0
    val usersList = mutableListOf<String>()
    println("Server Starting!")

    val express = require("express")
    val app = express()
    val path = require("path")
    val bodyParser = require("body-parser")
    val debug = require("debug")("kotlin_node_chat_app:server")
    val http = require("http")
    /**
     * Get port from environment and store in Express.
     */

    val port = normalizePort(process.env.PORT)
    app.use(bodyParser.json())
    app.set("port", port)

    // view engine setup
    app.set("views", path.join(__dirname, "../../webapp"))
    app.set("view engine", "ejs")
    app.use(express.static("webapp"))

    val server = http.createServer(app)

    app.use("/", router())

    val io = require("socket.io").listen(app.listen(port, {
        println("Chat app listening on port http://localhost:$port")
    }))

    io.on("connection", { socket ->

        socket.on("add_user", { nickName ->
            socket.nickname = nickName
            numOfUsers = numOfUsers.inc()
            usersList.add(nickName as String)
            val userJoinedData = json(Pair("numOfUsers", numOfUsers), Pair("nickName", nickName), Pair("usersList", usersList))
            socket.emit("login", userJoinedData)

            socket.broadcast.emit("user_joined", userJoinedData)
        })

        socket.on("disconnect", {
            usersList.remove(socket.nickname as String)
            numOfUsers = numOfUsers.dec()
            val userJoinedData = json(Pair("numOfUsers", numOfUsers), Pair("nickName", socket.nickname))
            socket.broadcast.emit("user_left", userJoinedData)
        })

        socket.on("new_message", { data ->
            val userJoinedData = json(Pair("nickName", socket.nickname), Pair("message", data))
            socket.broadcast.emit("new_message", userJoinedData)
        })
    })


}

fun normalizePort(port: Int): Int = if (port >= 0) port else 7000