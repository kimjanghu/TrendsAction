<template>
  <v-sheet
    class="px-5 py-2"
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
</template>

<script>
export default {
  data: () => ({
    cards: [
      { title: '자율프로젝트', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg' },
      { title: '꼴라쥬', src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg' },
      { title: 'WEB', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg' },
      { title: '반응형 웹페이지', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg' },
      { title: '애니메이션', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg' },
      { title: '히어로', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg' },
    ],
  }),

}
</script>

<style>

</style>