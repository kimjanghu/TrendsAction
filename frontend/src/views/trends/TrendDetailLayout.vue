<template>
  <v-container class="mt-10">
    <v-row>
      <v-col cols="12" md="9">
        <v-row justify="center">
          <v-col cols="12">
            <div class="neumor-design">
              <v-img
                width="100%"
                :height="$vuetify.breakpoint.smAndDown ? 300 : 300"
                lazy-src="https://picsum.photos/id/11/10/6"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                src="https://picsum.photos/id/11/500/300"
                class="trend-img"
                style="border-radius: 19px;"
              >
                <div class="trend-desc">
                  <v-chip
                    class="white--text ml-0 mb-2"
                    color="purple"
                    label
                    small
                  >
                    <span v-if="trendInfo.categoryId == 1">비즈니스/소비</span>
                    <span v-else-if="trendInfo.categoryId == 2">일상</span>
                    <span v-else>문화</span>
                  </v-chip>
                  <div style="color:white;">
                    <p class="text-h6">{{ trendInfo.name }}</p>
                    <span>{{ trendInfo.description }}</span>
                  </div>
                </div>
              </v-img>
            </div>
          </v-col>
        </v-row>
        <v-row justify="center">
          <v-col cols="12" md="12">
            <v-tabs
              class="neumor-design mb-5"
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
            <v-tabs-items v-model="tab" style="background-color:#F5F5F6;" class="neumor-design">
              <v-tab-item
                v-for="item in items"
                :key="item.id"
              >
                
                <trend-detail-news v-if="item.id==1" :userInfo="userInfo" :trendId="trendId"/>
                <trend-detail-sns v-if="item.id==2" :userInfo="userInfo" :trendId="trendId"/>
                <trend-detail-agora v-if="item.id==3" :userInfo="userInfo" :trendId="trendId"/>

              </v-tab-item>
            </v-tabs-items>
          </v-col>
          <!-- <v-col cols="12" md="3" v-if="$vuetify.breakpoint.mdAndUp">
            <div>
              <p class="my-2 subtitle-1 neumor-design">BEST NEWS</p>
              <v-card outlined class="neumor-design">
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
            
            
          </v-col> -->
        </v-row>
      </v-col>
      <v-col cols="12" md="3" v-if="$vuetify.breakpoint.mdAndUp" style="position: relative">
        <div class="set-sticky">
        <p class="mt-2 mb-3 subtitle-1 neumor-design">OTHERS</p>
        <div class="neumor-design">
          <v-list two-line class="pt-0 neumor-design" style="background: #F5F5F6;">
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
import { mapGetters } from 'vuex'

export default {
  components: {
    TrendDetailNews,
    TrendDetailSns,
    TrendDetailAgora
  },
  props: ['trendId'],
  data () {
      return {
        trendInfo: {},
        category: { 1: '비즈니스/소비', 2: '일상', 3: '문화' },
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
      userInfo: {},
      }
    },
    computed: {
      ...mapGetters('userStore', ['config']),
    },
    mounted() {
    },
    created() {
      // this.getBestNews()
      this.getUserInfo()
      this.getTrendInfo()
    },
    methods: {
      // getBestNews() {
      //   axios
      //     .get(SERVER.URL + SERVER.ROUTES.boards.getBestNews + 1, this.config)
      //     .then((res) => {
      //       this.bestNews = res.data.data;
      //     })
      //     .catch((err) => {
      //       console.log(err)
      //     } )
      // },
      getUserInfo() {
        const userId = window.localStorage.getItem('userId')
        axios
          .get(SERVER.URL + SERVER.ROUTES.accounts.user + `/${userId}`, this.config)
          .then((res) => { 
            console.log(res.data.data);
            this.userInfo = res.data.data
          })
          .catch((err) => { console.log(err)})
      },
      getTrendInfo() {
        axios
          .get(SERVER.URL + SERVER.ROUTES.trends.getTrendInfo + this.trendId)
          .then((res) => { console.log(res.data); this.trendInfo = res.data})
          .catch((err) => {console.log(err)})

      }
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
  top: 80px;
}

.neumor-design {
  padding: 2%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}
</style>