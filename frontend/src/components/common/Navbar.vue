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
        <li class="navbar-list-item" @click="$router.push({ name: $constants.URL_TYPE.PREDICT.LIST })">Predict</li>
        <li class="navbar-list-item" @click="dialog = true" v-if="!isLogin">
          Login
          <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
        </li>
        <li class="navbar-list-item" @click="logout()" v-if="isLogin">Logout</li>
      </ul>
    </div>
    <div v-else>
      <span @click.stop="drawer = !drawer" style="color: #ffffff; font-size: 1.5rem; cursor:pointer;">
        <i class="fas fa-bars navbar-icon"></i>
      </span>
    </div>
    
    <!-- <div class="sidebar">
      <ul>
        <li @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Trend</li>
        <li @click="$router.push({ name: $constants.URL_TYPE.PREDICT.LIST })">Predict</li>
        <li @click="dialog = true" v-if="!isLogin">
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
    </div> -->

      
    <!-- <v-tab
      class="navbar-link"
      @click="logout()" 
      v-if="isLogin"
    >
      Logout
    </v-tab>
    <div @click="dialog = true" v-if="isLogin" class="navbar-icon">
      <router-link
        tag="button"
        class="router-btn"
        :to="{ name: 'UserProfile' }"
      >
        <v-icon class="mr-3">
          mdi-account-circle
        </v-icon>
      </router-link>
      <v-icon>
        mdi-bell
      </v-icon>
    </div> -->
  </header>
    <!-- Side bar -->
    <v-navigation-drawer
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
    </v-navigation-drawer>

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
      header.classList.toggle('sticky', window.scrollY > 800)
    },
    goToHome() {
      this.$router.push('/')
        .catch(err => {
          if(err.name != "NavigationDuplicated" ){
            throw err
          }
        })
    },
  },
  mounted() {
    if (this.isLogin) {
      this.getUserInfo()
        .then(data => {
          this.userId = data.id
          console.log(data)
        })
    }

  }
}
</script>

<style lang="scss" scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 2;
  padding: 20px 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
header .logo {
  letter-spacing: 1px;
  font-size: 24px;
  color: #ffffff;
  cursor: pointer;
}

header .toggle {
  position: relative;
  z-index: 3;
}

header.sticky {
  background: #ffffff;
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
  z-index: 3;
  top: 0;
  right: -40%;
  width: 40%;
  height: 100%;
  background-color: #999999;
  display: flex;
  justify-content: center;
  align-items: center;
}

.sidebar ul {
  list-style: none;
}

.sidebar li {
  font-size: 1.5rem;
  margin: 30px 0;
}

.navbar {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 100px;
  width: 85%;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  
  .router-area {
    display: flex;
    align-items: center;

    .router-name {
      cursor: pointer;
      color: #01579b;
      margin: 0 20px;
    }

    .router-name:hover {
      font-weight: bold;
    }

    .navbar-icon {
      display: flex;
      align-items: center;

      .router-btn:focus {
        outline: none;
      }
    }
  }
}

.navbar-link {
  cursor: pointer;
}
</style>