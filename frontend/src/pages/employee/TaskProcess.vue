<template>
  <div>
    <v-container class="box">
      <el-descriptions class="mt-8" title="任务详情" :column="1" border>
        <template slot="extra">
          <v-btn
              color="deep-purple"
              class="float-right"
              small
              text
              @click="handleDownloadFile2"
          >
            下载任务描述文件
          </v-btn>
          <v-btn
              color="deep-purple"
              class="float-right"
              small
              text
              @click="handleDownloadFile1"
          >
            下载任务可执行文件
          </v-btn>
        </template>
        <el-descriptions-item>
          <template slot="label">
            <!--            <i class="el-icon-user"></i>-->
            任务名称
          </template>
          {{ taskInfo.name }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            测试类型
          </template>
          {{ taskInfo.type }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            任务难度
          </template>
          <el-rate
              v-model="taskInfo.difficulty"
              :colors="colors"
              :texts="texts"
              show-text
              disabled>
          </el-rate>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            测试设备
          </template>
          {{ taskInfo.device }}设备
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            任务简介
          </template>
          {{ taskInfo.intro }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            测试所需工人数量
          </template>
          {{ taskInfo.requiredNumber }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            开始时间
          </template>
          {{ taskInfo.createTime }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            结束时间
          </template>
          {{ taskInfo.endTime }}
        </el-descriptions-item>
      </el-descriptions>


      <v-row justify="space-around" class="ma-12">
        <v-btn
            color="deep-purple"
            dark
            @click="toEdit"
        >
          去在线填写报告
        </v-btn>
        <v-btn
            color="deep-purple"
            dark
            @click="handleReport">
          查看已有报告
        </v-btn>
      </v-row>

    </v-container>
  </div>
</template>

<script>
import { getTaskById } from "@/api/task";
import { FILE_BASE_PATH,preDownload } from "@/api/file";

export default {
  name: "TaskPeek",

  data() {
    return {
      taskInfo: {},
      colors: ['#FFCC5AFF', '#F7BA2A', '#FF9900'],
      texts:['易如反掌','简单','中等','困难','谨慎选择'],
      fileName: "",
      FLAG: 0,
      types: ["功能测试", "性能测试"],
      difficulty:0,
      devices:["android","linux","鸿蒙","ios"],
      currentFile: {
        id: 0,
        taskId: 0,
        number: 0,
        title: "",
        fileName: "",
        fileType: "",
        fileSize: "",
        downloadFlag: true,
        uploadTime: ""
      }
    };
  },

  methods: {
    // 查看报告的函数
    handleReport() {
      const tid = this.$route.params.taskId;
      this.$router.push(`/employee/postarea/${tid}/1`);
    },

    toEdit(){
      const { taskId } = this.$route.params;
      this.$router.push({
        name: "EmployeePostEdit",
        params: {taskId : taskId}
      });
    },

    handleDownloadFile1() {
      const { taskId } = this.$route.params;
      const FLAG = 1;
      preDownload({taskId,FLAG}).then(res => {
        this.fileName = res;
        window.open(`${FILE_BASE_PATH}/${this.fileName}?newName=${this.fileName}`);
      });
    },
    handleDownloadFile2() {
      const { taskId } = this.$route.params;
      const FLAG = 2;
      preDownload({taskId,FLAG}).then(
          res => {
            this.fileName = res;
            window.open(`${FILE_BASE_PATH}/${this.fileName}?newName=${this.fileName}`);
          });
    },
    loadTask() {
      const { taskId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getTaskById({ uid, taskId }).then(res => {
        console.log(res);
        this.taskInfo = res;
      });
    }
  },

  mounted() {
    this.loadTask();
  }
};
</script>

<style scoped>
.box {
  width: 80%;
}
</style>
