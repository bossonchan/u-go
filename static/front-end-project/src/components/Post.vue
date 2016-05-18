<template>
<div class="post-item">
  <div class="pure-g">
    <div class="pure-u-1-4">
    </div>
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

          <label for="item-photos">上传图片</label>
          <div class="item-photos">
            <div class="upload-area">
              <div class="upload-content">+</div>
              <input @change.stop.prevent="uploadPhoto($event)" type="file">
            </div>
            <div class="upload-photos">
              <div class="upload-photo-container" v-for="photo of itemPhotos">
                <img class="pure-img" :src="photo.url" >
              </div>
            </div>
          </div>
          <button @click.stop.prevent="postItem" class="pure-button pure-button-primary">发布物品</button>
        </fieldset>
      </form>
    </div>
    <div class="pure-u-1-4">
    </div>
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
    uploadPhoto(event) { 
      var that  = this
      var input = event.target || event.srcElement
      var file  = input.files[0]
      if (!file) return

      var formData = new FormData()
      formData.append("file", file, file.name)
      this.$http.post("/photos", formData).then(
        (response) => {
          that.itemPhotos.push({ url: response.data.url })
        },
        (response) => {
          that.global.message = "上传图片失败！"
        }
      )
    },

    postItem () {
      var that = this
      var data = {
        name: this.itemName,
        description: this.itemDesc,
        price: this.itemPrice,
        category: this.itemCategory,
        itemPhotos: this.itemPhotos
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
      itemPhotos: [],

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

.item-photos {
  display: block;
}

.upload-area {
  display: block;
  position: relative;
  margin-bottom: 20px;
}

.upload-content {
  display: block;
  width: 100px;
  height: 100px;

  line-height: 100px;
  text-align: center;
  font-size: 50px;
  color: #2d3e50;
  background: #ddd;
  cursor: pointer;
}

.upload-content:hover {
  background: #ccc;
}

.upload-content:active {
  background: #bbb;
}

.upload-area > input[type=file] {
  display: block;
  position: absolute;
  top: 0;
  left: 0;

  width: 100px;
  height: 100px;

  opacity: 0;
  cursor: pointer;

}

.upload-photos {
  display: block;
}

.upload-photo-container {
  display: inline-block;
  width: 100px;
  height: 100px;
}

.upload-photo-container > img {
  display: block;
  width: 100px;
  height: auto;
  vertical-align: middle;
}

</style>
