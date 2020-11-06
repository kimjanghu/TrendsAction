<template>
  <div class="login-area">
    <Loading />
    <!-- <v-progress-circular
      :size="70"
      :width="7"
      color="#2c3e50"
      class="loading"
      indeterminate
    ></v-progress-circular> -->
    <p class="loading-title">로그인 중입니다...</p>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import Loading from '@/components/common/Loading'

export default {
  name: 'OauthLogin',
  components: {
    Loading
  },
  methods: {
    ...mapActions('userStore', ['login']),
  },
  created() {
    // console.log(this.$route.query.code)
    this.login(this.$route.query.code)
    // this.$api.URL + this.$api.ROUTES.accounts.kakaoLogin
    // http://k3a408.p.ssafy.io:9090/users
    // const config = {
    //   headers: {
    //     token: Bearer this.$cookies.get('auth-token')
    //   }
    // }
    // this.$http.get(`http://k3a408.p.ssafy.io:9090/users/login/kakao`, config, {
    //   params: {
    //     code: this.$route.query.code
    //   }
    // })
    //   .then(res => {
    //     console.log(res.data.token)
    //     console.log(res.data.nickname)
    //   })
    //   .catch(err => {
    //     console.log(err)
    //   })
  }
}
</script>

<style scoped>
.login-area {
  position: fixed;
  top: 45%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  align-items: center;
}

.loading-title {
  margin-top: 20px;
  font-weight: bold;
}
/* .loading {
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
} */
</style>