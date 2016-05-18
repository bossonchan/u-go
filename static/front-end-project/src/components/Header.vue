<template>
<div class="header">
  <div class="home-menu pure-menu pure-menu-horizontal pure-menu-fixed">
  <a class="pure-menu-heading" v-link="{ path: '/home' }">U-Go</a>

  <ul class="pure-menu-list" v-if="global.isLogin && global.role">
    <li class="pure-menu-item"><a v-link="{ path: '/home' }" class="pure-menu-link" v-if="global.role != 'seller'">首页</a></li>
    <li class="pure-menu-item"><a v-link="{ path: '/seller' }" class="pure-menu-link" v-if="global.role == 'seller'">个人中心</a></li>
    <li class="pure-menu-item"><a v-link="{ path: '/buyer' }" class="pure-menu-link"  v-if="global.role == 'buyer'" >个人中心</a></li>
    <li class="pure-menu-item"><a @click.stop.prevent="sendLogoutRequest" class="pure-menu-link"  >{{ global.username }}（登出）</a></li>
  </ul>

  <ul class="pure-menu-list" v-else>
    <li class="pure-menu-item"><a v-link="{ path: '/home' }" class="pure-menu-link">首页</a></li>
    <li class="pure-menu-item"><a v-link="{ path: '/login' }" class="pure-menu-link">登录</a></li>
    <li class="pure-menu-item"><a v-link="{ path: '/register' }" class="pure-menu-link">注册</a></li>
  </ul>

</div>
</template>

<script>

import { store } from '../store'
import { router } from '../router'

export default {
  ready () {
    var that = this
    this.$http.get("/buyer/session", {}).then(
      (response) => {
        that.global.role = "buyer"
        that.global.isLogin = true
        that.global.username = response.data.username
      },
      (response) => {}
    )
    this.$http.get("/seller/session", {}).then(
      (response) => {
        that.global.role = "seller"
        that.global.isLogin = true
        that.global.username = response.data.username
      },
      (response) => {}
    )
  },


  methods: {
    sendLogoutRequest() {
      if (!this.global.isLogin) return

      var that = this
      var url = ""

      if (this.global.role == "seller") { url = "/seller/session" }
      if (this.global.role == "buyer") { url = "/seller/session" }
      if (!url) return

      this.$http.delete(url).then(
        (response) => {
          that.global.role = ""
          that.global.isLogin = false
          that.global.username = ""
          router.go("/home")
        },
        (response) => {}
      )
    }
  },

  data () {
    return {
      global: store
    }
  }
}

</script>

<style>
.home-menu {
  padding: 0.5em;
  text-align: center;
  box-shadow: 0 1px 1px rgba(0,0,0, 0.10);
}

.home-menu {
  background: #2d3e50;
}

.pure-menu.pure-menu-fixed {
  /* Fixed menus normally have a border at the bottom. */
  border-bottom: none;
  /* I need a higher z-index here because of the scroll-over effect. */
  z-index: 4;
}

.home-menu .pure-menu-heading {
  color: white;
  font-weight: 400;
  font-size: 120%;
}

.home-menu .pure-menu-selected a {
  color: white;
}

.home-menu a {
  color: #6FBEF3;
  cursor: pointer;
}

.home-menu li a:hover,
.home-menu li a:focus {
  background: none;
  border: none;
  color: #AECFE5;
}

@media (min-width: 48em) {

  /* We increase the body font size */
  body {
    font-size: 16px;
  }

  /* We want to give the content area some more padding */
  .content {
    padding: 1em;
  }

  /* We can align the menu header to the left, but float the
     menu items to the right. */
  .home-menu {
     text-align: left;
  }

  .home-menu ul {
    float: right;
  }
}
</style>
