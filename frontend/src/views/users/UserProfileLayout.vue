<template>
<div class="wrapper">
  <Navbar />
  <v-container class="mt-16">
    <v-row justify="center">
      <v-col cols="12" md ="4" lg="3">
        <v-sheet
          class="d-flex flex-column justify-center align-center neumor-design"
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
          
          <span class="mt-5 font-weight-bold" style="font-size: 1.1rem;">{{ nickname }}</span>
          <span>{{ email }}</span>
          <div v-if="categoryName" class="mt-5">
            <span class="my-favorite" v-for="(category, i) in categoryName" :key="i">
              {{ category }}
            </span>
          </div>
        </v-sheet>
        <router-link :to="{ name: 'BoardList', params: { id: userId } }">
          <input type="button" value="나의 트렌드 보드" class="neumor-design customBtn">
        </router-link>
        <router-link :to="{ name: 'UserProfile' }">
          <input type="button" value="나의 정보 수정" class="neumor-design customBtn">
        </router-link>
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
      categoryName: [],
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
        console.log(data)
        this.email = data.email
        this.nickname = data.nickname
        this.categoryName = data.categoryName
        this.profile = data.profile
      })
  },
  mounted() {
    
  }
}
</script>

<style scoped>
.wrapper {
  min-height: 100vh;
}
.custom-rounded {
  border-radius: 4px;
}

.customBtn {
  color:black; 
  width:100%; 
  height: 50px;
  margin-top: 1rem;
}

.customBtn:hover {
  border: none;
  border-radius: 25px;
  background: #F5F5F6;
  box-shadow: inset 11px 11px 22px #e4e4e5, 
              inset -11px -11px 22px #ffffff;
  cursor: pointer;
}

.customBtn:focus {
  outline: none;
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

.neumor-design {
  padding: 2%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}

.my-favorite {
  margin-right: 2px;
  border: 2px solid rgb(226, 226, 226);
  border-radius: 10px;
  background-color: rgb(226, 226, 226);
  padding: 2px 5px;
  text-align: center;
}

</style>