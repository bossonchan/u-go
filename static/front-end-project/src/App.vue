<template>
  <div>
    <p>
    	<a v-link="{ path: '/hello' }">hello</a>
    	<a v-link="{ path: '/foo' }">foo</a>
    </p>
    <router-view></router-view>
  <div>
</template>

<script>
import Hello from './components/Hello.vue'
import { router } from './router'

console.log(router)

router.map({
  '/hello': {
    component: Hello
  },
  '/foo': {
    component: Hello
  }
})

export default {
  components: {
    Hello
  }
}
</script>

<style>
body {
  font-family: Helvetica, sans-serif;
}
</style>
