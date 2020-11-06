<template>
<v-container class="pt-0">
  <v-sheet
    class="px-5 py-2"
    color="grey lighten-3"
  >
    <v-row>
      <v-col cols="12" md="12">
        <section class="profile-area d-flex">
          <v-file-input
            show-size
            counter
            label="프로필 사진을 등록해주세요."
            v-model="file"
          ></v-file-input>

          <v-btn
            :loading="loading3"
            :disabled="loading3"
            class="ma-2 white--text teal lighten-2"
            @click="loader = 'loading3'; uploadFile();"
          >
            사진등록
          </v-btn>
        </section>
        
        <section class="nickname-area d-flex">
          <v-text-field v-model="nickname" label="닉네임"></v-text-field>
          <v-btn
            class="ma-2 white--text teal lighten-2"
            @click="checkNickname();"
          >
            중복확인
          </v-btn>
        </section>

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
    <v-btn 
      dark 
      class="mt-2 custom-rounded mr-3" 
      color="teal lighten-2" 
      @click="editUserInfo"
    >수정</v-btn>
  </v-row>
</v-container>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'

export default {
  data() {
    return {
      userId: '',
      nickname: '',
      isNickname: false,
      select: [],
      loader: null,
      loading3: false,
      items: [
        '비즈니스/소비', 
        '일상', 
        '문화'
      ],
      // categoryItems: {
      //   '비즈니스/소비': 1,
      //   '일상': 2, 
      //   '문화': 3
      // },
      file: null
    }
  },
  watch: {
    loader () {
      const l = this.loader
      this[l] = !this[l]

      setTimeout(() => (this[l] = false), 3000)

      this.loader = null
    },
  },
  computed: {
    ...mapState('userStore', ['userInfo']),
    ...mapGetters('userStore', ['config'])
  },
  methods: {
    ...mapActions('userStore', ['getUserInfo']),
    uploadFile() {
      if (!this.file) {
        alert('프로필 사진을 업로드해주세요.')
        return
      }
      const fileData = new FormData()
      fileData.append('file', this.file)

      this.$http.post(this.$api.URL + this.$api.ROUTES.accounts.uploadProfilePicture + `/${this.userId}`, fileData, this.config)
        .then(() => {
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
      this.file = null
    },
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
        // profile: 'https://trendsaction.s3.ap-northeast-2.amazonaws.com/2020-11-02T06%3A32%3A23.313_Male-Avatar.jpg'
      }
      console.log(userInfo)
      this.$http.put(this.$api.URL + this.$api.ROUTES.accounts.user, userInfo, this.config)
        .then(res => {
          console.log(res)
          window.location.reload()
        })
        .catch(err => {
          console.log(err.response)
        })
    },
  },
  created() {
    this.getUserInfo()
      .then(data => {
        this.userId = data.id
        this.nickname = data.nickname
        this.select = data.categoryName
      })
  },
  mounted() { 
  }
}
</script>

<style scoped>
.custom-loader {
  animation: loader 1s infinite;
  display: flex;
}
@-moz-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-webkit-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@-o-keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
@keyframes loader {
  from {
    transform: rotate(0);
  }
  to {
    transform: rotate(360deg);
  }
}
</style>