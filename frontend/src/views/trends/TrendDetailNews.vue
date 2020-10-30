<template>
  <v-container>
    <!-- <v-card
      class="mx-auto my-5"
      v-for="news in newsList"
      :key="news.id"
      :href="'//' + news.pressLink"
      target="_blank"
    > -->
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
      <AddBoardBtn :my-board-list="myBoardList"/>
    </v-card>

    <div class="text-center">
      <v-pagination
        v-model="page"
        :length="3"
      ></v-pagination>
    </div>

  </v-container>
</template>

<script>
import AddBoardBtn from '@/components/AddBoardBtn.vue'
import axios from 'axios'
import SERVER from "@/lib/api";

export default {
  components: {
    AddBoardBtn
  },
  data() {
    return {
      page: 1,
      newsList: [],
      myBoardList: [],
    }
  },
  created() {
    this.getNews()
    this.getBoardList()
  },
  methods: {
    getNews() {
      axios
        .get(SERVER.URL + SERVER.ROUTES.trends.trendNews + 1)
        .then((res) => 
          { console.log(res);
            this.newsList = res.data })
        .catch((err) => { console.log(err)})
    },
    getBoardList() {
      axios
        .get(SERVER.URL+ SERVER.ROUTES.boards.getBoardList + 8)
        .then((res) => { this.myBoardList = res.data.data })
        .catch((err) => { console.log(err)})
    }
  }

}
</script>

<style>

</style>