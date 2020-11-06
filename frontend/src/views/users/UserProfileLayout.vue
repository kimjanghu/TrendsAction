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
            size="100"
          >
            <img
              :src="profile"
            >
          </v-avatar>
          
          <span class="mt-3">{{ nickname }}님의 마이페이지</span>
          <span>{{ email }}</span>
          <div v-if="categoryName">
            {{ categoryName }}
            <!-- <div v-for="category in categoryList" :key="category"  class="d-flex justify-center">
              <span>{{ category }}</span>
            </div> -->
          </div>
        </v-sheet>
        <v-btn dark class="mt-2 custom-rounded" width="100%" color="teal" :to="{ name: 'BoardList' }">내 보드</v-btn>
        <v-btn dark class="mt-2 custom-rounded" width="100%" color="teal" :to="{ name: 'UserProfile' }">내 프로필 수정하기</v-btn>
      </v-col>
      
      <v-col cols="12" md="8" lg="9" >
        <router-view></router-view>
      </v-col>
    
    </v-row>
  </v-container>
  </div>

</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'UserProfileLayout',
  components: {

  },
  data() { 
    return {
      email: '',
      nickname: '',
      profile: '',
      categoryName: '',
      userId: window.localStorage.getItem('userId'),
    }
  },
  computed: {

  },
  methods: {
    ...mapActions('userStore', ['getUserInfo']),
  },
  created() {
    this.getUserInfo()
      .then(data => {
        this.email = data.email
        this.nickname = data.nickname
        const categoryName = data.categoryName.join(', ')
        this.categoryName = categoryName
        if (data.profile) {
          this.profile = data.profile
        // } else {
        //   this.profile = 'https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light'
        }
      })
  },
  mounted() {
    
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