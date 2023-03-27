<template>
  <div>
    <v-container class="ma-20 pa-4">
<!--   显示系统推荐的任务   -->
      <template>
        <v-row class="mt-2 mb-8">
          <v-chip
              class="ma-2"
              color="purple darken-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            任务推荐
          </v-chip>
        </v-row>

        <v-row align="end">
          <task-item
              cols="12"
              md="4"
              v-for="task in recommendTasksList"
              :key="task.id"
              :taskName="task.name"
              :taskId="task.id"
              :description="task.intro"
              :type="task.type"
              :accepted="task.accepted"
              :status="task.status"
              :requiredNumber="task.requiredNumber"
              :manageable="task.manageable"
              @todo-task="showDialog"
              @finish-task="showFinishDialog"
          >
          </task-item>
        </v-row>
      </template>

      <v-row class="ma-12">
        <v-divider></v-divider>
      </v-row>

<!-- 显示所有可以被接取的任务 -->
      <template>
        <v-row class="mt-2 mb-8">
          <v-chip
              class="ma-2"
              color="purple darken-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            任务中心
          </v-chip>
        </v-row>
          <v-row>
            <task-item
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
                @todo-task="showDialog"
                @finish-task="showFinishDialog"
            >
            </task-item>
          </v-row>

          <!--    分页的代码 hot - task     -->
          <v-pagination
              color="purple darken-1"
              class="mt-3"
              v-model="taskCurrentPage"
              :length="taskTotalPage"
              cricle>
          </v-pagination>
      </template>

      <v-row class="ma-12">
        <v-divider></v-divider>
      </v-row>

<!-- 我的待做任务 -->
      <template>
        <v-row class="mt-8 mb-2">
          <v-chip
              class="ma-2"
              color="purple darken-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            待做任务
          </v-chip>
        </v-row>
        <v-row>
          <task-item-employee
              cols="12"
              md="4"
              v-for="task in todoTasksList"
              :key="task.id"
              :id="task.id"
              :taskName="task.taskName"
              :taskId="task.taskId"
              :status="task.status"
              @finish-task="showFinishDialog"
          >
          </task-item-employee>
        </v-row>
      </template>

      <v-row class="ma-12">
        <v-divider></v-divider>
      </v-row>

<!-- 我的历史任务 -->
      <template>
      <v-row class="mt-8 mb-2">
        <v-chip
            class="ma-2"
            color="purple darken-3"
            label
            text-color="white"
        >
          <v-icon left>
            mdi-label
          </v-icon>
          我的历史任务
        </v-chip>
      </v-row>
      <v-row>
        <task-item-employee
            cols="12"
            md="4"
            v-for="task in myTasksList"
            :key="task.id"
            :taskName="task.taskName"
            :taskId="task.taskId"
            :status="task.status"
        >
        </task-item-employee>
      </v-row>
    </template>
    </v-container>


<!-- 接受提示对话框 -->
    <v-dialog v-model="dialog" width="60%">
      <v-card>
        <v-card-title>接受任务确认</v-card-title>
        <v-card-text> 是否接受 {{ currentTaskName }} </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="deep-purple" text @click="confirmTodo">
            确认
          </v-btn>
          <v-btn color="deep-purple" text @click="dialog = false">
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 确定完成提交任务对话框 -->
    <v-dialog v-model="finishDialog" width="60%">
      <v-card>
        <v-card-title>完成任务确认</v-card-title>
        <v-card-text>
          是否提交 {{ currentTaskName }}
          <h5>请注意！提交任务后不能再对任务报告进行修改</h5>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="deep-purple" text @click="confirmFinish">
            确认
          </v-btn>
          <v-btn color="deep-purple" text @click="finishDialog = false">
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

<!--  应该是弹窗显示消息  -->
    <v-snackbar v-model="showSnackBar" :color="snackBarColor" top>
      {{ snackBarMsg }}
    </v-snackbar>
  </div>
</template>

<script>
import TaskItem from "@/components/TaskItem.vue";
import TaskItemEmployee from "@/components/TaskItemEmployee"
import {
  getValidTasks,
  getRecommendTasks
} from "@/api/task";
import {
  getTodoTasks,
  selectTodoTask,
  getFinishedTasks,
  finishTask
} from "@/api/todo_task"

export default {
  name: "EmployeeTaskList",

  components: {
    TaskItem,TaskItemEmployee
  },

  data() {
    return {
      dialog: false,
      finishDialog: false,
      currentTaskId: 0,
      currentTaskName: "",

      TasksList:[],
      taskCurrentPage: 1,
      taskTotalPage: 1,

      todoTasksList: [],
      myTasksList:[],
      recommendTasksList:[],

      showSnackBar: false,
      snackBarMsg: "",
      snackBarColor: "success",
    };
  },

  watch: {
    taskCurrentPage: function(val) {
      this.handleTask(val);
    }
  },

  methods: {

    showDialog(taskId, taskName) {
      this.currentTaskId = taskId;
      this.currentTaskName = taskName;
      // this.currentCoursePrice = taskPrice;
      this.dialog = true;
    },

    // 分页获取所有可被接受的任务
    handleTask(page){
      const uid = window.localStorage.getItem("userId");
      getValidTasks({
        uid,
        page
      }).then(res => {
        this.taskTotalPage = res.pages;
        this.TasksList = res.list;
      })
    },

    // 获取历史任务
    getUserAllTasks() {
      const uid = window.localStorage.getItem("userId");
      getFinishedTasks(uid).then(res => {
        this.myTasksList = res || [];
      });
    },

    // 待做任务的接口函数
    getUserTodoTasks() {
      const uid = window.localStorage.getItem("userId");
      getTodoTasks(uid).then(res => {
        this.todoTasksList = res || [];
      });
    },

    // 获取推荐任务接口函数
    getUserRecommendTasks(){
      const uid = window.localStorage.getItem("userId");
      getRecommendTasks(uid).then(res => {
        console.log(res);
        this.recommendTasksList = res || [];
      });
    },

    fetchData(){
      this.handleTask(1);
      this.getUserAllTasks();
      this.getUserTodoTasks();
      this.getUserRecommendTasks();
    },

    confirmTodo(){
      const uid = window.localStorage.getItem("userId");
      selectTodoTask(this.currentTaskId, uid).then(res => {
        console.log(res)
        this.snackBarColor = "success";
        this.snackBarMsg = res.msg;
        this.showSnackBar = true;
        this.dialog = false;
        this.fetchData();
      })
      this.fetchData();
    },

    showFinishDialog(taskId, taskName) {
      this.currentTaskId = taskId;
      this.currentTaskName = taskName;
      this.finishDialog = true;
    },

    confirmFinish(){
      const uid = window.localStorage.getItem("userId");
      finishTask(uid, this.currentTaskId).then(res => {
        console.log(res)
        if(res.code === 1){
          this.snackBarColor = "success";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
          this.finishDialog = false;
          this.fetchData();
        } else {
          this.snackBarColor = "error";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
          this.finishDialog = false;
          this.fetchData();
        }
      })
      this.fetchData();
    }
  },
  mounted() {
    this.fetchData();
  }
};
</script>