<template>
  <div>
    <Navbar />
    <v-container class="mt-16">
      <v-row>
        <v-col cols="12" md="9">
          <router-view :key="$route.fullPath"></router-view>
        </v-col>

        <v-col cols="12" md="3">
          <div class="set-sticky">
            <div class="neumor-design">
            <v-list class="pt-0 neumor-design" style="background: #F5F5F6;">
              <v-container>
                <v-row>
                  <v-col cols="12" class="py-0">
                    <v-select
                      label="원하는 년도"
                      v-model="selection.year"
                      :items="year"
                      filled
                    ></v-select>
                  </v-col>
                  <v-col cols="12" class="py-0">
                    <v-select
                      label="원하는 월"
                      v-model="selection.month"
                      :items="month"
                      filled
                    ></v-select>
                  </v-col>
                  <v-col cols="12" class="py-0">
                    <v-select
                      label="원하는 주"
                      v-model="selection.week"
                      :items="week"
                      filled
                    ></v-select>
                  </v-col>
                  <v-col cols="12" class="py-0">
                    <v-chip-group
                      color="primary"
                      text
                      mandatory
                      label
                    > 
                      <v-chip v-for="(category, i) in categories" :key="i">
                        <div @click="changeCategory(category.id)">
                        {{ category.name }}
                        </div>    
                      </v-chip>
                    </v-chip-group>
                  </v-col>
                  <v-col cols="12">
                    <v-btn width="100%" class="py-5" color="primary" @click="getPredictInfo(selection)">트랜즈 액션!</v-btn>
                  </v-col>
                </v-row>
              </v-container>

            </v-list>
            </div>
          </div>
          
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import Navbar from '@/components/common/Navbar2'

import axios from 'axios'
import SERVER from '@/lib/api'
import { mapGetters } from 'vuex'

export default {
  name: 'PredictLayout',
  components: { Navbar, },
  props: [ 'trendId', 'categoryId'],
  computed: {
    ...mapGetters('userStore', ['config']),
  },
  data() {
    return {
      year: [2020],
      month: [10, 11],
      week: [1, 2, 3, 4, 5],
      categories: [
        { id:9, name:'IT과학'},
        { id:10, name:'사회'},
        { id:11, name:'생활문화'},
        { id:12, name:'세계'},
      ],
      selection: {
        year: 2020,
        month: 10,
        week: 1,
        category: 9 
      },
      predictInfo: {},
      trendInfo: {},
    }
  },
  created() {
    
  },
  methods: {
    changeYear(year) {
      this.selection.year = year;
      this.getPredictInfo(this.selection)
    },
    changeMonth(month) {
      this.selection.month = month;
      this.getPredictInfo(this.selection)
    },
    changeWeek(week) {
      this.selection.week = week;
      this.getPredictInfo(this.selection)
    },
    changeCategory(category) {
      this.selection.category = category;
    },
    getTrendInfo() {
        axios
          .get(SERVER.URL + SERVER.ROUTES.trends.getTrendInfo + this.categoryId + '/' + this.trendId)
          .then((res) => {
            this.trendInfo = res.data })
          .catch((err) => {console.log(err)})
      },
    getPredictInfo(obj) {
      axios 
        .get(SERVER.URL + SERVER.ROUTES.trends.getPredictInfo + obj.year + '/' + obj.month + '/' + obj.week + '/' + obj.category)
        .then((res) => { 
          this.$router.push({ name: 'PredictDetail', params: { categoryId: res.data.categoryId , trendId: res.data.id }});
          })
        .catch((err) => { console.log(err)})
    }
  }
}
</script>

<style scoped>
.neumor-design {
  padding: 2%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}

.custom-link {
  text-decoration: none;
  color:black;
}

.set-sticky {
  position: -webkit-sticky;
  position: sticky;
  top: 100px;
}
</style>