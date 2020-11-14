<template>
  <v-sheet
    class="px-5 py-2 neumor-design"
  >
    <v-row>
      <v-col cols="12" md="6" lg="4" v-for="(board, idx) in boards" :key="idx">
        <v-card class="user-board-card" :to="{ name: 'BoardDetail', params: { id: $route.params.id, boardId: board.boardId } }">
          <div class="board-title-area">
            <p class="board-title">{{ board.name }}</p>
          </div>
          <v-responsive :aspect-ratio="4/3">
            <v-row class="board-row">
              <v-col class="py-0 px-0" cols="6" v-for="(news, i) in board.newsList.slice(0,4)" :key="i">
                <v-img :aspect-ratio="4/3" v-if="news.thumbnail" :src="news.thumbnail"></v-img>
                <v-img :aspect-ratio="4/3" v-else src="https://picsum.photos/400/300?random"></v-img>
              </v-col>
            </v-row>
          </v-responsive>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  data: () => ({
    boards: [],
  }),
  computed: {
    ...mapGetters('userStore', ['config'])
  },
  methods: {
    getUserBoards() {
      const userId = this.$route.params.id
      
      this.$http.get(this.$api.URL + this.$api.ROUTES.boards.getBoardList + `${userId}`, this.config)
        .then(res => {
          console.log(res.data.data)
          this.boards = res.data.data
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  created() {
    this.getUserBoards()
  }
}
</script>

<style lang="scss" scoped>
.user-board-card {
  position: relative;
  background-color: gray;

  .board-title {
    z-index: 1;
    color: white;
    position: absolute;
    padding: 10px;
    top: 0;
    width: 100%;
    background: rgb(0,0,0);
    background: linear-gradient(0deg, rgba(0,0,0,0.05) 0%, rgba(0,0,0,0.5) 100%);
  }

  .board-row {
    height: 50%
  }

  .board-box {
    background-color: #000;
    width: 100px;
  }

  .inner-img {
    // position: absolute;
    // margin: 0;
    padding: 0;
    background-image: url('https://cdn.vuetifyjs.com/images/cards/road.jpg');
    background-size: cover;
    height: 100%;
  }
}

.user-board-card::after {
  z-index: 10;
  background: rgb(0,0,0);
  background: linear-gradient(0deg, rgba(0,0,0,0.3) 0%, rgba(255,255,255,1) 100%);
}

.neumor-design {
  padding: 2%;
  border-radius: 19px;
  background: #F5F5F6;
  box-shadow:  9px 9px 18px #ebebec, 
              -9px -9px 18px #ffffff;
}
</style>