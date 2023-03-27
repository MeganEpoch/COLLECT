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
    </div>

    <div class="right">
    <v-card-actions>
      <v-row
          class="top"
          justify="space-around"
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
  name: "TaskItemEmployee",
  props: {
    id: {
      type: Number,
      default: 0
    },
    taskName: {
      type: String,
      default: "taskName"
    },
    taskId: {
      type: Number,
      default: 0
    },
    // //任务进行中的状态码
    // public static final Integer TODO_STATUS_DOING = 3;
    // // 已完成任务的状态码
    // public static final Integer TODO_STATUS_FINISHED = 2;
    status:{
      type: Number,
      default: 3
    },
    taskColor: {
      type: String,
      default: "#BCAAA4"
    }
  },

  data(){
    return{
      chip:["任务进行中","已完成任务"]
    }
  },

  methods:{
    handlePeek() {
      this.$router.push(`/employee/process/${this.taskId}`);
    },
      ReportListPeek() {
          this.$router.push(`/employee/postarea/${this.taskId}/0`);
      },
    finishTask(){
      this.$emit("finish-task", this.id, this.taskName);
    }
  },

  computed: {
    // 0 任务正在进行（2）  1 任务已完成（1）
    sta: function() {
      if (this.status === 3){
        return 0;
      }
      return 1;
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
}
</style>
