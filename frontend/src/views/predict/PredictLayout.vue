<template>
  <v-container class="mt-10">
    <v-row>

      <v-col cols="12" md="9">
        <router-view></router-view>
      </v-col>

      <v-col cols="12" md="3">
        <div class="neumor-design set-sticky">
        <v-list class="pt-0 neumor-design" style="background: #F5F5F6;">
          <v-list-group
            v-for="(category, i) in categories"
            :key="i"
            v-model="category.active"
            no-action
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title style="color: black;" v-text="`${category.year} 년`"></v-list-item-title>
              </v-list-item-content>
            </template>
            <v-list-item
              v-for="each in category.month"
              :key="each.title"
            >
              <v-list-item-content>
                <router-link class="custom-link" :to="{name: 'PredictDetail', params: {year: category.year, month: each.title, week:1, categoryId: 1 }}">
                <v-list-item-title v-text="`${each.title} 월`"></v-list-item-title>
                </router-link>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </v-list>
        </div>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'PredictList',
  data() {
    return {
      categories: [
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