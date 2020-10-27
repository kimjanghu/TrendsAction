<template>
  <v-container style="max-width: 600px;" class="ml-0">
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
            <span>박싸피</span>
          </template>
          <v-text-field
            v-model="input"
            hide-details
            flat
            label="어떻게 생각하세요?"
            solo
            @keydown.enter="comment"
          >
            <template v-slot:append>
              <v-btn
                class="mx-0"
                depressed
                @click="comment"
              >
                댓글작성
              </v-btn>
            </template>
          </v-text-field>
        </v-timeline-item>

        <!-- <v-timeline-item
          v-for="message in messages"
          :key="message.time"
          :color="message.color"
          small
        >
          <div>
            <div class="font-weight-normal">
              <strong>{{ message.from }}</strong> @{{ message.time }}
            </div>
            <div>{{ message.message }}</div>
          </div>
        </v-timeline-item> -->
        <v-timeline-item
          v-for="comment in comments"
          :key="comment.id"
          small
        >
          <div>
            <div class="font-weight-normal">
              <strong>{{ comment.user_id }}</strong> @{{ comment.date }}
            </div>
            <div>{{ comment.content }}</div>
          </div>
        </v-timeline-item>
      </v-timeline>
      
    </v-timeline>
  </v-container>
</template>

<script>
import axios from 'axios'
import SERVER from '@/lib/api'

export default {
    data: () => ({
      comments: [],
      events: [],
      input: null,
      nonce: 0,
      messages: [
        {
          from: '영등포솜주먹',
          message: `'multi-persona' 멀티 페르소나 : 가면이라 칭해져야 하는가.`,
          time: '10:42am',
          color: 'deep-purple lighten-1',
        },
        {
          from: '백엔드마스터',
          message: '이러한 멀티 페르소나에는 단점 또한 존재한다. 멀티 페르소나가 지속된다면 정체성이 불안정해질 수 있다. 정체성의 불안정이 지속되면 혼란이 올 수 있고, 결과적으로는 실제 자신의 정체성이 무엇인지 인식하기 어려워질 수 있다.',
          time: '10:37am',
          color: 'green',
        },
        {
          from: '도봉산나와바리',
          message: '멀티 페르소나, 나를 나라고 말할 수 있는 것은 누구인가?',
          time: '9:47am',
          color: 'deep-purple lighten-1',
        },
      ],
    }),

    computed: {
      timeline () {
        return this.events.slice().reverse()
      },
    },
    created() {
      this.getComments()
    },

    methods: {
      getComments() {
        axios
          .get(SERVER.ROUTES.comments.URL + SERVER.ROUTES.comments.rdComment + 1)
          .then((res) => 
            { console.log(res);
              this.comments = res.data })
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
    },
  }

</script>

<style>

</style>