<template>  
  <div>
  <header>
    <img src="@/assets/logo1.jpg" @click="goToHome" width="15%" style="cursor:pointer;" alt="logo">
    <div class="router-area" v-if="$vuetify.breakpoint.lgAndUp">
      <ul class="navbar-list">
        <li class="navbar-list-item" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Trend</li>
        <li class="navbar-list-item" @click="$router.push({ name: 'PredictList' })">Predict</li>
        <li class="navbar-list-item" @click="dialog = true" v-if="!isLogin">
          Login
          <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
        </li>
        <div v-else style="display:flex;">
          <li class="navbar-list-item" @click="logout()">Logout</li>
          <router-link
            class="navbar-icon"
            tag="button"
            :to="{ name: 'BoardList', params: { id: userId }}"
          >
            <span style="font-size: 1.5rem; cursor:pointer; margin-right: 10px;">
              <i class="fas fa-user-circle navbar-icon"></i>
            </span>
          </router-link>
          <span style="font-size: 1.5rem; cursor:pointer;">
            <i class="fas fa-bell navbar-icon" @click="getInviteMessage(); changeAlarm();"></i>
            <div v-if="isAlarm" class="board-alarm">
              <div v-if="messageList" class="board-alarm-area">
                <div v-for="message in messageList" :key="message.messageId">
                  <p class="board-alarm-text">{{ message.sendFromName }}님이 {{ message.boardId }} 보드에 {{ message.authority }}로 초대했습니다.</p>
                  <v-spacer></v-spacer>
                  <div class="d-flex align-center">
                    <span style="color: green; font-size: 1.5rem; cursor:pointer; margin-right: 5px;">
                      <i class="fas fa-check-circle" @click="acceptInvite(message.messageId, 'true')"></i>
                    </span>
                    <span style="color: red; font-size: 1.5rem; cursor:pointer; margin-right: 5px;">
                      <i class="fas fa-times-circle"  @click="acceptInvite(message.messageId, 'false')"></i>
                    </span>
                  </div>
                  <hr class="board-alarm-hr">
                </div>
              </div>
              <div v-else class="board-alarm-area">
                <p class="board-non-text">메시지가 없습니다.</p>
              </div>
            </div>
          </span>
        </div>
      </ul>
    </div>
    <div v-else>
      <span @click="toggleClassName" style="font-size: 1.5rem; cursor:pointer;">
        <i class="fas fa-bars navbar-icon toggle-icon"></i>
      </span>
    </div>
    
    <div class="sidebar">
      <div style="position:absolute; top:50px; left: 50px; font-size: 1.5rem; cursor:pointer;">
        <i class="fas fa-times" @click="toggleClassName"></i>
      </div>
      <ul style="text-align:center">
        <li style="cursor:pointer;" @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })">Trend</li>
        <li style="cursor:pointer;" @click="$router.push({ name: 'PredictList' })">Predict</li>
        <li style="cursor:pointer;" @click="dialog = true" v-if="!isLogin">
          Login
          <UsersLoginForm :dialog="dialog" @change-dialog="changeDialog" />
        </li>
        <div v-else>
          <li @click="logout()">Logout</li>
          <li style="cursor:pointer;" @click="$router.push({ name: 'BoardList', params: { id: userId }})">Mypage</li>
        </div>
      </ul>
    </div>
  </header>
  </div>
  
</template>

<script>
import UsersLoginForm from '@/components/users/UsersLoginForm'
import { mapGetters, mapActions, mapState } from 'vuex'
// import BoardsAlarm from '@/components/boards/BoardsAlarm'

export default {
  name: 'Navbar',
  data() {
    return {
      dialog: false,
      drawer: false,
      userId: null,
      group: null,
      isAlarm: false,
      messageList: []
    }
  },
  components: {
    UsersLoginForm,
    // BoardsAlarm
  },
  computed: {
    ...mapState('userStore', ['userInfo']),
    ...mapGetters('userStore', ['isLogin', 'config']),
  },
  watch: { 
    group () {
      this.drawer = false
    },
  },
  methods: {
    ...mapActions('userStore', ['logout', 'getUserInfo']),
    changeAlarm() {
      this.isAlarm = !this.isAlarm
    },
    changeDialog(dialog) {
      this.dialog = dialog
    },
    goToHome() {
      this.$router.push('/')
        .catch(err => {
          if(err.name != "NavigationDuplicated" ){
            throw err
          }
        })
    },
    toggleClassName() {
      let sidebar = document.querySelector('.sidebar');
      sidebar.classList.toggle('active')
    },
    acceptInvite(messageId, status) {
      const reqData = {
        messageId: +messageId,
        accepted: status
      }
      this.$http.post(this.$api.URL + this.$api.ROUTES.boards.acceptInvite, reqData, this.config)
        .then(res => {
          console.log(res)
        })
        .then(err => {
          console.log(err)
        })
    },
    getInviteMessage() {
      const userId = window.localStorage.getItem('userId')
      this.$http.get(this.$api.URL + this.$api.ROUTES.boards.getInviteMessage + `/${userId}`, this.config)
        .then(res => {
          this.messageList = res.data.data
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  mounted() {
    if (this.isLogin) {
      this.getUserInfo()
        .then(data => {
          this.userId = data.id
        })
    }

  },
}
</script>

<style lang="scss" scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 2;
  padding: 20px 8%;
  display: flex;
  align-items: center;
  justify-content: space-between;
  background-color: #F5F5F6;
}
header .logo {
  letter-spacing: 1px;
  font-size: 1.5rem;
  color: #111111;
  cursor: pointer;
}

header .navbar-list-item{
  color: #111111;
  transition: 0.3s;
}

header.sticky .navbar-icon{
  color: #111111;
  transition: 0.3s;
}

.navbar-list {
  list-style: none;
  display: flex;
} 

.navbar-list-item {
  margin: 0 30px;
  font-size: 18px;
  cursor: pointer;
}

.sidebar {
  position: fixed;
  // z-index: 3;
  top: 0;
  right: -60%;
  width: 60%;
  height: 100%;
  background-color: #efefef;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: 0.5s;
}

.sidebar.active {
  right: 0;
}

.sidebar ul {
  list-style: none;
}

.sidebar ul li {
  font-size: 1.5rem;
  font-weight: 500;
  margin: 30px 0;
}

.sidebar ul li:hover {
  color: #ffffff;
}

.navbar-link {
  cursor: pointer;
}

.navbar-icon:focus {
  outline: none;
}

.board-alarm {
  position: relative;

  .board-alarm-area {
    position: absolute;
    margin-top: 10px;
    right: 0;
    border: 5px solid rgb(134, 134, 134);
    border-radius: 10px;
    background: #F5F5F6;
    width: 350px;
    height: 400px;
    overflow: auto;
    padding: 15px;

    .board-alarm-text {
      color: #000;
      font-size: 15px;
      margin-left: 2px;
      margin-bottom: 0;
    }

    .board-alarm-hr {
      margin-bottom: 16px;
    }

    .board-non-text {
      position: absolute;
      color: #000;
      top: 50%;
      left: 50%;
      font-size: 20px;
      text-align: center;
      transform: translate(-50%, -50%);
    }
  }
}
</style>