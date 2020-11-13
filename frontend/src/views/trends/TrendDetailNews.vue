<template>
  <v-container>
    <!-- <v-card
      class="mx-auto my-5"
      v-for="news in newsList"
      :key="news.id"
      :href="'//' + news.pressLink"
      target="_blank"
    > -->
    <div v-if="newsList.length !== 0">
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

      <div class="text-center">
        <v-pagination
          v-model="page"
          :length="3"
        ></v-pagination>
      </div>
    </div>
    <div v-else class="text-center my-10 mx-10">
      관련된 뉴스가 없습니다.
      
      <v-btn @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })" color="primary" style="display:block; margin: 10px auto;">다른 키워드 보러가기</v-btn>
    </div>

  </v-container>
</template>

<script>
import AddBoardBtn from '@/components/AddBoardBtn.vue'
import axios from 'axios'
import SERVER from "@/lib/api";
import { mapGetters } from 'vuex';

export default {
  props: ['userInfo', 'trendId', 'categoryId'],
  components: {
    AddBoardBtn
  },
  computed: {
    ...mapGetters('userStore', ['isLogin'])
  },
  data() {
    return {
      page: 1,
      newsList: [],
    }
  },
  created() {
    this.getNews()
  },
  methods: {
    getNews() {
      axios
        .get(SERVER.URL + SERVER.ROUTES.trends.trendNews + this.trendId)
        .then((res) => 
          { 
            this.newsList = res.data })
        .catch((err) => { console.log(err)})
    },
  }

}
</script>

<style>

</style>