<template>
  <div id="application">
    <header-component></header-component>

    <div id="main">
      <router-view></router-view>
    </div>

    <div id="footer">
    	<p> Contact Me: <a href="mailto:bossonchan@gmail.com">bossonchan@gmail.com<a> </p>
    </div>

  <div>
</template>

<script>
import Login from './components/Login.vue'
import Home from './components/Home.vue'
import Item from './components/Item.vue'
import Seller from './components/Seller.vue'
import Buyer from './components/Buyer.vue'
import Post from './components/Post.vue'
import HeaderComponent from './components/Header.vue'
import { router } from './router'
import { store } from './store'

router.map({
  '/home': {
    component: Home
  },

  '/login': {
    component: Login
  },

  '/register': {
    component: Login
  },

  '/items/:itemId': {
    component: Item
  },

  '/seller': {
    component: Seller
  },

  '/buyer': {
    component: Buyer
  },

  '/post': {
    component: Post
  }

})

export default {
  components: {
    Home, Login, Item, Seller, Buyer, Post, HeaderComponent
  }
}
</script>

<style>
body {
  line-height: 1.7em;
  color: #7f8c8d;
  font-size: 13px;
}

h1,
h2,
h3,
h4,
h5,
h6,
label {
  color: #34495e;
}

#main {
  padding-top: 80px;
  min-height: 500px;
}

#footer {
  background: #2d3e50;
  text-align: center;
  padding: 1em;
  color: white;
}

.button-success,
.button-error,
.button-warning,
.button-secondary {
  color: white;
  border-radius: 4px;
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
}

.button-success {
  background: rgb(28, 184, 65); /* this is a green */
}

.button-error {
  background: rgb(202, 60, 60); /* this is a maroon */
}

.button-warning {
  background: rgb(223, 117, 20); /* this is an orange */
}

</style>
