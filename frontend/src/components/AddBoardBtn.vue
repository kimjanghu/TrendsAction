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
          <v-card class="custom-rounded">
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
              <v-dialog
                v-model="dialog2"
                persistent
                max-width="420px"
              >
                <template v-slot:activator="{ on, attrs }">
                  <span v-bind="attrs" v-on="on">
                    <i class="fas fa-plus-circle mr-1"></i>새그룹 추가
                  </span>
                </template>
                <v-card class="custom-rounded">
                  <v-card-title>
                    <span>새로운 보드 만들기</span>
                    <v-spacer></v-spacer>
                    <i class="fas fa-times" @click="dialog2 = false" style="cursor:pointer;"></i>
                  </v-card-title>
                  <v-divider></v-divider>
                  <v-card-text class="pt-5 pb-0">
                    <v-row justify="center">
                      <v-col
                        cols="9"
                      >
                        <v-text-field
                          v-model="newBoard"
                          placeholder="트렌드보드 이름"
                          outlined
                          dense
                          class="px-0 py-0 mb-0 custom-rounded"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="3">
                        <v-btn dark color="blue" width="75px" height="40px" class="custom-rounded" @click="addNewBoard">저장</v-btn>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </v-dialog>
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
        dialog2: false,
        newBoard: '',
        model: [],
        myboards: ['news', 'project', 'favorite', '꼴라쥬']
      }
    },
  methods: {
    addNewBoard() {
      this.myboards.push(this.newBoard);
      this.dialog2 = false;
      this.newBoard = '';
    }
  }

}
</script>

<style scoped>
.custom-rounded {
  border-radius: 4px;
}

</style>