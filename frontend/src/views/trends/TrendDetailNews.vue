<template>
  <v-container>
    <div v-if="newsList.length">
      <v-card
        class="mx-auto my-5"
        v-for="news in newsList"
        :key="news.id"
      >
        <v-img
          class="white--text align-end"
          height="200px"
          :src="news.thumbnail"
        >
        </v-img>
        <v-card-title>{{ news.title }}</v-card-title>
        <v-card-subtitle class="pb-0">
          {{ news.press }} / {{ news.pubDate }}
        </v-card-subtitle>     
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn :href="news.pressLink" target="_blank">기사보기</v-btn>
          <AddBoardBtn v-if="isLogin" :newsId="news.id"/>
        </v-card-actions> 
      </v-card>
      <infinite-loading @infinite="infiniteHandler" spinner="circles"></infinite-loading>
    </div>
    <div v-else class="text-center my-10 mx-10">
      관련된 뉴스가 없습니다.
      
      <v-btn @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })" color="primary" style="display:block; margin: 10px auto;">다른 키워드 보러가기</v-btn>
    </div>

  </v-container>
</template>

<script>
import AddBoardBtn from '@/components/AddBoardBtn.vue'
import InfiniteLoading from 'vue-infinite-loading'
import { mapGetters } from 'vuex'

export default {
  props: ['userInfo', 'trendId', 'categoryId'],
  components: {
    AddBoardBtn,
    InfiniteLoading
  },
  computed: {
    ...mapGetters('userStore', ['isLogin'])
  },
  data() {
    return {
      page: 1,
      newsList: []
    }
  },
  methods: {
    infiniteHandler($state) {
      this.$http
        .get(this.$api.URL + this.$api.ROUTES.trends.trendNews + this.trendId + `/${this.page+1}`)
        .then(res => {
          setTimeout(() => {
            if (res.data.list.length) {
              this.newsList = this.newsList.concat(res.data.list)
              $state.loaded()
              this.page += 1
              if (this.newsList.length / 10 === 0) {
                $state.complete()
              }
            } else {
              $state.complete()
            }
          }, 1000)
        })
        .catch(err => console.log(err.response.data))
    },
  },
  created() {
    this.$http
      .get(this.$api.URL + this.$api.ROUTES.trends.trendNews + this.trendId + `/${this.page}`)
      .then(res => {
        this.newsList = res.data.list
      })
      .catch(err => console.log(err.response.data))
  }
}
</script>

<style>

</style>