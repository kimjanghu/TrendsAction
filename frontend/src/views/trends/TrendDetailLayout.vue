<template>
  <v-container>
    <v-row>
      <v-col cols="12" md="9">
        <v-row justify="center">
          <v-col cols="12">
            <v-img
              width="100%"
              :height="$vuetify.breakpoint.smAndDown ? 300 : 300"
              lazy-src="https://picsum.photos/id/11/10/6"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              src="https://picsum.photos/id/11/500/300"
              class="trend-img"
            >
              <div class="trend-desc">
                <v-chip
                  class="white--text ml-0 mb-2"
                  color="purple"
                  label
                  small
                >
                  사회문화
                </v-chip>
                <div style="color:white;">
                  <p class="text-h6">멀티 페르소나</p>
                  <span>'멀티 페르소나'의 의미를 직역하면 '여러 개의 가면'이라는 뜻이 됩니다. 페르소나는 고대 그리스에서 배우들이 쓰던 가면을 일컫는 단어인데요. 심리학에서는 타인에게 비치는 외적 성격을 나타내는 용어로 쓰입니다.</span>
                </div>
              </div>
            </v-img>
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-col cols="12" md="9">
            <v-tabs
              v-model="tab"
              background-color="transparent"
              color="black"
              :grow="$vuetify.breakpoint.mobile"
            >
              <!-- <v-tab
                v-for="item in items"
                :key="item.id"
                :to="{name: item.link}"
              > -->
              <v-tab
                v-for="item in items"
                :key="item.id"
              >
                {{ item.name }}
              </v-tab>
            </v-tabs>
            <!-- <router-view></router-view> -->
            <v-tabs-items v-model="tab">
              <v-tab-item
                v-for="item in items"
                :key="item.id"
              >
                
                <trend-detail-news v-if="item.id==1"/>
                <trend-detail-sns v-if="item.id==2" />
                <trend-detail-agora v-if="item.id==3" />

              </v-tab-item>
            </v-tabs-items>
          </v-col>
          <v-col cols="12" md="3" v-if="$vuetify.breakpoint.mdAndUp">
            <div>
              <p class="my-2 subtitle-1">BEST NEWS</p>
              <v-card outlined>
                <v-list two-line>
                  <v-list-item
                    class="px-3 py-0"
                    v-for="(news, i) in bestNews"
                    :key="i"
                  >
                    <v-list-item-content class="py-0">
                      <v-row>
                        <v-col cols="1">
                          {{ i+1 }}
                        </v-col>
                        <v-col cols="10">
                          <v-list-item-subtitle v-text="news.press"></v-list-item-subtitle>
                          <v-list-item-title v-text="news.title"></v-list-item-title>
                        </v-col>
                      </v-row>
                    </v-list-item-content>
                  </v-list-item>
                </v-list>
              </v-card>
            </div>
            
            
          </v-col>
        </v-row>
      </v-col>
      <v-col cols="12" md="3" v-if="$vuetify.breakpoint.mdAndUp" style="position: relative">
        <div class="set-sticky">
        <p class="mt-2 mb-3 subtitle-1">OTHERS</p>
          <v-list two-line class="pt-0">
            <v-list-item
              class="pl-0"
              v-for="(keyword, i) in otherkeywords"
              :key="i"
              ripple
              @click="() => {}"
            >
              <v-img
                :src="keyword.image"
                class="mr-4"
                max-width="64"
                min-width="64"
                min-height="50"
                max-height="50"
              ></v-img>

              <v-list-item-content class="py-0">
                <v-list-item-subtitle v-text="keyword.category"></v-list-item-subtitle>
                <v-list-item-title v-text="keyword.title"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TrendDetailNews from './TrendDetailNews.vue'
import TrendDetailSns from './TrendDetailSns.vue'
import TrendDetailAgora from './TrendDetailAgora.vue'
import axios from 'axios'
import SERVER from '@/lib/api'
import { mapState, mapGetters } from 'vuex'



export default {
  components: {
    TrendDetailNews,
    TrendDetailSns,
    TrendDetailAgora
  },
  data () {
      return {
        tab: null,
        items: [ 
          {id: 1, name:'News', link: 'TrendDetailNews'},
          {id: 2, name:'Sns', link: 'TrendDetailSns'},
          {id: 3, name:'Agora', link: 'TrendDetailAgora'},
        ],
        otherkeywords: [
        {
          image: 'https://cdn-images-1.medium.com/max/1024/1*9C9hLji68wV373tk8okLYA.jpeg',
          title: '자본주의키즈',
          category: 'Travel',
        },
        {
          image: 'https://cdn-images-1.medium.com/max/1024/1*BBNtYUieAqHoXKjiJ2mMjQ.png',
          title: '브이노믹스',
          category: 'Technology',
        },
        {
          image: 'https://cdn-images-1.medium.com/max/1024/1*rTEtei1UEmNqbq6evRsExw.jpeg',
          title: '유투버',
          category: 'Media',
        },
        {
          image: 'https://cdn-images-1.medium.com/max/1024/1*FD2nkJewVeQnGf0ommQfrw.jpeg',
          title: '랜선 집들이',
          category: 'Technology',
        },
        {
          image: 'https://cdn-images-1.medium.com/max/1024/1*eogFpsVgNzXQLCVgFzT_-A.jpeg',
          title: '오팔세대',
          category: 'Travel',
        },
      ],
      bestNews: [],
      }
    },
    computed: {
      ...mapState('userStore', ['userInfo']),
      ...mapGetters('userStore', ['config']),
    },
    created() {
      this.getBestNews()
    },
    methods: {
      getBestNews() {
        axios
          .get(SERVER.URL + SERVER.ROUTES.boards.getBestNews + 1)
          .then((res) => {
            this.bestNews = res.data.data;
          })
          .catch((err) => {
            console.log(err)
          } )
      },
    }
}
</script>

<style>
.trend-img {
  margin-left: auto;
  margin-right: auto;
  position: relative;
}

.trend-desc {
  position: absolute;
  top:0;
  left: 0;
  margin: 2rem;
}

.other-words-list {
  border: 1px solid black;
}

.set-sticky {
  position: -webkit-sticky;
  position: sticky;
  top: 20px;
}
</style>