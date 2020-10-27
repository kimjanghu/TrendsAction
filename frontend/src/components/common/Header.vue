<template>
  <div class="header-area">
    <img src="https://source.unsplash.com/random" alt="">
  </div>
</template>

<script>
export default {
  name: 'Header'
}
</script>

<style>
.header-area {
  display: block;
  position: relative;
}

.header-area img {
  width: 100%;
  height: 550px;
  background-size: cover;
}

/* .header-area::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 550px;
  background-color: rgba(0, 0, 0, 0.5);
} */
</style>