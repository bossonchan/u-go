<template>
<div class="home">

  <div class="search">
    <form class="pure-form">
      <div class="pure-u-1-3"></div>
      <input class="pure-input-1-3" type="text" placeholder="请输入关键词" v-model="input">
      <button @click.stop.prevent="search" class="pure-button pure-button-primary">马上搜索</button>
    </form>
  </div>

  <div v-if="items.length == 0">
    <p style="text-align:center;">什么都找不到 :(</p>
  </div>

  <template  v-for="item of items">
    <a class="item" v-link="'/items/' + item.id">
      <img class="pure-img" :src="item.photos[0].url" :alt="item.name">
      <div>{{ item.name }}</div>
      <div>{{ item.price }}元</div>
    </a>
  </template>
</div>
</template>

<script>

import { store } from '../store'

export default {
  methods: {
    search () {
      this.items = [];
    }
  },

  data () {
    return { 
      input: "",

      cu: store,
      items: [
        { id: 1, name: "小米4手机", price: 1999.99, photos: [{ url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}] },
        { id: 2, name: "小米4手机", price: 1999.99, photos: [{ url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}] },
        { id: 3, name: "小米4手机", price: 1999.99, photos: [{ url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}] },
        { id: 4, name: "小米4手机", price: 1999.99, photos: [{ url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}] },
        { id: 5, name: "小米4手机", price: 1999.99, photos: [{ url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}] },
        { id: 6, name: "小米4手机", price: 1999.99, photos: [{ url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}] },
      ]
    }
  }
}
</script>

<style>
.home {
  overflow: hidden;
}

.item {
  display: block;
  position: relative;
  float: left;

  width: 200px;
  margin: 20px 20px;
  border: 1px solid #ccc;

  color: #2d3e50;
  text-decoration: none;
  cursor: pointer;
  transition: all 0.5s;
}

.item:hover {
  box-shadow: 0 0 2px 2px #6FBEF3;
}

.item > div {
  text-align: center;
}

</style>
