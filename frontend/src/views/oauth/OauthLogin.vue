<template>
  <div>
    로그인 중입니다.
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'OauthLogin',
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

<style>

</style>