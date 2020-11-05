<template>
  <div class="wrapper">
    <Header />
    <section id="first-section" class="section-area">
    <v-container>
      <v-row class="section" justify="center" align="center">
        <v-col class="text-center" cols="12">
          <h2>TrendsAction</h2>
          <p>Get insight, and set the trend</p>
        </v-col>
        <v-col class="text-center" cols="12" md="4">
          <div class="trend-card" 
            data-sal="slide-up"
            data-sal-duration="800"
            data-sal-repeat>
              <v-img
              src="../assets/images/getinfo.jpg"
              style="border-radius:19px;"
              ></v-img>
              <div class="trend-card-info">
                <h2 class="mb-3">STEP 1.</h2>
                <p>트렌드 키워드에 따라, NEWS, SNS 다양한 트렌드 정보를 얻고, 사용자들과 자유롭게 의견을 공유하세요.</p>
              </div>
          </div>
        </v-col>
        <v-col class="text-center" cols="12" md="4">
          <div class="trend-card"
            data-sal="slide-up"
            data-sal-duration="800"
            data-sal-delay="500"
            data-sal-repeat>
              <v-img
              src="../assets/images/share.jpg"
              style="border-radius:19px;"
              ></v-img>
              <div class="trend-card-info">
                <h2 class="mb-3">STEP 2.</h2>
                <p>혹시 관련 프로젝트나 스터디를 진행 중인가요? 나만의 트렌드 보드에 담아 저장하고, 친구와 팀원과 공유가 가능합니다.</p>
              </div>
          </div>
        </v-col>
        <v-col class="text-center" cols="12" md="4">
          <div class="trend-card"
            data-sal="slide-up"
            data-sal-duration="800"
            data-sal-delay="800"
            data-sal-repeat>
            <v-img
              src="../assets/images/step3.jpg"
              style="border-radius:19px;"
              ></v-img>
            <div class="trend-card-info">
              <h2 class="mb-3">STEP 3.</h2>
              <p>최신 이슈들을 파악하고 트렌드를 예측해보세요. 당신의 트렌드는 무엇인가요?</p>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <div class="arrow" @click="$vuetify.goTo('#second-section')"><span style="color:black; font-size: 3rem"><i class="fas fa-arrow-down"></i></span></div>
    </section>

    <section id="second-section" class="section-area">
      <div class="second-part">
        <div class="trendBx">
          <div>
            <h2>트렌드 키워드</h2>
            <input class="trendBx-btn" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })" type="button" value="Learn more">
          </div>
        </div>
      
        <div class="predictBx">
          <div>
            <h2>예측 트렌드 키워드</h2>
            <input class="trendBx-btn" @click="$router.push({ name: $constants.URL_TYPE.PREDICT.LIST })" type="button" value="Learn more">
          </div>     
        </div>
      </div>
      <!-- <div class="arrow" @click="$vuetify.goTo('#third-section')"><span style="color:black; font-size: 3rem"><i class="fas fa-arrow-down"></i></span></div> -->
    </section>

    <!-- <section id="third-section" style="height:300px;">
    </section> -->


      <!-- <v-row class="pt-0">
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
          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
            height="100%"
          ></v-img>
        </v-col>
      </v-row> -->
      
   
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

.about {
  position: relative;
  width: 100%;
  display: flex;
  justify-content: center;
}


.section {
  padding: 5%;
  margin: 10% 0;
}

.trend-card {
  position:relative;
  width: 100%;
  min-height: 400px;
  padding: 3%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}

.trend-card-img {
  position: relative;
  width: 100%;
  height: 40%;
  border-radius: 19px;
  background-image: url("../assets/images/getinfo.jpg");
  background-size: cover;
}

.trend-card-info {
  padding: 5%;
}


.trend-card:hover {
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #d0d0d1, 
              -9px -9px 18px #ffffff;
  transition: 0.4s;
}

.section-area {
  position: relative;
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.section-area .arrow {
  position: absolute;
  bottom: 30px;
  left: 0;
  right: 0;
  text-align: center;
  cursor: pointer;
}

.second-part {
  position: relative;
  width: 100%;
  height: 600px;
  text-align: center;
  display: flex;
  align-items: center;
}

.second-part .trendBx {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  height:100%;
  width: 50%;
  transition: 0.4s;
}

.trendBx {
  background-image: url('../assets/images/now.jpg');
  background-size: cover;
  color: #ffffff;
}

.trendBx:hover {
  background-color: #999999;
  transition: all .4s;
}

.second-part .predictBx {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  right: 0;
  height:100%;
  width: 50%;
  transition: 0.4s;
}

.predictBx {
  background-image: url('../assets/images/predict.jpg');
  background-size: cover;
  color: #ffffff;
}

.second-part .predictBx:hover {
  background-color:  #999999;
  transition: all .4s;
}

.trendBx-btn {
  margin-top: 20px;
  border: 2px solid #ffffff;
  border-radius: 30px;
  padding: 10px 20px;
  transition: .4s;
}

.trendBx-btn:hover {
  color: black;
  background-color: #ffffff;
  transition: .4s;
}
</style>