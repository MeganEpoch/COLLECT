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
          登录成功，欢迎{{ username }} !
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
        <form class="pa-12 grey lighten-3 mt-5 form">
          <el-input v-model="phone" placeholder="手机号码"></el-input>
          <br/><br/>
          <el-input v-model="password" placeholder="密码" show-password></el-input>
          <v-btn class="mt-8 info" @click="handleLogin">
            登录
          </v-btn>
          <v-btn
              text
              color="primary"
              class="float-right mt-8"
              @click="() => {this.$router.push('/register');}"
          >
            去注册
          </v-btn>
        </form>
      </v-container>
    </div>
  </v-parallax>
</template>

<script>
import { login } from "@/api/user";

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Login",
  data() {
    return {
      phone: "",
      password: "",
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      username: ""
    };
  },
  methods: {
    handleLogin() {
      login({phone: this.phone, password: this.password}).then(res => {
        if (res.code === 1) {
          window.localStorage.setItem("userId", res.data.id);
          window.localStorage.setItem("userPhone", res.data.phone);
          window.localStorage.setItem("username", res.data.uname);
          window.localStorage.setItem("userRole", res.data.userRole);
          this.username = res.data.uname;
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
          }, 1000);
          if (res.data.userRole === "EMPLOYEE") {
            setTimeout(() => {
              this.$router.push("/employee");
            }, 800);
          } else if (res.data.userRole === "EMPLOYER") {
            setTimeout(() => {
              this.$router.push("/employer");
            }, 800);
          } else if (res.data.userRole === "ADMIN") {
            setTimeout(() => {
              this.$router.push("/admin");
            }, 800);
          }
        } else {
          this.msg = res.msg;
          this.showFailDialog = true;

          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      });
    }
  },
  mounted() {
    this.phone = window.localStorage.getItem("userPhone");
    this.password = window.localStorage.getItem("userPassword");
    window.localStorage.removeItem("userPassword");
  }
};
</script>

<style scoped>
.form {
  position: relative;
  top: 100%;
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
  opacity: 0.9;
}
</style>