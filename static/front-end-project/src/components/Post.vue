<template>
<div class="post-item">
  <div class="pure-g">
    <div class="pure-u-1-4"></div>
    <div class="pure-u-1-2">
      <form class="pure-form pure-form-stacked">
        <fieldset>
          <label for="item-name">物品标题</label>
          <input class="pure-input-1-2" type="text" name="name" id="item-name" >

          <label for="item-description">物品描述</label>
          <textarea id="item-description" name="description" cols="70" rows="4"></textarea>

          <label for="item-price">物品价格</label>
          <input type="text" name="price", id="item-price">

          <label for="item-category">物品分类</label>
          <select>
            <option value="">衣服</option>
            <option value="">药品</option>
            <option value="">家具</option>
            <option value="">电子产品</option>
          </select>
          <button @click.stop.prevent="" class="pure-button pure-button-primary">发布物品</button>
        </fieldset>
      </form>
    </div>
    <div class="pure-u-1-4"></div>
  </div>
</div>

</template>

<script>

import { store } from '../store'

export default  {
  data () {
    return {
      cu: store
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
