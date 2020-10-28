<template>
<div>
  <v-container>
    <v-row justify="center">
      <v-col cols="12" md ="4" lg="3">
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
          <span>관심카테고리</span>
          
        </v-sheet>
        <v-btn dark class="mt-2 custom-rounded" width="100%" color="teal" :to="{ name: 'UserProfileUpdate' }">내 프로필 관리</v-btn>
        <v-btn dark class="mt-2 custom-rounded" width="100%" color="teal" :to="{ name: 'BoardList' }">내 보드 관리</v-btn>
      </v-col>
      
      <v-col cols="12" md="8" lg="9" >
        <router-view></router-view>
      </v-col>
    
    </v-row>
  </v-container>
  </div>

</template>

<script>
export default {
  components: {
  },
  data() { 
    return {
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

<style scoped>
.custom-rounded {
  border-radius: 4px;
}

.header {
  background-color: #777777;
  height: 300px;
  width: 100%;
}

.main-section {
  background-color:#efefef;
  width: 100%;
}

</style>