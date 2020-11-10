<template>
  <div>
    <v-row justify="center">
      <v-col cols="12">
        <div class="neumor-design">
          <v-img
            width="100%"
            :height="$vuetify.breakpoint.smAndDown ? 300 : 300"
            lazy-src="https://picsum.photos/id/11/10/6"
            gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
            src="https://picsum.photos/id/11/500/300"
            class="trend-img"
            style="border-radius: 19px;"
          >
            <div class="trend-desc">
              <v-chip
                class="white--text ml-0 mb-2"
                color="purple"
                label
                small
              >
                <span>카테고리</span>
              </v-chip>
              <div style="color:white;">
                <p class="text-h6">예측키워드 타이틀</p>
                <span>키워드 description</span>
              </div>
            </div>
          </v-img>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>