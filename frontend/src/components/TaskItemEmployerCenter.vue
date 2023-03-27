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
      【{{ type }}】
      {{ text }}
    </v-card-text>
    </div>
      </el-col>
      <el-col :span="4">
        <div class="grid-content">


<!--        <div class="right">-->
    <v-card-actions>
<!--      <v-row-->
<!--          align="center"-->
<!--          justify="space-around"-->
<!--          class="top"-->
<!--      >-->
        <v-btn class="mt-8" dark color="indigo" @click="handleReport">
          查看报告
        </v-btn>
<!--      </v-row>-->
    </v-card-actions>
    </div>
      </el-col>
    </el-row>
  </v-card>
</template>

<script lang="ts">
import Vue from "vue";
import {getTaskStatus} from "../api/task";

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
    },
    manageable: {
      type: Boolean,
      default: false
    },

    taskStatus: 0
  },

  methods:{
    // 查看报告的函数 需要修改链接
    handleReport() {
      this.judgeStatus()
      setTimeout(() => {
        console.log('延迟进行跳转');
        if (this.taskStatus === 2) {
          console.log("跳转至报告聚合页面")
          this.$router.push(`/employer/aggregate/${this.taskId}`);
        }else {
          console.log("跳转至报告列表页面")
          this.$router.push(`/employer/postarea/${this.taskId}/1`);
        }
      }, 1000);

    },

    judgeStatus(){
      getTaskStatus(this.taskId).then(res => {
        console.log("获取报告状态")
        console.log(res)
        this.taskStatus = res
        console.log("成功获取报告状态")
      })
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
