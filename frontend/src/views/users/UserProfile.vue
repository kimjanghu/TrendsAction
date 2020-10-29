<template>
<v-container class="pt-0">
  <v-sheet
    class="px-5 py-2"
    color="grey lighten-3"
  >
    <v-row>
      <v-col cols="12" md="4">
        <v-text-field label="이메일"></v-text-field>
        <v-text-field label="닉네임"></v-text-field>

      </v-col>
    </v-row>
  </v-sheet>
  <v-row justify="end">
    <UserProfileUpdate />
  </v-row>
</v-container>
</template>

<script>
import UserProfileUpdate from './UserProfileUpdate.vue'

export default {
  components: { UserProfileUpdate }
}
</script>

<style>

</style>