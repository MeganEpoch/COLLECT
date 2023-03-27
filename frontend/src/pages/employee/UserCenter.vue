<template>
  <div>
    <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showSuccessDialog"
        transition="scroll-y-transition"
    >
      个人信息更改成功
    </v-alert>

    <!--  个人信息更改失败弹窗  -->
    <v-alert
        class="alert"
        outlined
        type="warning"
        text
        v-show="showFailDialog"
        transition="scroll-y-transition"
    >

      <v-dialog v-model="dialog" width="500">
        <v-card>
          <v-card-title>
            任务偏好选择
          </v-card-title>
          <v-card-text>
            <!--     待修改       -->
            <!--            <v-select-->
            <!--                v-model="labelInfo.ability"-->
            <!--                :items="abilities"-->
            <!--                label="学历选择"-->
            <!--                outlined-->
            <!--            ></v-select>-->
            <v-select
                v-model="labelInfo.device"
                :items="devices"
                label="设备选择"
                outlined
            ></v-select>
            <!--            <v-select-->
            <!--                v-model="labelInfo.activity"-->
            <!--                :items="activities"-->
            <!--                label="活跃度选择"-->
            <!--                outlined-->
            <!--            ></v-select>-->
            <v-select
                v-model="labelInfo.taskType"
                :items="taskTypes"
                label="偏好任务类型选择"
                outlined
            ></v-select>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="handleChange">
              确认
            </v-btn>
            <v-btn color="primary" text @click="dialog = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-alert>


    <v-container class="pl-16 pr-16 mt-16">
      <el-row :gutter="20">
        <el-col :span="8">
          <div class="grid-content">
            <el-row class="mt-9"></el-row>
            <img
                class="picture"
                style="width: 200px; height: 200px"
                src="../../assets/employee.png"
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

            <br/>

            <el-descriptions class="margin-top" title="个人详细信息设置" :column="1" border>
              <template slot="extra">
                <el-button
                    type="primary"
                    size="small"
                    @click="showDialog"
                    dark
                    class="deep-purple">
                  更改个人详细信息
                </el-button>
              </template>
              <!--        <el-descriptions-item label="学历">-->
              <!--          {{labelInfo.ability}}-->
              <!--        </el-descriptions-item>-->
              <!--        <el-descriptions-item label="设备">-->
              <!--          {{labelInfo.device}}-->
              <!--        </el-descriptions-item>-->
              <!--        <el-descriptions-item label="活跃度">-->
              <!--          {{labelInfo.activity}}-->
              <!--        </el-descriptions-item>-->
              <!--        <el-descriptions-item label="任务偏好">-->
              <!--          {{labelInfo.taskType}}-->
              <!--        </el-descriptions-item>-->
            </el-descriptions>
          </div>
        </el-col>
      </el-row>
    </v-container>

    <v-container class="pl-16 pr-16">

    </v-container>

    <!--    <v-container class="pl-16 pr-16 mb-16">-->
    <!--      <el-descriptions class="margin-top" title="我的历史任务选择偏好" :column="2" border>-->
    <!--        <el-descriptions-item label="历史报告任务推荐">-->
    <!--          {{systemLabelInfo.recommendation}}-->
    <!--        </el-descriptions-item>-->
    <!--        <el-descriptions-item label="我的任务偏好">-->
    <!--          {{systemLabelInfo.preference}}-->
    <!--        </el-descriptions-item>-->
    <!--      </el-descriptions>-->
    <!--    </v-container>-->
  </div>
</template>

<script>
import { getUser,choosePreference } from "@/api/user";

export default {
  name: "UserCenter",

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
      // 新增用户标签 专业能力、测试设备、活跃度、任务偏好（只有任务类型）
      labelInfo:{
        // ability:"",
        device:"Android",
        // activity:"",
        taskType:"功能测试"
      },
      // 学历 设备 一周工作天数 任务类型偏好
      // abilities:["小学","初中","高中","大专","大学","研究生","博士","不详"],
      devices:["Android","Linux","鸿蒙","ios"],
      // activities:["一周一天","一周三天","一周五天","全周"],
      taskTypes: ["功能测试", "性能测试"],

      // 新增系统给定标签 历史报告推荐、任务偏好
      // systemLabelInfo:{
      //   recommendation:"",
      //   preference:""
      // },
      dialog: false,
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      showAlert: false,
      alertMsg: "",
      value: 0,
    };
  },

  methods: {
    refreshUserInfo() {
      const userId = window.localStorage.getItem("userId");
      getUser(userId).then(res => {
        this.userInfo = res || {};
      });
    },

    // refreshLabelInfo(){
    //   const userId = window.localStorage.getItem("userId");
    //   getUserLabel(userId).then(res => {
    //     this.labelInfo = res || {};
    //   });
    // },

    showDialog(){
      this.dialog = true;
    },

    // 更改个人信息选择的按钮 payload{uid,labelInfo}
    // 修改成功返回1 否则返回其他以及保存失败的消息
    handleChange(){
      this.dialog = false
      const payload = {
        ...this.labelInfo,
        // id: 1,
        employeeId: window.localStorage.getItem("userId"),
      };
      console.log(payload);
      // var qs = require('qs');
      // var payload1 = qs.stringify(payload)
      // console.log(payload1)
      choosePreference(payload).then(res =>{
        console.log(res);
        if (res.code === 1){
          this.showSuccessDialog = true;
          // this.refreshLabelInfo();
          setTimeout(()=>{
            this.showSuccessDialog = false;
          }, 1000);
        }else {
          console.log(res);
          this.showFailDialog = true;
          this.msg = res.msg;
          // this.msg = "保存失败"
          // this.refreshLabelInfo();
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      })
    },

    // refreshSystemLabelInfo(){
    //   const userId = window.localStorage.getItem("userId");
    //   getUserSystemLabel(userId).then(res => {
    //     this.systemLabelInfo = res || {};
    //   })
    // }
  },

  mounted() {
    this.refreshUserInfo();
    // this.refreshLabelInfo();
    // this.refreshSystemLabelInfo();
  }
};
</script>

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
