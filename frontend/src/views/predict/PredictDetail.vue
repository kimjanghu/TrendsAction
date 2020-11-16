<template>
  <div>
    <v-row justify="center">
      <v-col cols="12">
        <div class="neumor-design">
          <div>
            <v-chip
              class="white--text ml-0 mb-2"
              color="purple"
              label
              small
            >
              <span v-if="trendInfo.categoryId == 9">IT과학</span>
              <span v-else-if="trendInfo.categoryId == 10">사회</span>
              <span v-else-if="trendInfo.categoryId == 11">생활문화</span>
              <span v-else>세계</span>
            </v-chip>
            <div class="mb-5">
              <p class="text-h6 mb-0">{{ trendInfo.name }}</p>
              <span class="mr-2" v-for="(item, i) in mainKeywords" :key="i">#{{ item }}</span>
              <p class="mt-5">{{ trendInfo.description }}</p>
            </div>
          </div>
        
          <v-img
            width="100%"
            :height="$vuetify.breakpoint.smAndDown ? 300 : 300"
            lazy-src="https://picsum.photos/id/11/10/6"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            :src="trendInfo.thumbnail"
            class="trend-img"
            style="border-radius: 19px;"
          >
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
          >
            {{ item.name }}
          </v-tab>
        </v-tabs>

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
    </v-row>
  </div>
</template>

<script>
import TrendDetailNews from '../trends/TrendDetailNews.vue'
import TrendDetailSns from '../trends/TrendDetailSns.vue'
import TrendDetailAgora from '../trends/TrendDetailAgora.vue'

import axios from 'axios'
import SERVER from '@/lib/api'
import { mapGetters } from 'vuex'

export default {
  props: ['trendId', 'categoryId'],
  components: {
    TrendDetailNews,
    TrendDetailSns,
    TrendDetailAgora
  },
  data() {
    return {
      tab: null,
      items: [ 
        {id: 1, name:'News', link: 'TrendDetailNews'},
        {id: 2, name:'Sns', link: 'TrendDetailSns'},
        {id: 3, name:'Agora', link: 'TrendDetailAgora'},
      ],
      userInfo: {},
      trendInfo: {},
      mainKeywords: [],
    }
  },
  computed: {
    ...mapGetters('userStore', ['config']),
  },
  mounted() {
  },
  created() {
    this.getUserInfo()
    this.getTrendInfo()
    console.log(this.trendId, this.categoryId)
  },
  methods: {
    getUserInfo() {
      const userId = window.localStorage.getItem('userId')
      axios
        .get(SERVER.URL + SERVER.ROUTES.accounts.user + `/${userId}`, this.config)
        .then((res) => { 
          this.userInfo = res.data.data
        })
        .catch((err) => { console.log(err)})
    },
    getPredictInfo() {
      axios 
        .get(SERVER.URL + SERVER.ROUTES.trends.getPredictInfo +this.selection.year + '/' + this.selection.month + '/' + this.selection.week + '/' + this.selection.category, this.config)
        .then((res) => {this.predictInfo = res.data.data})
        .catch((err) => { console.log(err)})
    },
    getTrendInfo() {
      axios
        .get(SERVER.URL + SERVER.ROUTES.trends.getTrendInfo + this.categoryId + '/' + this.trendId)
        .then((res) => {
          this.trendInfo = res.data;
          this.mainKeywords = res.data.keywords.split("//") })
        .catch((err) => {console.log(err)})
    },
  }

}
</script>

<style>

</style>