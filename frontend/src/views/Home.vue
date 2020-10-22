<template>
  <div>
    <Header />
    <div class="main-area d-flex">
      <Carousel />
      <MainList />
    </div>
    <v-container>
      
    </v-container>
  </div>
</template>

<script>
import Carousel from '@/components/common/Carousel'
import MainList from '@/components/common/MainList'
import Header from '@/components/common/Header'

export default {
  name: 'Home',
  components: {
    Carousel,
    MainList,
    Header
  },
  data() {
    return {
      
    }
  }
}
</script>

<style scoped>
.main-area {
  margin: 20px auto;
  width: 85%;
}
</style>