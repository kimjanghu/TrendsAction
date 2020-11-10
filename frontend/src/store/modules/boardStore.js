import cookies from 'vue-cookies'
// import router from '@/router'
import axios from 'axios'
import SERVER from '@/lib/api'
import constants from '@/lib/constants'

const boardStore = {
  namespaced: true,
  state: {
    constants,
    authToken: cookies.get('auth-token'),
    email: window.localStorage.getItem('email'),
    userId: window.localStorage.getItem('userId'),
    boardInfo: null,
    contents: null,
    authority: '',
    hosts: [],
    guests: []
  },
  getters: {
    config: state => ({
      headers: {
        Authorization: `Bearer ${state.authToken}`,
        Email: `${state.email}`
      }
    })
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_BOARD_INFO(state, boardInfo) {
      state.boardInfo = boardInfo
    },
    SET_BOARD_CONTENTS(state, contents) {
      state.contents = contents
    },
    SET_BOARD_HOST(state, hosts) {
      state.hosts = hosts
    },
    SET_BOARD_GUEST(state, guests) {
      state.guests = guests
    },
    SET_USER_AUTHORITY(state, authority) {
      state.authority = authority
    }
  },
  actions: {
    getUserAuthority({ commit, getters }, boardId) {
      const userId = +window.localStorage.getItem('userId')

      axios.get(SERVER.URL + SERVER.ROUTES.boards.getUserAuthority + `/${userId}` + `/${boardId}`, getters.config)
        .then(res => {
          console.log(res.data.data)
          commit('SET_USER_AUTHORITY', res.data.data)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getBoardMember({ commit, getters }, boardId) {
      axios.get(SERVER.URL + SERVER.ROUTES.boards.getBoardMember + `/${boardId}`, getters.config)
        .then(res => {
          const boardMembers = res.data.data
          const hosts = []
          const guests = []
          boardMembers.forEach(el => {
            if (el.authority === 'host') {
              hosts.push({
                nickname: el.nickname,
                profile: el.profile
              })
            } else {
              guests.push({
                nickname: el.nickname,
                profile: el.profile
              })
            }
          })
          // console.log(hosts)
          // console.log(guests)
          commit('SET_BOARD_HOST', hosts)
          commit('SET_BOARD_GUEST', guests)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserBoard({ commit, getters }, boardId) {
      return new Promise(function(resolve) {
        axios.get(SERVER.URL + SERVER.ROUTES.boards.getBoardContent + `/${boardId}`, getters.config)
        .then(res => {
          let arr = []
          if (res.data.data.newsList) {
            arr = arr.concat(res.data.data.newsList)
          }

          if (res.data.data.twittList) {
            arr = arr.concat(res.data.data.twittList)
          }
          commit('SET_BOARD_CONTENTS', arr)
          resolve(res.data.data)
          return res.data.data
        })
        .then(data => {
          const boardInfo = {
            boardName: data.name,
            coverImage: 'https://source.unsplash.com/random'
          }
          // this.boardName = data.name
          // this.editName = data.name
          if (data.thumbnail) {
            boardInfo['coverImage'] = data.thumbnail
          }
          commit('SET_BOARD_INFO', boardInfo)
          // resolve(data)
        })
        .catch(err => {
          console.log(err)
        })
      })
    }
    
  }
}

export default boardStore