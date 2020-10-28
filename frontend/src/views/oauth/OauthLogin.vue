<template>
  <div>
  </div>
</template>

<script>
export default {
  name: 'OauthLogin',
  created() {
    console.log(this.$route.query.code)
    // this.$api.URL + this.$api.ROUTES.accounts.kakaoLogin
    // http://k3a408.p.ssafy.io:9090/users
    this.$http.get(`http://k3a408.p.ssafy.io:9090/users/login/kakao?code=${this.$route.query.code}`)
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
  }
}
</script>

<style>

</style>