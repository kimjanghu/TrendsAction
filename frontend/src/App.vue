<template>
  <v-app>
    <div>
      <Navbar />
    </div>
    <Header />
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
import Header from '@/components/common/Header'
import Footer from '@/components/common/Footer'

export default {
  name: 'App',

  components: {
    Navbar,
    Header,
    Footer
  },

  data: () => ({
    //
  }),
};
</script>

<style scoped>
.main-area {
  width: 85%;
  margin: 0 auto;
}
</style>
