<template>
<v-container style="padding-top: 8%" class="px-5 neumor-design">
  <v-row justify="center" align="center" class="mt-3 pb-0">
    <v-col cols="12" md="10" class="py-0">
      <v-text-field disabled filled v-model="email" label="이메일"></v-text-field>
    </v-col>
  </v-row>
  <v-row justify="center" align="center">
    <v-col cols="12" md="10" class="py-0">
      <v-row>
        <v-col cols="12" lg="10" class="py-0">
          <v-text-field filled v-model="nickname" label="닉네임"></v-text-field>
        </v-col>
        <v-col cols="12" lg="2" class="py-0">
          <v-btn
            width="100%"
            height="50px"
            class="grey lighten-2"
            @click="checkNickname();"
          >
            중복확인
          </v-btn>
        </v-col>
      </v-row>        
    </v-col>
  </v-row>
  <v-row justify="center" align="center">
    <v-col cols="12" lg="10" class="pb-0">
      <v-combobox
          v-model="select"
          :items="items"
          label="관심 카테고리"
          multiple
          filled
        ></v-combobox>
    </v-col>
  </v-row>
  <v-row justify="center" align="center">
    <v-col cols="12" lg="10" class="py-0">
      <v-row>
        <v-col cols="12" lg="10" class="py-0">
          <v-file-input
            show-size
            counter
            filled
            label="사진을 등록해주세요."
            v-model="file"
          ></v-file-input>
        </v-col>
        <v-col cols="12" lg="2" class="py-0">
          <v-btn
          :loading="loading3"
          :disabled="loading3"
          width="100%"
          height="50px"
          class="grey lighten-2"
          @click="loader = 'loading3'; uploadFile();"
          >
            사진등록
          </v-btn>
        </v-col>
      </v-row>
    </v-col>
  </v-row>

  <v-row justify="end">
    <v-btn 
      dark
      width="20%"
      height="40px"
      class="mt-5 mb-5 mr-8 custom-rounded mr-3" 
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
      email: '',
      isNickname: false,
      select: [],
      loader: null,
      loading3: false,
      items: [
        '비즈니스/소비', 
        '일상', 
        '문화',
        'IT과학',
        '사회',
        '세계'
      ],
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
      }
      this.$http.put(this.$api.URL + this.$api.ROUTES.accounts.user, userInfo, this.config)
        .then(() => {
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
        this.email = data.email
        this.nickname = data.nickname
        this.select = data.categoryName
      })
  },
  mounted() { 
  }
}
</script>

<style scoped>
.neumor-design {
  padding: 2%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}

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