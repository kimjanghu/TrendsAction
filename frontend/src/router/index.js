import Vue from 'vue'
import VueRouter from 'vue-router'
import constants from '@/lib/constants'
import Home from '../views/Home.vue'

// trend
import TrendDetailLayout from '../views/trends/TrendDetailLayout.vue'
import TrendDetailNews from '../views/trends/TrendDetailNews.vue'
import TrendDetailSns from '../views/trends/TrendDetailSns.vue'
import TrendDetailAgora from '../views/trends/TrendDetailAgora.vue'

// user
import UserProfileLayout from '../views/users/UserProfileLayout.vue'
import UserProfile from '../views/users/UserProfile.vue'

// board
import BoardList from '../views/boards/BoardList.vue'
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
    path: '/oauth/kakao',
    name: constants.URL_TYPE.USER.LOGIN,
    component: loadView('oauth/OauthLogin')
  },
  {
    path: '/logout',
    name: constants.URL_TYPE.USER.LOGOUT,
    component: loadView('users/UserLogout')
  },
  {
    path: '/trend-detail/:trendId',
    component: TrendDetailLayout,
    props: true,
    children: [
      {
        path: 'news',
        name: 'TrendDetailNews',
        component: TrendDetailNews
      },
      {
        path: 'sns',
        name: 'TrendDetailSns',
        component: TrendDetailSns
      },
      {
        path: 'agora',
        name: 'TrendDetailAgora',
        component: TrendDetailAgora
      },
    ]
  },
  {
    path: '/mypage/:id',
    name: 'UserProfileLayout',
    component: UserProfileLayout,
    children: [
      {
        path: 'profile',
        name: 'UserProfile',
        component: UserProfile,
      },
      {
        path: 'board',
        name: 'BoardList',
        component: BoardList
      },
    ]
  },
  {
    path: '/mypage/:id/board/:boardId',
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
