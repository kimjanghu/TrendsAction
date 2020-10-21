<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" lg="10" xl="8">
        <v-img :aspect-ratio="16/9"
          lazy-src="https://picsum.photos/id/11/10/6"
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
            <p class="text-h6">멀티 페르소나</p>
            <span>'멀티 페르소나'의 의미를 직역하면 '여러 개의 가면'이라는 뜻이 됩니다. 페르소나는 고대 그리스에서 배우들이 쓰던 가면을 일컫는 단어인데요. 심리학에서는 타인에게 비치는 외적 성격을 나타내는 용어로 쓰입니다.</span>
          </div>
        </v-img>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="12" md="8" lg="7">
        <v-tabs
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
      <v-col cols="12" md="4" lg="3" v-if="$vuetify.breakpoint.mdAndUp">
        <p class="mt-2 mb-5 subtitle-1">Others</p>
        <v-list three-line>
          <v-list-item
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

            <v-list-item-content>
              <span
                class="font-weight-regular caption"
                style="margin-bottom:-15px"
                v-text="keyword.category"
              ></span>
              <span v-text="keyword.title" class="mt-0"></span>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TrendDetailNews from './TrendDetailNews.vue'
import TrendDetailSns from './TrendDetailSns.vue'
import TrendDetailAgora from './TrendDetailAgora.vue'


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
          {id: 1, name:'News'},
          {id: 2, name:'Sns'},
          {id: 3, name:'Agora'},
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
      }
    },

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
</style>