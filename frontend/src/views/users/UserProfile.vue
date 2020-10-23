<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md ="4" lg="3" xl="2">
        <v-sheet
          class="d-flex flex-column justify-center align-center"
          color="teal lighten-3"
          :height="$vuetify.breakpoint.smAndDown? 200:350"
        >
        <v-avatar
          color="#efefef"
          size="92"
        >
          <img
          src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
          alt="John"
        >
        </v-avatar>
        <span class="mt-3">박싸피</span>
        <span>abcd@naver.com</span>
        <v-btn dark rounded class="mt-2" color="teal">내 프로필 변경</v-btn>
        </v-sheet>
        
      </v-col>
      <v-col cols="12" md="8" lg="7" xl="6">
        <v-sheet
          class="d-flex"
          color="grey lighten-3"
          :height="$vuetify.breakpoint.smAndDown? 200:350"
        >
         
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {

}
</script>

<style>

</style>