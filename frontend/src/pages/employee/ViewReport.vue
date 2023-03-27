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
                  <div class="img">
                  <el-image
                          v-if="reportInfo.picture!=='undefined'"
                          :src="reportInfo.picture"
                          class="picture"
                          :preview-src-list="[reportInfo.picture]"
                          fit="contain"
                  />
                  </div>
              </el-descriptions-item>
            </el-descriptions>
          </div>
        </el-col>

        <el-col :span="8">
          <div class="grid-content">
            <el-row class="mt-16"></el-row>
            <!--  评论区  -->
            <v-container>
              <h4>评论区</h4>
              <p></p>
              <p>您可以对报告进行评分和评价</p>
              <!--   评分区   -->
              <v-row type="flex" class="row-bg ma-5" justify="space-around">
                <el-rate
                    v-model="rating"
                    :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
                    show-text>
                </el-rate>
              </v-row>

              <!--   发布评论的地方   -->
              <el-input
                  type="textarea"
                  :autosize="{ minRows: 2, maxRows: 10}"
                  placeholder="请输入评论内容"
                  v-model="commentInfo.content">
              </el-input>
              <v-btn class="float-right deep-purple ma-5" small dark @click="sendComment">
                提交报告评分及评价
              </v-btn>
            </v-container>
          </div>
        </el-col>
      </el-row>
    </v-container>
    <v-row class="ma-12"></v-row>

    <!--  提示评论结果  -->
    <v-snackbar v-model="showSnackBar" :color="snackBarColor" top>
      {{ snackBarMsg }}
    </v-snackbar>
  </div>
</template>

<script>
import { getReport } from "@/api/report";
import {createComment} from "@/api/comment"
import {FILE_BASE_PATH} from "@/api/file";

export default {
  name: "ViewReport",

  data() {
    return {
      reportInfo: {},
      commentInfo: {},
      rating: 0,
      //reportImg: require('${FILE_BASE_PATH}/${reportInfo.picture}?newName=${reportInfo.picture}')
      showSnackBar: false,
      snackBarMsg: "",
      snackBarColor: "success",
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

    sendComment(){
      const payload = {
        employeeId: window.localStorage.getItem("userId"),
        content: this.commentInfo.content,
        reportId: this.$route.params.reportId,
        score: this.rating
      }
      console.log(payload)
      createComment(payload).then(res => {
        console.log(res);
        if (res.code === 1){
          this.snackBarColor = "success";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
        } else {
          this.snackBarColor = "error";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
        }
      });
    },
  },

  mounted() {
    this.loadReport();
  }
};
</script>

<style scoped>
.box {
  width: 90%;
}
.picture{
    width: auto;
    height: auto;
    max-width: 100%;
    max-height: 100%;
}
.img{
    width: 300px;
    height: 300px;
}
</style>
