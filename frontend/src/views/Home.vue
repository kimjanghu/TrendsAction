<template>
  <div class="wrapper">
    <Header />
    <v-container id="first-section">
      <!-- 트렌드 키워드 간단 소개 -->
      <v-row class="section pb-0" justify="center" align="center">
        <v-col cols="12" md="6" xl="4">
          <!-- <div class="imgBx"></div> -->
          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            height="100%"
          ></v-img>
        </v-col>
        <v-col cols="12" md="6" xl="4" style="padding: 10%">
          <div
            data-sal="slide-left"
            data-sal-duration="800"
            data-sal-repeat
          >
            <h2>밀려드는 트렌드 속</h2>
            <p>살아남고 싶은 자, 트렌드를 파악해라
            </p>
            <v-btn color="primary" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Learn more</v-btn>
          </div>
        </v-col>
        </v-row>

      

      <v-row class="section mt-10" justify="center" align="center">
        <v-col class="text-center" cols="12">
          <h2>TrendsAction</h2>
          <p>Get insight, and set the trend</p>
        </v-col>
        <v-col class="text-center" cols="12" md="6" lg="3">
          <div class="trend-card" 
            data-sal="slide-up"
            data-sal-duration="800"
            data-sal-repeat>
            <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            class="imggg"
            ></v-img>
            <p class="subtitle-1 mt-5 mb-0">STEP 1.</p>
            <em>트렌드 정보를 얻고</em>
          </div>
        </v-col>
        <v-col class="text-center" cols="12" md="6" lg="3">
          <div class="trend-card"
            data-sal="slide-up"
            data-sal-duration="800"
            data-sal-delay="300"
            data-sal-repeat>
            <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            class="imggg"
            ></v-img>
            <p class="subtitle-1 mt-5 mb-0">STEP 2.</p>
            <em>정보를 공유하고</em>
          </div>
        </v-col>
        <v-col class="text-center" cols="12" md="6" lg="3">
          <div class="trend-card"
            data-sal="slide-up"
            data-sal-duration="800"
            data-sal-delay="600"
            data-sal-repeat>
            <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            class="imggg"
            ></v-img>
            <p class="subtitle-1 mt-5 mb-0">STEP 3.</p>
            <em>트렌드를 예측하세요</em>
          </div>
        </v-col>
      </v-row>

<v-row class="pt-0">
        <v-col cols="12" md="6" xl="4" style="padding: 10%">
          <div
            data-sal="slide-right"
            data-sal-duration="800"
            data-sal-delay="800"
            data-sal-repeat
            style="text-align:right"
          >
            <h2>밀려드는 트렌드 속</h2>
            <p>살아남고 싶은 자, 트렌드를 파악해라
            </p>
            <v-btn color="primary" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Learn more</v-btn>
          </div>
        </v-col>
        <v-col cols="12" md="6" xl="4">
          <!-- <div class="imgBx"></div> -->
          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            height="100%"
          ></v-img>
        </v-col>
      </v-row>

      <!-- 트렌드 키워드 카드 -->
      <v-row class="section" justify="center" align="center">
        <v-col class="text-center" cols="12">
          <h2>card</h2>
          <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Maxime sunt sequi aperiam harum amet quasi minima cupiditate vero. Voluptatum, mollitia deserunt? At recusandae a aut alias est distinctio, quibusdam quia? Lorem ipsum dolor sit, amet consectetur adipisicing elit. Modi atque harum inventore, similique iste sunt in, reiciendis voluptatibus minus odio fugit. Doloremque deserunt perspiciatis mollitia. Quo voluptatibus repudiandae esse! Iste.
          </p>
        </v-col>
        <v-col class="text-center" cols="12" md="6" lg="3" v-for="n in 4" :key="n" data-sal="slide-up"
      data-sal-duration="800">
          <div class="trend-card"><v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            class="imggg"
          ></v-img></div>
        </v-col>
      </v-row>

      <!-- 예측 트렌드 키워드 간단 소개 -->
      <v-row class="section" justify="center" align="center">
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

.section {
  padding: 5%;
  margin: 10% 0;
}

.trend-card {
  width: 100%;
  height: 300px;
  padding: 3%;
border-radius: 19px;
background: #F5F5F6;
box-shadow:  9px 9px 18px #ebebec, 
             -9px -9px 18px #ffffff;
}

.trend-card .imggg {
  height: 60%;
  width: 100%;
  border-radius: 19px;

}

.about {
  position: relative;
  width: 100%;
  display: flex;
}
.about .contentBx {
  background: #999999;
  padding: 100px;
  width: 50%;
}

.imgBx {
  height: 350px;
  width: 100%;
  background-image: url("https://cdn.vuetifyjs.com/images/cards/sunshine.jpg");
}
</style>