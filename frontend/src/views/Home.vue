<template>
  <div>
    <Header />
    <!-- <div class="main-area d-flex">
      <Carousel />
      <MainList />
    </div> -->
    <v-container class="main-area" id="first-section">
      <v-row justify="center">
        <v-col class="text-center trend-area" cols="12" lg="10" xl="8">
          <p @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">트렌드 리스트</p>
          <p>트렌드 2021의 트렌드 키워드 소개 내용</p>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col class="text-center trend-area-two" cols="12" lg="10" xl="8">
          <p @click="$router.push({ name: $constants.URL_TYPE.PREDICT.LIST })">예측 리스트</p>
          <p>TrendsAction의 트렌드 키워드 소개 내용</p>
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
.main-area {
  margin: 0 auto;
  padding: 0;
  width: 75%;
}
.trend-area {
  background-color: #fff;
  height: 500px;
}

.trend-area-two {
  background-color: #000;
  height: 500px;
}
</style>