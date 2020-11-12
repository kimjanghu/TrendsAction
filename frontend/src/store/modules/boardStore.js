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
    maintainers: [],
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
    SET_BOARD_INFO(state, boardInfo) {
      state.boardInfo = boardInfo
    },
    SET_BOARD_CONTENTS(state, contents) {
      state.contents = contents
    },
    SET_BOARD_HOST(state, hosts) {
      state.hosts = hosts
    },
    SET_BOARD_MAINTAINER(state, maintainers) {
      state.maintainers = maintainers
    },
    SET_BOARD_GUEST(state, guests) {
      state.guests = guests
    },
    SET_USER_AUTHORITY(state, authority) {
      state.authority = authority
    }
  },
  actions: {
    changeAuthority({ getters, dispatch }, changeData) {
      axios.put(SERVER.URL + SERVER.ROUTES.boards.changeAuthority, changeData, getters.config)
        .then(() => {
          alert('권한을 변경하였습니다.')
          dispatch('getBoardMember', changeData.boardId)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserAuthority({ commit, getters }, boardId) {
      const userId = +window.localStorage.getItem('userId')

      axios.get(SERVER.URL + SERVER.ROUTES.boards.getUserAuthority + `/${userId}` + `/${boardId}`, getters.config)
        .then(res => {
          commit('SET_USER_AUTHORITY', res.data.data)
        })
        .catch(err => {
          console.log(err)
        })
    },
    removeMember({ dispatch, getters }, removeData) {
      axios.delete(SERVER.URL + SERVER.ROUTES.boards.leaveBoard + `/${removeData.userId}` + `/${removeData.boardId}`, getters.config)
        .then(() => {
          dispatch('getBoardMember', removeData.boardId)
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
          const maintainers = []
          const guests = []
          boardMembers.forEach(el => {
            if (el.authority === 'host') {
              hosts.push({
                userId: el.userId,
                nickname: el.nickname,
                profile: el.profile
              })
            } else if (el.authority === 'guest') {
              guests.push({
                userId: el.userId,
                nickname: el.nickname,
                profile: el.profile
              })
            } else {
              maintainers.push({
                userId: el.userId,
                nickname: el.nickname,
                profile: el.profile
              })
            }
          })
          commit('SET_BOARD_HOST', hosts)
          commit('SET_BOARD_MAINTAINER', maintainers)
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
          if (data.thumbnail) {
            boardInfo['coverImage'] = data.thumbnail
          }
          commit('SET_BOARD_INFO', boardInfo)
        })
        .catch(err => {
          console.log(err)
        })
      })
    }
  }
}

export default boardStore