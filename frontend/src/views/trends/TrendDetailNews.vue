<template>
  <v-container>
    <v-card
      class="mx-auto my-5"
      v-for="n in 6"
      :key="n"
    >
      <v-img
        class="white--text align-end"
        height="200px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title>Top 10 Australian beaches</v-card-title>
      </v-img>

      <v-card-subtitle class="pb-0">
        Number 10
      </v-card-subtitle>

      <v-card-text class="text--primary">
        <div>Whitehaven Beach</div>

        <div>Whitsunday Island, Whitsunday Islands</div>
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          color="orange"
          text
        >
          즐겨찾기
        </v-btn>

        <v-btn
          color="orange"
          text
        >
          상세보기
        </v-btn>
      </v-card-actions>
    </v-card>

    <div class="text-center">
      <v-pagination
        v-model="page"
        :length="6"
      ></v-pagination>
    </div>

  </v-container>
</template>

<script>
export default {
  data() {
    return {
      page: 1,
    }
  }

}
</script>

<style>

</style>