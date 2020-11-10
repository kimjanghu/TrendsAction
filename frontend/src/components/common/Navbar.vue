<template>  
  <div>
  <header>
    <h2
      class="logo"
      @click="goToHome"
    >
      TrendsAction
    </h2>
    <div class="router-area" v-if="$vuetify.breakpoint.lgAndUp">
      <ul class="navbar-list">
        <li class="navbar-list-item" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Trend</li>
        <li class="navbar-list-item" @click="$router.push({ name: 'PredictDetail', params: { year: '2021', month: '11', week: 1, categoryId: 1} })">Predict</li>
        <li class="navbar-list-item" @click="dialog = true" v-if="!isLogin">
          Login
          <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
        </li>
        <div v-else style="display:flex;">
          <li class="navbar-list-item" @click="logout()">Logout</li>
          
            <router-link
              class="navbar-icon"
              tag="button"
              :to="{ name: 'UserProfile', params: { id: userId }}"
            >
              <span style="color: #ffffff; font-size: 1.5rem; cursor:pointer; margin-right: 10px;">
                <i class="fas fa-user-circle navbar-icon"></i>
              </span>
            </router-link>
            <span style="color: #ffffff; font-size: 1.5rem; cursor:pointer;">
              <i class="fas fa-bell navbar-icon"></i>
            </span>
         
        </div>
      </ul>
    </div>
    <div v-else>
      <!-- <span @click.stop="drawer = !drawer" style="color: #ffffff; font-size: 1.5rem; cursor:pointer;">
        <i class="fas fa-bars navbar-icon"></i>
      </span> -->
      <span @click="toggleClassName" style="color: #ffffff; font-size: 1.5rem; cursor:pointer;">
        <i class="fas fa-bars navbar-icon toggle-icon"></i>
      </span>
    </div>
    
    <div class="sidebar">
      <div style="position:absolute; top:50px; left: 50px; font-size: 1.5rem; cursor:pointer;">
        <i class="fas fa-times" @click="toggleClassName"></i>
      </div>
      <ul style="text-align:center">
        <li style="cursor:pointer;" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Trend</li>
        <li style="cursor:pointer;" @click="$router.push({ name: 'PredictDetail', params: { year: '2021', month: '11', week: 1, categoryId: 1} })">Predict</li>
        <li style="cursor:pointer;" @click="dialog = true" v-if="!isLogin">
          Login
          <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
        </li>
        <div v-else>
          <li @click="logout()">Logout</li>
          <div style="cursor:pointer; display:flex; justify-content:center">
            <v-icon class="mr-3">mdi-account-circle</v-icon>
            <v-icon>mdi-bell</v-icon>
          </div>
        </div>
      </ul>
    </div>
  </header>
    <!-- Side bar -->
    <!-- <v-navigation-drawer
      v-model="drawer"
      absolute
      right
      temporary
    >
      <v-list-item v-if="!isLogin" class="avatar-info">
        <v-list-item-avatar>
          <v-img src="mdi-account"></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title>로그인이 필요합니다.</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item v-if="isLogin" class="avatar-info">
        <v-list-item-avatar>
          <v-icon>mdi-menu-down</v-icon>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title>메뉴 리스트</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-orange--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>홈</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>내 정보</v-list-item-title>
          </v-list-item>

          <v-list-item v-if="isLogin">
            <v-list-item-icon>
              <v-icon>mdi-logout</v-icon>
            </v-list-item-icon>
            <v-list-item-title>로그아웃</v-list-item-title>
          </v-list-item>

          <v-list-item v-else>
            <v-list-item-icon>
              <v-icon>mdi-login</v-icon>
            </v-list-item-icon>
            <v-list-item-title>로그인</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer> -->

  </div>
  
</template>

<script>
import UsersLoginForm from '@/components/users/UsersLoginForm'
import { mapGetters, mapActions, mapState } from 'vuex'

export default {
  name: 'Navbar',
  data() {
    return {
      dialog: false,
      drawer: false,
      userId: null,
      group: null
    }
  },
  components: {
    UsersLoginForm
  },
  computed: {
    ...mapState('userStore', ['userInfo']),
    ...mapGetters('userStore', ['isLogin'])
  },
  watch: { 
    group () {
      this.drawer = false
    },
  },
  created() {
    window.addEventListener('scroll', this.handleScroll)
  },
  destroyed() {
    window.addEventListener('scroll', this.handleScroll)
  },
  methods: {
    ...mapActions('userStore', ['logout', 'getUserInfo']),
    changeDialog(dialog) {
      this.dialog = dialog
    },
    handleScroll() {
      let header = document.querySelector('header');
      header.classList.toggle('sticky', window.scrollY > 650)
    },
    goToHome() {
      this.$router.push('/')
        .catch(err => {
          if(err.name != "NavigationDuplicated" ){
            throw err
          }
        })
    },
    toggleClassName() {
      let sidebar = document.querySelector('.sidebar');
      sidebar.classList.toggle('active')
    }
  },
  mounted() {
    if (this.isLogin) {
      this.getUserInfo()
        .then(data => {
          this.userId = data.id
        })
    }

  },
}
</script>

<style lang="scss" scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 2;
  padding: 20px 8%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
header .logo {
  letter-spacing: 1px;
  font-size: 1.5rem;
  color: #ffffff;
  cursor: pointer;
}

header .toggle {
  position: relative;
  z-index: 3;
}

header.sticky {
  background: #F5F5F6;
  transition: 0.3s;
}

header.sticky h2{
  color: #111111;
  transition: 0.3s;
}

header.sticky .navbar-list-item{
  color: #111111;
  transition: 0.3s;
}

header.sticky .navbar-icon{
  color: #111111;
  transition: 0.3s;
}

.navbar-list {
  list-style: none;
  display: flex;
  color: #ffffff;
} 

.navbar-list-item {
  margin: 0 30px;
  font-size: 18px;
  cursor: pointer;
}

.sidebar {
  position: fixed;
  // z-index: 3;
  top: 0;
  right: -60%;
  width: 60%;
  height: 100%;
  background-color: #efefef;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.5s;
}

.sidebar.active {
  right: 0;
}

.sidebar ul {
  list-style: none;
}

.sidebar ul li {
  font-size: 1.5rem;
  font-weight: 500;
  margin: 30px 0;
}

.sidebar ul li:hover {
  color: #ffffff;
}

.navbar-link {
  cursor: pointer;
}

.navbar-icon:focus {
  outline: none;
}
</style>