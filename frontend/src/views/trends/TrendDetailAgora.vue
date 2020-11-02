<template>
  <v-container class="ml-0">
    <v-timeline
      dense
      clipped
    >
      <div class="font-weight-bold ml-8 mb-2">
        Today
      </div>

      <v-timeline
        align-top
        dense
      >
        <v-timeline-item
          fill-dot
          class="white--text mb-12"
          color="orange"
          large
        >
          <template v-slot:icon>
            <v-avatar>
              <img
                src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                alt="John"
              >
            </v-avatar>
          </template>
          <v-card>
            <v-card-text>
              <v-textarea
                v-model="input"
                hide-details
                filled
                @keydown.enter="addComment"
                :disabled="!isLogin"
                :label="this.isLogin ? '어떻게 생각하세요?' : '로그인하고 자유롭게 의견을 나눠봐요!'"></v-textarea>
              </v-card-text>
              <v-card-actions class="pt-0">
                <v-spacer></v-spacer>
                <v-btn
                  v-if="isLogin"
                  @click="addComment"
                >
                  댓글작성
                </v-btn>

                <v-btn
                  @click="dialog = true" 
                  v-else>
                  로그인
                  <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
                </v-btn>
                
              </v-card-actions>
          </v-card>
        </v-timeline-item>
        <v-timeline-item
          fill-dot
          v-for="(comment, i) in comments"
          :key="i"
          large
        >
          <template v-slot:icon>
            <v-avatar>
              <img
                src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                alt="John"
              >
            </v-avatar>
          </template>
          
          <v-card>
            <v-container>
              <CommentUDBtn :comment="comment" :userInfo="userInfo" @deleteComment="deleteComment"/> 
            </v-container>
          </v-card>
         
          
        </v-timeline-item>
        
      </v-timeline>
      
    </v-timeline>
  </v-container>
</template>

<script>
import axios from 'axios'
import SERVER from '@/lib/api'
import CommentUDBtn from '@/components/CommentUDBtn.vue'
import UsersLoginForm from '@/components/users/UsersLoginForm'
import { mapGetters } from 'vuex'

export default {
  props: ['userInfo'],
  components: {
    CommentUDBtn,
    UsersLoginForm,
  },
  data: () => ({
    dialog: false,
    comments: [],
    events: [],
    input: null,
    nonce: 0,
    
  }),
  computed: {
    ...mapGetters('userStore', ['isLogin']),
  },
  created() {
    this.getComments()
  },

  methods: {
    deleteComment() {
      this.getComments()
    },
    getComments() {
      axios
        .get(SERVER.URL + SERVER.ROUTES.comments.rdComment + 1)
        .then((res) => 
          { console.log(res);
            this.comments = res.data })
        .catch((err) => { console.log(err)})
    },
    addComment() {
      let body = {
        content: this.input,
        trendId: 1,
        userId: this.userInfo.id
      }
      axios
        .post(SERVER.URL + SERVER.ROUTES.comments.cuComment, body )
        .then((res) => 
          { console.log(res); this.input = ''; this.getComments()})
        .catch((err) => { console.log(err)})
    },
    comment () {
      const time = (new Date()).toTimeString()
      this.messages.push({
        id: this.nonce++,
        from: "박싸피",
        message: this.input,
        color: 'green',
        time: time.replace(/:\d{2}\sGMT-\d{4}\s\((.*)\)/, (match, contents, ) => {
          return ` ${contents.split(' ').map(v => v.charAt(0)).join('')}`
        }),
      })

      this.input = null
    },
    changeDialog(dialog) {
      this.dialog = dialog
    }
  },
}

</script>

<style>

</style>