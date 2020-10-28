<template>
<v-container>
  <v-row class="justify-contents-between">
    <span class="text-h4 font-weight-bold">자율프로젝트</span>
    <v-dialog
      v-model="dialog"
      width="350"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark rounded v-on="on" v-bind="attrs">공유하기</v-btn>
      </template>
      <v-card class="custom-rounded">
        <v-card-title>
          <span>보드 공유하기</span>
          <v-spacer></v-spacer>
          <i class="fas fa-times" @click="dialog = false" style="cursor:pointer;"></i>
        </v-card-title>
        <v-divider></v-divider>
        <v-card-text class="pt-5">
          <v-text-field
            placeholder="이메일로 검색해주세요"
            outlined
            dense
            class="px-0 py-0 mb-0 custom-rounded"
          ></v-text-field>
          <v-btn width="100%" color="primary">공유하기</v-btn>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
  <v-sheet
    class="px-5 py-2 mt-3"
    color="grey lighten-3"
  >
    <v-row>
      <v-col cols="12" md="4" v-for="(card,n) in cards" :key="n">
        <v-card 
          color="white" 
          :height="$vuetify.breakpoint.smAndDown? 250:200" 
          :to="{name: 'BoardDetail',}">
          <v-img
            :src="card.src"
            class="white--text align-end"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            height="200px"
          >
            <v-card-title v-text="card.title"></v-card-title>
          </v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</v-container>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      cards: [
      { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
      { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
      { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
      { title: '뉴스 타이틀', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
      ],
    }
  }
}
</script>

<style>

</style>