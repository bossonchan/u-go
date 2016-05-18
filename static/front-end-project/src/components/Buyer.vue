<template>
<div class="buyer-centre">
  <div class="pure-g">
    <div class="pure-u-1-8"></div>
    <div class="pure-u-3-4">
      <div class="reservations" >
        <h1>已发送的预定请求</h1>

        <div v-if="reservations.length == 0">
          <p>还没有预定任何物品</p>
        </div>

        <template v-for="(index, reservation) of reservations">
          <div class="reservation">
            <div>
              <span @click.stop.prevent="removeReservation(index)" class="delete">删除</span>
              <span class="time" >
                <span>{{ (new Date(reservation.time)).getFullYear() }}</span>-<span>{{ (new Date(reservation.time)).getMonth() + 1}}</span>-<span>{{ (new Date(reservation.time)).getDate()     }}</span>
                <span>{{ (new Date(reservation.time)).getHours()    }}</span>:<span>{{ (new Date(reservation.time)).getMinutes()  }}</span>
              </span>
              --
              预定了物品<a v-link="'/items/' + reservation.item.id">{{ reservation.item.name  }}</a>
            </div>
          </div>
        </template>
      </div>

      <div class="shopping-cart">
        <h1>购物车列表</h1>

        <div v-if="shoppingCartItems.length == 0">
          <p>购物车中还没有添加任何物品</p>
        </div>

        <template v-for="(index, scitem) of shoppingCartItems">
          <div class="shopping-cart-item">
            <img class="pure-img" :src="scitem.item.itemPhotos.length > 0 ?scitem.item.itemPhotos[0].url : '/uploads/1463411943380.jpg'" >
            <div>
              <div class="name" >
                <a v-link="'/items/' + scitem.item.id">{{ scitem.item.name }}</a>
              </div>
              <div class="time" >
                <span>{{ (new Date(scitem.time)).getFullYear() }}</span>-<span>{{ (new Date(scitem.time)).getMonth() + 1}}</span>-<span>{{ (new Date(scitem.time)).getDate()     }}</span>
                <span>{{ (new Date(scitem.time)).getHours()    }}</span>:<span>{{ (new Date(scitem.time)).getMinutes()  }}</span>
              </div>
              <button @click.stop.prevent="removeItemFromShoppingCart(index)" class="pure-button button-error">从购物车移除</button>
            </div>
          </div>
        </template>
      </div>

    </div>
    <div class="pure-u-1-8"></div>
  </div>
  
</div>

</template>

<script>

import { store } from '../store'
import { router } from '../router'

export default {
  ready () {
    var that = this
    this.$http.get("/reservations").then(
      (response) => { that.reservations = response.data },
      (response) => {
        let { status } = response
        if (status == 401 || status == 403) {
          router.go("/login")
        }
      }
    )
    this.$http.get("/shoppingcart").then(
      (response) => { that.shoppingCartItems = response.data },
      (response) => {
        let { status } = response
        if (status == 401 || status == 403) {
          router.go("/login")
        }
      }
    )
  },

  methods: {
    removeReservation (index) {
      var that = this
      var reservation = this.reservations[index]
      this.$http.delete("/reservations/" + reservation.id).then(
        (response) => { 
          this.reservations.splice(index, 1)
        },
        (response) => { 
          that.global.message = response.data.message
        },
      )
    },
    removeItemFromShoppingCart(index) {
      var that = this
      var item = this.shoppingCartItems[index]
      this.$http.delete("/shoppingcart/" + item.id).then(
        (response) => { 
          this.shoppingCartItems.splice(index, 1)
        },
        (response) => { 
          that.global.message = response.data.message
        },
      )
    }
  },

  data () {
    return {
      global: store,
      shoppingCartItems: [],

      reservations: []
    }
  }
}
</script>

<style>

.shopping-cart, .reservations {
  overflow: hidden;
}

.shopping-cart-item {
  display: block;
  float:  left;

  width: 200px;
  border: 1px solid #ccc;
  padding: 5px;
  margin: 10px 5px;

  text-align: center;
}

.shopping-cart-item > img {
  display: block;
  width: 100%;
}

</style>
