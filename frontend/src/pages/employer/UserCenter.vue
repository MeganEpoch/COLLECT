<template>
  <div>
    <v-container class="pl-16 pr-16 mt-16">
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="grid-content">
            <el-row class="mt-9"></el-row>
            <img
                class="picture"
                style="width: 200px; height: 200px"
                src="../../assets/employer.png"
            >
          </div>
        </el-col>
        <el-col :span="16">
          <div class="grid-content">
            <el-descriptions title="基本用户信息" direction="vertical" :column="4" border>
              <el-descriptions-item label="ID">
                <template slot="label">
                  <i class="el-icon-user"></i>
                  ID
                </template>
                {{userInfo.id}}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  <i class="el-icon-user"></i>
                  用户名
                </template>
                {{userInfo.uname}}
              </el-descriptions-item>
              <el-descriptions-item label="手机号" :span="2">{{userInfo.phone}}</el-descriptions-item>
              <el-descriptions-item label="用户角色">
                <el-tag size="small">{{userInfo.userRole}}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="注册时间">{{userInfo.createTime}}</el-descriptions-item>
            </el-descriptions>
          </div>
        </el-col>
      </el-row>
    </v-container>

    <el-divider class="box"></el-divider>

    <v-container class="pl-16 pr-16">
      <v-row class="mb-2">
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
  </div>
</template>

<script>
import TaskItemEmployerCenter from "@/components/TaskItemEmployerCenter";
import {getManageableTasks,} from "@/api/task";
import { getUser } from "@/api/user";

export default {
  name: "EmployeeTaskList",

  components: {
    TaskItemEmployerCenter
  },

  data() {
    return {
      userInfo: {
        id: 0,
        uname: "",
        phone: "",
        password: null,
        picture: null,
        balance: 0,
        userRole: "",
        createTime: ""
      },
      currentTaskId: 0,
      currentTaskName: "",
      myManageableTasksList:[],
    };
  },

  watch: {
    searchCurrentPage: function() {
      this.getManageableTasks();
    }
  },

  methods: {
    refreshUserInfo() {
      const userId = window.localStorage.getItem("userId");
      console.log("用户id为" + userId)
      getUser(userId).then(res => {
        console.log(res)
        this.userInfo = res || {};
      });
    },

    getManageableTasks() {
      const uid = window.localStorage.getItem("userId");
      getManageableTasks(uid).then(res => {
        this.myManageableTasksList = res;
      });
    },

    fetchData(){
      this.refreshUserInfo();
      this.getManageableTasks();
    },

  },
  // mounted就是页面初始化的时候调用的函数
  mounted() {
    this.fetchData();
  }
};
</script>
<style>
.box{
  width: 80%;
}
</style>