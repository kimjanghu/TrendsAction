<template>
  <div class="wrapper">
    <Header />
    <v-container class="main-area" id="first-section">
      <!-- 트렌드 키워드 간단 소개 -->
      <v-row justify="center" align="center" class="trend-area">
        <v-col class="text-center" cols="12" md="6" xl="4">
          <p>About Us</p>
          <p data-sal="slide-left"
      data-sal-duration="800">트렌드 2021의 트렌드 키워드 소개 내용</p>
        </v-col>
        <v-col class="text-center" cols="12" md="6" xl="4">
          <v-btn color="primary" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Learn more</v-btn>
          <p>트렌드 2021의 트렌드 키워드 소개 내용</p>
        </v-col>
      </v-row>

      <!-- 트렌드 키워드 카드 -->
      <v-row justify="center" align="center" class="trend-area">
        <v-col class="text-center" cols="12">
          <p>2021 트렌드 키워드</p>
        </v-col>
        <v-col class="text-center" cols="12" md="6" lg="3" v-for="n in 4" :key="n" data-sal="slide-up"
      data-sal-duration="800">
          <div class="trend-card"></div>
        </v-col>
      </v-row>

      <!-- 예측 트렌드 키워드 간단 소개 -->
      <v-row justify="center" align="center" class="trend-area">
        <v-col class="text-center" cols="12" md="6" xl="4">
          <p>About Us</p>
          <p>트렌드 2021의 트렌드 키워드 소개 내용</p>
        </v-col>
        <v-col class="text-center" cols="12" md="6" xl="4">
          <v-btn color="primary" @click="$router.push({ name: $constants.URL_TYPE.PREDICT.LIST })">Learn more</v-btn>
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
import sal from "sal.js";

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
  },
  mounted() {
    sal();
  }
}
</script>

<style lang="scss" scoped>

.wrapper {
  position: relative;
}
.main-area {
  margin: 0 auto;
  padding: 0;
  width: 75%;
}
.trend-area {
  background-color: #fafafafa;
  height: 500px;
  margin: 20px 0;
}

.trend-card {
  width: 100%;
  height: 300px;
  background-color: #999999
}
.trend-area-two {
  background-color: #000;
  height: 500px;
}
</style>