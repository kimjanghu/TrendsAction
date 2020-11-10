<template>
  <div>
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
                <span>카테고리</span>
              </v-chip>
              <div style="color:white;">
                <p class="text-h6">예측키워드 타이틀</p>
                <span>키워드 description</span>
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
    </v-row>
  </div>
</template>

<script>
import TrendDetailNews from '../trends/TrendDetailNews.vue'
import TrendDetailSns from '../trends/TrendDetailSns.vue'
import TrendDetailAgora from '../trends/TrendDetailAgora.vue'

export default {
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
    }
  }

}
</script>

<style>

</style>