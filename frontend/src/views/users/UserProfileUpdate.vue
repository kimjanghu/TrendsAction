<template>
  <v-dialog
    v-model="dialog"
    persistent
    max-width="400px"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn dark class="mt-2 custom-rounded" width="100%" color="teal" v-bind="attrs" v-on="on">내 프로필 변경</v-btn>
    </template>
    <v-card class="custom-rounded">
      <v-card-title>
        <span>내정보 변경</span>
        <v-spacer></v-spacer>
        <i class="fas fa-times" @click="dialog = false" style="cursor:pointer;"></i>
      </v-card-title>
      <v-card-text class="pt-5 pb-0">
        <v-text-field
          label="닉네임"
          placeholder="닉네임을 입력해주세요"
          outlined
          class="px-0 py-0 mb-0 custom-rounded"
        ></v-text-field>
        <v-combobox
          class="custom-rounded"
          v-model="select"
          :items="items"
          label="관심 카테고리"
          multiple
          outlined
        ></v-combobox>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          text
          @click="dialog = false"
        >
          수정
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
    }
  }

}
</script>

<style>

</style>