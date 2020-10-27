<template>
  <div>
    <Header />
    <!-- <div class="main-area d-flex">
      <Carousel />
      <MainList />
    </div> -->
    <v-container class="main-area">
      <v-row justify="center">
        <v-col class="text-center trend-area" cols="12" lg="10" xl="8">
          <p @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">트렌드 리스트</p>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col class="text-center trend-area mt-6" cols="12" lg="10" xl="8">
          <p @click="$router.push({ name: $constants.URL_TYPE.PREDICT.LIST })">예측 리스트</p>
        </v-col>
      </v-row>
      <!-- <v-row justify="center">
        <v-col justify="center" cols="12" lg="10" xl="8">
          <p @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">트렌드 리스트</p>
        </v-col>
      </v-row> -->
    </v-container>
  </div>
</template>

<script>
// import Carousel from '@/components/common/Carousel'
// import MainList from '@/components/common/MainList'
import Header from '@/components/common/Header'

export default {
  name: 'Home',
  components: {
    // Carousel,
    // MainList,
    Header
  },
  data() {
    return {
      
    }
  },
  created() {

  }
}
</script>

<style lang="scss" scoped>
/* .main-area {
  margin: 20px auto;
  width: 75%;
} */
.trend-area {
  background-color: #000;
  height: 500px;
}
</style>