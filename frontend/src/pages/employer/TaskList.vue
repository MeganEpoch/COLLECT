<template>
  <div>
    <v-container class="pa-4">
      <template>
        <v-row class="mt-2 mb-2">
          <v-chip
              class="ma-2"
              color="indigo darken-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            任务中心
          </v-chip>
        </v-row>

        <v-row align="end">
          <task-item-employer
              cols="12"
              md="4"
              v-for="task in TasksList"
              :key="task.id"
              :taskName="task.name"
              :taskId="task.id"
              :description="task.intro"
              :type="task.type"
              :accepted="task.accepted"
              :status="task.status"
              :requiredNumber="task.requiredNumber"
              :manageable="task.manageable"
          >
          </task-item-employer>
        </v-row>

        <v-pagination
            class="mt-5"
            v-model="taskCurrentPage"
            :length="taskTotalPage"
            color="indigo darken-1"
            cricle>
        </v-pagination>
      </template>
    </v-container>

    <v-container class="pa-4">
      <v-row class="mt-8 mb-2">
        <v-chip
            class="ma-2"
            color="indigo darken-3"
            label
            text-color="white"
        >
          <v-icon left>
            mdi-label
          </v-icon>
          我发布的任务
        </v-chip>
      </v-row>
      <v-row>
        <task-item-employer-center
            cols="12"
            md="4"
            v-for="task in myManageableTasksList"
            :key="task.id"
            :taskName="task.name"
            :taskId="task.id"
            :description="task.intro"
            :type="task.type"
            :accepted="task.accepted"
            :status="task.status"
            :requiredNumber="task.requiredNumber"
            :manageable="task.manageable"
        >
        </task-item-employer-center>
      </v-row>
    </v-container>

    <v-row class="ma-8">
    </v-row>
  </div>
</template>

<script>
import TaskItemEmployerCenter from "@/components/TaskItemEmployerCenter";
import TaskItemEmployer from "@/components/TaskItemEmployer";
import {getValidTasks,getManageableTasks} from "@/api/task";

export default {
  name: "EmployerTaskList",

  components: {
    TaskItemEmployer,
    TaskItemEmployerCenter
  },

  data() {
    return {
      TasksList:[],
      myManageableTasksList:[],
      taskCurrentPage: 1,
      taskTotalPage: 1,
    };
  },

  watch: {
    taskCurrentPage: function(val) {
      this.handleTask(val);
    }
  },

  methods: {
    handleTask(page){
      const uid = window.localStorage.getItem("userId");
      getValidTasks({uid, page}).then(res => {
        this.taskTotalPage = res.pages;
        this.TasksList = res.list;
      })
    },

    fetchData(){
      this.handleTask(1);
    },

    getManageableTasks() {
      const uid = window.localStorage.getItem("userId");
      getManageableTasks(uid).then(res => {
        this.myManageableTasksList = res;
      });
    },

  },
  // mounted就是页面初始化的时候调用的函数
  mounted() {
    this.fetchData();
    this.getManageableTasks();
  }
};
</script>