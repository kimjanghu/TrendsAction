import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import axios from 'axios'
import VueCookies from 'vue-cookies'
import constants from '@/lib/constants'
import SERVER from '@/lib/api'
import sal from "sal.js"
import "##/sal.js/dist/sal.css";

Vue.use(VueCookies)

Vue.prototype.$http = axios
Vue.prototype.$constants = constants
Vue.prototype.$api = SERVER
Vue.config.productionTip = false

new Vue({
  created() {
    sal.init()
  },
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
