<template>
<v-container class="pt-0">
  <v-sheet
    class="px-5 py-2"
    color="grey lighten-3"
  >
    <v-row>
      <v-col cols="12" md="4">
        <v-text-field v-model="nickname" label="닉네임"></v-text-field>
        <button @click="checkNickname">중복확인</button>
        <v-combobox
          class="custom-rounded"
          v-model="select"
          :items="items"
          label="관심 카테고리"
          multiple
          outlined
        ></v-combobox>
      </v-col>
    </v-row>
  </v-sheet>

  <v-row justify="end">
    <v-btn dark class="mt-2 custom-rounded mr-3" color="teal" @click="editUserInfo">수정</v-btn>
  </v-row>
</v-container>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'

export default {
  data() {
    return {
      nickname: '',
      isNickname: false,
      select: [],
      items: [
        1, 2, 3, 4
      ],
    }
  },
  computed: {
    ...mapState('userStore', ['userInfo']),
    ...mapGetters('userStore', ['config'])
  },
  methods: {
    ...mapActions('userStore', ['getUserInfo']),
    checkNickname() {
      this.$http.get(this.$api.URL + this.$api.ROUTES.accounts.checkNickname + `/${this.nickname}`)
        .then(res => {
          if (res.data.status) {
            this.isNickname = true
          }
          alert(res.data.message)
        })
        .catch(err => {
          console.log(err)
        })
    },
    editUserInfo() {
      const userInfo = {
        categoryList: this.select,
        email: this.userInfo.email,
        id: this.userInfo.id,
        nickname: this.nickname,
        profile: this.userInfo.profile
      }
      console.log(userInfo)
      console.log(this.config)
      this.$http.put(this.$api.URL + this.$api.ROUTES.accounts.user, userInfo, this.config)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created() {
    this.getUserInfo()
    setTimeout(() => {
      console.log(this.userInfo)
      this.nickname = this.userInfo.nickname
      this.select = this.userInfo.categoryList
    }, 500)
  },
  mounted() { 
  }
}
</script>

<style>

</style>