<template>
  <v-container>
    <v-card
      class="mx-auto my-5"
      v-for="n in 3"
      :key="n"
    >
      <v-img
        class="white--text align-end"
        height="200px"
        :src="news.thumbnail"
      >
      </v-img>
      <v-card-title>{{ news.title }}</v-card-title>
      <v-card-subtitle class="pb-0">
        {{ news.press }} / 2020.01.02 16:28:17
      </v-card-subtitle>

      <AddBoardBtn />
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

export default {
  components: {
    AddBoardBtn
  },
  data() {
    return {
      page: 1,
      news: {
        title: "멀티 페르소나 시대…나는 다중적이고 다양하다",
        press: "매일경제",
        thumbnail: "https://file.mk.co.kr/meet/neds/2020/01/image_readtop_2020_6012_15779500974037161.jpg",
      }
    }
  },

}
</script>

<style>

</style>