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
    email: window.localStorage.getItem('email'),
    userInfo: null,
  },
  getters: {
    isLogin: state => !!state.authToken,
    config: state => ({
      headers: {
        Authorization: `Bearer ${state.authToken}`,
        Email: `${state.email}`,
        // 'Access-Control-Allow-Origin':'*'
      }
    })
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_EMAIL(state, email) {
      state.email = email
    },
    SET_USERINFO(state, userInfo) {
      state.userInfo = userInfo
    }
  },
  actions: {
    getUserInfo({ commit, getters }) {
      return new Promise(function (resolve) {
        const userId = window.localStorage.getItem('userId')
        axios.get(SERVER.URL + SERVER.ROUTES.accounts.user + `/${userId}`, getters.config)
        .then(res => {
          commit('SET_USERINFO', res.data.data)
          resolve(res.data.data)
        })
        .catch(err => {
          console.log(err)
        })  
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
          commit('SET_EMAIL', res.data.email)
          return res.data
        })
        .then(data => {
          console.log(data)
          window.localStorage.setItem('email', data.email)
          window.localStorage.setItem('userId', data.userId)
          window.localStorage.setItem('nickname', data.nickname)
          if (data.nickname) {
            // router.push('/')
            window.location.href = process.env.VUE_APP_PAGE_URL + '/'
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