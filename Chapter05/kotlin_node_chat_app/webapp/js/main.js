if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'main'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'main'.");
}
if (typeof this['kotlinx-html-js'] === 'undefined') {
  throw new Error("Error loading module 'main'. Its dependency 'kotlinx-html-js' was not found. Please, check whether 'kotlinx-html-js' is loaded prior to 'main'.");
}
var main = function (_, Kotlin, $module$kotlinx_html_js) {
  'use strict';
  var get_create = $module$kotlinx_html_js.kotlinx.html.dom.get_create_4wc2mh$;
  var h2 = $module$kotlinx_html_js.kotlinx.html.h2_eh5gi3$;
  var hr = $module$kotlinx_html_js.kotlinx.html.hr_17yvwq$;
  var set_id = $module$kotlinx_html_js.kotlinx.html.set_id_ueiko3$;
  var ul = $module$kotlinx_html_js.kotlinx.html.ul_pzlyaf$;
  var set_onInputFunction = $module$kotlinx_html_js.kotlinx.html.js.set_onInputFunction_pszlq2$;
  var input = $module$kotlinx_html_js.kotlinx.html.input_e1g74z$;
  var ButtonType = $module$kotlinx_html_js.kotlinx.html.ButtonType;
  var set_onClickFunction = $module$kotlinx_html_js.kotlinx.html.js.set_onClickFunction_pszlq2$;
  var button = $module$kotlinx_html_js.kotlinx.html.button_whohl6$;
  var div = $module$kotlinx_html_js.kotlinx.html.div_ri36nr$;
  var h4 = $module$kotlinx_html_js.kotlinx.html.h4_zdyoc7$;
  var div_0 = $module$kotlinx_html_js.kotlinx.html.js.div_wkomt5$;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var first = Kotlin.kotlin.text.first_gw00vp$;
  var last = Kotlin.kotlin.text.last_gw00vp$;
  var span = $module$kotlinx_html_js.kotlinx.html.span_6djfml$;
  var li = $module$kotlinx_html_js.kotlinx.html.li_jf6zlv$;
  var p = $module$kotlinx_html_js.kotlinx.html.p_8pggrc$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var h3 = $module$kotlinx_html_js.kotlinx.html.h3_agelx2$;
  var isBlank = Kotlin.kotlin.text.isBlank_gw00vp$;
  function main$lambda(closure$socket) {
    return function (it) {
      closure$socket.emit('new_message', it);
    };
  }
  function main$lambda_0(closure$chatWindow, closure$socket) {
    return function (it) {
      closure$chatWindow.showChatWindow_61zpoe$(it);
      closure$socket.emit('add_user', it);
    };
  }
  function main$lambda_1(closure$chatWindow) {
    return function (data) {
      closure$chatWindow.showNewUserJoined_qk3xy8$(data);
      closure$chatWindow.showOnlineUsers_qk3xy8$(data);
    };
  }
  function main$lambda_2(closure$chatWindow) {
    return function (data) {
      closure$chatWindow.showNewUserJoined_qk3xy8$(data);
      closure$chatWindow.addNewUsers_qk3xy8$(data);
    };
  }
  function main$lambda_3(closure$chatWindow) {
    return function (data) {
      closure$chatWindow.showUserLeft_qk3xy8$(data);
    };
  }
  function main$lambda_4(closure$chatWindow) {
    return function (data) {
      closure$chatWindow.showNewMessage_qk3xy8$(data);
    };
  }
  function main(args) {
    var socket = window.socket;
    var chatWindow = new ChatWindow(main$lambda(socket));
    var loginWindow = new LoginWindow(main$lambda_0(chatWindow, socket));
    loginWindow.showLogin();
    socket.on('login', main$lambda_1(chatWindow));
    socket.on('user_joined', main$lambda_2(chatWindow));
    socket.on('user_left', main$lambda_3(chatWindow));
    socket.on('new_message', main$lambda_4(chatWindow));
  }
  function ChatWindow(callback) {
    this.callback = callback;
    this.chatMessage_0 = null;
    this.nickName_0 = this.nickName_0;
  }
  function ChatWindow$showChatWindow$lambda$lambda($receiver) {
    $receiver.unaryPlus_pdl1vz$('Welcome to Kotlin Blueprints chat app');
  }
  function ChatWindow$showChatWindow$lambda$lambda_0($receiver) {
  }
  function ChatWindow$showChatWindow$lambda$lambda$lambda($receiver) {
    set_id($receiver, 'chatMessages');
  }
  function ChatWindow$showChatWindow$lambda$lambda$lambda$lambda(this$ChatWindow) {
    return function ($receiver) {
      set_id($receiver, 'chatInputBox');
      $receiver.placeholder = 'Enter your message here...';
      $receiver.maxLength = (100).toString();
      set_onInputFunction($receiver, this$ChatWindow.onMessageInput_0());
    };
  }
  function ChatWindow$showChatWindow$lambda$lambda$lambda$lambda_0(this$ChatWindow) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$('Send Message');
      $receiver.type = ButtonType.button;
      set_onClickFunction($receiver, this$ChatWindow.onSendMessageClicked_0());
    };
  }
  function ChatWindow$showChatWindow$lambda$lambda$lambda_0(this$ChatWindow) {
    return function ($receiver) {
      input($receiver, void 0, void 0, void 0, void 0, 'chatMessageInput', ChatWindow$showChatWindow$lambda$lambda$lambda$lambda(this$ChatWindow));
      button($receiver, void 0, void 0, void 0, 'sendMessageButton', ChatWindow$showChatWindow$lambda$lambda$lambda$lambda_0(this$ChatWindow));
    };
  }
  function ChatWindow$showChatWindow$lambda$lambda_1(this$ChatWindow) {
    return function ($receiver) {
      set_id($receiver, 'leftDiv');
      ul($receiver, 'chatMessages', ChatWindow$showChatWindow$lambda$lambda$lambda);
      div($receiver, void 0, ChatWindow$showChatWindow$lambda$lambda$lambda_0(this$ChatWindow));
    };
  }
  function ChatWindow$showChatWindow$lambda$lambda$lambda_1($receiver) {
    $receiver.unaryPlus_pdl1vz$('Online users');
  }
  function ChatWindow$showChatWindow$lambda$lambda$lambda_2($receiver) {
  }
  function ChatWindow$showChatWindow$lambda$lambda$lambda_3($receiver) {
    set_id($receiver, 'onlineUsersList');
  }
  function ChatWindow$showChatWindow$lambda$lambda_2($receiver) {
    set_id($receiver, 'rightDiv');
    h4($receiver, void 0, ChatWindow$showChatWindow$lambda$lambda$lambda_1);
    hr($receiver, void 0, ChatWindow$showChatWindow$lambda$lambda$lambda_2);
    ul($receiver, 'onlineUserList', ChatWindow$showChatWindow$lambda$lambda$lambda_3);
  }
  function ChatWindow$showChatWindow$lambda(this$ChatWindow) {
    return function ($receiver) {
      h2($receiver, 'chatWelcomeMessage', ChatWindow$showChatWindow$lambda$lambda);
      hr($receiver, void 0, ChatWindow$showChatWindow$lambda$lambda_0);
      div($receiver, void 0, ChatWindow$showChatWindow$lambda$lambda_1(this$ChatWindow));
      div($receiver, void 0, ChatWindow$showChatWindow$lambda$lambda_2);
      div($receiver, 'clearBoth');
    };
  }
  ChatWindow.prototype.showChatWindow_61zpoe$ = function (nickName) {
    var tmp$;
    this.nickName_0 = nickName;
    var formContainer = Kotlin.isType(tmp$ = document.getElementById('container'), HTMLDivElement) ? tmp$ : Kotlin.throwCCE();
    var chatWindow = div_0(get_create(document), 'chatWindow', ChatWindow$showChatWindow$lambda(this));
    formContainer.appendChild(chatWindow);
  };
  ChatWindow.prototype.showOnlineUsers_qk3xy8$ = function (data) {
    var onlineUsersList = document.getElementById('onlineUsersList');
    if (onlineUsersList != null) {
      var tmp$;
      var usersList = Array.isArray(tmp$ = data['usersList']) ? tmp$ : null;
      if (usersList != null) {
        var tmp$_0, tmp$_0_0;
        var index = 0;
        for (tmp$_0 = 0; tmp$_0 !== usersList.length; ++tmp$_0) {
          var item = usersList[tmp$_0];
          index = index + 1 | 0;
          onlineUsersList.appendChild(this.getUserListItem_0(item));
        }
      }
    }
  };
  ChatWindow.prototype.doSomething_pdl1vj$ = function (data) {
    if (data != null) {
      println(data.length);
      println(Kotlin.compareTo(data, 'Kotlin Blueprints'));
      println(data.toUpperCase());
      println(Kotlin.toBoxedChar(first(data)));
      println(Kotlin.toBoxedChar(last(data)));
    }
  };
  function ChatWindow$getUserListItem$lambda$lambda(closure$nickName, this$ChatWindow) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$(this$ChatWindow.getInitials_0(closure$nickName));
    };
  }
  function ChatWindow$getUserListItem$lambda$lambda_0(closure$nickName) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$(closure$nickName);
    };
  }
  function ChatWindow$getUserListItem$lambda(closure$nickName, this$ChatWindow) {
    return function ($receiver) {
      span($receiver, 'ringInitials', ChatWindow$getUserListItem$lambda$lambda(closure$nickName, this$ChatWindow));
      span($receiver, 'onlineUserListItemText', ChatWindow$getUserListItem$lambda$lambda_0(closure$nickName));
    };
  }
  ChatWindow.prototype.getUserListItem_0 = function (nickName) {
    return li(get_create(document), 'onlineUserListItem', ChatWindow$getUserListItem$lambda(nickName, this));
  };
  ChatWindow.prototype.addNewUsers_qk3xy8$ = function (data) {
    var onlineUsersList = document.getElementById('onlineUsersList');
    onlineUsersList != null ? onlineUsersList.appendChild(this.getUserListItem_0(Kotlin.toString(data['nickName']))) : null;
  };
  ChatWindow.prototype.showNewUserJoined_qk3xy8$ = function (data) {
    var tmp$;
    this.logListItem_0(Kotlin.toString(data['nickName']) + ' has joined');
    var noOfUsers = typeof (tmp$ = data['numOfUsers']) === 'number' ? tmp$ : Kotlin.throwCCE();
    this.logListItem_0(this.getParticipantsMessage_0(noOfUsers));
  };
  ChatWindow.prototype.getParticipantsMessage_0 = function (noOfUsers) {
    return noOfUsers === 1 ? 'There is ' + noOfUsers + ' participant' : 'There are ' + noOfUsers + ' participants';
  };
  function ChatWindow$logListItem$lambda$lambda(closure$message) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$(closure$message);
    };
  }
  function ChatWindow$logListItem$lambda(closure$message) {
    return function ($receiver) {
      p($receiver, void 0, ChatWindow$logListItem$lambda$lambda(closure$message));
    };
  }
  ChatWindow.prototype.logListItem_0 = function (message) {
    var onlineUsersList = document.getElementById('chatMessages');
    var li_0 = li(get_create(document), 'log', ChatWindow$logListItem$lambda(message));
    onlineUsersList != null ? onlineUsersList.appendChild(li_0) : null;
  };
  ChatWindow.prototype.showUserLeft_qk3xy8$ = function (data) {
    var tmp$;
    this.logListItem_0(Kotlin.toString(data['nickName']) + ' left');
    this.logListItem_0(this.getParticipantsMessage_0(typeof (tmp$ = data['numOfUsers']) === 'number' ? tmp$ : Kotlin.throwCCE()));
  };
  ChatWindow.prototype.removeUserFromList_qk3xy8$ = function (data) {
    var tmp$, tmp$_0;
    var onlineUsersList = document.getElementById('onlineUsersList');
    var childNodes = onlineUsersList != null ? onlineUsersList.childNodes : null;
    tmp$_0 = (tmp$ = onlineUsersList != null ? onlineUsersList.childElementCount : null) != null ? tmp$ : Kotlin.throwNPE();
    for (var child = 0; child <= tmp$_0; child++) {
      var element = (childNodes != null ? childNodes : Kotlin.throwNPE())[child];
    }
  };
  function ChatWindow$onMessageInput$lambda(this$ChatWindow) {
    return function (it) {
      var tmp$;
      var input = Kotlin.isType(tmp$ = it.currentTarget, HTMLInputElement) ? tmp$ : Kotlin.throwCCE();
      this$ChatWindow.chatMessage_0 = input.value;
    };
  }
  ChatWindow.prototype.onMessageInput_0 = function () {
    return ChatWindow$onMessageInput$lambda(this);
  };
  function ChatWindow$onSendMessageClicked$lambda(this$ChatWindow) {
    return function (it) {
      var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
      if (typeof (tmp$_0 = (tmp$ = this$ChatWindow.chatMessage_0) != null ? !Kotlin.kotlin.text.isBlank_gw00vp$(tmp$) : null) === 'boolean' ? tmp$_0 : Kotlin.throwCCE()) {
        var formContainer = Kotlin.isType(tmp$_1 = document.getElementById('chatInputBox'), HTMLInputElement) ? tmp$_1 : Kotlin.throwCCE();
        this$ChatWindow.callback((tmp$_2 = this$ChatWindow.chatMessage_0) != null ? tmp$_2 : Kotlin.throwNPE());
        tmp$_3 = this$ChatWindow.nickName_0;
        tmp$_5 = (tmp$_4 = this$ChatWindow.chatMessage_0) != null ? tmp$_4 : Kotlin.throwNPE();
        this$ChatWindow.logMessageFromMe_0(tmp$_3, tmp$_5);
        formContainer.value = '';
      }
    };
  }
  ChatWindow.prototype.onSendMessageClicked_0 = function () {
    return ChatWindow$onSendMessageClicked$lambda(this);
  };
  ChatWindow.prototype.showNewMessage_qk3xy8$ = function (data) {
    var tmp$, tmp$_0, tmp$_1;
    tmp$_0 = typeof (tmp$ = data['message']) === 'string' ? tmp$ : Kotlin.throwCCE();
    this.logMessage_0(typeof (tmp$_1 = data['nickName']) === 'string' ? tmp$_1 : Kotlin.throwCCE(), tmp$_0);
  };
  function ChatWindow$logMessage$lambda$lambda$lambda(closure$nickName, this$ChatWindow) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$(this$ChatWindow.getInitials_0(closure$nickName));
    };
  }
  function ChatWindow$logMessage$lambda$lambda$lambda_0(closure$message) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$(closure$message);
    };
  }
  function ChatWindow$logMessage$lambda$lambda(closure$nickName, this$ChatWindow, closure$message) {
    return function ($receiver) {
      span($receiver, 'filledInitials', ChatWindow$logMessage$lambda$lambda$lambda(closure$nickName, this$ChatWindow));
      span($receiver, 'chatMessage', ChatWindow$logMessage$lambda$lambda$lambda_0(closure$message));
    };
  }
  function ChatWindow$logMessage$lambda(closure$nickName, this$ChatWindow, closure$message) {
    return function ($receiver) {
      div($receiver, 'receivedMessages', ChatWindow$logMessage$lambda$lambda(closure$nickName, this$ChatWindow, closure$message));
    };
  }
  ChatWindow.prototype.logMessage_0 = function (nickName, message) {
    var onlineUsersList = document.getElementById('chatMessages');
    var li_0 = li(get_create(document), void 0, ChatWindow$logMessage$lambda(nickName, this, message));
    onlineUsersList != null ? onlineUsersList.appendChild(li_0) : null;
  };
  function ChatWindow$logMessageFromMe$lambda$lambda$lambda(closure$message) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$(closure$message);
    };
  }
  function ChatWindow$logMessageFromMe$lambda$lambda$lambda_0(closure$nickName, this$ChatWindow) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$(this$ChatWindow.getInitials_0(closure$nickName));
    };
  }
  function ChatWindow$logMessageFromMe$lambda$lambda(closure$message, closure$nickName, this$ChatWindow) {
    return function ($receiver) {
      span($receiver, 'chatMessage', ChatWindow$logMessageFromMe$lambda$lambda$lambda(closure$message));
      span($receiver, 'filledInitialsMe', ChatWindow$logMessageFromMe$lambda$lambda$lambda_0(closure$nickName, this$ChatWindow));
    };
  }
  function ChatWindow$logMessageFromMe$lambda(closure$message, closure$nickName, this$ChatWindow) {
    return function ($receiver) {
      div($receiver, 'sentMessages', ChatWindow$logMessageFromMe$lambda$lambda(closure$message, closure$nickName, this$ChatWindow));
    };
  }
  ChatWindow.prototype.logMessageFromMe_0 = function (nickName, message) {
    var onlineUsersList = document.getElementById('chatMessages');
    var li_0 = li(get_create(document), void 0, ChatWindow$logMessageFromMe$lambda(message, nickName, this));
    onlineUsersList != null ? onlineUsersList.appendChild(li_0) : null;
  };
  ChatWindow.prototype.getInitials_0 = function (nickName) {
    var splitNames = split(nickName, [' ']);
    var message = '';
    if (splitNames.size > 1) {
      message += String.fromCharCode(Kotlin.toBoxedChar(first(splitNames.get_za3lpa$(0)))).toUpperCase();
      message += String.fromCharCode(Kotlin.toBoxedChar(first(splitNames.get_za3lpa$(1)))).toUpperCase();
    }
     else {
      message += nickName.length > 2 ? nickName.substring(0, 2).toUpperCase() : nickName.toUpperCase();
    }
    return message;
  };
  ChatWindow.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'ChatWindow',
    interfaces: []
  };
  function LoginWindow(callback) {
    this.callback = callback;
    this.nickName_0 = this.nickName_0;
    this.email_0 = this.email_0;
  }
  function LoginWindow$showLogin$lambda$lambda($receiver) {
    $receiver.unaryPlus_pdl1vz$('Welcome to Kotlin Blueprints chat app');
  }
  function LoginWindow$showLogin$lambda$lambda_0(this$LoginWindow) {
    return function ($receiver) {
      set_id($receiver, 'nickName');
      set_onInputFunction($receiver, this$LoginWindow.onInput_0());
      $receiver.maxLength = (16).toString();
      $receiver.placeholder = 'Enter your nick name';
    };
  }
  function LoginWindow$showLogin$lambda$lambda_1(this$LoginWindow) {
    return function ($receiver) {
      $receiver.unaryPlus_pdl1vz$('Login');
      set_onClickFunction($receiver, this$LoginWindow.onLoginButtonClicked_0());
    };
  }
  function LoginWindow$showLogin$lambda(this$LoginWindow) {
    return function ($receiver) {
      set_id($receiver, 'loginDiv');
      h3($receiver, 'title', LoginWindow$showLogin$lambda$lambda);
      input($receiver, void 0, void 0, void 0, void 0, 'nickNameInput', LoginWindow$showLogin$lambda$lambda_0(this$LoginWindow));
      button($receiver, void 0, void 0, void 0, 'loginButton', LoginWindow$showLogin$lambda$lambda_1(this$LoginWindow));
    };
  }
  LoginWindow.prototype.showLogin = function () {
    var tmp$;
    var formContainer = Kotlin.isType(tmp$ = document.getElementById('container'), HTMLDivElement) ? tmp$ : Kotlin.throwCCE();
    var loginDiv = div_0(get_create(document), void 0, LoginWindow$showLogin$lambda(this));
    formContainer.appendChild(loginDiv);
  };
  function LoginWindow$onLoginButtonClicked$lambda(this$LoginWindow) {
    return function (it) {
      var tmp$;
      if (!isBlank(this$LoginWindow.nickName_0)) {
        var formContainer = Kotlin.isType(tmp$ = document.getElementById('loginDiv'), HTMLDivElement) ? tmp$ : null;
        formContainer != null ? formContainer.remove() : null;
        this$LoginWindow.callback(this$LoginWindow.nickName_0);
      }
    };
  }
  LoginWindow.prototype.onLoginButtonClicked_0 = function () {
    return LoginWindow$onLoginButtonClicked$lambda(this);
  };
  function LoginWindow$onInput$lambda(this$LoginWindow) {
    return function (it) {
      var tmp$, tmp$_0;
      var input = Kotlin.isType(tmp$ = it.currentTarget, HTMLInputElement) ? tmp$ : Kotlin.throwCCE();
      tmp$_0 = input.id;
      if (Kotlin.equals(tmp$_0, 'nickName'))
        this$LoginWindow.nickName_0 = input.value;
      else if (Kotlin.equals(tmp$_0, 'emailId'))
        this$LoginWindow.email_0 = input.value;
    };
  }
  LoginWindow.prototype.onInput_0 = function () {
    return LoginWindow$onInput$lambda(this);
  };
  LoginWindow.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: 'LoginWindow',
    interfaces: []
  };
  _.main_kand9s$ = main;
  var package$views = _.views || (_.views = {});
  package$views.ChatWindow = ChatWindow;
  package$views.LoginWindow = LoginWindow;
  main([]);
  Kotlin.defineModule('main', _);
  return _;
}(typeof main === 'undefined' ? {} : main, kotlin, this['kotlinx-html-js']);

//# sourceMappingURL=main.js.map
