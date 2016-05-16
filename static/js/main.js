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

    "get-item": [
      "GET",
      "/items/1",
      {}
    ],

    "reserve-item": [
      "POST",
      "/items/1/reservation",
      {}
    ],

    "get-items": [
      "GET",
      "/items",
      {}
    ],

    "get-buyer-reservations": [
      "GET",
      "/reservations",
      {}
    ],

    "remove-reservation": [
      "DELETE",
      "/reservations/1",
      {}
    ],

    "add-item-to-shopping-cart": [
      "POST",
      "/items/1/shoppingcart",
      {}
    ],

    "get-shopping-cart-items": [
      "GET",
      "/shoppingcart",
      {}
    ],

    "remove-item-from-shopping-cart":[
      "DELETE",
      "/shoppingcart/1",
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
    ],

    "remove-item": [
      "DELETE",
      "/items/1",
      {}
    ],

    "get-seller-items": [
      "GET",
      "/items",
      {}
    ],

    "get-seller-reservations": [
      "GET",
      "/reservations",
      {}
    ],

    "reject-reservation": [
      "DELETE",
      "/reservations/1",
      {}
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

  // handle upload file
  var form = document.getElementById("uploadFileForm");
  var input = document.getElementById("uploadFileInput");
  var button = document.getElementById("uploadFileBtn");

  button.onclick = function(event) {
    event.preventDefault();
    button.innerHTML = "uploading..";
    var files =  input.files;
    var file  = files[0];
    var formdata = new FormData();
    formdata.append("file", file, file.name);
    formdata.append("name", "name");
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/photos", true);
    xhr.send(formdata);
    xhr.onload = function () {
      if (xhr.status == 200) {
        console.log(xhr)
        button.innerHTML = "upload";
      }
    };
  };


};
