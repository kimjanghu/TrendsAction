<template>
  <v-container>
    <v-card
      class="mx-auto my-5 px-3 py-3"
      v-for="n in 3"
      :key="n"
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
            <v-list-item-title><span class="font-weight-black">박싸피</span><span class="ml-3">@10:21am</span></v-list-item-title>
          </v-list-item-content>
      </v-card-title>

      <v-card-text class="body-1 font-weight-bold">
        "사람들과 함께 있을 때의 나, SNS 속의 나, 집에서의 나. 당신은 늘 한결같은 당신으로 존재하는가? 이 질문에 단번에 ‘그렇다’고 답할 사람이 과연 몇이나 있을까? 별로 없다. 왜냐하면 지금은 분명 ‘멀티 페르소나’의 시대이기 때문이다."
      </v-card-text>

      <AddBoardBtn/>
    </v-card>

    <div class="text-center">
      <v-pagination
        v-model="page"
        :length="3"
      ></v-pagination>
    </div>
  </v-container>
</template>

<script>
import AddBoardBtn from '@/components/AddBoardBtn.vue'
export default {
  components: {
    AddBoardBtn
  },
  data() {
    return {
      page: 1,
    }
  },

}
</script>

<style>

</style>