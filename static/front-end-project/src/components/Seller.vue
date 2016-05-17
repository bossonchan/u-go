<template>
<div class="seller-centre">

  <div class="pure-g">
    <div class="pure-u-1-4"></div>

    <div class="pure-u-1-2">
      <div class="reservations">
        <h1>待处理请求</h1>
        <div class="container">
          <template v-for="reservation of reservations">
            <div class="reservation">
              <div>
                <span class="delete">删除</span>
                <span class="time" >
                  <span>{{ (new Date(reservation.time)).getFullYear() }}</span>-<span>{{ (new Date(reservation.time)).getMonth() + 1}}</span>-<span>{{ (new Date(reservation.time)).getDate()     }}</span>
                  <span>{{ (new Date(reservation.time)).getHours()    }}</span>:<span>{{ (new Date(reservation.time)).getMinutes()  }}</span>
                </span>
                --
                用户 <span class="username" >{{ reservation.buyer.username }}</span>
                预定了物品<a v-link="'/items/' + reservation.item.id">{{ reservation.item.name  }}</a> ，
                联系电话为<span class="phone">{{ reservation.buyer.phoneNumber }}</span>
              </div>
            </div>
          </template>
        </div>
      </div>
      <div class="items">
        <h1> 已发布物品 </h1>

        <div class="container">
          <template v-for="item of items">
            <div class="item-posted">
              <div class="item-summary">
                <a v-link="'/items/' + item.id">{{ item.name }}</a>
                <div>
                  <div>价格：<span>{{ item.price }}元</span></div>
                  <div>
                    发布时间：
                    <span>{{ (new Date(item.time)).getFullYear() }}</span>-<span>{{ (new Date(item.time)).getMonth() + 1}}</span>-<span>{{ (new Date(item.time)).getDate()     }}</span>
                    <span>{{ (new Date(item.time)).getHours()    }}</span>:<span>{{ (new Date(item.time)).getMinutes()  }}</span>
                  </div>
                  <div>
                    <button class="pure-button button-error">下架</button>
                  </div>
                </div>
              </div>
              <div class="item-image">
                <img class="pure-img" :src="item.photos[0].url" >
              </div>
            </div>
          </template>
        </div>
      </div>

    </div>

    <div class="pure-u-1-4">
      <a v-link="{ path: '/post' }" class="pure-button button-success post-item">马上发布产品！</a>
    </div>
  </div>

</div>

</template>

<script>
import { store } from '../store'
export default {
  data () {
    return {
      cu: store,

      items: [
        { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
        { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
        { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
        { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
      ],

      reservations: [
        {
          id : 1,
          item: { id: 1, name: "小米4手机", price: 1999.99, time: 1463488299698, photos: [{ url:  "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg"}]},
          buyer: {
            username: "Shin",
            phoneNumber: "13580512947"
          },
          time: 1463488299698
        }
      ]
    }
  }
}
</script>

<style>
.seller-centre h1 {
  font-size: 120%;
  border-bottom: 1px solid #ccc;
}

.container {
  padding: 0 1em;
}

.item-posted {
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  overflow: hidden;
}

.item-posted .item-summary {
  float: left;
}

.item-posted .item-image {
  float: right;
}

.item-posted .item-image {
  width: 150px;
}

.seller-centre a {
  color: #34495e;
}

.seller-centre a:hover {
  color: #6FBEF3;
}

.reservation .phone, .username {
  font-weight: bold;
}


.reservation .delete {
  color: rgb(202, 60, 60);
  font-weight: bold;
  text-decoration: underline;
  cursor: pointer;
}


a.post-item {
  color: white;
  position: fixed;
  top: 100px;

  margin-left: 10px;
}

</style>
