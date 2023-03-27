//main.js 默认代码
// import { createApp } from 'vue'
// import App from './App.vue'
//
// createApp(App).mount('#app')

//为了顺利利用vue add vuetify命令进行安装，需要在输入命令之前更改main.js代码
//main.js 更改后代码
import Vue from 'vue'
import App from './App.vue'
import router from "./router";
import vuetify from './plugins/vuetify'
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import Router from 'vue-router'
// 引入echarts组件库
import * as echarts from "echarts";
Vue.prototype.$echarts = echarts;
// 引入G6可视化组件库（在这里全局引入似乎没有用）
//import G6 from '@antv/g6';
// 引入 iview 的组织结构树 vue-org-tree
// import Vue2OrgTree from 'vue2-org-tree'

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}

Vue.use(ElementUI);

// Vue.use(Vue2OrgTree)

// Vue.prototype.$host="http://localhost:8081/api/file/download/"
// Vue.prototype.$host="http://124.221.207.249/api/file/download/"

new Vue({
    vuetify,
    router,
    render: h => h(App)
}).$mount('#app');
