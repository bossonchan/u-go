<template>
<div class="login">

  <div class="pure-g">
    <div class="pure-u-1-8"></div>

  	<div class="pure-u-1-4">
  		<form class="pure-form pure-form-stacked">
  		  <legend>登录</legend>
  		  <fieldset>

  		    <label for="login-username">用户名：</label>
  		    <input type="text" name="username" id="login-username" v-model="loginUsername">

  		    <label for="login-password">密码：</label>
  		    <input type="password" name="password" id="login-password" v-model="loginPassword">

          <label for="buyer">
            <input type="radio" id="buyer" value="buyer" checked v-model="loginRole"> 买家
          </label>

          <label for="seller">
            <input type="radio" id="seller" value="seller" v-model="loginRole"> 卖家
          </label>

          <button @click.prevent.stop="sendLoginRequest" class="pure-button pure-button-primary">登录</button>
  		  </fieldset>
  		</form>
  	</div>

    <div class="pure-u-1-8"></div>

  	<div class="pure-u-1-4">
  		<form class="pure-form pure-form-stacked">
  		  <legend>买家注册</legend>
  		  <fieldset>
  		    <label for="register-buyer-username">用户名：</label>
  		    <input type="text" name="username" id="register-buyer-username" v-model="buyerUsername">

  		    <label for="register-buyer-password">密码：</label>
  		    <input type="password" name="password" id="register-buyer-password" v-model="buyerPassword">

  		    <label for="register-buyer-phone">电话号码：</label>
  		    <input type="text" name="phone" id="register-buyer-phone" v-model="buyerPhoneNumber">

          <button @click.stop.prevent="sendBuyerRegisterRequest" class="pure-button button-success">马上注册为买家</button>
  		  </fieldset>
  		</form>

  		<form class="pure-form pure-form-stacked">
  		  <legend>卖家注册</legend>
  		  <fieldset>
  		    <label for="register-seller-username">用户名：</label>
  		    <input type="text" name="username" id="register-seller-username" v-model="sellerUsername">

  		    <label for="register-seller-password">密码：</label>
  		    <input type="password" name="password" id="register-seller-password" v-model="sellerPassword">

  		    <label for="register-seller-identity">身份证：</label>
  		    <input type="text" name="identity" id="register-seller-identity" v-model="sellerIdentity">

  		    <label for="register-seller-phone">电话号码：</label>
  		    <input type="text" name="phone" id="register-seller-phone" v-model="sellerPhoneNumber">

          <button @click.stop.prevent="sendSellerRegisterRequest" class="pure-button button-success">马上注册为卖家</button>
  		  </fieldset>
  		</form>
  	</div>
  </div>

</div>
</template>

<script>

import { store } from '../store'
import { router } from '../router'

export default {
  methods: {
    sendBuyerRegisterRequest()  {
      var that = this
      var data = {
        username: this.buyerUsername,
        password: this.buyerPassword,
        phoneNumber: this.buyerPhoneNumber
      }

      this.$http.post("/buyer", data).then(
        (response) => {
          that.global.message = "注册成功！快使用注册的账号登录吧！"
          that.buyerUsername = ""
          that.buyerPassword = ""
          that.buyerPhoneNumber = ""
        },
        (response) => {
          let { status, data } = response
          that.global.message = data.message
        }
      )
    },

    sendSellerRegisterRequest() {
      var that = this
      var data = {
        username: this.sellerUsername,
        password: this.sellerPassword,
        identity: this.sellerIdentity,
        phoneNumber: this.sellerPhoneNumber
      }

      this.$http.post("/seller", data).then(
        (response) => {
          that.global.message = "注册成功！快使用注册的账号登录吧！"
          that.sellerUsername = ""
          that.sellerPassword = ""
          that.sellerIdentity = ""
          that.sellerPhoneNumber = ""
        },
        (response) => {
          let { status, data } = response
          that.global.message = data.message
        }
      )
    },

    sendLoginRequest() {
      var that = this
      var data = {
        username: this.loginUsername,
        password: this.loginPassword
      }

      if (this.loginRole == "buyer") {
        this.$http.post("/buyer/session", data).then(
          (response) => {
            that.global.role = "buyer"
            that.global.isLogin = true
            that.global.username = response.data.username

            that.loginUsername = ""
            that.loginPassword = ""

            router.go("/home")
          },
          (response) => {
            that.global.message = response.data.message
          }
        )
      }

      if (this.loginRole == "seller")  {
        this.$http.post("/seller/session", data).then(
          (response) => {
            that.global.role = "seller"
            that.global.isLogin = true
            that.global.username = response.data.username

            that.loginUsername = ""
            that.loginPassword = ""

            router.go("/seller")
          },
          (response) => {
            that.global.message = response.data.message
          }
        )
      }
    }
  },
  data () {
    return {
      loginUsername: "",
      loginPassword: "",
      loginRole: "buyer",

      buyerUsername: "",
      buyerPassword: "",
      buyerPhoneNumber: "",

      sellerUsername: "",
      sellerPassword: "",
      sellerIdentity: "",
      sellerPhoneNumber: "",

      global: store,
    }
  }
}

</script>

<style>
.button-success {
  color: white;
  background: rgb(28, 184, 65); /* this is a green */
}
</style>
