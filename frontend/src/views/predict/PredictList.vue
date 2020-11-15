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
          <v-row class="mb-5" justify="center" align="center">
            <v-btn @click="getPredictTrends(select)">확인!</v-btn>
          </v-row>
          <v-row justify="center" align="center">
            <p><strong>{{ select.year }}년 {{ select.month }}월 {{ select.week }}주</strong> 의 예측 트렌드 키워드</p>
          </v-row>
          <v-row >
            <v-col v-for="(keyword, i) in predictKeywordList" :key="i" cols="12" lg="3">
              <router-link :to="{ name: 'PredictDetail', params: { categoryId:keyword.categoryId , trendId: keyword.id }}" style="text-decoration:none; color:#222222;">  
                <div class="text-center trend-keyword">
                  <div>
                    <v-chip
                      class="white--text ml-0 mb-2"
                      color="purple"
                      label
                      small
                    >
                      <span v-if="keyword.categoryId == 9">IT과학</span>
                      <span v-else-if="keyword.categoryId == 10">사회</span>
                      <span v-else-if="keyword.categoryId == 11">생활문화</span>
                      <span v-else>세계</span>
                    </v-chip>
                    <p class="mb-0">{{ keyword.name }}</p>
                  </div>
                </div>
              </router-link>
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
  name: 'PredictList',
  components: { Navbar },
  data() {
    return {
      predictKeywordList: [],
      year: [2020],
      month: [10, 11],
      week: [ 1,2,3,4,5],
      select: {
        year: 2020,
        month: 10,
        week: 1,
      }
    }
  },
  computed: {
    ...mapGetters('userStore', ['config']),
  },
  created() {
    this.getPredictTrends({ year:2020, month:10, week:1})
  },
  methods: {
    getPredictTrends(obj) {
      axios 
        .get(SERVER.URL + SERVER.ROUTES.trends.getPredictInfo + obj.year + '/' + obj.month + '/' + obj.week, this.config)
        .then((res) => { this.predictKeywordList = res.data })
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