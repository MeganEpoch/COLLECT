<template>
  <div>
    <v-app-bar color="indigo lighten-2" dense dark>
      <v-app-bar-nav-icon @click="direct('/employer')">
          <el-tooltip content="Home" placement="bottom">
              <v-icon>mdi-home</v-icon>
          </el-tooltip>
      </v-app-bar-nav-icon>

      <v-toolbar-title @click="direct('/employer')" class="cursor">
        COLLECT 发包方
      </v-toolbar-title>

      <v-spacer></v-spacer>

<!--      <v-menu left bottom>-->
<!--        <template v-slot:activator="{ on, attrs }">-->
<!--          <v-btn icon v-bind="attrs" v-on="on">-->
<!--            <v-icon>mdi-dots-vertical</v-icon>-->
<!--          </v-btn>-->
<!--        </template>-->

<!--        <v-list>-->
<!--          <v-list-item-->
<!--              v-for="opt in optionList"-->
<!--              :key="opt.optionName"-->
<!--              @click="direct(opt.link)"-->
<!--          >-->
<!--            <v-list-item-title>{{ opt.optionName }}</v-list-item-title>-->
<!--          </v-list-item>-->
<!--        </v-list>-->
<!--      </v-menu>-->
      <router-link :to="`/employer/create`">
        <v-btn small class="right" dark color="indigo lighten-2">
          新建任务
        </v-btn>
      </router-link>
      <v-app-bar-nav-icon @click="userHome">
          <el-tooltip content="个人中心" placement="bottom">
              <v-icon>mdi-account-box</v-icon>
          </el-tooltip>
      </v-app-bar-nav-icon>

      <v-app-bar-nav-icon @click="direct('/')">
          <el-tooltip content="退出登录" placement="bottom">
              <v-icon>mdi-exit-to-app</v-icon>
          </el-tooltip>
      </v-app-bar-nav-icon>

      <template v-slot:activator="{ on, attrs }"></template>
    </v-app-bar>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "EmployerLayout",
  data() {
    return {
      optionList: [
        {
          optionName: "个人中心",
          link: `/employer/user/${window.localStorage.getItem("userId")}`
        },
        {
          optionName: "登出",
          link: "/"
        }
      ]
    };
  },
  methods: {
    direct(link) {
      if (link === "/") {
        this.logout();
      }
      if (this.$route.path !== link) {
        this.$router.push(link);
      }
    },
    userHome(){
      this.$router.push(`/employer/user/${window.localStorage.getItem("userId")}`)
    },

    logout() {
      window.localStorage.removeItem("userId");
      window.localStorage.removeItem("userPhone");
      window.localStorage.removeItem("username");
      window.localStorage.removeItem("userRole");
    }
  }
};
</script>

<style scoped>
.cursor {
  cursor: pointer;
}
</style>
