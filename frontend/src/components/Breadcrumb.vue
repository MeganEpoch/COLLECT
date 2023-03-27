<!--<template>-->
<!--  <div class="crumbs">-->
<!--    &lt;!&ndash; 面包屑 &ndash;&gt;-->
<!--    <el-card class="box-card">-->
<!--      <el-breadcrumb separator="/">-->
<!--        <el-breadcrumb-item v-for="(level,index)  in this.breadList" :key="index" :to="level.path">-->
<!--          {{level.title}}-->
<!--        </el-breadcrumb-item>-->
<!--      </el-breadcrumb>-->
<!--    </el-card>-->

<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "Breadcrumb",-->
<!--  data() {-->
<!--    return {-->
<!--      breadList: [] // 路由集合-->
<!--    };-->
<!--  },-->
<!--  watch: {-->
<!--    $route() {-->
<!--      this.getBreadcrumb();-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    isHome(route) {-->
<!--      return route.name === "home";-->
<!--    },-->
<!--    getBreadcrumb() {-->
<!--      let matched = this.$route.matched;-->
<!--      //如果不是首页-->
<!--      if (!this.isHome(matched[0])) {-->
<!--        matched = [{ path: "/home", meta: { title: "首页" } }].concat(matched);-->
<!--      }-->
<!--      this.breadList = matched;-->
<!--    }-->
<!--  },-->
<!--  created() {-->
<!--    this.getBreadcrumb();-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->

<!--</style>-->