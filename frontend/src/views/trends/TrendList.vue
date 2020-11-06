<template>
  <v-container style="background-color: #F5F5F6;" class="mt-10">
    <v-col cols="12" v-for="keyword in keywordList" :key="keyword.categoryName" class="trend-area">
      <v-container class="trend-container">
        <p class="trend-category">#{{ keyword.categoryName }}</p>
        <v-row justify="center">
          <v-col v-for="trend in keyword[keyword.categoryName]" :key="trend.trendId" cols="12" lg="6" xl="3" >
              <div class="text-center trend-keyword">
                <router-link :to="{ name: 'TrendDetailLayout', params: { trendId: trend.trendId }}" style="text-decoration:none; color:#222222;">
                  {{ trend.trendName }}
                </router-link>
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
    </v-col>

  </v-container>
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

  .trend-container {
    border: none;
    border-radius: 25px;
    background: #F5F5F6;
    box-shadow:  11px 11px 22px #ebebec, 
                -11px -11px 22px #ffffff;
    margin: 20px 0;
    padding: 30px;

    p {
      margin-bottom: 20px ;
    }

    .trend-category {
      font-size: 25px;
      font-weight: bold;
    }
  }
}

.trend-keyword {
  padding: 20px;
  width: 70%;
  margin: 0 auto;
  border: none;
  border-radius: 25px;
  background: #F5F5F6;
  box-shadow:  11px 11px 22px #ebebec, 
              -11px -11px 22px #ffffff;
}

.trend-keyword:hover {
  border: none;
  border-radius: 25px;
  background: #F5F5F6;
  box-shadow: inset 11px 11px 22px #e4e4e5, 
              inset -11px -11px 22px #ffffff;
  cursor: pointer;
}

</style>