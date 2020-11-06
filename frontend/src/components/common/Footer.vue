<template>
  <v-footer
    dark
    padless
    width="100%"
  >
    <v-card
      width="100%"
      color="#222222"
      flat
      tile
      class="white--text text-center"
    >
      <v-card-text class="subtitle-1 mt-3">
        <span>TrendsAction</span>
        <span class="mx-3">|</span>
        <span>이용약관</span> 
        <span class="mx-3">|</span>
        <span>개인정보처리방침</span>
      </v-card-text>
      <v-card-text>
        <span>김동휘<a href="https://lab.ssafy.com/wheeking" target="_blank"><v-btn icon class="mr-4 white--text"><i class="fab fa-gitlab"></i></v-btn></a></span>
        <span>김남규<a href="https://lab.ssafy.com/kngking22" target="_blank"><v-btn icon class="mr-4 white--text"><i class="fab fa-gitlab"></i></v-btn></a></span>
        <span>김장후<a href="https://lab.ssafy.com/hopewill1030" target="_blank"><v-btn icon class="mr-4 white--text"><i class="fab fa-gitlab"></i></v-btn></a></span>
        <span>박지윤<a href="https://lab.ssafy.com/bellnuite" target="_blank"><v-btn icon class="mr-4 white--text"><i class="fab fa-gitlab"></i></v-btn></a></span>
        <span>최재빈<a href="https://lab.ssafy.com/justin613" target="_blank"><v-btn icon class="mr-4 white--text"><i class="fab fa-gitlab"></i></v-btn></a></span>
      </v-card-text>

      <v-divider></v-divider>

      <v-card-text class="white--text">
        Copyright@{{ new Date().getFullYear() }} — <strong>오나달</strong> with SSAFY
      </v-card-text>
    </v-card>
  </v-footer>
</template>

<script>
export default {
  name: 'Footer',
  data() {
    return {
      icons: [
        'mdi-facebook',
        'mdi-twitter',
        'mdi-linkedin',
        'mdi-instagram',
      ],
    }
  }
}
</script>

<style>

</style>