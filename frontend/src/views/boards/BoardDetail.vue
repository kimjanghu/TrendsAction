<template>
  <div>
    <Navbar />
    <div class="board-header">
      <img v-if="boardInfo" :src="boardInfo.coverImage" alt="" class="board-header-img">
      <section class="board-info">
        <div class="user-info" v-if="boardInfo">
          <p class="board-title">{{ boardInfo.boardName }}</p>
          <button
            class="board-btn"
          >
            <p @click="memberDialog = true">참여중인 멤버 {{ hosts.length + maintainers.length + guests.length }}명</p>
            <BoardsMember 
              :dialog="memberDialog"
              @change-member-dialog="changeMemberDialog"
            />
          </button>
          
          <div v-if="authority === 'host'" class="mt-10">
            <button
              class="board-btn"
            >
              <p class="board-btn-text" @click="dialog = true">보드편집</p>
              <BoardsEditForm 
                :dialog="dialog"
                :boardInfo="boardInfo"
                @change-dialog="changeDialog" 
                @invite-info="sendInvite"
                @change-info="changeBoardName"
              />
            </button>
            <button
              class="board-btn"
              @click="deleteBoard"
            >
              <p class="board-btn-text">보드삭제</p>
            </button>
            <p 
              class="cover-img-btn"
              @click="$refs.inputUpload.click()"
            >커버이미지 변경</p>
            <input v-show="false" ref="inputUpload" type="file" @change="uploadFile">
          </div>
          <div v-else class="mt-10">
            <button
              class="board-btn"
              @click="asyncRemoveMember"
            >
              <p class="board-btn-text">보드탈퇴</p>
            </button>
          </div>
        </div>
      </section>
    </div>

    <v-container class="mt-10 mb-16">
      <v-sheet
        class="px-10 py-10 mt-3 neumor-design"
      > 
        <v-row>
          <v-col cols="12" md="6" lg="4" v-for="(content, idx) in contents" :key="idx">
            <div v-if="content.newsId">
              <v-card
                class="mx-auto"
                max-width="400"
                min-height="300"
                max-height="400"
                target="_blank"
              >
                <v-responsive :aspect-ratio="4/3">
                  <span v-if="authority === ('host' || 'maintainer')" class="contents-close-btn" style="position: absolute; top: 5px; right: 7px; z-index: 1;">
                    <i class="fas fa-times" @click="deleteNews(content.newsId)"></i>
                  </span>
                  <v-img
                    :aspect-ratio="4/3"
                    class="white--text align-end"
                    height="220px"
                    :src="content.thumbnail"
                  >
                    <v-card-title></v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    {{ content.title }}
                  </v-card-subtitle>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn :href="content.link">기사 보기</v-btn>
                  </v-card-actions>
                </v-responsive>
              </v-card>
            </div>



            <div v-if="content.twitterId">
              <v-card
                class="mx-auto"
                color="#26c6da"
                dark
                max-width="400"
                min-height="300"
                max-height="400"
              >
                <v-responsive :aspect-ratio="4/3">
                  <span v-if="authority === ('host' || 'maintainer')" class="contents-close-btn" style="position: absolute; top: 5px; right: 7px; z-index: 1;">
                    <i class="fas fa-times" @click="deleteTwitter(content.twitterId)"></i>
                  </span>
                  <v-card-title>
                    <v-icon
                      large
                      left
                    >
                      mdi-twitter
                    </v-icon>
                    <span class="title font-weight-light">Twitter</span>
                  </v-card-title>
                  
                  <v-card-text class="headline font-weight-bold">
                    {{ content.content }}
                  </v-card-text>

                  <v-card-actions>
                    <v-list-item class="grow">
                      <v-list-item-avatar color="grey darken-3">
                        <v-img
                          class="elevation-6"
                          alt=""
                          src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                        ></v-img>
                      </v-list-item-avatar>

                      <v-list-item-content>
                        <v-list-item-title>Evan You</v-list-item-title>
                      </v-list-item-content>

                      <v-row
                        align="center"
                        justify="end"
                      >
                        <v-icon class="mr-1">
                          mdi-heart
                        </v-icon>
                        <span class="subheading mr-2">256</span>
                        <span class="mr-1">·</span>
                        <v-icon class="mr-1">
                          mdi-share-variant
                        </v-icon>
                        <span class="subheading">45</span>
                      </v-row>
                    </v-list-item>
                  </v-card-actions>
                </v-responsive>
              </v-card>
            </div>
          </v-col>
        </v-row>
      </v-sheet>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
import BoardsEditForm from '@/components/boards/BoardsEditForm'
import BoardsMember from '@/components/boards/BoardsMember'
import Navbar from '@/components/common/Navbar'

