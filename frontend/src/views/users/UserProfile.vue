<template>
<v-container class="pt-0">
  <v-sheet
    class="px-5 py-2"
    color="grey lighten-3"
    v-if="!isupdate"
  >
    <v-row>
      <v-col cols="12" md="4">
        <v-text-field label="이메일"></v-text-field>
        <v-text-field label="닉네임"></v-text-field>
        <v-combobox
          class="custom-rounded"
          v-model="select"
          :items="items"
          label="관심 카테고리"
          multiple
          outlined
        ></v-combobox>

      </v-col>
    </v-row>
  </v-sheet>

  <v-sheet
    v-else
    class="px-5 py-2"
    color="grey lighten-3"

  >
    <v-row>
      <p>수정</p>
      <v-col cols="12" md="4">
        <v-text-field label="이메일"></v-text-field>
        <v-text-field label="닉네임"></v-text-field>
        <v-combobox
          class="custom-rounded"
          v-model="select"
          :items="items"
          label="관심 카테고리"
          multiple
          outlined
        ></v-combobox>

      </v-col>
    </v-row>
  </v-sheet>
  <v-row justify="end">
    <v-btn v-if="!isupdate" dark class="mt-2 custom-rounded mr-3" color="teal" @click="isupdate = true">수정</v-btn>
    <v-btn v-else dark class="mt-2 custom-rounded mr-3" color="teal" @click="isupdate = false">저장</v-btn>
  </v-row>
</v-container>
</template>

<script>
// import UserProfileUpdate from './UserProfileUpdate.vue'
export default {
  // components: { UserProfileUpdate },
  data() {
    return {
      isupdate : false,
      select: ['IT', '경제'],
      items: [
        'IT',
        '사회/문화',
        '경제',
        '스포츠',
      ],
    }
  }
}
</script>

<style>

</style>