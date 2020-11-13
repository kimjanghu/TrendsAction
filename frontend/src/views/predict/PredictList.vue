<template>
  <div>
    <Navbar />
    <v-container class="predict-area" style="background-color: #F5F5F6;">
      <v-col cols="12" class="trend-area">
        <v-container class="trend-container">
          <p class="trend-category">예측트렌드 키워드</p>
          <v-row>
            <v-col cols="12" lg="4">
              <v-select
                label="원하는 년도"
                v-model="select.year"
                :items="year"
                filled
              ></v-select>
            </v-col>
            <v-col cols="12" lg="4">
              <v-select
                label="원하는 월"
                v-model="select.month"
                :items="month"
                filled
              ></v-select>
            </v-col>
            <v-col cols="12" lg="4">
              <v-select
                label="원하는 주"
                v-model="select.week"
                :items="week"
                filled
              ></v-select>
            </v-col>
          </v-row>
          <v-row justify="center">
            <p><strong>{{ select.year }}년 {{ select.month }}월 {{ select.week }}주</strong> 의 트렌드 예측 키워드</p>
          </v-row>
          <v-row justify="center">
            <v-col v-for="(keyword, i) in predictKeywordList" :key="i" cols="12" lg="3">
              <!-- <router-link :to="{ name: 'TrendDetailNews', params: { trendId: trend.trendId }}" style="text-decoration:none; color:#222222;">   -->
                <div class="text-center trend-keyword">
                  <div>
                    <p>카테고리</p>
                    <p>{{ keword.name }}</p>
                  </div>
                </div>
              <!-- </router-link> -->
            </v-col>
          </v-row>
        </v-container>
      </v-col>
    </v-container>
  </div>
</template>

<script>
import Navbar from '@/components/common/Navbar2'
import axios from 'axios'
import SERVER from '@/lib/api'
import { mapGetters } from 'vuex'

export default {
  name: 'TrendList',
  components: { Navbar },
  data() {
    return {
      predictKeywordList: [],
      year: [2020],
      month: [ 6,7,8,9,10 ],
      week: [ 1,2,3,4,5],
      select: {
        year: 2020,
        month: 11,
        week: 1,
      }
    }
  },
  computed: {
    ...mapGetters('userStore', ['config']),
  },
  created() {
    this.getPredictTrends()
  },
  methods: {
    getPredictTrends() {
      axios 
        .get(SERVER.URL + SERVER.ROUTES.trends.getPredictInfo +this.selection.year + '/' + this.selection.month + '/' + this.selection.week, this.config)
        .then((res) => { this.predictKeywordList = res.data.data })
        .catch((err) => { console.log(err)})
    }
  },
}
</script>

<style lang="scss" scoped>
.predict-area {
  position: relative;
  width: 100%;
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

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
  padding: 20px 0;
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