<template>
  <div class="background">
    <v-container class="pl-16 pr-16 mt-16">

      <el-row :gutter="20">
        <el-col :span="8">
          <div class="grid-content">
            <el-row class="mt-9"></el-row>
            <img
                class="picture"
                style="width: 200px; height: 200px"
                src="../../assets/admin.png"
            >
<!--            <p>管理员</p>-->
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
  </div>
</template>

<script>
import { getUser } from "@/api/user";

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
      dialog: false,
      showAlert: false,
      value: 0,
      msg: ""
    };
  },

  methods: {
    refreshUserInfo() {
      const userId = window.localStorage.getItem("userId");
      getUser(userId).then(res => {
        this.userInfo = res || {};
      });
    }
  },

  mounted() {
    this.refreshUserInfo();
  },
};
</script>


<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
/*.picture{*/
/*  border: skyblue 1px solid;*/
/*  box-shadow: 2px 2px 2px 2px lightskyblue;*/
/*  resize: none;*/
/*  outline: none;*/
/*}*/
/*.background {*/
/*  width: 100%;*/
/*  height: 100%;*/
/*  background-color: #E0F2F1;*/
/*  position: fixed;*/
/*}*/
</style>
