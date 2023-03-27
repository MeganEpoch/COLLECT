<template>
  <div>
    <v-container class="pa-4">
      <template>
        <v-row class="mt-2">
          <v-chip
              class="ma-2"
              color="indigo darken-3"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-label
            </v-icon>
            报告列表
          </v-chip>
        </v-row>

<!--&lt;!&ndash;        需要加一个报告分割符&ndash;&gt;-->
<!--        <v-row-->
<!--            align="center"-->
<!--            justify="end"-->
<!--        >-->
<!--          <v-btn-->
<!--              text-->
<!--              @click="Aggregate"-->
<!--          >-->
<!--            报告聚合-->
<!--          </v-btn>-->
<!--        </v-row>-->

        <v-row align="end">
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
            class="ma-5"
            v-model="reportCurrentPage"
            :length="reportTotalPage"
            color="indigo darken-1"
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
        console.log(res)
        this.reportTotalPage = res.lastPage;
        this.reportsList = res.list;
      })
    },

    fetchData(){
      this.handleTask(1);
    },

    // 点击进行报告聚合并跳转到聚合后的聚合报告页面
    Aggregate(){
      console.log("跳转")
      const tid = this.$route.params.taskId
      this.$router.push(`/employer/aggregate/${tid}`);
    }

  },
  // mounted就是页面初始化的时候调用的函数
  mounted() {
    this.fetchData();
  }
};
</script>