export default {
  name: 'BoardDetail',
  components: {
    BoardsEditForm,
    BoardsMember,
    Navbar,
  },
  data() {
    return {
      nickname: '',
      profile: '',
      dialog: false,
      memberDialog: false
    }
  },
  computed: {
    ...mapGetters('userStore', ['config']),
    ...mapState('boardStore', [
      'boardInfo', 
      'contents', 
      'hosts',
      'maintainers',
      'guests',
      'authority'
    ]),
  },
  methods: {
    ...mapActions('userStore', ['getUserInfo']),
    ...mapActions('boardStore', [
      'getUserBoard',
      'getBoardMember',
      'getUserAuthority',
      'removeMember'
    ]),
    changeDialog(dialog) {
      this.dialog = dialog
    },
    changeMemberDialog(dialog) {
      this.memberDialog = dialog
    },
    deleteNews(newsId) {
      const boardId = this.$route.params.boardId

      const check = confirm('컨텐츠를 삭제하시겠습니까?')
      if (check) {
        this.$http.delete(this.$api.URL + this.$api.ROUTES.boards.addNews + `/${boardId}` + `/${newsId}`, this.config)
          .then(() => {
            this.getUserBoard(boardId)
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    deleteTwitter(twitterId) {
      const boardId = this.$route.params.boardId

      const check = confirm('컨텐츠를 삭제하시겠습니까?')
      if (check) {
        this.$http.delete(this.$api.URL + this.$api.ROUTES.boards.addTwitter + `/${boardId}` + `/${twitterId}`, this.config)
          .then(() => {
            this.getUserBoard(boardId)
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    moveMypage(userId) {
      alert('보드에서 탈퇴하였습니다.')
      this.$router.push({ name: 'BoardList', params: { id: userId }})
    },
    async asyncRemoveMember() {
      const removeData = {
        userId: window.localStorage.getItem('userId'),
        boardId: +this.$route.params.boardId
      }
      const check = confirm('보드를 탈퇴 하시겠습니까?')

      if (check) {
        await this.removeMember(removeData)
        await this.moveMypage(removeData.userId)
      }
    },
    deleteBoard() {
      const boardId = this.$route.params.boardId
      const userId = +window.localStorage.getItem('userId')
      const check = confirm('보드를 삭제하시겠습니까?')

      if (check) {
        this.$http.delete(this.$api.URL + this.$api.ROUTES.boards.addNewBoard + `/${boardId}`, this.config)
          .then(() => {
            alert('보드가 삭제되었습니다.')
            this.$router.push({ name: 'BoardList', params: { id: userId }})
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    sendInvite(inviteData) {
      this.$http.post(this.$api.URL + this.$api.ROUTES.boards.sendInvite, inviteData, this.config)
        .then(() => {
          alert('초대 메시지를 보냈습니다.')
          this.dialog = false
        })
        .catch(err => {
          console.log(err)
        })
    },
    uploadFile() {
      const file = this.$refs.inputUpload.files[0]
      const fileData = new FormData()
      fileData.append('file', file)
      
      const boardId = this.$route.params.boardId
      this.$http.post(this.$api.URL + this.$api.ROUTES.boards.setBoardCover + `/${boardId}`, fileData, this.config)
        .then(() => {
          window.location.reload()
        })
        .catch(err => {
          console.log(err)
        })
    },
    changeBoardName(boardData) {
      this.$http.put(this.$api.URL + this.$api.ROUTES.boards.addNewBoard, boardData, this.config)
        .then(() => {
          this.boardName = this.editName
          this.dialog = false
          alert('보드 이름이 변경되었습니다.')
        })
        .catch(err => {
          console.log(err)
        })
    },
  },
  created() {
    this.getUserInfo()
      .then(data => {
        this.nickname = data.nickname
        this.userEmail = data.email
        this.profile = data.profile
      })
    this.getUserBoard(this.$route.params.boardId)
      .then(res => {
        this.editName = res.name
      })
    this.getBoardMember(this.$route.params.boardId)
    this.getUserAuthority(this.$route.params.boardId)
  },
}
</script>

<style lang="scss" scoped>
.board-title {
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 5px;
}

.board-header {
  position: relative;
  height: 70vh;

  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
  }

  .board-header-img {
    width: initial;
    max-width: none;
    min-width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    height: 100%;
    object-fit: auto;
  }
  
  .board-info {
    z-index: 1;
    // background: rgba(0, 0, 0, 0.4);
    position: absolute;
    color: #fff;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    // border: 3px solid #000;
    // border-radius: 10px;
    padding: 20px 50px 0;

    .user-info {
      margin: 20px 0;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }

    .cover-img-btn {
      cursor: pointer;
      font-size: 0.5rem;
      border-radius: 10px;
      margin-top: 10px;
      padding: 3px 0;
      text-align: center;

      &:hover {
        background-color: white;
        color: black;
        transition: 0.3s;

      }
    }
  }
}

.news-title-area {
  width: 100%;
  height: 100px;
  border-top: 1px solid #000;

  .news-title {
    margin: 0 10px;
    position: absolute;
    bottom: 0;
  }
}

// .board-btn {
//   border: 2px solid #000;
//   border-radius: 10px;
//   margin: 2px 0;
//   padding: 4px 7px ;
//   outline: 0;

//   &:hover {
//     background-color: #fff;
//     color: #000;
//     transition: .4s;
//   }

//   .board-btn-text {
//     margin: 0;
//     font-size: 13px;
//   }
// }

.board-btn {
  border-radius: 10px;
  outline: 0;
  margin-bottom: 10px;
  p {
    margin: 3px 15px;
    font-size: 0.8rem;
  }
}

.board-btn:hover {
  background-color: white;
  color: black;
  transition: 0.3s;
}

.contents-close-btn {
  padding: 2px 7px;
  cursor: pointer;
  border-radius: 100%;
}

.contents-close-btn:hover {
  color: #fff;
  padding: 2px 7px;
  border-radius: 100%;
  background-color: black;
  opacity: 0.3;
}

.neumor-design {
  padding: 2%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}
</style>