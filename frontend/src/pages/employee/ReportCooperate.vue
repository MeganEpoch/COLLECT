<template>
  <div class="global">
    <v-dialog v-model="similarDialog" width="500px">
      <v-card>
        <v-card-title>协作报告与原报告相似度
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-col>
              <p>总相似度：{{sim.similarity}}</p>
              <p>报告名称相似度：{{sim.title}}</p>
              <p>漏洞分类相似度：{{sim.bugForm}}</p>
              <p>严重等级相似度：{{sim.severity}}</p>
              <p>测试设备信息相似度：{{sim.device}}</p>
              <p>缺陷情况说明相似度：{{sim.intro}}</p>
              <p>缺陷复现步骤相似度：{{sim.steps}}</p>
              <v-btn class="ml-0 info"
                     style="float: right"
                     @click="goBack">确定</v-btn>
            </v-col>
          </v-container>
        </v-card-text>
      </v-card>
    </v-dialog>

    <v-container>
      <v-row class="global">
        <v-col class="left">
          <v-row>
            <v-col>
              <form  class="pa-12 grey lighten-5 mt-8 rounded-xl rounded-tr-0"
                     style="box-shadow:0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
                                    ">

                <v-chip
                    class="chip"
                    color="deep-purple accent-1"
                    label
                    text-color="white"
                >
                  <v-icon left>mdi-minus-circle</v-icon>
                  原相似报告
                </v-chip>

                <p></p>
                <div class="d-flex justify-space-between">
                  <v-text-field
                      class="d-inline-block"
                      v-model="reportInfo.title"
                      label="报告名称"
                      readonly
                  ></v-text-field>
                </div>
                <div class="d-flex justify-space-between">
                  <v-text-field
                      class="d-inline-block"
                      v-model="reportInfo.bugForm"
                      label="漏洞分类"
                      readonly
                  ></v-text-field>
                </div>
                <div class="d-flex justify-space-between">
                  <v-text-field
                      class="d-inline-block"
                      v-model="reportInfo.severity"
                      label="严重等级"
                      readonly
                  ></v-text-field>
                </div>
                <div class="d-flex justify-space-between">
                  <v-text-field
                      class="d-inline-block"
                      v-model="reportInfo.recurrence"
                      label="复现程度"
                      readonly
                  ></v-text-field>
                </div>
                <div class="d-flex justify-space-between">
                  <v-text-field
                      class="d-inline-block"
                      v-model="reportInfo.device"
                      label="测试设备信息"
                      readonly
                  ></v-text-field>
                </div>
                <v-textarea
                    v-model="reportInfo.intro"
                    label="缺陷情况说明"
                    readonly
                ></v-textarea>
                <v-textarea
                    v-model="reportInfo.steps"
                    label="缺陷复现步骤"
                    readonly
                ></v-textarea>

<!--                <img :src="$host+reportInfo.picture+'?newName='+reportInfo.picture" width="100%"/>-->
                      <el-image
                              v-if="reportInfo.picture!=='undefined'"
                              :src="reportInfo.picture"
                              class="picture"
                              :preview-src-list="[reportInfo.picture]"
                      />

              </form>
            </v-col>
          </v-row>
        </v-col>

        <v-divider
            vertical
            class="mx-4"
            inset
            dark
        >
        </v-divider>

        <div class="box">
          <v-col class="right">
            <div class="pa-12  mt-8 grey lighten-5 rounded-xl rounded-tl-0"
                 style="box-shadow:0 3px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)">
              <v-chip
                  class="chip"
                  color="deep-purple accent-1"
                  label
                  text-color="white"
              >
                <v-icon left>mdi-wrench</v-icon>
                协作报告
              </v-chip>
              <br/><br/>

              <el-form
                  ref="form"
                  :model="form"
                  :rules="rules"
                  label-width="110px"
                  :label-position="labelPosition"
              >

                <el-form-item label="报告名称" prop="name">
                  <el-input v-model="form.name"></el-input>
                </el-form-item>

                <el-form-item label="漏洞分类" prop="bug_type">
                  <el-select v-model="form.bug_type" placeholder="请选择">
                    <el-option label="不正常退出" value="不正常退出"></el-option>
                    <el-option label="功能不完整" value="功能不完整"></el-option>
                    <el-option label="用户体验" value="用户体验"></el-option>
                    <el-option label="页面布局缺陷" value="页面布局缺陷"></el-option>
                    <el-option label="性能" value="性能"></el-option>
                    <el-option label="安全" value="安全"></el-option>
                    <el-option label="其他" value="其他"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="严重等级" prop="severity_type">
                  <el-select v-model="form.severity_type" placeholder="请选择">
                    <el-option label="待定" value="待定"></el-option>
                    <el-option label="较轻" value="较轻"></el-option>
                    <el-option label="一般" value="一般"></el-option>
                    <el-option label="严重" value="严重"></el-option>
                    <el-option label="紧急" value="紧急"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="复现程度" prop="repeat_type">
                  <el-select v-model="form.repeat_type" placeholder="请选择">
                    <el-option label="无规律复现" value="无规律复现"></el-option>
                    <el-option label="小概率复现" value="小概率复现"></el-option>
                    <el-option label="大概率复现" value="大概率复现"></el-option>
                    <el-option label="必现" value="必现"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="测试设备信息" prop="device_type">
                  <el-select v-model="form.device_type" placeholder="请选择">
                    <el-option label="Android设备" value="Android设备"></el-option>
                    <el-option label="Linux设备" value="Linux设备"></el-option>
                    <el-option label="鸿蒙设备" value="鸿蒙设备"></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="缺陷的应用截图" prop="#">
                    <el-upload
                            show-file-list
                            drag
                            action="#"
                            :http-request="handleUploadImage"
                            :file-list="fileList"
                            list-type="picture"
                            :limit="1"
                            accept=".jpg,.png"
                    >
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件,数量1</div>
                    </el-upload>
                </el-form-item>

                <el-form-item label="缺陷情况说明" prop="textarea1">
                  <el-input
                      type="textarea"
                      autosize
                      placeholder="请输入内容"
                      color="grey"
                      v-model="form.textarea1" >
                  </el-input>
                </el-form-item>
                <el-row label="对比">
                  <code-diff
                      :old-string="reportInfo.intro"
                      :new-string="form.textarea1"
                      :context="10"/>
                </el-row>
                <el-form-item label="缺陷复先步骤" prop="textarea2">
                  <el-input
                      type="textarea"
                      autosize
                      placeholder="请输入内容"
                      v-model="form.textarea2">
                  </el-input>
                </el-form-item>
                <el-row label="对比">
                  <code-diff
                      :old-string="reportInfo.steps"
                      :new-string="form.textarea2"
                      :context="10"/>
                </el-row>
                <el-row type="flex" class="row-bg" justify="space-around">
                  <el-button type="primary"
                             style="margin-left: 3%"
                             @click="submitForm('form')"
                  >立即提交</el-button>
                  <el-button type="warning" @click="resetForm('form')">重置</el-button>
                  <el-button @click="goBack()">取消</el-button>
                </el-row>
              </el-form>
            </div>
          </v-col>
        </div>
      </v-row>

    </v-container>

  </div>
