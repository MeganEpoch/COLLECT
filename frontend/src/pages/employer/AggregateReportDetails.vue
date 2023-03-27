<template>
<!--  <el-row :gutter="20">-->
<!--    <el-col :span="16">-->
<!--      <div class="grid-content">-->
        <v-container class="box" v-loading.fullscreen.lock="loading">
          <v-row class="mt-10"></v-row>
          <h1>融合报告详情</h1>
          <h2>融合报告ID：{{reportInfo.id}}</h2>
<!--          <v-row-->
<!--              align="center"-->
<!--              justify="end"-->
<!--          >-->
<!--            报告审核情况：<el-tag>报告审核情况</el-tag>-->
<!--          </v-row>-->

          <el-divider></el-divider>

          <p>
            <v-row class="d-flex justify-space-between">
              <v-col>
                创建时间：<el-tag size="small">{{reportInfo.reportTime}}</el-tag>
              </v-col>
              <v-col>
                BUG严重程度：<el-tag size="small">{{reportInfo.severity}}</el-tag>
              </v-col>
            </v-row>
          </p>
          <p>
            BUG分类：<el-tag size="small">{{reportInfo.bugForm}}</el-tag>
          </p>
          <p>
            BUG复现程度：<el-tag size="small">{{reportInfo.recurrence}}</el-tag>
          </p>

          <el-divider></el-divider>

          <div>
            <p>
              主要点：{{reportInfo.intro}}
            </p>
            <div class="img">
              <el-image
                      :src="reportInfo.picture"
                      class="picture"
                      :preview-src-list="[reportInfo.picture]"
              />
            </div>
          </div>

          <el-divider></el-divider>

          <h4>补充点</h4>

          <div
              class="report-area"
              v-for="(report, reportIndex) in reports"
              :key="reportIndex"
          >
            <v-card class="report">
              <p><el-tag size="small">报告详情</el-tag>{{ report.intro }}</p>
<!--              <p>报告图片展示：<img :src="report.picture" width="100%"/></p>-->
              <span
                  class="aggregateReport-trigger"
                  @click="handlePeek(report.reportId)"
              >
                    报告详情
                  </span>
            </v-card>
            <v-row class="mt-8"></v-row>
          </div>

          <v-row class="ma-16"></v-row>

        </v-container>
<!--      </div>-->
<!--    </el-col>-->

    <!--  有点想在中间加一个分割线 但是没有找到正确的方法  -->
<!--    <el-col :span="8">-->
<!--      <div class="grid-content">-->
<!--        <v-container class="box">-->
<!--          <v-row class="mt-10"></v-row>-->
<!--          <p>需不需要加一个词云图片？</p>-->
<!--          <img :src="reportInfo.picture" width="100%"/>-->

<!--          <el-divider></el-divider>-->

<!--          <h2>报告关系展示</h2>-->
<!--          <el-tree-->
<!--              :data="data"-->
<!--              :props="defaultProps"-->
<!--              @node-click="handleNodeClick"-->
<!--          ></el-tree>-->
<!--        </v-container>-->
<!--      </div>-->
<!--    </el-col>-->
<!--  </el-row>-->
</template>

<script>
import {getMasterReport, getReportExtra, getReport} from "@/api/report";

export default {
  name: "AggregateReportDetails",
  data() {
    return {
      reportInfo:[],
      masterReportId: 0,
      reports:[],
      loading: true,
      fullscreenLoading: true
    };
  },

  methods:{
    // 跳转到每个报告详情查看页面
    handlePeek(reportID) {
      const tid = this.$route.params.taskId;
      this.$router.push(`/employer/post/${tid}/${reportID}`);
    },

    handleNodeClick(data) {
      console.log(data);
    },

    loadReport() {
      // id: this.$route.params.reportId
      // getReportCluster({ id: 1 }).then(res => {
      //   console.log(res)
      //   this.reportInfo = res.data;
      //   //this.reportInfo.picture="bb703866-038f-4937-ad75-1e0e19b0b1df.png";
      //   console.log(res.data);
      // });
      console.log("开始获取页面内容")
      const payload = {
        taskId: this.$route.params.taskId,
        cluster: this.$route.params.cluster
      }
      getMasterReport(payload).then(res => {
        console.log("获取主报告")
        console.log(res)
        this.masterReportId = res
        const masterId = this.masterReportId
        console.log("获取主报告完成")
        getReport({ id: masterId}).then(res => {
          console.log("获取报告详情")
          console.log(res)
          this.reportInfo = res.data;
        })
        getReportExtra({reportId: masterId}).then(res => {
          console.log("获取补充点")
          console.log(res)
          this.reports = res
        })
        this.loading = false
      })
    },

    openFullScreen1() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 10000);
    },
  },

  mounted() {
    this.openFullScreen1()
    this.loadReport();
  }
};
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.box {
  width: 60%;
}
.topcss {
  margin-top: 50px;
  border: skyblue 1px solid;
  box-shadow: 2px 2px 2px 2px #44b7ff;
}
.comment-edit {
  margin-top: 5px;
  padding: 10px;
  width: 100%;
  height: 200px;
  background-color: white;
  color: gray;
  border: skyblue 1px solid;
  border-radius: 3px;
  box-shadow: 2px 2px 2px 2px lightskyblue;
  resize: none;
  outline: none;
  cursor: text;
}
.aggregateReport-trigger {
  cursor: pointer;
  color: #000b82;
  margin-left: 10px;
}
.report{
  margin-top: 2px;
  padding: 10px;
}
.picture{
  width: auto;
  height: auto;
  max-width: 100%;
  max-height: 100%;
}
.img{
  width: 400px;
  height: 400px;
}
</style>