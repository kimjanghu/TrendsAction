<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      scrollable
      persistent
      max-width="400px"
    >
      <v-card class="custom-rounded">
        <v-card-title>
          <span class="headline">Board Member</span>
          <v-spacer></v-spacer>
          <v-btn
            color="#000"
            text
            @click="changeMemberDialog"
          >
            <v-icon>
              mdi-close
            </v-icon>
          </v-btn>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col
                cols="12"
              >
                <p>Host</p>
                <hr>
                <div v-if="hosts.length" class="avatar-profile-area">
                  <div class="member-profile">
                    <div class="avartar-profile">
                      <v-avatar
                        color="#efefef"
                        size="30"
                      >
                        <img
                          :src="hosts[0].profile"
                        >
                      </v-avatar>
                      <span class="avatar-text">{{ hosts[0].nickname }}</span>
                    </div>
                  </div>
                </div>
              </v-col>
              <v-col
                cols="12"
              >
                <p>Maintainer</p>
                <hr>
                <div v-if="maintainers.length" class="avatar-profile-area">
                  <div v-for="maintainer in maintainers" :key="maintainer.nickname" class="member-profile" :data-id="maintainer.userId">
                    <div class="avartar-profile">
                      <v-avatar
                        color="#efefef"
                        size="30"
                      >
                        <img
                          :src="maintainer.profile"
                        >
                      </v-avatar>
                      <span class="avatar-text">{{ maintainer.nickname }}</span>
                    </div>
                    <div v-if="authority === 'host'">
                      <span style="color: black; font-size: 1rem; cursor:pointer; margin-right: 10px">
                        <i class="fas fa-exchange-alt" @click="reqAuthorityData(maintainer.userId, 'guest')"></i>
                      </span>
                      <span style="color: red; font-size: 1rem; cursor:pointer; margin-right: 10px">
                        <i class="fas fa-minus-circle" @click="asyncRemoveMember(maintainer.userId)"></i>
                      </span>
                    </div>
                  </div>  
                </div>
                <div v-else>
                  <p class="empty-member-text">현재 Maintainer가 없습니다.</p>
                </div>
              </v-col>
              <v-col
                cols="12"
              >
                <p>Guest</p>
                <hr>
                <div v-if="guests.length" class="avatar-profile-area">
                  <div v-for="guest in guests" :key="guest.nickname" class="member-profile" :data-id="guest.userId">
                    <div class="avartar-profile">
                      <v-avatar
                        color="#efefef"
                        size="35"
                      >
                        <img
                          :src="guest.profile"
                        >
                      </v-avatar>
                      <span class="avatar-text">{{ guest.nickname }}</span>
                    </div>
                    <div v-if="authority === 'host'">
                      <span style="color: black; font-size: 1rem; cursor:pointer; margin-right: 10px">
                        <i class="fas fa-exchange-alt" @click="reqAuthorityData(guest.userId, 'maintainer')"></i>
                      </span>
                      <span style="color: red; font-size: 1rem; cursor:pointer; margin-right: 10px">
                        <i class="fas fa-minus-circle" @click="asyncRemoveMember(guest.userId)"></i>
                      </span>
                    </div>
                  </div>  
                </div>
                <div v-else>
                  <p class="empty-member-text">현재 Guest가 없습니다.</p>
                </div>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'

export default {
  name: 'BoardsMember',
  props: {
    dialog: {
      type: Boolean
    }
  },
  computed: {
    ...mapGetters('userStore', ['config']),
    ...mapState('boardStore', [
      'hosts', 
      'maintainers',
      'guests',
      'authority'
    ]),
  },
  methods: {
    ...mapActions('boardStore', [
      'getBoardMember',
      'removeMember',
      'changeAuthority'
    ]),
    changeMemberDialog() {
      this.$emit('change-member-dialog', false)
    },
    checkMemberProfile(userId) {
      alert('강퇴에 성공했습니다.')
      const deleteBtn = document.querySelector(`.member-profile[data-id="${userId}"]`)
      deleteBtn.remove();
    },
    reqAuthorityData(userId, authority) {
      const changeData = {
        userId: userId,
        boardId: +this.$route.params.boardId,
        authority: authority
      }
      const check = confirm('해당 사용자의 권한을 변경하시겠습니까?')

      if (check) {
        this.changeAuthority(changeData)
      }
    },
    async asyncRemoveMember(userId) {
      const removeData = {
        userId: userId,
        boardId: this.$route.params.boardId
      }
      const check = confirm('유저를 강퇴하시겠습니까?')

      if (check) {
        await this.removeMember(removeData)
        await this.checkMemberProfile(userId)
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.custom-rounded {
  border-radius: 4px;
}

.avatar-profile-area {
  display: flex;
  flex-direction: column;

  .member-profile {
    display: flex;
    justify-content: space-between;
    align-items: center;

    .avartar-profile {
      margin: 10px 0 10px 10px;

      .avatar-text {
        margin-left: 10px;
        font-size: 12px;
      }
    }
  }
}

.empty-member-text {
  margin: 20px 0;
  text-align: center;
}
</style>