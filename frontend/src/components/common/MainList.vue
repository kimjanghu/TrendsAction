<template>
  <div>
    <v-card
      class="ml-10 card-list"
      width="300"
      height="400"
      tile
    >
      <v-list flat>
        <v-subheader>Category</v-subheader>
        <v-list-item-group
          v-model="selectedItem"
          color="primary"
        >
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
          >
            <v-list-item-icon>
              <v-icon v-text="item.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'MainList',
  data() {
    return {
      selectedItem: 1,
      items: [
        { text: 'Real-Time', icon: 'mdi-clock' },
        { text: 'Audience', icon: 'mdi-account' },
        { text: 'Conversions', icon: 'mdi-flag' },
        { text: 'Conversions', icon: 'mdi-flag' },
        { text: 'Conversions', icon: 'mdi-flag' },
        { text: 'Conversions', icon: 'mdi-flag' },
        { text: 'Conversions', icon: 'mdi-flag' },
        { text: 'Conversions', icon: 'mdi-flag' },
      ],
    }
  }
}
</script>

<style lang="scss" scoped>
.card-list {
  overflow: scroll;
}
</style>