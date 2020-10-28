<template>
  <div style="background-color: gray;">

    <div class="trend-area">
      <v-container class="trend-container">
        <p class="trend-category">#culture code</p>
        <v-row justify="center">
          <v-col v-for="(keyword, idx) in keywordArr" :key="idx" cols="12" lg="6" xl="3">
            <div class="text-center trend-keyword">
              {{ keyword }}
            </div>
          </v-col>
        </v-row>
      </v-container>

      <v-container class="trend-container">
        <p class="trend-category">#life style</p>
        <v-row justify="center">
          <v-col v-for="(keyword, idx) in keywordArr" :key="idx" cols="12" lg="6" xl="3">
            <div class="text-center trend-keyword">
              {{ keyword }}
            </div>
          </v-col>
        </v-row>
      </v-container>

      <v-container class="trend-container">
        <p class="trend-category">#business consumption</p>
        <v-row justify="center">
          <v-col v-for="(keyword, idx) in keywordArr" :key="idx" cols="12" lg="6" xl="3">
            <div class="text-center trend-keyword">
              {{ keyword }}
            </div>
          </v-col>
        </v-row>
      </v-container>
    </div>

  </div>
</template>

<script>
export default {
  name: 'TrendList',
  data() {
    return {
      keywordArr: [
        '브이노믹스', 
        '레이어드 홈', 
        '자본주의 키즈', 
        '거침없이 피보팅',
        '롤코라이프',
        '#오하운, 오늘하루운동',
        'N차 신상',
        'CX 유니버스',
        '레이블링 게임',
        '휴먼터치'
      ],
    }
  }
}
</script>

<style lang="scss" scoped>
.trend-area {
  width: 75%;
  margin: 0 auto;
  padding: 20px;

  .trend-container {
    background-color: #fff;
    border: 5px solid #000;
    border-radius: 25px;
    box-shadow: 20px 20px 20px rgba(0, 0, 0, 0.3);
    margin: 50px 0;

    .trend-category {
      font-size: 25px;
      font-weight: bold;
    }
  }
}

.trend-keyword {
  border: 1px solid #000;
  border-radius: 30px;
  padding: 20px;
  width: 70%;
  margin: 0 auto;
}

</style>