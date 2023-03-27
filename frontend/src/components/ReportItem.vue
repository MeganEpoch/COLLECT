<template>
  <v-card color="blue lighten-5" width=45% height="100%" class="ma-4 pa-2">
<!--    <v-row justify="space-between">-->
<!--      <v-col cols="8">-->
    <el-row :gutter="20">
      <el-col :span="16">
        <div class="grid-content">
        <v-card-title class="headline">
          {{reportName}}
          <v-chip
              small
              class="ml-4"
              color="blue lighten-4"
          >
            提交人：{{ employeeName }}
          </v-chip>
        </v-card-title>

        <v-card-text class="text">
          缺陷情况说明：{{intro}}
        </v-card-text>
<!--      </v-col>-->

        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content">

      <v-img
          class="shrink ma-2"
          contain
          height="125px"
          :src="picture"
          style="flex-basis: 125px"
      ></v-img>
<!--    </v-row>-->
        </div>
      </el-col>
    </el-row>

    <v-divider></v-divider>

    <v-card-actions>
<!--   报告评分   -->
<!--      <v-rating-->
<!--          v-model="rating"-->
<!--          background-color="grey darken-1"-->
<!--          readonly-->
<!--          half-increments-->
<!--          length="5"-->
<!--          size="36"-->
<!--          v-if="rating === null"-->
<!--      ></v-rating>-->
      <div class="rate">
        <v-row v-if="rating === null">
          <el-rate
              v-model="rating"
              disabled
              show-score
              disabled-void-color="#C6D1DE"
              text-color="#ff9900"
              score-template="暂无评分">
          </el-rate>
        </v-row>
        <v-row v-if="rating !== null">
          <el-rate
              v-model="rating"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{rating}">
          </el-rate>
        </v-row>
      </div>
      <v-spacer></v-spacer>
      <v-row
          class="ma-1"
          justify="space-around"
      >
        <v-btn small dark :color="judgeColor" @click="handlePeek">
          报告详情
        </v-btn>
        <v-btn v-show="sta === 0" small dark color="deep-purple" @click="handleCooperate">
          报告协作
        </v-btn>
      </v-row>
    </v-card-actions>

  </v-card>
</template>

<script lang="ts">
import Vue from "vue";
import {getScore} from "../api/comment"

export default {
  name: "ReportItem",
  props: {
    reportName: {
      type: String,
      default: "taskName"
    },
    intro: {
      type: String,
      default: "缺陷情况说明"
    },
    steps: {
      type: String,
      default: "缺陷复先步骤"
    },
    device: {
      type: String,
      default: "测试设备信息"
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
    employeeName: {
      type: String,
      default: "众包工人名称"
    },
    employeeId:{
      type: Number,
      default: 0
    }
  },

  data(){
    return{
      rating:0,
      judgeColor:""
    }
  },

  methods:{
    handlePeek() {
      // 跳转到具体帖子内容界面
      // 增加对用户身份的判断
      const tid = this.$route.params.taskId;
      // this.$router.push(`/employer/post/${tid}/${this.id}`);
      if (localStorage.getItem("userRole").toLowerCase() === "employee"){
        this.$router.push(`/employee/post/${tid}/${this.id}`);
      }
      else {
        this.$router.push(`/employer/post/${tid}/${this.id}`);
      }
    },
    handleCooperate() {
      // 跳转到报告协作界面
      const tid = this.$route.params.taskId;
      this.$router.push(`/employee/cooperate/${tid}/${this.id}`);
    },

    // 后端返回的是10分制度，需要÷2 同时希望显示具体分数
    loadRating(){
      getScore({ id: this.id }).then(res =>{
        if (res.length === 0){
          console.log("暂无评分");
          this.rating = null;
        } else {
          console.log("评分："+ res);
          this.rating = res/2;
          this.rating = this.rating.toFixed(1)
        }
      })
    },

    // 根据用户身份来决定按钮的颜色
    handleColor(){
      if (localStorage.getItem("userRole").toLowerCase() === "employee"){
        this.judgeColor = "deep-purple"
      }
      else {
        this.judgeColor = "indigo"
      }
    }
  },

  computed: {
    text: function() {
      return this.description.length < 60
          ? this.description
          : this.description.substring(0, 60) + "...";
    },
    // 判断身份来确定是否显示协作报告  0众包工人 1发包方
    sta: function() {
      if (localStorage.getItem("userRole").toLowerCase() === "employee") {
        return 0;
      }
      return 1;
    }
  },

  mounted(){
    this.loadRating();
    this.handleColor();
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
.rate{
  margin-right: 20px;
}
</style>
