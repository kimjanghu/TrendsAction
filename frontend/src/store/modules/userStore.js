import cookies from 'vue-cookies'
import router from '@/router'
import axios from 'axios'
import SERVER from '@/lib/api'
import constants from '@/lib/constants'

const userStore = {
  namespaced: true,
  state: {
    constants,
    authToken: cookies.get('auth-token')
  },
  getters: {
    isLogin: state => !!state.authToken,
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    }
  },
  actions: {
    // User
    login({ commit }, loginData) {
      axios.post(process.env.VUE_APP_API_URL + SERVER.ROUTES.login, loginData)
        .then(res => {
          commit('SET_TOKEN', res.data.uid)
          const userInfo = { uid: res.data.uid, nickname: res.data.nickname }
          window.localStorage.setItem('userInfo', JSON.stringify(userInfo))
          window.document.location.href = '/home'
        })
        .catch(() => alert('Check login information again'))
    },
    logout({ commit }) {
      const check = confirm('로그아웃 하시겠습니까?')
      if (check) {
        commit('SET_TOKEN', null)
        cookies.remove('auth-token')
        window.localStorage.removeItem('userInfo')
        router.push('/home')
        alert('로그아웃 되었습니다.')
      } else {
        router.go(-1)
      }
    },
  }
}

export default userStore