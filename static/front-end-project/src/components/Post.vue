<template>
<div class="post-item">
  <div class="pure-g">
    <div class="pure-u-1-4"></div>
    <div class="pure-u-1-2">
      <form class="pure-form pure-form-stacked">
        <fieldset>
          <label for="item-name">物品标题</label>
          <input class="pure-input-1-2" type="text" name="name" id="item-name" v-model="itemName">

          <label for="item-description">物品描述</label>
          <textarea id="item-description" name="description" cols="70" rows="4" v-model="itemDesc"></textarea>

          <label for="item-price">物品价格</label>
          <input type="number" step="0.01" name="price", id="item-price" v-model="itemPrice">

          <label for="item-category">物品分类</label>
          <select v-model="itemCategory">
            <option value="CLOTHES" selected>{{ global.categories["CLOTHES"] }}</option>
            <option value="COSMETICS">{{ global.categories["COSMETICS"] }}</option>
            <option value="ACCESSORY">{{ global.categories["ACCESSORY"] }}</option>
            <option value="ELECTRONICS">{{ global.categories["ELECTRONICS"] }}</option>
            <option value="FOODSTUFFS">{{ global.categories["FOODSTUFFS"] }}</option>
            <option value="BOOKS">{{ global.categories["BOOKS"] }}</option>
            <option value="MEDICINES">{{ global.categories["MEDICINES"] }}</option>
            <option value="FURNITURES">{{ global.categories["FURNITURES"] }}</option>
            <option value="OTHER">{{ global.categories["OTHER"] }}</option>
          </select>
          <button @click.stop.prevent="postItem" class="pure-button pure-button-primary">发布物品</button>
        </fieldset>
      </form>
    </div>
    <div class="pure-u-1-4"></div>
  </div>
</div>

</template>

<script>

import { store } from '../store'
import { router } from '../router'

export default  {
  ready() { 
    this.$http.get("/seller/session").then(
      (response) => {},
      (response) => {
        let { status } = response
        if (status == 401) {
          router.go("/login")
        }

      }
    )
  },
  methods: {
    postItem () {
      var that = this
      var data = {
        name: this.itemName,
        description: this.itemDesc,
        price: this.itemPrice,
        category: this.itemCategory
      }
      this.$http.post("/items", data).then(
        (response) => {
          var itemId = response.data.id
          router.go("/items/" + itemId)
        },

        (response) => {
          that.global.message = response.data.message
        }
      )
    }
  },

  data () {
    return {
      itemName: "",
      itemDesc: "",
      itemPrice: "",
      itemCategory: "",

      global: store
    }
  }
}
</script>

<style>

.post-item  form {
  overflow: hidden;
}

.post-item  form  button {
  float: right;
}

</style>
