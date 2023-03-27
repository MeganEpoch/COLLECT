<template>
  <div>
    <v-container class="box">

<!--      <el-row :gutter="20">-->
<!--        <el-col :span="8">-->
<!--          <div class="grid-content">-->
<!--            <el-row class="mt-16"></el-row>-->
<!--            <img-->
<!--                class="picture"-->
<!--                style="width: 200px; height: 200px"-->
<!--                src="../../assets/taskpeek.png"-->
<!--            >-->
<!--          </div>-->
<!--        </el-col>-->

<!--        <el-col :span="16">-->
<!--          <div class="grid-content">-->

            <el-descriptions class="mt-8" title="任务详细信息展示" :column="1" border>
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
<!--          </div>-->
<!--        </el-col>-->
<!--      </el-row>-->
    </v-container>
  </div>
</template>

<script>
import { getTaskById } from "@/api/task";

export default {
  name: "TaskPeek",

  data() {
    return {
      taskInfo: {},
      colors: ['#FFCC5AFF', '#F7BA2A', '#FF9900'],
      texts:['易如反掌','简单','中等','困难','谨慎选择'],
    };
  },

  methods: {
    loadCourse() {
      const { taskId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getTaskById({ uid, taskId }).then(res => {
        console.log(res);
        this.taskInfo = res;
      });
    }
  },

  mounted() {
    this.loadCourse();
  }
};
</script>

<style scoped>
.box {
  width: 60%;
}
</style>
