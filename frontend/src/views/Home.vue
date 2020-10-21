<template>
  <div class="main-area">
    <div>
      <Carousel />
    </div>
    
    
  </div>
</template>

<script>
import Carousel from '@/components/common/Carousel'

export default {
  name: 'Home',
  components: {
    Carousel
  }
}
</script>

<style scoped>
.main-area {
  margin: 100px auto;
}
</style>
