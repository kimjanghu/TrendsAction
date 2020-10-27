<template>
  <div>
  </div>
</template>

<script>
export default {
  name: 'OauthLogin',
  created() {
    this.$http.post(this.$api.ROUTES.accounts.URL + this.$api.ROUTES.accounts.kakaoLogin, this.$route.query)
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