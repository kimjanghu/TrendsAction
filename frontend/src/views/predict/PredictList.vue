<template>
  <v-container>
    <h2>{{ $route.params.year }}년 {{ $route.params.month }}월</h2>
    <v-chip-group
      v-model="weekSelection"
      color="indigo"
      text
      mandatory
      label
    >
      <v-chip v-for="(each, i) in month" :key="i">
        <router-link class="custom-link" :to="{name: 'PredictDetail', params: { year: $route.params.year, month: $route.params.month, week: i+1, categoryId: categorySelection }}">
          <div class="custom-link">{{ each.value }}주</div>
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
        <router-link class="custom-link" :to="{name: 'PredictDetail', params: { year: $route.params.year, month: $route.params.month, week: weekSelection+1, categoryId: category.id }}">
          {{ category.value }}    
        </router-link>
      </v-chip>
    </v-chip-group>

    <router-view></router-view>
  
  </v-container>
</template>

<script>
export default {
  data() {
    return {
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
      ]
    }
  }

}
</script>

<style scoped>
.custom-link {
  text-decoration: none;
}

</style>