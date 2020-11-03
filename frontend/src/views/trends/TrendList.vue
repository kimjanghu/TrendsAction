<template>
  <div style="background-color: gray;">

    <div v-for="keyword in keywordList" :key="keyword.categoryName" class="trend-area">
      <v-container class="trend-container">
        <p class="trend-category">#{{ keyword.categoryName }}</p>
        <v-row justify="center">
          <v-col v-for="trend in keyword[keyword.categoryName]" :key="trend.trendId" cols="12" lg="6" xl="3">
            <div class="text-center trend-keyword">
              {{ trend.trendName }}
            </div>
          </v-col>
        </v-row>
      </v-container>
      <!-- <v-container class="trend-container">
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
      </v-container> -->
    </div>

  </div>
</template>

<script>
export default {
  name: 'TrendList',
  data() {
    return {
      keywordList: []
    }
  },
  methods: {
    getBookTrends() {
      this.$http.get(this.$api.URL + this.$api.ROUTES.trends.trendList + '/0')
        .then(res => {
          this.keywordList = res.data
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created() {
    this.getBookTrends()
  }
}
</script>

<style lang="scss" scoped>
.trend-area {
  width: 75%;
  margin: 0 auto;
  padding: 10px;

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