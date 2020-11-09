<template>
  <v-container class="mt-10">
    <v-row>
      <v-col cols="12" md="3">
        <v-list>
          <v-list-group
            v-for="item in items"
            :key="item.title"
            v-model="item.active"
            no-action
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title v-text="item.title"></v-list-item-title>
              </v-list-item-content>
            </template>

            <v-list-item
              v-for="child in item.items"
              :key="child.title"
            >
              <v-list-item-content>
                <v-list-item-title v-text="child.title"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </v-list>
      </v-col>
      <v-col cols="12" md="9">
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
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'PredictList',
  data() {
    return {
      items: [
        {
          active: true,
          items: [
            { title: '1월', },
            { title: '2월', },
            { title: '3월', },
            { title: '4월', },
            { title: '5월', },
            { title: '6월', },
          ],
          title: '2020',
        },
        {
          items: [
            { title: '1월', },
            { title: '2월', },
            { title: '3월', },
            { title: '4월', },
            { title: '5월', },
            { title: '6월', },
          ],
          title: '2021',
        },
      ],
    }
  }

}
</script>

<style>

</style>