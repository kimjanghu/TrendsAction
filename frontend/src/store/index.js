import Vue from 'vue'
import Vuex from 'vuex'
import userStore from '@/store/modules/userStore'
import boardStore from '@/store/modules/boardStore'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    userStore,
    boardStore
  }
})
