(function (_, Kotlin) {
  'use strict';
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var Pair = Kotlin.kotlin.Pair;
  var json = Kotlin.kotlin.js.json_pyyo18$;
  function main$lambda(closure$port) {
    return function () {
      println('Chat app listening on port http://localhost:' + closure$port);
    };
  }
  function main$lambda$lambda(closure$socket, closure$numOfUsers, closure$usersList) {
    return function (nickName) {
      var tmp$;
      closure$socket.nickname = nickName;
      closure$numOfUsers.v = closure$numOfUsers.v + 1 | 0;
      closure$usersList.add_11rb$(typeof (tmp$ = nickName) === 'string' ? tmp$ : Kotlin.throwCCE());
      var userJoinedData = json([new Pair('numOfUsers', closure$numOfUsers.v), new Pair('nickName', nickName), new Pair('usersList', closure$usersList)]);
      closure$socket.emit('login', userJoinedData);
      return closure$socket.broadcast.emit('user_joined', userJoinedData);
    };
  }
  function main$lambda$lambda_0(closure$usersList, closure$socket, closure$numOfUsers) {
    return function () {
      var tmp$;
      closure$usersList.remove_11rb$(typeof (tmp$ = closure$socket.nickname) === 'string' ? tmp$ : Kotlin.throwCCE());
      closure$numOfUsers.v = closure$numOfUsers.v - 1 | 0;
      var userJoinedData = json([new Pair('numOfUsers', closure$numOfUsers.v), new Pair('nickName', closure$socket.nickname)]);
      return closure$socket.broadcast.emit('user_left', userJoinedData);
    };
  }
  function main$lambda$lambda_1(closure$socket) {
    return function (data) {
      var userJoinedData = json([new Pair('nickName', closure$socket.nickname), new Pair('message', data)]);
      return closure$socket.broadcast.emit('new_message', userJoinedData);
    };
  }
  function main$lambda_0(closure$numOfUsers, closure$usersList) {
    return function (socket) {
      socket.on('add_user', main$lambda$lambda(socket, closure$numOfUsers, closure$usersList));
      socket.on('disconnect', main$lambda$lambda_0(closure$usersList, socket, closure$numOfUsers));
      return socket.on('new_message', main$lambda$lambda_1(socket));
    };
  }
  function main(args) {
    var numOfUsers = {v: 0};
    var usersList = Kotlin.kotlin.collections.ArrayList_init_ww73n8$();
    println('Server Starting!');
    var express = require('express');
    var app = express();
    var path = require('path');
    var bodyParser = require('body-parser');
    var debug = require('debug')('kotlin_node_chat_app:server');
    var http = require('http');
    var port = normalizePort(process.env.PORT);
    app.use(bodyParser.json());
    app.set('port', port);
    app.set('views', path.join(__dirname, '../../webapp'));
    app.set('view engine', 'ejs');
    app.use(express.static('webapp'));
    var server = http.createServer(app);
    app.use('/', router());
    var io = require('socket.io').listen(app.listen(port, main$lambda(port)));
    io.on('connection', main$lambda_0(numOfUsers, usersList));
  }
  function normalizePort(port) {
    return port >= 0 ? port : 7000;
  }
  function router$lambda(req, res) {
    return res.render('index');
  }
  function router() {
    var express = require('express');
    var router = express.Router();
    router.get('/', router$lambda);
    return router;
  }
  _.main_kand9s$ = main;
  _.normalizePort_za3lpa$ = normalizePort;
  _.router = router;
  main([]);
  Kotlin.defineModule('app', _);
  return _;
}(module.exports, require('kotlin')));

//# sourceMappingURL=app.js.map
