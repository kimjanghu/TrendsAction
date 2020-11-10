<template>
  <div>
    <div class="font-weight-normal d-flex justify-space-between">
      <div><strong>{{ comment.nickname }}</strong> <span class="ml-1">@{{ comment.date }}</span></div>
      <div class="mb-3" v-if="userInfo.nickname == comment.nickname" >
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
          @click="editComment(comment.id)"
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
                  @click="deleteComment(comment.id)"
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
import axios from 'axios'
import SERVER from '@/lib/api'
import { mapGetters } from 'vuex'

export default {
  props: ['comment', 'userInfo'],
  components: {
  },
  data() {
    return {
      isEdit: false,
      dialog: false,
    }
  },
  computed: { 
    ...mapGetters('userStore', ['config'])
  },
  methods: {
    editComment(commentId) {
      let body = {
        content: this.comment.content,
        userId: this.userInfo.id,
        trendId: 1,
        id: commentId,
      }
      axios
        .put(SERVER.URL + SERVER.ROUTES.comments.cuComment, body, this.config)
        .then((res) => { 
          console.log(res);
          this.isEdit = false
          })
        .catch((err) => { console.log(err)})
    },
    deleteComment(commentId) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.comments.rdComment + '/' + commentId, this.config)
        .then((res) => { 
          console.log(res)
          this.$emit('deleteComment')
          this.dialog = false
          })
        .catch((err) => console.log(err))
    }
  }
}
</script>

<style scoped>
.custom-rounded {
  border-radius: 4px;
}

</style>