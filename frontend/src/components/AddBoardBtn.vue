<template>
  <v-card-actions>
    <v-list-item class="grow">
      <v-row
        align="center"
        justify="end"
      >
        <v-dialog
          v-model="dialog"
          scrollable
          max-width="400px"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-icon class="mr-1" 
              v-bind="attrs"
              v-on="on">
                mdi-share-variant
            </v-icon>
            <!-- <span class="subheading">45</span> -->
          </template>
          <v-card>
            <v-card-title>
              <span>트렌드보드에 추가</span>
              <v-spacer></v-spacer>
              <i class="fas fa-times" @click="dialog = false" style="cursor:pointer;"></i>
            </v-card-title>
            <v-divider></v-divider>
            
            <v-card-text style="height: 300px;">
              <v-container>
                <v-list shaped>
                  <v-list-item-group
                    v-model="model"
                    multiple
                  >
                    <template v-for="(item, i) in myboards">
                      <v-divider
                        v-if="!item"
                        :key="`divider-${i}`"
                      ></v-divider>

                      <v-list-item
                        v-else
                        :key="`item-${i}`"
                        :value="item"
                        active-class="deep-purple--text text--accent-4"
                      >
                        <template v-slot:default="{ active }">
                          <v-list-item-content>
                            <v-list-item-title v-text="item"></v-list-item-title>
                          </v-list-item-content>

                          <v-list-item-action>
                            <v-checkbox
                              :input-value="active"
                              color="deep-purple accent-4"
                            ></v-checkbox>
                          </v-list-item-action>
                        </template>
                      </v-list-item>
                    </template>
                  </v-list-item-group>
                </v-list>
              </v-container>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions class="ml-3 py-3">
              <span><i class="fas fa-plus-circle mr-1"></i>새그룹 추가</span>
              <v-spacer></v-spacer>
              <v-btn
                color="blue darken-1"
                text
                @click="dialog = false"
              >
                Action
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
    </v-list-item>
  </v-card-actions>
</template>

<script>
export default {
  data () {
      return {
        dialogm1: '',
        dialog: false,
        model: [],
        myboards: ['news', 'project', 'favorite', '꼴라쥬']
      }
    },
  methods: {
  }

}
</script>

<style>

</style>