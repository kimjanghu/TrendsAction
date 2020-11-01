<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="400px"
    >
      <v-card>
        <v-card-title>
          <span class="text-center mx-auto mt-7">TrendsAction에 오신것을 환영합니다.</span>
          
        </v-card-title>
        <v-card-text>
          <p class="text-center">소셜로그인으로 로그인하기</p>
          <a :href="kakaoLoginLink" alt="kakao-login">
            <div>
              <img @click="changeDialog" class="kakao-login-img" alt="kakao logo" src="@/assets/kakao/kakao_login_large_wide.png" />
            </div>
          </a>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
            text
            @click="changeDialog"
          >
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
// import GoogleLogin from 'vue-google-login'

export default {
  name: 'UsersLoginForm',
  data() {
    return {
      client_id: process.env.VUE_APP_KAKAO_KEY,
      redirect_uri: 'http://localhost:3000/oauth/kakao'
    }
  },
  props: {
    dialog: {
      type: Boolean
    }
  },
  components: {

  },
  computed: {
    kakaoLoginLink() {
      return `https://kauth.kakao.com/oauth/authorize?client_id=${this.client_id}&redirect_uri=${this.redirect_uri}&response_type=code`
    },
  },
  methods: {
    changeDialog() {
      this.$emit('change-dialog', false)
    },
  }
}
</script>

<style lang="scss" scoped>
.kakao-login-img {
  width: 100%;
  height: 50px;
}
</style>