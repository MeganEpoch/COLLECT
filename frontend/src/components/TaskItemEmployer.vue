<template>
  <v-card color="indigo lighten-5" width="100%" height="130px" class="ma-4 pa-2">
<!--    <div class="left">-->
    <el-row :gutter="20">
      <el-col :span="20">
        <div class="grid-content">
      <v-card-title class="headline">
        {{ taskName }}
      </v-card-title>

      <v-card-text class="text">
        【{{ type }}任务】
        {{ text }}
      </v-card-text>
    </div>
      </el-col>

      <el-col :span="4">
        <div class="grid-content">

<!--    <div class="right">-->
      <v-card-actions>
<!--        <v-row-->
<!--            align="center"-->
<!--            justify="space-around"-->
<!--            class="top"-->
<!--        >-->
          <v-btn class="mt-8" color="indigo" dark @click="handlePeek">任务详情</v-btn>
<!--        </v-row>-->
      </v-card-actions>
    </div>
      </el-col>
    </el-row>
  </v-card>
</template>

<script>
import Vue from "vue";

export default {
  name: "TaskItemEmployer",
  props: {
    taskName: {
      type: String,
      default: "taskName"
    },
    taskId: {
      type: Number,
      default: 0
    },
    description: {
      type: String,
      default: "任务简介"
    },
    type: {
      type: String,
      default: "功能测试"
    },
    requiredNumber:{
      type: Number,
      default: 0
    },
    status:{
      type: Number,
      default: 4
    },
    accepted: {
      type: Boolean,
      default: false
    }
  },

  methods:{
    handlePeek() {
      console.log()
      this.$router.push(`/employer/peek/${this.taskId}`);
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
/*.headline{*/
/*  float: left;*/
/*  margin-bottom: 1%;*/
/*}*/
.text {
  height: 40px;
  overflow: hidden;
}
.left{
  float: left;
  /*margin-bottom: 5%;*/
}
.right {
  float: right;
}
.top {
  float: top;
  margin-right: 20%;
  margin-top: 40%;
}
</style>
