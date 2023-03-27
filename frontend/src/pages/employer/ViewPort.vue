<template>
  <div>
    <v-container class="box">
      <el-row :gutter="20">
        <el-col :span="16">
          <div class="grid-content">
            <el-row class="mt-16"></el-row>
            <el-descriptions title="报告详情" :column="4" border>
              <el-descriptions-item :span="4">
                <template slot="label">
                  报告名称
                </template>
                {{ reportInfo.title }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  漏洞分类
                </template>
                {{ reportInfo.bugForm }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  严重等级
                </template>
                {{ reportInfo.severity }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  复现程度
                </template>
                {{ reportInfo.recurrence }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  测试设备信息
                </template>
                {{ reportInfo.device }}
              </el-descriptions-item>
              <el-descriptions-item :span="4">
                <template slot="label">
                  缺陷情况说明
                </template>
                {{ reportInfo.intro }}
              </el-descriptions-item>
              <el-descriptions-item :span="4">
                <template slot="label">
                  缺陷复现步骤
                </template>
                {{ reportInfo.steps }}
              </el-descriptions-item>
              <el-descriptions-item small :span="4">
                <template slot="label">
                  缺陷信息截图
                </template>
                  <el-image
                          v-if="reportInfo.picture!=='undefined'"
                          :src="reportInfo.picture"
                          class="picture"
                          :preview-src-list="[reportInfo.picture]"
                  />
              </el-descriptions-item>
            </el-descriptions>
          </div>
        </el-col>

        <el-col :span="8">
          <div class="grid-content">
            <el-row class="mt-16"></el-row>
            <v-container class="box" >
              <h2>评论区</h2>

              <p v-if="rating === null" class="mt-3">暂无评分及评论</p>
              <!--查看报告评分-->
              <v-row type="flex" class="row-bg" justify="space-around" v-if="rating !== null">
<!--        <span class="mt-3">-->
<!--        评分详情：{{ rating }}-->
<!--        </span>-->
<!--                <v-rating-->
<!--                    v-model="rating"-->
<!--                    background-color="warning lighten-1"-->
<!--                    color="warning"-->
<!--                    readonly-->
<!--                    half-increments-->
<!--                    length="5"-->
<!--                    size="48"-->
<!--                ></v-rating>-->
                <el-rate
                    v-model="rating"
                    :colors="colors"
                    show-text
                    show-score
                    text-color="#ff9900"
                    score-template="{rating}"
                    disabled>
                </el-rate>
              </v-row>

              <!--      -->
              <div
                  class="comment-area"
                  v-for="(comment, commentIndex) in comments"
                  :key="commentIndex"
              >
                <v-card class="comment">
                  <h6>评论人ID： {{ comment.employeeId }}</h6>
                  <h5 style="color: gray;">评论人打分： {{ comment.score }}</h5>
                  <p class="mt-3" v-if="comment.content !== null">评论详情： {{ comment.content }}</p>
                </v-card>
              </div>
            </v-container>

          </div>
        </el-col>
      </el-row>
    </v-container>
  </div>
</template>

<script>
import { getReport } from "@/api/report";
import {getComments, getScore} from "@/api/comment"
import {FILE_BASE_PATH} from "@/api/file";

export default {
  name: "EmployerReportContent",

  data() {
    return {
      reportInfo: { },
      flag: true,
      comments: [],
      rating: null,
      colors: ['#FFCC5AFF', '#F7BA2A', '#FF9900'],
      //reportImg: require('${FILE_BASE_PATH}/${reportInfo.picture}?newName=${reportInfo.picture}')
    };
  },

  methods: {
    loadReport() {
      // const { id } = this.$route.params.reportId;
      getReport({ id: this.$route.params.reportId }).then(res => {
        console.log(res)
        this.reportInfo = res.data;
        //this.reportInfo.picture="bb703866-038f-4937-ad75-1e0e19b0b1df.png";
        console.log(this.reportInfo.picture);
        //this.reportImg=require(`${FILE_BASE_PATH}/${this.reportInfo.picture}?newName=${this.reportInfo.picture}`)
      });
    },

    loadCommend(){
      console.log("报告ID："+ this.$route.params.reportId)
      getComments({ id: this.$route.params.reportId }).then(res =>{
        console.log(res);
        this.comments = res;
      })
    },

    // 后端返回的是10分制度，需要÷2 同时希望显示具体分数
    loadRating(){
      getScore({ id: this.$route.params.reportId }).then(res =>{
        if (res.length === 0){
          console.log("暂无评分");
          this.rating = null;
        } else {
          console.log("评分："+ res);
          this.rating = res/2;
          this.rating = this.rating.toFixed(1)
        }
      })
    }
  },

  mounted() {
    this.loadReport();
    this.loadCommend();
    this.loadRating();
  }
};
</script>

<style scoped>
.box {
  width: 90%;
}
.comment {
  padding: 20px;
  margin-top: 30px;
  height: 100%;
}
.picture{
    width: auto;
    height: 300px;
}
</style>
