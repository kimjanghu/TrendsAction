<template>
  <div class="navbar transparent">
    <div class="web-title">
      <h2>TrendsAction</h2>
    </div>
    <div class="router-area" v-if="$vuetify.breakpoint.smAndUp">
      <v-tabs align-with-title>
        <v-tab>Trend</v-tab>
        <v-tab>Predict</v-tab>
        <v-tab @click="dialog = true" v-if="!isLogin">
          Login
          <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
        </v-tab>
        <v-tab @click="dialog = true" v-if="isLogin">
          Logout
        </v-tab>
        <div @click="dialog = true" v-if="isLogin" class="navbar-icon">
          <v-icon class="mr-3">
            mdi-account-circle
          </v-icon>
          <v-icon>
            mdi-bell
          </v-icon>
        </div>
      </v-tabs>
    </div>
    <div v-if="$vuetify.breakpoint.xsOnly">
      햄버거
    </div>
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
      isLogin: true
    }
  },
  components: {
    UsersLoginForm
  },
  computed: {
    // ...mapGetters('userStore', ['isLogin'])
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
    }
  }
}
</style>