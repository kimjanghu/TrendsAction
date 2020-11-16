<template>
  <div>
    <Navbar />
    <div class="container">
      <h1>👀</h1>
      <h1>관련된 콘텐츠가 없습니다.</h1>
      <v-btn class="mt-5" color="primary" @click="goToHome">메인으로</v-btn>
    </div>
  </div>
</template>

<script>
import Navbar from '@/components/common/Navbar2'

export default {
  name: "ErrorPage",
  components: { Navbar },
  methods: {
    goToHome() {
      this.$router.push('/')
        .catch(err => {
          if(err.name != "NavigationDuplicated" ){
            throw err
          }
        })
    },
  }

}
</script>

<style scoped>
.container {
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

</style>