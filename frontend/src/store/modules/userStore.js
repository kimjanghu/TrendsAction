import cookies from 'vue-cookies'
import router from '@/router'
import axios from 'axios'
import SERVER from '@/lib/api'
import constants from '@/lib/constants'

const userStore = {
  namespaced: true,
  state: {
    constants,
    authToken: cookies.get('auth-token'),
    userInfo: null
  },
  getters: {
    isLogin: state => !!state.authToken,
    config: state => ({
      headers: {
        token: `Bearer ${state.authToken}`
      }
    })
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_USERINFO(state, userInfo) {
      state.userInfo = userInfo
    }
  },
  actions: {
    getUserInfo({ commit, getters }) {
      // const config = {
      //   headers: {
      //     token: `Bearer ${this.$cookies.get('auth-token')}`
      //   }
      // }
      // const userId = window.localStorage.getItem('userId')
      axios.get(SERVER.URL + SERVER.ROUTES.accounts.user, getters.config)
        .then(res => {
          commit('SET_USERINFO', res.data.data)
          console.log(res.data.data)
        })
        .catch(err => {
          console.log(err)
        })
    },
    login({ commit }, code) {
      // Bearer
      // http://k3a408.p.ssafy.io:9090/users/login/kakao
      axios.get(SERVER.URL + SERVER.ROUTES.accounts.kakaoLogin, {
        params: {
          code: code
        }
      })
        .then(res => {
          commit('SET_TOKEN', res.data.token)
          return res.data
        })
        .then(data => {
          console.log(data)
          window.localStorage.setItem('userId', data.userId)
          window.localStorage.setItem('nickname', data.nickname)
          if (data.nickname) {
            router.push('/')
            return
          }
          router.push({ name: 'UserProfile', params: { id: data.userId }})
          alert('Nickname을 설정해주세요.')
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
        window.localStorage.removeItem('nickname')
        router.push('/')
          .catch(err => {
            if(err.name != "NavigationDuplicated" ){
              throw err
            }
          })
        alert('로그아웃 되었습니다.')
      } else {
        router.go(-1)
      }
    },
  }
}

export default userStore