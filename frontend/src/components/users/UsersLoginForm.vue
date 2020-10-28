<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="500px"
    >
      <v-card>
        <v-card-title>
          <span class="headline">Login</span>
        </v-card-title>
        <v-card-text>
          <a :href="kakaoLoginLink" alt="kakao-login">
            <img alt="kakao logo" src="@/assets/kakao/kakao_login_medium_wide.png" />
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
          <v-btn
            color="blue darken-1"
            text
            @click="changeDialog"
          >
            Save
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
      redirect_uri: 'http://localhost:3000/oauth/kakao',
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