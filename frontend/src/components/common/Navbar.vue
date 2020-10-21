<template>
  <div class="navbar transparent">
    <div class="web-title">
      <h2>TrendsAction</h2>
    </div>
    <div class="router-area">
      <!-- <p class="router-name">
        Trend
      </p>
      <p class="router-name">
        Predict
      </p>
      <p class="router-name">
        Login
      </p> -->
      <v-tabs align-with-title>
        <v-tab>Trend</v-tab>
        <v-tab>Predict</v-tab>
        <v-tab>Login</v-tab>
      </v-tabs>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Navbar'
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 100px;
  width: 85%;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: rgba(255, 255, 255, 0.5);
  
  .router-area {
    display: flex;
    align-items: center;

    .router-name {
      cursor: pointer;
      color: #01579b;
      margin: 0 20px;
    }

    .router-name:hover {
      font-weight: bold;
    }
  }
}
</style>