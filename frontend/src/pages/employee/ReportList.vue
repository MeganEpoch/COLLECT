<template>
  <div>
    <v-container class="pa-4">
      <template>
        <!-- 报告列表 -->
        <v-row class="mt-2 mb-8">
          <v-chip
              class="ma-2"
              color="purple darken-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            报告列表
          </v-chip>
        </v-row>

        <p v-if="reportsList.length === 0">暂无报告</p>

        <v-row>
          <report-item
              cols="12"
              md="4"
              v-for="report in reportsList"
              :key="report.id"
              :reportName="report.title"
              :intro="report.intro"
              :steps="report.steps"
              :device="report.device"
              :picture="report.picture"
              :id="report.id"
              :employeeName="report.employeeName"
              :employeeId="report.employeeId"
          >
          </report-item>
        </v-row>

        <!--    分页的代码 hot - task     -->
        <v-pagination
            color="purple darken-2"
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
import ReportItem from "@/components/ReportItem";
import {
  getReports
} from "@/api/report";

export default {
  name: "ReportList",

  components: {
    ReportItem
  },

  data() {
    return {
      msg: "",
      currentreportId: 0,
      currentreportName: "",

      reportsList:[],
      reportCurrentPage: 1,
      reportTotalPage: 1,
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
      getReports({
        taskId: this.$route.params.taskId,
        page
      }).then(res => {
        console.log()
        this.reportTotalPage = res.lastPage;
        this.reportsList = res.list;
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