<template>
  <div>
    <div class="board-header">
      <img :src="coverImage" alt="" class="board-header-img">
      <section class="board-info">
        <v-avatar
          color="#efefef"
          size="200"
        >
          <img
            :src="profile"
          >
        </v-avatar>
        <div class="user-info">
          <h1>{{ boardName }}</h1>
          <p>참여중인 멤버 {{ hosts.length + guests.length }}명</p>
          <v-row justify="center">
            <v-dialog
              v-model="dialog"
              persistent
              max-width="600px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                  color="primary"
                  dark
                  v-bind="attrs"
                  v-on="on"
                >
                  보드편집
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="edit-board-title">Edit Board</span>
                  <v-spacer></v-spacer>
                  <v-btn
                    @click="dialog = false; closeModal();"
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
                          <button class="add-btn">
                            <span class="add-name">Host로 추가하기</span>
                          </button>
                          <button class="add-btn">
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
          <p 
            class="cover-img-btn"
            @click="$refs.inputUpload.click()"
          >커버이미지 변경</p>
          <input v-show="false" ref="inputUpload" type="file" @change="uploadFile">
        </div>
      </section>
    </div>
    <v-container>
      <v-sheet
        class="px-5 py-2 mt-3"
        color="white"
      >
        <v-row>
          <v-col cols="12" md="6" lg="4" v-for="(content, idx) in contents" :key="idx">
            <div v-if="content.newsId">
              <v-card
                class="mx-auto"
                max-width="400"
              >
                <v-responsive :aspect-ratio="4/3">
                  <v-img
                    class="white--text align-end"
                    height="220px"
                    src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
                  >
                    <v-card-title>Top 10 Australian beaches</v-card-title>
                  </v-img>

                  <v-card-subtitle class="pb-0">
                    Number 10
                  </v-card-subtitle>

                  <v-card-text class="text--primary">
                    <div>Whitehaven Beach</div>

                    <div>Whitsunday Island, Whitsunday Islands</div>
                  </v-card-text>
                </v-responsive>
              </v-card>
            </div>
            <div v-if="content.twitterId">
              <v-card
                class="mx-auto"
                color="#26c6da"
                dark
                max-width="400"
                height="320"
                max-height="320"
              >
                <v-responsive :aspect-ratio="4/3">
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




              <!-- <v-card
                color="#26c6da"
              >
                <v-responsive :aspect-ratio="4/3">
                  <v-card-title
                  >
                    <v-icon
                      large
                      left
                    >
                      mdi-twitter
                    </v-icon>
                    <span class="title font-weight-light">Twitter</span>
                  </v-card-title>
                </v-responsive>
              </v-card> -->
            </div>
          </v-col>
        </v-row>
      </v-sheet>
    </v-container>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import Loading from '@/components/common/Loading'

export default {
  name: 'BoardDetail',
  data() {
    return {
      nickname: '',
      profile: '',
      dialog: false,
      contents: null,
      boardName: '',
      editName: '',
      coverImage: '',
      email: '',
      isResult: false,
      searchResult: null,
      hosts: [],
      guests: [],
      isLoading: false
    }
  },
  components: {
    Loading
  },
  computed: {
    ...mapGetters('userStore', ['config'])
  },
  methods: {
    ...mapActions('userStore', ['getUserInfo']),
    closeModal() {
      this.editName = this.boardName
      this.email = ''
    },
    uploadFile() {
      const file = this.$refs.inputUpload.files[0]
      const fileData = new FormData()
      fileData.append('file', file)
      
      const boardId = this.$route.params.boardId
      this.$http.post(this.$api.URL + this.$api.ROUTES.boards.setBoardCover + `/${boardId}`, fileData, this.config)
        .then(() => {
          this.getUserBoard()
        })
        .catch(err => {
          console.log(err)
        })
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
            this.isLoading = false
            this.email = ''
            return
          }
          this.isResult = true
          this.email = ''
          this.searchResult = res.data.data
          this.isLoading = false
        })
        .catch(err => {
          console.log(err)
        })
    },
    getBoardMember() {
      const boardId = this.$route.params.boardId
      this.$http.get(this.$api.URL + this.$api.ROUTES.boards.getBoardMember + `/${boardId}`, this.config)
        .then(res => {
          const boardMembers = res.data.data
          boardMembers.forEach(el => {
            if (el.authority === 'host') {
              this.hosts.push({
                nickname: el.nickname,
                profile: el.profile
              })
            } else {
              this.guests.push({
                nickname: el.nickname,
                profile: el.profile
              })
            }
          })
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
      this.$http.put(this.$api.URL + this.$api.ROUTES.boards.addNewBoard, boardData)
        .then(res => {
          console.log(res)
          this.boardName = this.editName
          this.dialog = false
          this.editName = ''
          alert('보드 이름이 변경되었습니다.')
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserBoard() {
      const boardId = this.$route.params.boardId
      this.$http.get(this.$api.URL + this.$api.ROUTES.boards.getBoardContent + `/${boardId}`, this.config)
        .then(res => {
          let arr = []
          if (res.data.data.newsList) {
            arr = arr.concat(res.data.data.newsList)
          }

          if (res.data.data.twittList) {
            arr = arr.concat(res.data.data.twittList)
          }
          this.contents = arr
          return res.data.data
        })
        .then(data => {
          this.boardName = data.name
          this.editName = data.name
          if (data.thumbnail) {
            this.coverImage = data.thumbnail
            return
          }
          this.coverImage = 'https://source.unsplash.com/random'
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created() {
    this.getUserInfo()
      .then(data => {
        this.nickname = data.nickname
        this.userEmail = data.email
        if (data.profile) {
          this.profile = data.profile
          return
        }
        this.profile = 'https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light'
      })
    this.getUserBoard()
    this.getBoardMember()
  },
}
</script>

<style lang="scss" scoped>
.board-header {
  position: relative;
  height: 500px;

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
    background: rgba(0, 0, 0, 0.4);
    position: absolute;
    color: #fff;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border: 3px solid #000;
    border-radius: 10px;
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
      font-size: 2px;
      margin: 10px 0 0 0;
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