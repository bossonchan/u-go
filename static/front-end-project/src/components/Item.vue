<template>
<div class="item-detail pure-g" v-if="item">

  <div class="pure-u-1-3">
    <div class="item-gallery">
      <img class="gallery-display" :src="currentDisplay" >
      <div class="gallery-selection">
        <img @click="choosePhoto(index)" class="pure-img" :src="photo.url" v-for="(index, photo) of item.itemPhotos">
      </div>
    </div>
  </div>

  <div class="pure-u-2-3">
    <div class="item-info">
      <div class="name">{{ item.name }} <span v-show="item.status == 'INACTIVE'" style="color:red" >（已下架）</span></div>
      <div class="category">分类：{{ global.categories[item.category] }}</div>
      <div class="description">{{ item.description }}</div>
      <div class="time">
        发布时间：
        <span>{{ (new Date(item.time)).getFullYear() }}</span>-<span>{{ (new Date(item.time)).getMonth() + 1}}</span>-<span>{{ (new Date(item.time)).getDate()     }}</span>
        <span>{{ (new Date(item.time)).getHours()    }}</span>:<span>{{ (new Date(item.time)).getMinutes()  }}</span>
      </div>
      <div class="price">售价：<span>{{ item.price }}</span> 元</div>
      <div class="seller">
        <div class="seller-name">联系人：{{ item.seller.username }}</div>
        <div class="seller-phone">联系电话：{{ item.seller.phoneNumber }}</div>
      </div>
    </div>

    <div class="item-operation" v-show="!(global.isLogin && global.role == 'seller')">
      <button @click.stop.prevent="sendReservationRequest" class="pure-button button-success">立即预订</button>
      <button @click.stop.prevent="addToShoppingCart" class="pure-button button-warning" >加入购物车</button>
    </div>

  </div>

</div>
<div style="text-align:center;" v-else>
  <p style="">找不到该产品！</p>
  <a v-link="{ path: '/home' }">回到首页</a>
</div>
</template>

<script>
import { store } from '../store'
import { router } from '../router'

export default {
  ready() {
    var params = this.$route.params || {}
    var itemId = params.itemId || ""
    var that   = this

    this.$http.get("/items/" + itemId).then(
      (response) => {
        that.item = response.data
        if (that.item.itemPhotos.length > 0) {
          that.currentDisplay = that.item.itemPhotos[0].url
        }
      },
      (response) => {}
    )
  },

  methods: {
    choosePhoto(index) { 
      var photo = this.item.itemPhotos[index]
      this.currentDisplay = photo.url
    },

    sendReservationRequest () { 
      if (!this.global.isLogin) return router.go('/login')
      if (!this.item) return

      var that = this
      this.$http.post("/items/" + this.item.id + "/reservation").then(
        (response) => { 
          that.global.message = "已成功发送预订请求"
        },
        (response) => {
           that.global.message = response.data.message
        }
      )
    },
    addToShoppingCart () {
      if (!this.global.isLogin) return router.go('/login')
      if (!this.item) return

      var that = this
      this.$http.post("/items/" + this.item.id + "/shoppingcart").then(
        (response) => {
          that.global.message = "添加成功！"
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

      currentDisplay: store.defaultPhoto,

      item: null
    }

  }
}
</script>

<style>
.item-detail {
  display: block;
  position: relative;

  padding: 3em;
}

.item-gallery {
  display: block;
  overflow: hidden;
}

.gallery-display {
  border-bottom: 1px solid #ccc;
}

.item-gallery .gallery-display {
  display: block;
  width: 100%;
}

.item-gallery .gallery-selection {
  display: block;
  overflow: hidden;
  margin: 10px;
}

.gallery-selection > img {
  display: block;

  width: auto;
  height: 50px;

  margin: 5px;
  float: left;

  cursor: pointer;
  transition: all 0.5s;
}

.gallery-selection > img:hover {
  box-shadow: 0 0 2px 2px #6FBEF3;
}

.item-info, .item-operation {
  padding: 0 2em;
}

.item-info .name {
  padding: 0;
  color: #000;
  font-weight: bold;
  font-size: 120%;
  line-height: 2;
}

.item-info > div {
  padding: 1em;
}

.item-info .price span  {
  color: rgb(223, 117, 20);
  text-decoration: underline;
  font-weight: bold;
}

.item-info .seller {
  display: block;
}

.item-info .seller > div {
  display: inline-block;
  margin-right: 30px;
  color: #2d3e50;
}

.item-operation {
  display: block;
}

.item-operation > button {
  margin-left: 1em;
  color: white;
}

</style>
