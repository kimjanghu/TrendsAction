import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import TrendDetailLayout from '../views/trends/TrendDetailLayout.vue'
import UserProfile from '../views/users/UserProfile.vue'
import BoardDetail from '../views/boards/BoardDetail.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/trend-detail',
    name: 'TrendDetailLayout',
    component: TrendDetailLayout
  },
  {
    path: '/mypage',
    name: 'UserProfile',
    component: UserProfile
  },
  {
    path: '/board-detail',
    name: 'BoardDetail',
    component: BoardDetail
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
