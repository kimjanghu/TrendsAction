<template> 
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      persistent
      max-width="500px"
    >
      <v-card>
        <v-card-title>
          <span class="edit-board-title">Edit Board</span>
          <v-spacer></v-spacer>
          <v-btn
            @click="changeDialog"
          >
            <v-icon>
              mdi-close
            </v-icon>
          </v-btn>
        </v-card-title>

        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" class="d-flex">
                <v-text-field
                  label="보드 이름 수정"
                  v-model="editName"
                  required
                ></v-text-field>
                <v-btn
                  class="ma-2 white--text teal lighten-2"
                  @click="changeBoardName"
                >
                  변경
                </v-btn>
              </v-col>
              <v-col cols="12" class="d-flex">
                <v-text-field
                  label="사용자 이메일 검색"
                  v-model="email"
                  required
                ></v-text-field>
                <v-btn
                  class="ma-2 white--text teal lighten-2"
                  @click="searchUser"
                >
                  검색
                </v-btn>
              </v-col>
              <v-col cols="12" class="d-flex justify-center align-center">
                <div v-if="isLoading">
                  <Loading />
                </div>
                <div v-if="!isResult && !isLoading">
                  <p>검색 결과가 없습니다.</p>
                </div>
                <div v-if="isResult && !isLoading" class="d-flex justify-center flex-column align-center">
                  <p>닉네임: {{ searchResult.nickname }}</p>
                  <p>이메일: {{ searchResult.email }}</p>
                  <!-- <button class="add-btn" @click="sendInvite('host')">
                    <span class="add-name">Host로 추가하기</span>
                  </button> -->
                  <button class="add-btn" @click="sendInvite('guest')">
                    <span class="add-name">Guest로 추가하기</span>
                  </button>
                </div>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapGetters } from 'vuex'
import Loading from '@/components/common/Loading'

export default {
  name: 'BoardEditForm',
  data() {
    return {
      isResult: false,
      isLoading: false,
      editName: '',
      email: '',
      searchResult: null
    }
  },
  components: {
    Loading
  },
  props: {
    boardInfo: {
      type: Object
    },
    dialog: {
      type: Boolean
    },
  },
  computed: {
    ...mapGetters('userStore', ['config'])
  },
  methods: {
    changeDialog() {
      this.email = ''
      this.isResult = false
      this.$emit('change-dialog', false)
    },
    sendInvite(authority) {
      const inviteInfo = {
        boardId: this.$route.params.boardId,
        sendFrom: +window.localStorage.getItem('userId'),
        sendTo: this.searchResult.userId,
        authority: authority
      }
      this.$emit('invite-info', inviteInfo)
    },
    searchUser() {
      this.isLoading = true
      const searchData = {
        email: this.email
      }
      this.$http.post(this.$api.URL + this.$api.ROUTES.boards.searchUser, searchData, this.config)
      
        .then(res => {
          if (res.data.message == '해당하는 유저가 없습니다.') {
            this.isResult = false
            return
          }
          this.isResult = true
          this.searchResult = res.data.data
        })
        .then(() => {
          this.isLoading = false
          this.email = ''
        })
        .catch(err => {
          console.log(err)
        })
    },
    changeBoardName() {
      const boardData = {
        boardId: +this.$route.params.boardId,
        name: this.editName
      }
      this.editName = ''
      this.$emit('change-info', boardData)
    }
  },
  mounted() {
    this.editName = this.boardInfo.boardName
  },
}
</script>

<style lang="scss" scoped>
.edit-board-title {
  font-size: 20px;
}

.add-btn {
  margin-top: 10px;
  padding: 5px;
  border: 3px solid #000;
  border-radius: 10px;

  .add-name {
    font-weight: bold;
  }
}
</style>