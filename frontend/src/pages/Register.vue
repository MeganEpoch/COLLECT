<template>
  <v-parallax
      dark
      height = "760"
      src="https://w.wallhaven.cc/full/yj/wallhaven-yjw53l.jpg"
  >
    <div>
      <v-container class="box">
        <!-- alert -->
        <v-alert
            class="alert"
            outlined
            type="success"
            text
            v-show="showSuccessDialog"
            transition="scroll-y-transition"
        >
          注册成功，欢迎{{ username }} !
        </v-alert>
        <!-- alert -->
        <v-alert
            class="alert"
            outlined
            type="warning"
            text
            v-show="showFailDialog"
            transition="scroll-y-transition"
        >
          {{ msg }}
        </v-alert>

        <!--        <el-dialog title="个人信息修改" :visible.sync="dialogFormVisible">-->
        <!--          <el-form :model="form" :label-position="labelPosition">-->
        <!--            <el-form-item label="设备选择" :label-width="formLabelWidth">-->
        <!--              <el-select v-model="form.device" placeholder="请选择您常用设备">-->
        <!--                <el-option label="Android设备" value="Android"></el-option>-->
        <!--                <el-option label="Linux设备" value="Linux"></el-option>-->
        <!--                <el-option label="鸿蒙设备" value="鸿蒙"></el-option>-->
        <!--                <el-option label="ios设备" value="ios"></el-option>-->
        <!--              </el-select>-->
        <!--            </el-form-item>-->
        <!--            <el-form-item label="偏好任务类型选择" :label-width="formLabelWidth">-->
        <!--              <el-select v-model="form.taskType" placeholder="请选择您希望接受的任务类型">-->
        <!--                <el-option label="功能测试" value="功能测试"></el-option>-->
        <!--                <el-option label="性能测试" value="性能测试"></el-option>-->
        <!--              </el-select>-->
        <!--            </el-form-item>-->
        <!--          </el-form>-->
        <!--          <div slot="footer" class="dialog-footer">-->
        <!--            <el-button type="primary" @click="handleChange">确 定</el-button>-->
        <!--          </div>-->
        <!--        </el-dialog>-->

        <el-form :model="register" :label-position="labelPosition" class="pa-12 grey lighten-5 mt-10 form">
          <el-form-item
              label="用户名" :label-width="formLabelWidth"
              prop="username"
              :rules="{ required: true, message: '用户名不能为空'}"
          >
            <el-input v-model="register.username" placeholder="用户名"></el-input>
          </el-form-item>
          <el-form-item
              label="手机号" :label-width="formLabelWidth"
              prop="phone"
              :rules="[
                    { required: true, message: '手机号码不能为空'},
                    { type: 'number', message: '手机号码必须为数字值'}
                    ]"
          >
            <el-input type="age" v-model.number="register.phone" placeholder="手机号码"></el-input>
          </el-form-item>
          <el-form-item
              label="密码" :label-width="formLabelWidth"
              prop="password"
              :rules="{ required: true, message: '密码不能为空'}"
          >
            <el-input v-model="register.password" placeholder="密码" show-password></el-input>
          </el-form-item>
          <el-form-item v-if="userRole !== 'employee'"  label="用户角色" :label-width="formLabelWidth">
            <!--            <v-select v-model="userRole" :items="roles" label="用户角色"></v-select>-->
            <el-select v-model="userRole" placeholder="用户角色">
              <el-option label="发包方" value="employer"></el-option>
              <el-option label="众包工人" value="employee"></el-option>
              <el-option label="管理员" value="admin"></el-option>
            </el-select>
            <br/>
            <v-btn class="ml-0 ma-4 info" @click="handleRegister">
              注册
            </v-btn>
            <v-btn text color="primary" class="float-right ma-4" @click="() => {this.$router.push('/login');}">
              去登录
            </v-btn>
          </el-form-item>


          <div v-if="userRole === 'employee'">
            <el-form :model="form" :label-position="labelPosition">
              <el-form-item label="设备选择" :label-width="formLabelWidth">
                <el-select v-model="form.device" placeholder="请选择您常用设备">
                  <el-option label="Android设备" value="Android"></el-option>
                  <el-option label="Linux设备" value="Linux"></el-option>
                  <el-option label="鸿蒙设备" value="鸿蒙"></el-option>
                  <el-option label="ios设备" value="ios"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="任务偏好选择" :label-width="formLabelWidth">
                <el-select v-model="form.taskType" placeholder="请选择您希望接受的任务类型">
                  <el-option label="功能测试" value="功能测试"></el-option>
                  <el-option label="性能测试" value="性能测试"></el-option>
                </el-select>
              </el-form-item>


              <el-form-item label="用户角色" :label-width="formLabelWidth">
                <!--                <v-select v-model="userRole" :items="roles" label="用户角色"></v-select>-->
                <el-select v-model="userRole" placeholder="用户角色">
                  <el-option label="发包方" value="employer"></el-option>
                  <el-option label="众包工人" value="employee"></el-option>
                  <el-option label="管理员" value="admin"></el-option>
                </el-select>
                <br/>
                <v-btn class="ml-0 ma-4 info" @click="handleRegister">
                  注册
                </v-btn>
                <v-btn text color="primary" class="float-right ma-4" @click="() => {this.$router.push('/login');}">
                  去登录
                </v-btn>
              </el-form-item>
            </el-form>
          </div>

        </el-form>
      </v-container>
    </div>
  </v-parallax>
</template>

<script>
import { register,choosePreference } from "@/api/user";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Register",
  data() {
    return {
      register:{
        username: "",
        phone: "",
        password: "",
      },
      userRole: "employee",
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      roles: ["employer", "employee", "admin"],
      dialogFormVisible: false,
      form:{
        taskType:'功能测试',
        device:"Android"
      },
      formLabelWidth: '100px',
      labelPosition: 'right',
      uid:0,
    };
  },
  methods: {
    handleRegister() {
      register({
        uname: this.register.username,
        phone: this.register.phone,
        password: this.register.password,
        userRole: this.userRole.toUpperCase()
      }).then(res => {
        console.log(res);
        if (res.code === 1) {
          // if (res.data.userRole==="EMPLOYEE"){
          //   this.uid = res.data.id;
          //   //this.dialogFormVisible = true;
          //   this.showSuccessDialog = true;
          // } else {
            window.localStorage.setItem("userPhone", this.register.phone);
            window.localStorage.setItem("userPassword", this.register.password);
            this.register.username = res.data.uname;
            this.uid = res.data.id;
            this.showSuccessDialog = true;
            setTimeout(() => {
              this.showSuccessDialog = false;
            }, 500);
            setTimeout(() => {
              this.$router.push("/login");
            }, 1000);
          // }
        } else {
          this.msg = res.msg;
          this.showFailDialog = true;
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      });
    },

    handleChange(){
      // this.dialogFormVisible = false;
      console.log(this.uid)
      const payload = {
        ...this.form,
        employeeId: this.uid,
      };
      console.log(payload);
      choosePreference(payload).then(res =>{
        console.log(res);
        if (res.code === 1){
          this.$router.push("/login");
        }
        else {
          alert('请完整填写个人偏好！');
        }
      })
    },
  }
};
</script>

<style scoped>
.form {
  position: relative;
  top: 50px;
}
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
.box {
  margin-bottom: 15%;
  width: 60%;
  opacity: 1;
}
</style>