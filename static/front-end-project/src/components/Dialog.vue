<template>
<div class="dialog" v-show="global.message">
  <div class="dialog-content">
    <div class="dialog-message" >
      <p>{{ global.message }}</p>
    </div>
    <div class="dialog-operation" >
      <button @click="close" class="pure-button">确定</button>
    </div>
  </div>
</div>
</template>

<script>

import { store } from '../store'

export default {
  methods: {
    close () {
      this.global.message = ""
    }
  },

  data () {
    return {
      global: store
    }
  }
}

</script>

<style>

.dialog {
  display: block;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  z-index: 1000;
}

.dialog-content {
  display: block;
  position: fixed;

  top: 50%;
  left: 50%;

  width: 300px;
  height: 200px;
  border: 1px solid #ccc;
  border-radius: 5px;

  margin-top: -150px;
  margin-left: -150px;

  background: white;
}

.dialog-content .dialog-message {
  padding: 10px;
  min-height: 120px;
}

.dialog-content .dialog-operation {
  display: block;
  position: relative;
  text-align: center;
}

</style>
