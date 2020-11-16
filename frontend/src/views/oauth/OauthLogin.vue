<template>
  <div class="login-area">
    <Loading />
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
    this.login(this.$route.query.code)
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