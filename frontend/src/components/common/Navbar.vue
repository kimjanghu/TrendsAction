<template>
  <div class="navbar transparent">
    <div class="web-title">
      <h2
        class="navbar-link"
        @click="$router.push('/')"
      >
        TrendsAction
      </h2>
    </div>
    <div class="router-area" v-if="$vuetify.breakpoint.mdAndUp">
      <v-tabs align-with-title>
        <v-tab
          class="navbar-link"
          @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })"
        >
          Trend
        </v-tab>
        <v-tab
          class="navbar-link"
          @click="$router.push({ name: $constants.URL_TYPE.PREDICT.LIST })"
        >
          Predict
        </v-tab>
        <v-tab 
          class="navbar-link"
          @click="dialog = true" 
          v-if="!isLogin"
        >
          Login
          <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
        </v-tab>
        <v-tab
          class="navbar-link"
          @click="dialog = true" 
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
        </div>
      </v-tabs>
    </div>
    <div v-if="$vuetify.breakpoint.smAndDown">
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
    </div>

    <!-- Side bar -->
    <v-navigation-drawer
      v-model="drawer"
      absolute
      right
      temporary
    >
      <!-- 로그인 false -->
      <v-list-item v-if="!isLogin" class="avatar-info">
        <!-- 익명 이미지 -->
        <v-list-item-avatar>
          <v-img src="mdi-account"></v-img>
        </v-list-item-avatar>
        <!-- 비로그인 유저 안내 -->
        <v-list-item-content>
          <v-list-item-title>로그인이 필요합니다.</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <!-- 로그인 true -->
      <v-list-item v-if="isLogin" class="avatar-info">
        <!-- 유저 프로필 이미지 -->
        <v-list-item-avatar>
          <v-icon>mdi-menu-down</v-icon>
        </v-list-item-avatar>

        <!-- 유저 닉네임 -->
        <v-list-item-content>
          <v-list-item-title>메뉴 리스트</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <!-- 하단 구분선 -->
      <v-divider></v-divider>

      <!-- 사이드바 메뉴 리스트 -->
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
// import { mapGetters } from 'vuex'

export default {
  name: 'Navbar',
  data() {
    return {
      dialog: false,
      isLogin: true,
      drawer: false,
      group: null
    }
  },
  components: {
    UsersLoginForm
  },
  computed: {
    // ...mapGetters('userStore', ['isLogin'])
  },
  watch: { 
    group () {
      this.drawer = false
    },
  },
  methods: {
    changeDialog(dialog) {
      this.dialog = dialog
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 100px;
  width: 85%;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: rgba(255, 255, 255, 0.5);
  
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