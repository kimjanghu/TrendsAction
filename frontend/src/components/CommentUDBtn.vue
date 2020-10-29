<template>

  <div>
    <div class="font-weight-normal d-flex justify-space-between">
      <div><strong>{{ comment.nickname }}</strong> <span class="ml-1">@{{ comment.date }}</span></div>
      <div class="mb-3">
        <v-btn
          small
          color="primary"
          dark
          class="mr-2"
          @click="isEdit = true"
          v-if="isEdit == false"
        >
          수정
        </v-btn>
        <v-btn
          small
          color="primary"
          dark
          class="mr-2"
          v-else
          @click="isEdit = false"
        >
          저장
        </v-btn>


        <v-dialog
          v-model="dialog"
          persistent
          max-width="290"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              small
              color="primary"
              dark
              v-bind="attrs"
              v-on="on"
            >
              삭제
            </v-btn>
          </template>
          <v-card class="custom-rounded">
            <v-card-title>
              <v-spacer></v-spacer>
              <i class="fas fa-times" @click="dialog = false" style="cursor:pointer;"></i>
            </v-card-title>
            <v-card-text class="text-center">
              <div><strong>🚨경고🚨</strong><br />정말 삭제하시겠습니까?</div>
              <div class="mt-3">
                <v-btn
                  small
                  dark
                  color="primary"
                  @click="dialog = false"
                >
                  네
                </v-btn>
                <v-btn
                  class="ml-2"
                  small
                  dark
                  color="primary"
                  @click="dialog = false"
                >
                  아니오
                </v-btn>
              </div>
            </v-card-text>
          </v-card>
        </v-dialog>
      </div>
    </div>
    <div v-if="isEdit == false">{{ comment.content }}</div>
    <div v-else>
      <v-textarea
        label="의견 수정"
        filled
        v-model="comment.content"
      ></v-textarea>
    </div>
  </div>

</template>

<script>

export default {
  props: ['comment'],
  components: {
  },
  data() {
    return {
      isEdit: false,
      dialog: false,
    }
  },
  methods: {
  }
  

}
</script>

<style scoped>
.custom-rounded {
  border-radius: 4px;
}

</style>