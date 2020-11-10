<template>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      scrollable
      persistent
      max-width="400px"
    >
      <v-card>
        <v-card-title>
          <span class="headline">Board Member</span>
          <v-spacer></v-spacer>
          <v-btn
            color="blue darken-1"
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
                  <div class="avartar-profile">
                    <v-avatar
                      color="#efefef"
                      size="35"
                    >
                      <img
                        :src="hosts[0].profile"
                      >
                    </v-avatar>
                    <span class="avatar-text">{{ hosts[0].nickname }}</span>
                  </div>
                </div>
              </v-col>
              <v-col
                cols="12"
              >
                <p>Maintainer</p>
                <hr>
                <div>
                  <p class="empty-member-text">현재 Maintainer가 없습니다.</p>
                </div>
              </v-col>
              <v-col
                cols="12"
              >
                <p>Guest</p>
                <hr>
                <div v-if="guests.length" class="avatar-profile-area">
                  <div v-for="guest in guests" :key="guest.nickname">
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
import { mapState } from 'vuex'

export default {
  name: 'BoardsMember',
  props: {
    dialog: {
      type: Boolean
    }
  },
  computed: {
    ...mapState('boardStore', [
      'hosts', 
      'guests'
    ]),
  },
  methods: {
    changeMemberDialog() {
      this.$emit('change-member-dialog', false)
    },
  }
}
</script>

<style lang="scss" scoped>
.avatar-profile-area {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  
  .avartar-profile {
    margin: 10px 0;

    .avatar-text {
      margin-left: 10px;
      font-size: 15px;
    }
  }
}

.empty-member-text {
  margin: 20px 0;
  text-align: center;
}
</style>