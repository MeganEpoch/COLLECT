<template>
  <v-card width=100% height="100%" class="ma-4 pa-2 row-bg">
    <div class="left">
      <v-card-title class="headline">
        报告ID：{{id}}
      </v-card-title>

      <!--            <v-card-text class="text">
                      缺陷情况说明：{{intro}}
                  </v-card-text>-->
    </div>

    <v-row class="right">
      <v-col>
        相似度：{{similarity}}
      </v-col>
      <v-col>
        <v-card-actions class="right">
          <v-btn class="right" color="accent" @click="handleCooperate">
            报告协作
          </v-btn>
        </v-card-actions>
      </v-col>
    </v-row>

  </v-card>
</template>

<script lang="ts">
import Vue from "vue";

export default {
  name: "SimilarReportItem",
  props: {
    id: {
      type: Number,
      default: "1"
    },
    title: {
      type: Number,
      default: "taskName"
    },
    intro: {
      type: Number,
      default: "简介相似度"
    },
    similarity: {
      type: Number,
      default: "总相似度"
    },
    steps: {
      type: Number,
      default: "缺陷复先步骤"
    },
    device: {
      type: Number,
      default: "测试设备信息"
    },
    // 还缺一个图片的属性
    picture: {
      type: Number,
      default: "picture"
    },
  },

  methods:{
    handleCooperate() {
      // 跳转到报告协作界面
      const tid = this.$route.params.taskId;
      this.$router.push(`/employee/cooperate/${tid}/${this.id}`);
    },
  },

  computed: {
    text: function() {
      return this.description.length < 60
          ? this.description
          : this.description.substring(0, 60) + "...";
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
.left{
  float: left;
  margin-bottom: 2%;
}
.right{
  float: right;
  margin-top: 1%;
}
</style>
