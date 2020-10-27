<template>

  <div>
    <div class="font-weight-normal d-flex justify-space-between">
      <div><strong>{{ comment.user_id }}</strong> @{{ comment.date }}</div>
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
          v-model="dialog2"
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
          <v-card>
            <v-card-text>정말 삭제하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-btn
                color="green darken-1"
                text
                @click="dialog2 = false"
              >
                아니오
              </v-btn>
              <v-btn
                color="green darken-1"
                text
                @click="dialog2 = false"
              >
                네
              </v-btn>
            </v-card-actions>
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
      dialog1: false,
      dialog2: false,
    }
  },
  methods: {
  }
  

}
</script>

<style>

</style>