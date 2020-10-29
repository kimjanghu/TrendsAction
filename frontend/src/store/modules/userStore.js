import cookies from 'vue-cookies'
import router from '@/router'
import axios from 'axios'
// import SERVER from '@/lib/api'
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
    login({ commit }, code) {
      console.log(code)
      axios.get(`http://k3a408.p.ssafy.io:9090/users/login/kakao`, {
        params: {
          code: code
        }
      })
        .then(res => {
          commit('SET_TOKEN', res.data.token)
        })
        .then(data => {
          window.localStorage.setItem('userId', data.userId)
          if (data.nickname) {
            router.push('/')
            return
          }
          router.push({ name: 'UserProfile', params: { id: data.userId }})
        })
        .catch(err => {
          console.log(err)
          alert('Check login information again')
        })
    },
    logout({ commit }) {
      const check = confirm('로그아웃 하시겠습니까?')
      if (check) {
        commit('SET_TOKEN', null)
        cookies.remove('auth-token')
        window.localStorage.removeItem('userId')
        router.push('/')
        alert('로그아웃 되었습니다.')
      } else {
        router.go(-1)
      }
    },
  }
}

export default userStore