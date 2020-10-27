<template>
  <v-sheet
    class="px-5 py-2"
    color="grey lighten-3"
  >
    <v-row>
      <v-col cols="12" md="6" v-for="n in 4" :key="n">
        <v-card 
          color="white" 
          :height="$vuetify.breakpoint.smAndDown? 250:200" 
          :to="{name: 'BoardDetail',}">
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<script>
export default {

}
</script>

<style>

</style>