<template>
  <v-card wihth="100%" height="100%" class="ma-4 pa-2">
    <v-row justify="space-between">
      <v-col cols="8">
        <v-card-title class="headline" coler="blue-grey lighten-2">
          {{reportId}}
          <v-chip
              small
              class="ml-4"
              color="blue-grey lighten-5"
          >
            聚合报告数：{{ reportNumber }}
          </v-chip>

          <v-spacer></v-spacer>

          <v-row
              align="center"
              justify="end"
          >
            <v-icon class="mr-1">
              mdi-heart
            </v-icon>
            <v-chip
                mall
                class="ml-4"
                color="blue-grey lighten-5"
            >
              项目审核情况：{{chip[achieved]}}
            </v-chip>
          </v-row>
        </v-card-title>

        <v-spacer></v-spacer>

        <v-card-text class="text">
          缺陷情况说明：{{intro}}
        </v-card-text>

        <v-card-actions>

          <v-spacer></v-spacer>
          <v-row
              class="ma-1"
              justify="space-around"
          >
            <v-btn small dark @click="handlePeek">
              聚合报告详情
            </v-btn>
          </v-row>
        </v-card-actions>
      </v-col>

      <v-img
          class="shrink ma-2"
          contain
          height="125px"
          :src="picture"
          style="flex-basis: 125px"
      ></v-img>
    </v-row>
  </v-card>
</template>

<script>

export default {
  name: "AggregateReportItem",
  props: {
    reportId:{
      type: String,
      default: "reportId"
    },
    // 所融合的报告数
    reportNumber:{
      type: String,
      default: "1"
    },
    // 项目审批情况
    achieved:{
      type: Number,
      default: 0
    },
    intro: {
      type: String,
      default: "缺陷情况说明"
    },
    // 还缺一个图片的属性
    picture: {
      type: String,
      default: "picture"
    },
    id: {
      type: Number,
      default: 0
    },
  },

  data(){
    return{
      chip:["未审核","已审核"]
    }
  },

  methods:{

    // 跳转到具体融合报告详情的
    handlePeek() {
      const tid = this.$route.params.taskId;
      this.$router.push(`/employer/post/${tid}/${this.id}`);
    },
  }
}
</script>

<style scoped>
.text {
  /* margin-top: 2%;*/
  height: 40px;
  overflow: hidden;
  margin-bottom: 2%;
}
.headline{
  float: left;
  margin-bottom: 1%;
}
</style>