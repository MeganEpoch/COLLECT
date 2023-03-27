<template>
  <div>
    <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showSuccessDialog"
        transition="scroll-y-transition"
    >
      {{ msg }} !
    </v-alert>
    <!-- alert -->
    <v-alert
        class="alert"
        outlined
        type="warning"
        text
        v-show="showFailDialog"
        transition="scroll-y-transition"
    >
      {{ msg }}
    </v-alert>
    <div>
      <v-container class="box">
        <div class="pa-12 grey lighten-5">
          <h2 class="ma-5">新建任务</h2>
        <el-form
            ref="form"
            :model="taskInfo"
            :rules="rules"
            label-width="110px"
            :label-position="labelPosition"
        >
          <el-form-item label="任务名称" prop="title">
            <el-input v-model="taskInfo.name"></el-input>
          </el-form-item>
          <el-form-item label="测试类型" prop="type">
            <el-select v-model="taskInfo.type" placeholder="请选择">
              <el-option label="功能测试" value="功能测试"></el-option>
              <el-option label="性能测试" value="性能测试"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="任务简介" prop="intro">
            <el-input
                type="textarea"
                :autosize="{ minRows: 3, maxRows: 10}"
                placeholder="请输入内容"
                v-model="taskInfo.intro" >
            </el-input>
          </el-form-item>
          <el-form-item label="所需人数" prop="requiredNumber">
            <el-input v-model="taskInfo.requiredNumber"></el-input>
          </el-form-item>
          <el-form-item label="测试难度" prop="difficulty">
            <p></p>
            <el-rate
                v-model="taskInfo.difficulty"
                :colors="colors"
                :texts="texts"
                show-text>
            </el-rate>
          </el-form-item>
          <el-form-item label="测试设备" prop="device">
            <el-select v-model="taskInfo.device" placeholder="请选择">
              <el-option label="Android设备" value="android"></el-option>
              <el-option label="IOS设备" value="ios"></el-option>
              <el-option label="鸿蒙设备" value="鸿蒙"></el-option>
              <el-option label="Linux设备" value="linux"></el-option>
            </el-select>
          </el-form-item>
          <el-row>
            <el-col>
              <el-form-item label="任务开始时间" prop="time">
                <el-date-picker
                    v-model="taskInfo.createTime"
                    type="datetime"
                    placeholder="选择开始时间"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    :picker-options="pickerOptions">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col>
              <el-form-item label="任务结束时间" prop="time">
                <el-date-picker
                    v-model="taskInfo.endTime"
                    type="datetime"
                    placeholder="选择结束时间"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    :picker-options="pickerOptions">
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item label="上传待测试文件">
            <el-upload
                show-file-list
                drag
                action="#"
                :http-request="handleUploadFile1"
                :file-list="fileList"
                list-type="file"
                :limit="1"
                accept=".exe"
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将待测应用可执行文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">只能上传exe文件,数量1</div>
            </el-upload>
          </el-form-item>
          <el-form-item label="上传描述文件">
            <el-upload
                show-file-list
                drag
                action="#"
                :http-request="handleUploadFile2"
                :file-list="fileList"
                list-type="file"
                :limit="1"
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将测试描述文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">只能上传1份文件</div>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <v-btn class="ml-10 info" @click="submit">
              创建任务
            </v-btn>
          </el-form-item>
        </el-form>
        </div>
      </v-container>
    </div>
  </div>
</template>

<script>
import {createTask} from "@/api/task";
import {uploadFile} from "@/api/file";

export default {
  name: "TaskCreate",

  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
      rules:{
        title:[
          { required: true, message:"请输入任务标题", trigger:'blur'},
          { min: 2, max: 15, message: "长度在2到15个字符", trigger: 'blur'}
        ],
        type:[
          { required: true, message: '请选择测试类型', trigger: 'change' }
        ],
        intro:[
          { required: true, message:"请输入任务简介", trigger:'blur'}
        ],
        difficulty: [
          { required: true, message: '请选择测试难度', trigger: 'change' }
        ],
        requiredNumber: [
          { required: true, message: '测试所需人数不能为空'},
          // { type: 'number', message: '测试所需人数必须为数字值'}
        ],
        device: [
          { required: true, message: '请选择测试设备', trigger: 'change' }
        ],
        time:[
          { required: true, message:"请选择测试持续时间", trigger:'blur'}
        ],
      },
      colors: ['#FFCC5AFF', '#F7BA2A', '#FF9900'],
      texts:['易如反掌','简单','中等','困难','谨慎选择'],
      labelPosition: 'right',
      taskInfo: {
        name: "",
        type: "",
        intro: "",
        requiredNumber: null,
        difficulty: 0,
        device: "",
        createTime: "",
        endTime: "",
        fileName1:"",
        fileName2:"",
        status:4
      },
      // rules: [
      //   value => !!value || 'Required.',
      // ],
      instructionFile1: null,
      instructionFile2: null,
      types: ["功能测试", "性能测试"],
      equipments:["android","linux","鸿蒙","ios"],
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      files: [],
      showAlert: false,
      alertMsg: "",
      fileList: [],
      currentEditFile: {
        id: 0,
        taskId: 0,
        number: 0,
        title: "",
        fileName: "",
        fileType: "",
        fileSize: "",
        freeFlag: false,
        downloadFlag: true,
        availableFlag: false,
        uploadTime: ""
      }
    };
  },

  methods: {
    async handleUploadFile1(file) {
      const formData = new window.FormData();
      formData.append("file", file.file);
      const res = await uploadFile(formData);
      console.log(res);
      if (res.code === 1) {
        this.taskInfo.fileName1 = res.data.name;
        console.log(this.taskInfo.fileName1);
      }
    },
    async handleUploadFile2(file) {
      const formData = new window.FormData();
      formData.append("file", file.file);
      const res = await uploadFile(formData);
      console.log(res);
      if (res.code === 1) {
        this.taskInfo.fileName2 = res.data.name;
      }
    },

    goback(){
      window.history.back();
    },

    submit() {
      const uid = window.localStorage.getItem("userId");
      const uname = window.localStorage.getItem("username");
      const payload = {
        ...this.taskInfo,
        employerId: uid,
        employerName: uname,
        accepted: false,
        manageable: true
      };
      console.log(payload);
      createTask(payload).then(res => {
        console.log(res);
        if (res.code === 1) {
          this.msg=res.msg;
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
            this.goback();
          }, 1000);
        } else {
          this.showFailDialog = true;
          this.msg = res.msg;
          setTimeout(() => {
            this.showFailDialog = false;
            this.goback();
          }, 1000);
        }
      });
    },
  },
};
</script>

<style scoped>
.alert {
  position: fixed;
  left: 45%;
  top: 100px;
  z-index: 999;
}
.box {
  width: 60%;
}
</style>
