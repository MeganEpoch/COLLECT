<template>
    <div>
        <v-container class="pl-16 pr-16">
            <!-- alert -->
            <v-alert
                    class="alert"
                    outlined
                    type="success"
                    text
                    v-show="showSuccessDialog"
                    transition="scroll-y-transition"
            >
                修改成功!
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
            <form class="pa-12 grey lighten-5 mt-8">
<!--                <v-text-field v-model="userInfo.id" label="ID" readonly></v-text-field>-->
                <v-text-field
                        v-model="userInfo.uname"
                        label="用户名"
                        readonly
                ></v-text-field>
                <v-text-field
                        v-model="userInfo.phone"
                        label="手机号"
                        readonly
                ></v-text-field>
                <v-text-field
                        v-model="userInfo.userRole"
                        label="用户角色"
                        readonly
                ></v-text-field>
                <v-select
                        :items="preferences"
                        v-model="userInfo.preference"
                        label="任务偏好"
                ></v-select>
                <v-select
                        :items="active_types"
                        v-model="userInfo.activation"
                        label="活跃度"
                ></v-select>
                <p>专业能力</p>
                <v-row>
                    <v-col>
                        <v-text-field
                                v-model="userInfo.rate"
                                label="历史提交报告评分"
                                readonly
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-select
                                :items="backgrounds"
                                v-model="userInfo.background"
                                label="学历"
                        ></v-select>
                    </v-col>
                </v-row>
                <v-select
                        :items="devices"
                        v-model="userInfo.device"
                        label="测试设备"
                ></v-select>
                <v-text-field
                        v-model="userInfo.createTime"
                        label="注册时间"
                        readonly
                ></v-text-field>
                <v-btn class="ml-0 mt-8 info float-right" @click="handleSave">
                    保存修改
                </v-btn>
                <br></br>
            </form>
        </v-container>
    </div>
</template>

<script>
    import { getUser,saveEdit } from "@/api/user";

    export default {
        name: "UserCenterEdit",

        data() {
            return {
                userInfo: {
                    id: 0,
                    uname: "",
                    phone: "",
                    password: null,
                    picture: null,
                    balance: 0,
                    userRole: "",
                    createTime: "",
                    device: "Android设备",
                    preference: "功能测试",
                    activation: "每周完成1~2次任务",
                    rate: null,
                    background: ""
                },
                dialog: false,
                showAlert: false,
                showSuccessDialog: false,
                showFailDialog: false,
                value: 0,
                msg: "",
                preferences: ["功能测试","性能测试"],
                active_types: ["每周完成1~2次测试","每周完成3~4次测试","每周完成5次以上测试"],
                backgrounds: ["小学","初中","高中","专科","本科","硕士","博士"],
                devices: ["Android设备","Linux设备","鸿蒙设备"]
            };
        },

        methods: {

            refreshUserInfo() {
                const userId = window.localStorage.getItem("userId");
                getUser(userId).then(res => {
                    this.userInfo = res || {};
                });
            },
            handleSave(){
                const payload={
                    preference: this.userInfo.preference,
                    activation: this.userInfo.activation,
                    background: this.userInfo.background,
                    device: this.userInfo.device};
                console.log(payload);
                saveEdit(payload).then(res =>{
                    console.log(res);
                    if(res.code==1){
                        setTimeout(() => {
                            this.showSuccessDialog = false;
                        }, 1000);
                        const {userId} = window.localStorage.getItem("userId");
                        setTimeout(() => {
                            this.$router.push(`/employee/user/${userId}`);
                        }, 1000);
                    }else {
                        this.msg = res.msg;
                        this.showFailDialog = true;
                        setTimeout(() => {
                            this.showFailDialog = false;
                        }, 1000);
                    }
                })
            }
        },

        mounted() {
            this.refreshUserInfo();
        }
    };
</script>

<style scoped>
    .alert {
        position: fixed;
        left: 50%;
        top: 100px;
        z-index: 999;
    }
</style>
