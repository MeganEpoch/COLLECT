<template>
  <div>
    <v-container class="box">
      <template class="box">
        <v-row class="mt-16 mb-8">
          <v-chip
              class="ma-2"
              color="teal darken-2"
              label
              text-color="white"
          >
            <v-icon left>
              mdi-twitter
            </v-icon>
            现有规则
          </v-chip>
        </v-row>

        <v-card
            elevation="2"
            outlined
            shaped
            class="mt-12"
        >
          <v-card-title>COLLECT 众包测试管理系统规则列表</v-card-title>
          <v-card-text>
<!--            {{fixedRuleMsg}}-->
<!--            <p></p>-->
            <p v-if="value.length === 0">请选择生效规则</p>
            <p v-if="value.length !== 0">生效规则：{{value.content}}</p>
          </v-card-text>
          <v-card-text>
            <template>
              <v-row type="flex" class="row-bg" justify="space-around">
                <v-btn dark color="teal" @click="changeRuleDialog = true">
                  更改现有规则
                </v-btn>
                <v-btn dark color="teal" @click="showDialog">
                  添加规则
                </v-btn>
              </v-row>
            </template>
          </v-card-text>
        </v-card>
      </template>
    </v-container>

    <v-dialog v-model="changeRuleDialog" persistent max-width="400px">
      <v-card>
        <v-card-title>
          更改规则
        </v-card-title>
        <v-card-text>
          <v-col cols="12">
            <el-select
                v-model="value"
                :value="value.content"
                filterable
                default-first-option
                placeholder="请选择系统标签"
                @change="handleChange({ id: value.id })"
            >
              <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.content"
                  :value="item"
              ></el-option>
            </el-select>
          </v-col>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="info" text @click="changeRuleDialog = false">
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-dialog v-model="newRuleDialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          新增系统规则
        </v-card-title>
        <v-card-text>
          <v-col cols="12">
            <v-text-field
                v-model="ruleInfo.content1"
                label="规则描述"
                required
            ></v-text-field>
          </v-col>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="info" text @click="handleRule">
            确认添加规则
          </v-btn>
          <v-btn color="info" text @click="newRuleDialog = false">
            取消
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import {chooseValidRule, getRules, addRule, getCurrentRule} from  "@/api/recommend"


export default {
  name: "ViewRule",
  data(){
    return{
      // 固有规则
      fixedRuleMsg:"等待编辑",
      options: [],
      value: [],
      newRuleDialog: false,
      changeRuleDialog: false,
      ruleInfo:{
        content1:"",
        type1: 0,
        status1: 0 //新增的规则都是无用规则
      }
    }
  },

  methods:{
    // 加载现有规则
    showDialog(){
      this.newRuleDialog = true;
    },
    // 添加规则
    handleRule(){
      const payload = {
        content: this.ruleInfo.content1,
        status: this.ruleInfo.status1,
        type: this.ruleInfo.type1,
      };
      console.log(payload);
      // 添加规则的后端方法
      addRule(payload).then(res =>{
        console.log(res);
        this.newRuleDialog = false;
        this.refreshRules();
      })
    },
    // 指定系统现在生效的规则
    handleChange(payload){
      this.changeRuleDialog = false;
      console.log(payload)
      chooseValidRule(payload).then(res => {
        console.log(res);
      })
    },
    // 获取系统现有可选规则
    refreshRules(){
      getRules().then(res => {
        console.log(res);
        this.options = res;
      })
    },
    // 获取系统现在正在执行的规则
    refreshCurrentRule(){
      // debugger
      getCurrentRule().then(res => {
        console.log(res);
        this.value = res;
      })
    }
  },

  mounted() {
    this.refreshRules();
    this.refreshCurrentRule();
  }
}
</script>

<style scoped>
.box {
  width: 75%;
}
.row-bg{
  padding: 10px 0;
}
</style>