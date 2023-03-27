<template>
  <v-card color="purple lighten-5" width="100%" height="100%" class="ma-4 pa-2">
    <div class="left">
      <v-card-title class="headline">
        {{ taskName }}
        <!--   以下是增加一个小标签来显示任务是否处于被接受的状态   -->
        <v-chip
            small
            class="ml-4"
            :color="taskColor[sta]"
        >
          {{ chip[sta] }}
        </v-chip>
        <!--   以上是增加一个小标签来显示任务是否处于被接受的状态   -->
      </v-card-title>

      <v-card-text class="text">
        【{{ type }}】
        {{ text }}
      </v-card-text>
    </div>

    <div class="right">
      <v-card-actions>
        <v-row
            justify="space-around"
            class="top"
        >
          <v-btn
              color="deep-purple"
              class="ma-1"
              small
              outlined
              v-show="sta === 0"
              @click="handlePeek">
            任务详情
          </v-btn>
          <v-btn
              color="deep-purple"
              class="ma-1"
              small
              outlined
              v-show="sta === 0"
              @click="todoTask">
            接受任务
          </v-btn>
          <v-btn
              color="deep-purple"
              class="ma-1"
              small
              outlined
              v-show="sta === 1"
              @click="handleTodo">
            任务详情
          </v-btn>
          <v-btn
              color="deep-purple"
              class="ma-1"
              small
              outlined
              v-show="sta === 1"
              @click="finishTask">
            完成任务
          </v-btn>
        </v-row>
      </v-card-actions>
    </div>
  </v-card>
</template>

<script lang="ts">
import Vue from "vue";

export default {
  name: "TaskItem",
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
    // 需要的人数和现在的人数
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
    taskColor: {
      type: String,
      default: "#BCAAA4"
    }
  },

  data(){
    return{
      chip:["未接受","已接受","任务已过期"]
    }
  },

  methods:{
    // 这个函数可能要改
    todoTask(){
      this.$emit("todo-task", this.taskId, this.taskName);
    },
    handleTodo() {
      // if (this.hasLogin) {
      this.$router.push(`/employee/process/${this.taskId}`);
      // }
      // else {
      //   this.$emit("todo-task", this.taskId, this.taskName);
      // }
    },
    handlePeek() {
      this.$router.push(`/employee/peek/${this.taskId}`);
    },
    finishTask(){
      this.$emit("finish-task", this.taskId);
    }
  },

  computed: {
    text: function() {
      return this.description.length < 60
          ? this.description
          : this.description.substring(0, 60) + "...";
    },

    // 3 人数已满(删掉)  0 未接受  1 已接受   2 任务已过期(正在招募4/过期5)
    sta: function() {
      if (this.accepted) {
        return 1;
      } else if (this.status === 5){
        return 2;
      }
      return 0;
    }
  }
}
</script>

<style scoped>
.text {
  height: 100%;
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
  margin-top: 10%;
}
</style>
