<template>
  <v-app>
    <div>
      <router-view class="index" />
      <!-- <router-view /> 定位默认路由 -->
    </div>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";

export default {
  name: "App",

  components: {},

  data: () => ({
    //
  })
};
</script>

<style lang="scss">
a {
  text-decoration: none;
}
</style>
