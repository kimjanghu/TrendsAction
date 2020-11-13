<template>
<div>
  <Navbar />
  <v-container class="mt-16">
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
        <v-btn dark class="mt-2 custom-rounded" width="100%" color="teal" :to="{ name: 'BoardList', params: { id: userId } }">내 보드</v-btn>
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
import Navbar from '@/components/common/Navbar2'
import { mapActions } from 'vuex'

export default {
  name: 'UserProfileLayout',
  components: {
    Navbar,
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
        this.profile = data.profile
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