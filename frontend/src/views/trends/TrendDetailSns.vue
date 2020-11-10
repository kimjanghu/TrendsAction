<template>
  <v-container>
    <div v-if="snsList.length !== 0">
      <v-card
        class="mx-auto my-5 px-3 py-3"
        v-for="sns in snsList"
        :key="sns.id"
      >
        <v-card-title>
          <v-list-item-avatar color="grey darken-3">
              <v-img
                class="elevation-6"
                alt=""
                src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
              ></v-img>
            </v-list-item-avatar>

            <v-list-item-content>
              <v-list-item-title><span class="font-weight-black">{{ sns.author }}</span><span class="ml-3">@{{ sns.date }}</span></v-list-item-title>
            </v-list-item-content>
        </v-card-title>

        <v-card-text class="body-1 font-weight-bold">
          {{ sns.content }}
        </v-card-text>

        <AddBoardBtn v-if="isLogin" :userInfo="userInfo" :snsId="sns.id"/>
      </v-card>

      <div class="text-center">
        <v-pagination
          v-model="page"
          :length="3"
        ></v-pagination>
      </div>
    </div>
    <div v-else class="text-center my-10 mx-10">
      관련된 트윗이 없습니다.
      <v-btn @click="$router.push({ name: $constants.URL_TYPE.TREND.LIST })" color="primary" style="display:block; margin: 10px auto;">다른 키워드 보러가기</v-btn>
    </div>
  </v-container>
</template>

<script>
import AddBoardBtn from '@/components/AddBoardBtn.vue'
import axios from 'axios'
import SERVER from '@/lib/api'
import { mapGetters } from 'vuex';

export default {
  props: ['userInfo', 'trendId'],
  components: {
    AddBoardBtn
  },
  data() {
    return {
      page: 1,
      snsList: []
    }
  },
  created() {
    this.getSns()
  },
  computed: {
    ...mapGetters('userStore', ['isLogin'])
  },
  methods: {
    getSns() {
      axios
        .get(SERVER.URL + SERVER.ROUTES.trends.trendSns + this.trendId)
        .then((res) => 
          { 
            this.snsList = res.data })
        .catch((err) => { console.log(err)})
    }
  }


}
</script>

<style>

</style>