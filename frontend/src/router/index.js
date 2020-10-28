import Vue from 'vue'
import VueRouter from 'vue-router'
import constants from '@/lib/constants'
import Home from '../views/Home.vue'
import TrendDetailLayout from '../views/trends/TrendDetailLayout.vue'
import UserProfile from '../views/users/UserProfile.vue'
import BoardList from '../views/boards/BoardList.vue'
import BoardDetail from '../views/boards/BoardDetail.vue'
import UserProfileUpdate from '../views/users/UserProfileUpdate.vue'

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
    path: '/trend-detail',
    name: 'TrendDetailLayout',
    component: TrendDetailLayout
  },
  {
    path: '/mypage',
    component: UserProfile,
    children: [
      {
        path: 'update',
        name: 'UserProfileUpdate',
        component: UserProfileUpdate,
      },
      {
        path: 'board',
        name: 'BoardList',
        component: BoardList
      },
      {
        path: 'board-detail',
        name: 'BoardDetail',
        component: BoardDetail
      },
    ]
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
