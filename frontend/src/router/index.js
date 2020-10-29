import Vue from 'vue'
import VueRouter from 'vue-router'
import constants from '@/lib/constants'
import Home from '../views/Home.vue'
import TrendDetailLayout from '../views/trends/TrendDetailLayout.vue'
import UserProfile from '../views/users/UserProfile.vue'
import BoardDetail from '../views/boards/BoardDetail.vue'

Vue.use(VueRouter)

function loadView(view) {
  return () => import(/* webpackChunkName: "view-[request]" */ `@/views/${view}.vue`)
}

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: () => import('@/views/About.vue')
  },
  {
    path: '/oauth/kakao',
    name: constants.URL_TYPE.USER.LOGIN,
    component: loadView('oauth/OauthLogin')
  },
  {
    path: '/trend-detail',
    name: 'TrendDetailLayout',
    component: TrendDetailLayout
  },
  {
    path: '/mypage/:id',
    name: 'UserProfile',
    component: UserProfile
  },
  {
    path: '/board-detail',
    name: 'BoardDetail',
    component: BoardDetail
  },
  {
    path: '/trend',
    name: constants.URL_TYPE.TREND.LIST,
    component: loadView('trends/TrendList')
  },
  {
    path: '/predict',
    name: constants.URL_TYPE.PREDICT.LIST,
    component: loadView('predict/PredictList')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
