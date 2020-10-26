<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md ="4" lg="3" xl="2">
        <v-sheet
          class="d-flex flex-column justify-center align-center"
          color="teal lighten-3"
          :height="$vuetify.breakpoint.smAndDown? 300:330"
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
          <v-dialog
            v-model="dialog"
            persistent
            max-width="400px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn dark rounded class="mt-2" color="teal" v-bind="attrs" v-on="on">내 프로필 변경</v-btn>
            </template>
            <v-card>
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
          
        </v-sheet>
        
      </v-col>
      <v-col cols="12" md="8" lg="7" xl="6">
        <v-sheet
          class="px-5 py-2"
          color="grey lighten-3"
        >
        <v-row>
          <v-col cols="12" md="6" v-for="n in 4" :key="n">
            <v-card color="white" :height="$vuetify.breakpoint.smAndDown? 250:200" ></v-card>
          </v-col>
        </v-row>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() { 
    return {
      dialog: false,
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