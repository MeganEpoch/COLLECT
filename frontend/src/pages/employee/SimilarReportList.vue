<template>
  <div>
    <v-container class="ma-8 pa-4">

      <template>
        <!-- 报告列表 -->
        <v-row class="mt-2 mb-8">
          <v-chip
              class="ma-2"
              color="deep-purple lighten-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            相似报告列表
          </v-chip>
        </v-row>

        <p v-if="similarReportList.length === 0">该任务暂无可展示的报告</p>

        <v-row>
          <similar-report-item
              cols="12"
              md="4"
              v-for="report in similarReportList"
              :id="report.reportId2"
              :title="report.title"
              :intro="report.intro"
              :steps="report.steps"
              :device="report.device"
              :picture="report.picture"
              :similarity="report.similarity"
          >
          </similar-report-item>
        </v-row>

        <!--    分页的代码 hot - task     -->
        <v-pagination
            v-model="reportCurrentPage"
            :length="reportTotalPage"
            class="mt-12"
            cricle>
        </v-pagination>


      </template>
    </v-container>
  </div>
</template>

<script>
import SimilarReportItem from "@/components/SimilarReportItem";
import {
  getSimilarReports
} from "@/api/report";

export default {
  name: "ReportList",

  components: {
    SimilarReportItem
  },

  data() {
    return {
      msg: "",
      currentreportId: 0,
      currentreportName: "",

      similarReportList:[],
      reportCurrentPage: 1,
      reportTotalPage: 1,
      report: {
        reportId2: '',
        title: '',
        intro: '',
        steps: '',
        device: '',
        picture: '',
        similarity: ''
      }
    };
  },

  watch: {
    reportCurrentPage: function(val) {
      // this.handleSearchCourse();
      this.handleTask(val);
    }
  },

  methods: {

    handleTask(page){
      const payload = {
        taskId: this.$route.params.taskId,
        reportId: this.$route.params.reportId,
        page
      };
      console.log(payload);
      getSimilarReports(payload).then(res => {
        console.log();
        this.reportTotalPage = res.pages;
        this.similarReportList = res.list;
      })
    },

    fetchData(){
      this.handleTask(1);
    },

  },
  // mounted就是页面初始化的时候调用的函数
  mounted() {
    this.fetchData();
  }
};
</script>