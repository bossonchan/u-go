import Vue from 'vue'
import VueResource from 'vue-resource'

Vue.use(VueResource)

import App from './App.vue'
import { router } from './router'

/* eslint-disable no-new */
router.start(App, '#app')