</template>

<script>
import { getReport } from "@/api/report";
import { saveReport,getSimilarReport } from "@/api/report";
import {oss} from "@/api/file";
import CodeDiff from 'vue-code-diff'

export default {
  name: "ReportCooperate",
  components: {CodeDiff},

  data() {
    return {
      reportInfo: { },
      labelPosition: 'right',
      form: {
        name: '',
        bug_type: '',
        severity_type: '',
        repeat_type: '',
        device_type: '',
        image: '',
        textarea1: '',
        textarea2: '',
      },
      rules:{
        name:[
          { required: true, message:"请输入报告标题", trigger:'blur'},
          { min: 2, max: 15, message: "长度在2到15个字符", trigger: 'blur'}
        ],
        textarea1:[
          { required: true, message:"请输入缺陷情况说明", trigger:'blur'}
        ],
        textarea2:[
          { required: true, message:"请输入缺陷复先步骤（从应用启动到缺陷出现的操作步骤）", trigger:'blur'}
        ],
        textarea3:[
          { required: true, message:"请输入测试设备信息", trigger:'blur'}
        ]
      },
      similarDialog: false,
      sim: {
        similarity: 0,
        title: 0,
        bugForm: 0,
        severity: 0,
        recurrence: 0,
        device: 0,
        intro: 0,
        steps: 0

      },
      list: [],
      fileList: [],
      instructionFile: '',
      reportId: null,
    }
  },


  methods: {
    loadReport() {
      getReport({ id: this.$route.params.reportId }).then(res => {
        console.log(res)
        this.reportInfo = res.data;
        //this.reportInfo.picture="bb703866-038f-4937-ad75-1e0e19b0b1df.png";
        console.log(this.reportInfo.picture);
      });
    },
    async handleUploadImage(file) {
      const formData = new window.FormData();
      formData.append("file", file.file);
      const res = await oss(formData);
      console.log(res);
      //if (res.code === 1) {
        this.form.image = res.url;
      //}
    },
    submitForm(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const payload1 = {
            employeeName: window.localStorage.getItem("username"),
            employeeId: window.localStorage.getItem("userId"),
            taskId: this.$route.params.taskId,
            title: this.form.name,
            intro: this.form.textarea1,
            steps: this.form.textarea2,
            picture: this.form.image,
            bugForm: this.form.bug_type,
            severity: this.form.severity_type,
            recurrence: this.form.repeat_type,
            device: this.form.device_type,
            id: null,
            reportTime: null,
          };
          console.log(payload1);
          saveReport(payload1).then(res => {
            console.log(res);
            this.reportId = res.data.id;
          });
          setTimeout(() => {
            const payload2 = {
              taskId: this.$route.params.taskId,
              reportId: this.reportId,
              oldReportId: this.$route.params.reportId
            };
            console.log(payload2);
            getSimilarReport(payload2).then(res => {
              console.log(res);
              this.sim = res;
              this.similarDialog = true;
            })
          }, 1000);
          //alert('submit!');
          // alert("{{res.msg}}")
          this.resetForm(form);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    // resetField	对该表单项进行重置，将其值重置为初始值并移除校验结果 是里面自带的方法 不需要定义
    resetForm(form) {
      this.$refs[form].resetFields();
    },
    goBack() {
      window.history.back();
    },
  },

  mounted() {
    this.loadReport();
  }
};
</script>

<style scoped>
.chip {
  margin-top: 1%;
}
.global {
  width: 100%;
}
.left {
  width: 100%;
}
.right {
  width: 100%;
}
.rightBorder {
  box-shadow:0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.row-bg{
  padding: 10px 0;
}
.box {
  width: 50%;
}
.picture{
    width: auto;
    height: 200px;
}
</style>
