<template>
<div class="item-detail pure-g">

  <div class="pure-u-1-3">
    <div class="item-gallery">
      <img class="gallery-display" :src="item.photos[0].url" >
      <div class="gallery-selection">
        <img class="pure-img" :src="photo.url" v-for="photo of item.photos">
      </div>
    </div>
  </div>

  <div class="pure-u-2-3">
    <div class="item-info">
      <div class="name">{{ item.name }}</div>
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

    <div class="item-operation">
      <button class="pure-button button-success">立即预约</button>
      <button class="pure-button button-warning">加入购物车</button>
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

      item: {
        id: 1,
        name: "小米4手机",
        description: "米手机4采用了高通骁龙801手机处理器，内含四个Krait 400 2.5GHz 处理核心。运算速度提升14%，性能更强大。它能出色地同时处理多个复杂任务。它的强大还体现在图像处理器速度较前代提升近一 倍，这让拍照与录像都有了更多玩法和可能性。内含一个 Hexagon DSP 核心，专门以更低功耗运行电影、音乐、拍照等任务。这意味着在性能更强大的同时，手机续航都比以往更加持久耐用。",
        price: 1999.99,
        time : 1463488299698,
        photos: [
          { url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg" },
          { url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg" },
          { url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg" },
          { url: "http://www.isport123.com/images/ovygy33bmqxhc2lhovxs4y3pnu/news/20150925/63351443172946.jpg" },
        ],
        seller: {
          username: "Shin",
          phoneNumber: "13580512947"
        }
      }
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

.item-gallery .gallery-display {
  display: block;
  width: 100%;
}

.item-gallery .gallery-selection {
  display: block;

  overflow: hidden;
}

.gallery-selection > img {
  display: block;

  width: auto;
  height: 50px;

  margin: 5px 5px 5px 0;
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
