window.onload = function() {

  var username = "Shin" + (Math.random() * 1000 >> 0);

  var settings = {
    "register-as-buyer" : [
      "POST",
      "/buyer",
      { username: username, password: "123123", phoneNumber: "13580512947" }
    ],

    "login-as-buyer": [
      "POST",
      "/buyer/session",
      { username: username, password: "123123" }
    ],

    "logout-as-buyer": [
      "DELETE",
      "/buyer/session",
      {}
    ],

    "register-as-seller" : [
      "POST",
      "/seller",
      { username: username, password: "123123", phoneNumber: "13580512947", identity: "44059319904131111" }
    ],

    "login-as-seller": [
      "POST",
      "/seller/session",
      { username: username, password: "123123" }
    ],

    "logout-as-seller": [
      "DELETE",
      "/seller/session",
      {}
    ],

    "post-item": [
      "POST",
      "/items",
      { name: "小米4手机", description: "九成新，便宜卖。", category: "ELECTRONICS", price: 2000 }
    ]

  };



  for (var key in settings) {
    (function(className) {
      var button = document.createElement("button");
      var $button = $(button);
      $button.addClass("btn").addClass(className).html(className);

      $(button).click(function() {
        var options = settings[className];
        $.ajax({
          url: options[1],
          type: options[0],
          dataType: "json",
          data: options[0] == "POST" ? JSON.stringify(options[2]) : "",
          headers: {
            'Content-Type': "application/json;charset=utf-8"
          },
          success: handleSuccess(key, options),
          error: handleError(key, options)
        });
      });

      $("#controls").append($button);
    })(key);
  }

  var $display = $("#display > pre");

  function handleSuccess(key, options) {
    return function(result) {
      var obj = {
        api: options[0] + " " + options[1],
        result: result
      };
      $display.html(JSON.stringify(obj, " ", 4));
    };
  }

  function handleError(key, options) {
    return function(error) {
      $display.html(JSON.stringify(error.responseJSON, " ", 4));
    }
  }

};
