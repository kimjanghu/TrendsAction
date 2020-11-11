<template>
  <div>
    <Navbar />
    <v-container class="mt-16">
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
                <v-tab
                  v-for="item in items"
                  :key="item.id"
                  :to="{name: item.link}"
                >{{ item.name }}</v-tab>
              </v-tabs>
              <router-view class="neumor-design" :userInfo="userInfo" :trendId="trendId"></router-view>
            </v-col>
          </v-row>
        </v-col>
        <v-col cols="12" md="3" v-if="$vuetify.breakpoint.mdAndUp" style="position: relative">
          <div class="set-sticky">
          <p class="mt-2 mb-3 px-3 subtitle-1 neumor-design">OTHERS</p>
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
                  :src="keyword.thumbnail"
                  class="mr-4"
                  max-width="64"
                  min-width="64"
                  min-height="50"
                  max-height="50"
                ></v-img>
                <v-list-item-content class="py-0" @click="goToTrendDetail(keyword.id)">
                  <v-list-item-subtitle v-text="keyword.category"></v-list-item-subtitle>
                  <v-list-item-title v-text="keyword.name"></v-list-item-title>
                </v-list-item-content>
     
              </v-list-item>
            </v-list>
          </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/lib/api'
import { mapGetters } from 'vuex'
import Navbar from '@/components/common/Navbar2'

export default {
  components: {
    Navbar,
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
        otherkeywords: [],
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
      goToTrendDetail(keywordId) {
        this.$router.push({ name: 'TrendDetailNews', params: { trendId: keywordId }})
        this.$router.go(0)
      },
      getUserInfo() {
        const userId = window.localStorage.getItem('userId')
        axios
          .get(SERVER.URL + SERVER.ROUTES.accounts.user + `/${userId}`, this.config)
          .then((res) => { 
            this.userInfo = res.data.data
          })
          .catch((err) => { console.log(err)})
      },
      getTrendInfo() {
        axios
          .get(SERVER.URL + SERVER.ROUTES.trends.getTrendInfo + this.trendId)
          .then((res) => {
            this.trendInfo = res.data; 
            this.getOtherKeywords(res.data.categoryId)})
          .catch((err) => {console.log(err)})
      },
      getOtherKeywords(catergoryId) {
        axios
          .get(SERVER.URL + SERVER.ROUTES.trends.trendCategories + catergoryId)
          .then((res) => { this.otherkeywords = res.data})
          .catch((err) => console.log(err))
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
  top: 100px;
}

.neumor-design {
  padding: 2%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}
</style>