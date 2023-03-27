<!--页面模板，定义了页面的 HTML 元素-->
<!--TODO：有图片 有步骤提示-->
<!--出现缺陷的应用截图、
缺陷情况说明、
缺陷复先步骤（从应用启动到缺陷出现的操作步骤）、
测试设备信息等-->
<template>
  <div>
    <v-container class="pl-16 pr-16">
      <div style="margin: 60px;" class="pa-12 grey lighten-5 mt-8">
        <el-form
            ref="form"
            :model="form"
            :rules="rules"
            label-width="110px"
            :label-position="labelPosition"
        >
          <el-form-item label="报告名称" prop="title">
            <el-input v-model="form.title"></el-input>
          </el-form-item>

          <el-form-item label="漏洞分类" prop="bugForm">
            <el-select v-model="form.bugForm" placeholder="请选择">
              <el-option label="不正常退出" value="不正常退出"></el-option>
              <el-option label="功能不完整" value="功能不完整"></el-option>
              <el-option label="用户体验" value="用户体验"></el-option>
              <el-option label="页面布局缺陷" value="页面布局缺陷"></el-option>
              <el-option label="性能" value="性能"></el-option>
              <el-option label="安全" value="安全"></el-option>
              <el-option label="其他" value="其他"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="严重等级" prop="severity">
            <el-select v-model="form.severity" placeholder="请选择">
              <el-option label="待定" value="待定"></el-option>
              <el-option label="较轻" value="较轻"></el-option>
              <el-option label="一般" value="一般"></el-option>
              <el-option label="严重" value="严重"></el-option>
              <el-option label="紧急" value="紧急"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="复现程度" prop="recurrence">
            <el-select v-model="form.recurrence" placeholder="请选择">
              <el-option label="无规律复现" value="无规律复现"></el-option>
              <el-option label="小概率复现" value="小概率复现"></el-option>
              <el-option label="大概率复现" value="大概率复现"></el-option>
              <el-option label="必现" value="必现"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="测试设备信息" prop="device">
            <el-select v-model="form.device" placeholder="请选择">
              <el-option label="Android设备" value="Android设备"></el-option>
              <el-option label="Linux设备" value="Linux设备"></el-option>
              <el-option label="鸿蒙设备" value="鸿蒙设备"></el-option>
            </el-select>
          </el-form-item>

          <!--          <el-form-item label="缺陷的应用截图" prop="#">
                        <v-file-input
                                v-model="instructionFile"
                                label="缺陷的应用截图"
                                @change="handleUploadImage"
                        ></v-file-input>
                    </el-form-item>-->

          <!--                      :http-request="upload"
                                :before-upload="beforeAvatarUpload"-->

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

          <el-form-item label="缺陷情况说明" prop="intro">
            <el-input
                type="textarea"
                autosize
                placeholder="请输入内容"
                v-model="form.intro" >
            </el-input>
          </el-form-item>
          <el-form-item label="缺陷复先步骤" prop="steps">
            <el-input
                type="textarea"
                autosize
                placeholder="请输入内容"
                v-model="form.steps">
            </el-input>
          </el-form-item>
          <el-row type="flex" class="row-bg" justify="space-around">
            <el-button type="primary" @click="submitForm('form')">立即提交</el-button>
            <el-button type="warning" @click="resetForm('form')">重置</el-button>
            <el-button @click="goBack()">取消</el-button>
          </el-row>
        </el-form>
      </div>
    </v-container>
  </div>
</template>

<!--页面脚本，主要用来实现当前页面数据初始化、事件处理等等操作-->
<script>
import { saveReport,getOldReport } from "@/api/report";
import {oss} from "@/api/file";

export default {
  name: "PostEdit",
  data() {
    return {
      labelPosition: 'right',
      form: {
        title: '',
        bugForm: '',
        severity: '',
        recurrence: '',
        device: '',
        picture: '',
        intro: '',
        steps: '',
        id: '',
        reportTime: ''
      },
      rules:{
        title:[
          { required: true, message:"请输入报告标题", trigger:'blur'},
          { min: 2, max: 15, message: "长度在2到15个字符", trigger: 'blur'}
        ],
        intro:[
          { required: true, message:"请输入缺陷情况说明", trigger:'blur'}
        ],
        steps:[
          { required: true, message:"请输入缺陷复先步骤（从应用启动到缺陷出现的操作步骤）", trigger:'blur'}
        ],
      },
      dialogFormVisible: false,
      fileList: [],
      instructionFile: '',
      reportId: null,
    }
  },
  methods: {
    async handleUploadImage(file) {
      const formData = new window.FormData();
      formData.append("file", file.file);
      const res = await oss(formData);
      console.log(res);
      //if (res.code === 1) {
      this.form.picture = res.url;
      //}
    },
    // 返回任务详情页面
    goBack() {
      window.history.back();
    },

    submitForm(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          const payload = {
            employeeName: window.localStorage.getItem("username"),
            employeeId: window.localStorage.getItem("userId"),
            taskId: this.$route.params.taskId,
            title: this.form.title,
            intro: this.form.intro,
            steps: this.form.steps,
            picture: this.form.picture,
            bugForm: this.form.bugForm,
            severity: this.form.severity,
            recurrence: this.form.recurrence,
            device: this.form.device,
            id: this.form.id,
            reportTime: this.form.reportTime
          };
          console.log(payload);
          saveReport(payload).then(res => {
            console.log(res);
            this.snackbar = true;
            this.reportId = res.data.id;
          })
          alert('submit!');
          // alert("{{res.msg}}")
          this.resetForm(form);
          setTimeout(() => {
            const taskId = this.$route.params.taskId;
            this.$router.push(`/employee/similar/${taskId}/${this.reportId}/0`);
          }, 1000);
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
    // submitUpload() {
    //   this.$refs.upload.submit();
    // },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    loadReport(){
      const payload={
        employeeId: window.localStorage.getItem("userId"),
        taskId: this.$route.params.taskId
      }
      console.log(payload);
      getOldReport(payload).then(res =>{
        console.log(res);
        if(res.code==1){
          this.form=res.data;
        }
      })
    }
  },
  mounted() {
    this.loadReport();
  }
};
</script>

<style>
.row-bg{
  padding: 10px 0;
}
</style>