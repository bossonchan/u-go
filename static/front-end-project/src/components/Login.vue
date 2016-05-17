<template>
<div class="login">

  <div class="pure-g">
    <div class="pure-u-1-8"></div>

  	<div class="pure-u-1-4">
  		<form class="pure-form pure-form-stacked">
  		  <legend>登录</legend>
  		  <fieldset>

  		    <label for="login-username">用户名：</label>
  		    <input type="text" name="username" id="login-username" v-model="username">

  		    <label for="login-password">密码：</label>
  		    <input type="password" name="password" id="login-password" v-model="password">

          <label for="buyer">
            <input type="radio" id="buyer" name="role" checked> 买家
          </label>

          <label for="seller">
            <input type="radio" id="seller" name="role"> 卖家
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
  		    <input type="text" name="username" id="register-buyer-username" v-model="username">

  		    <label for="register-buyer-password">密码：</label>
  		    <input type="password" name="password" id="register-buyer-password" v-model="password">

  		    <label for="register-buyer-phone">电话号码：</label>
  		    <input type="text" name="phone" id="register-buyer-phone" v-model="phoneNumber">

          <button @click.stop.prevent="sendBuyerRegisterRequest" class="pure-button button-success">马上注册为买家</button>
  		  </fieldset>
  		</form>

  		<form class="pure-form pure-form-stacked">
  		  <legend>卖家注册</legend>
  		  <fieldset>
  		    <label for="register-seller-username">用户名：</label>
  		    <input type="text" name="username" id="register-seller-username" v-model="username">

  		    <label for="register-seller-password">密码：</label>
  		    <input type="password" name="password" id="register-seller-password" v-model="password">

  		    <label for="register-seller-identity">身份证：</label>
  		    <input type="text" name="identity" id="register-seller-identity" v-model="identity">

  		    <label for="register-seller-phone">电话号码：</label>
  		    <input type="text" name="phone" id="register-seller-phone" v-model="phoneNumber">

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

    sendBuyerRegisterRequest: () => {

    },

    sendSellerRegisterRequest: () => {

    },

    sendLoginRequest: () => {
      store.login((error) => {
        if (!error) {
          router.go('/home')
        }
      })
    }

  },
  data () { return store }
}

</script>

<style>
.button-success {
  color: white;
  background: rgb(28, 184, 65); /* this is a green */
}
</style>
