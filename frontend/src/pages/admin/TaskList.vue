<template>
  <div>
    <v-container class="pa-4">
      <template>
        <v-row class="mt-4 mb-2">
          <v-chip
              class="ma-2"
              color="teal darken-2"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            任务中心
          </v-chip>
        </v-row>

          <v-row align="center" justify="space-around">
            <task-item-admin
                cols="12"
                md="4"
                v-for="task in tasksList"
                :key="task.id"
                :taskName="task.name"
                :taskId="task.id"
                :description="task.intro"
                :type="task.type"
                :accepted="task.accepted"
                :status="task.status"
                :requiredNumber="task.required_number"
            >
            </task-item-admin>
          </v-row>
      </template>
    </v-container>
  </div>
</template>

<script>
import TaskItemAdmin from "@/components/TaskItemAdmin";
import { getAllTasks } from "@/api/task";

export default {
  name: "AdminTaskList",

  components: {
    TaskItemAdmin
  },

  data() {
    return {
      tasksList:[],
    };
  },

  methods: {
    handleTask(){
      getAllTasks().then(res => {
        console.log(res);
        this.tasksList = res;
      })
    },

    fetchData(){
      this.handleTask();
    },
  },

  // mounted就是页面初始化的时候调用的函数
  mounted() {
    this.fetchData();
  }
};
</script>