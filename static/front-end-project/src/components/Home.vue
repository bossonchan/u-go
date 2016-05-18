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
      <img class="pure-img" :src="item.itemPhotos.length > 0 ? item.itemPhotos[0].url : global.defaultPhoto" :alt="item.name">
      <div>{{ item.name }}</div>
      <div>{{ item.price }}元</div>
    </a>
  </template>
</div>
</template>

<script>

import { store } from '../store'

export default {
  ready () {
    var that = this

    this.$http.get("/items").then(
      (response) => {
        that.items = response.data
      },
      (response) => {}
    )
  },

  methods: {
    search () {
      var that = this
      this.$http.get("/items", { search: this.input }).then(
        (response) => {
          that.items = response.data
        },
        (response) => {
          that.global.message = response.data.message
        }
      )
    }
  },

  data () {
    return { 
      global: store,

      input: "",
      items: []
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
