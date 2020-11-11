<template>
  <div>
    <Navbar />
    <v-container class="mt-16">
      <v-row>
        <v-col cols="12" md="9">
          <router-view></router-view>
        </v-col>

        <v-col cols="12" md="3">
          <div class="set-sticky">
            <div class="neumor-design">
            <v-list class="pt-0 neumor-design" style="background: #F5F5F6;">
              <v-list-group
                v-for="(category, i) in dateCategories"
                :key="i"
                v-model="category.active"
                no-action
              >
                <template v-slot:activator>
                  <v-list-item-content @click="yearSelection = category.year">
                    <v-list-item-title style="color: black;" v-text="`${category.year} 년`"></v-list-item-title>
                  </v-list-item-content>
                </template>
                <v-list-item
                  v-for="each in category.month"
                  :key="each.title"
                >
                  <v-list-item-content @click="monthSelection = each.title">
                    <router-link class="custom-link" :to="{name: 'PredictDetail', params: {year: category.year, month: each.title, week:1, categoryId: 1 }}">
                      <v-list-item-title v-text="`${each.title} 월`"></v-list-item-title>
                    </router-link>
                  </v-list-item-content>
                </v-list-item>
              </v-list-group>
            </v-list>
            </div>
            <div class="mt-5 neumor-design">
              <i class="ml-3 my-3 fas fa-filter"></i>
              <span class="ml-2">filter</span>
              <v-chip-group
                v-model="weekSelection"
                color="indigo"
                text
                mandatory
                label
              >
                <v-chip v-for="(each, i) in month" :key="i">
                  <router-link class="custom-link" :to="{name: 'PredictDetail', params: { year: yearSelection, month: monthSelection, week: i+1, categoryId: categorySelection+1 }}">
                    <div>{{ each.value }}주</div>
                  </router-link>
                </v-chip>
              </v-chip-group>
              <v-chip-group
                v-model="categorySelection"
                color="primary"
                text
                mandatory
                label
              > 
                <v-chip v-for="(category, i) in categories" :key="i">
                  <router-link class="custom-link" :to="{name: 'PredictDetail', params: { year: yearSelection, month: monthSelection, week: weekSelection+1, categoryId: category.id }}">
                    {{ category.value }}    
                  </router-link>
                </v-chip>
              </v-chip-group>
            </div>
          </div>
          
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import Navbar from '@/components/common/Navbar2'

export default {
  name: 'PredictList',
  components: { Navbar, },
  data() {
    return {
      yearSelection: '2021',
      monthSelection: '11',
      weekSelection: '1',
      categorySelection: 0,
      categories: [
        { id: 1, value: '사회/문화'},
        { id: 2, value: 'IT'},
        { id: 3, value: '비즈니스'},
        { id: 4, value: '세계'},
      ],
      month: [
        { value: 1 },
        { value: 2 },
        { value: 3 },
        { value: 4 },
        { value: 5 },
      ],
      dateCategories: [
        {
          active: true,
          year: '2021',
          month: [
            { title: '6', },
            { title: '7', },
            { title: '8', },
            { title: '9', },
            { title: '10', },
            { title: '11', },
          ],
        },
      ],
    }
  },
  methods: {
    tmp(year) {
      this.yearSelection = year
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