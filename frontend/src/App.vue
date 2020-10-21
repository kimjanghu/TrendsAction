<template>
  <v-app>
    <div>
      <Navbar />
    </div>
    <v-main class="main-area">
      <router-view 
        :key="$route.fullPath"
      ></router-view>
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import Navbar from '@/components/common/Navbar'
import Footer from '@/components/common/Footer'

export default {
  name: 'App',

  components: {
    Navbar,
    Footer
  },

  data: () => ({
    //
  }),
};
</script>

<style scoped>
/* .main-area {
  width: 85%;
  margin: 0 auto;
} */
</style>
