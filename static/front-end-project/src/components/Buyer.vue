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

        <template v-for="reservation of reservations">
          <div class="reservation">
            <div>
              <span class="delete">删除</span>
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

        <template v-for="scitem of shoppingCartItems">
          <div class="shopping-cart-item">
            <img class="pure-img" :src="scitem.item.photos[0].url" >
            <div>
              <div class="name" >
                <a v-link="'/items/' + scitem.item.id">{{ scitem.item.name }}</a>
              </div>
              <div class="time" >
                <span>{{ (new Date(scitem.time)).getFullYear() }}</span>-<span>{{ (new Date(scitem.time)).getMonth() + 1}}</span>-<span>{{ (new Date(scitem.time)).getDate()     }}</span>
                <span>{{ (new Date(scitem.time)).getHours()    }}</span>:<span>{{ (new Date(scitem.time)).getMinutes()  }}</span>
              </div>
              <button class="pure-button button-error">从购物车移除</button>
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

export default {
  data () {
    return {
      cu: store,
      shoppingCartItems: [
        {
          id : 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        },
        {
          id : 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        },
        {
          id : 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        },
        {
          id : 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        },
        {
          id : 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        },
        {
          id : 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        }
      ],

      reservations: [
        {
          id: 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        },
        {
          id: 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          time: 1463488299698
        }
      ]
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
