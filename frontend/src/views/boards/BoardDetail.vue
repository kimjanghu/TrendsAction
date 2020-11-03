<template>
<v-container>
  <div class="board-header">
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
        <h1>보드 이름</h1>
        <p>Host</p>
        <p>Guest</p>
        

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
                <span class="headline">보드 편집하기</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="보드 이름 수정"
                        required
                      ></v-text-field>
                      <v-btn
                        class="ma-2 white--text teal lighten-2"
                      >
                        변경하기
                      </v-btn>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="사용자 이름 검색"
                        required
                      ></v-text-field>
                      <v-btn
                        class="ma-2 white--text teal lighten-2"
                      >
                        변경하기
                      </v-btn>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="dialog = false"
                >
                  Close
                </v-btn>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="dialog = false"
                >
                  Save
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>

      </div>      
    </section>
  </div>
  
  <v-sheet
    class="px-5 py-2 mt-3"
    color="white"
  >
    <v-row>
      <v-col cols="12" md="4" v-for="(content, idx) in contents" :key="idx">
        <!-- :height="$vuetify.breakpoint.smAndDown? 250:200"  -->
        <!-- :to="{name: 'BoardDetail',}" -->
        <v-card 
          color="white"
        >
          <v-responsive :aspect-ratio="4/3">
            <div v-if="content.newsId">
              <v-img
                :src="content.src"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="100%"
              >
                <v-card-title v-text="content.title"></v-card-title>
              </v-img>
            </div>
            <div v-if="content.twitterId">
              {{ content.author }}
              {{ content.content }}
            </div>
            
          </v-responsive>
        </v-card>
        
      </v-col>
      
    </v-row>
  </v-sheet>
</v-container>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'BoardDetail',
  data() {
    return {
      nickname: '',
      profile: '',
      dialog: false,
      cards: [
        { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
        { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
        { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
        { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
      ],
      contents: null
    }
  },
  methods: {
    ...mapActions('userStore', ['getUserInfo']),
    getUserBoard() {
      const boardId = this.$route.params.boardId
      console.log(boardId)
      this.$http.get(this.$api.URL + this.$api.ROUTES.boards.getBoardContent + `/${boardId}`)
        .then(res => {
          let arr = []
          if (res.data.data.newsList) {
            arr = arr.concat(res.data.data.newsList)
          }

          if (res.data.data.twittList) {
            arr = arr.concat(res.data.data.twittList)
          }
          this.contents = arr
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
        if (data.profile) {
          this.profile = data.profile
        } else {
          this.profile = 'https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light'
        }
      })
    this.getUserBoard()
  },
}
</script>

<style lang="scss" scoped>
.board-header {
  position: relative;
  height: 500px;
  
  .board-info {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    // display: flex;
    // flex-direction: column;
    // align-items: center;
    // justify-content: center;

    .user-info {
      margin: 30px 0;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    }
  }
}
</style>