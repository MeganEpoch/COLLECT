// 路由管理器
import Vue from "vue";
import VueRouter from "vue-router";
// @代表src文件夹
// admin
import AdminLayout from "@/layouts/adminLayout.vue";
import AdminTaskList from "@/pages/admin/TaskList.vue";
import AdminCenter from "@/pages/admin/UserCenter.vue"
import RuleCenter from "@/pages/admin/ViewRule"
// employer
import EmployerLayout from "@/layouts/employerLayout.vue";
import TaskList from "@/pages/employer/TaskList";
import TaskCreate from "@/pages/employer/TaskCreate";
import PostArea from "@/pages/employer/ReportList";
import ViewPort from "@/pages/employer/ViewPort.vue";
import EmployerCenter from "@/pages/employer/UserCenter.vue";
import AggregateReport from "@/pages/employer/AggregateReport.vue"
import AggregateReportDetails from "@/pages/employer/AggregateReportDetails.vue"
import Gtry from "@/pages/employer/Gtry.vue"
// employee
import EmployeeLayout from "@/layouts/employeeLayout.vue";
import EmployeeTaskList from "@/pages/employee/TaskList.vue";
import EmployeeCenter from "@/pages/employee/UserCenter.vue";
import PostEdit from "@/pages/employee/PostEdit.vue";
import TaskPeek from "@/pages/employee/TaskPeek";
import TaskProcess from "@/pages/employee/TaskProcess";
import ReportList from "@/pages/employee/ReportList"
import EmployeeViewReport from "@/pages/employee/ViewReport"
import SimilarReportList from "@/pages/employee/SimilarReportList";
import ReportCooperate from "@/pages/employee/ReportCooperate";
// default
import DefaultLayout from "@/layouts/defaultLayout.vue";
import Login from "@/pages/Login.vue";
import Register from "@/pages/Register.vue";
import Home from "@/pages/Home.vue";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Home",
        component: DefaultLayout,
        children: [
            {
                path: "/",
                name: "Home",
                component: Home
            },
            {
                path: "login",
                name: "Login",
                component: Login
            },
            {
                path: "register",
                name: "Register",
                component: Register
            }
        ]
    },
    {
        path: "/admin",
        name: "Admin",
        component: AdminLayout,
        children: [
            {
                path: "/",
                name: " AdminTaskList",
                component: AdminTaskList
            },
            {
                path: "peek/:taskId",
                name: "AdminTaskPeek",
                component: TaskPeek
            },
            {
                path: "user/:userId",
                name: "AdminCenter",
                component: AdminCenter
            },
            {
                path: "rule",
                name: "RuleCenter",
                component: RuleCenter
            }
        ]
    },
    {
        path: "/employer",
        name: "Employer",
        component: EmployerLayout,
        children: [
            {
                path: "/",
                name: "EmployerTaskList",
                component: TaskList,
            },
            {
                path: "peek/:taskId",
                name: "EmployerTaskPeek",
                component: TaskPeek,
            },
            {
                path: "user/:userId",
                name: "EmployerCenter",
                component: EmployerCenter,
            },
            {
                path: "create",
                name: "EmployerTaskCreate",
                component: TaskCreate,
            },
            {
                path: "postarea/:taskId/:page",
                name: "PostArea",
                component: PostArea,
            },
            {
                // 名字被修改过，不知道对其他对方有没有影响
                path: "post/:taskId/:reportId",
                name: "EmployerReportContent",
                component: ViewPort,
            },
            // 聚类报告列表
            {
                path: "aggregate/:taskId",
                name: "AggregateReport",
                component: AggregateReport,
            },
            // 聚合报告详情
            {
                path: "aggregate/:taskId/:cluster",
                name: "AggregateReportDetails",
                component: AggregateReportDetails,
            },
            {
                path: "gtry",
                name: "Gtry",
                component: Gtry
            }
        ]
    },
    {
        path: "/employee",
        name: "Employee",
        component: EmployeeLayout,
        children: [
            {
                path: "/",
                name: "EmployeeTaskList",
                component: EmployeeTaskList
            },
            {
                path: "user/:userId",
                name: "EmployeeUserCenter",
                component: EmployeeCenter
            },
            {
                path: "process/post/postEdit/:taskId",
                name: "EmployeePostEdit",
                component: PostEdit
            },
            {
                path: "peek/:taskId",
                name: "EmployeeTaskPeek",
                component: TaskPeek
            },
            {
                path: "process/:taskId",
                name: "EmployeeTaskProcess",
                component: TaskProcess
            },
            {
                path: "postarea/:taskId/:page",
                name: "EmployeePostArea",
                component: ReportList
            },
            {
                path: "similar/:taskId/:reportId/:page",
                name: "EmployeeSimilarReportList",
                component: SimilarReportList
            },
            {
                path: "cooperate/:taskId/:reportId",
                name: "EmployeeReportCooperate",
                component: ReportCooperate
            },
            {
                // 这个地址需要修改
                path: "post/:taskId/:reportId",
                name: "EmployeeViewReport",
                component: EmployeeViewReport
            }
        ]
    }
];

const router = new VueRouter({
    mode: "hash",
    base: process.env.BASE_URL,
    routes
});

// router.beforeEach((to, from, next) => {
//     let legal = false;
//     if (!to.path.startsWith("/student") && !to.path.startsWith("/teacher"))
//         legal = true;
//     // if (to.path.startsWith("/student") && judgeStudent()) legal = true;
//     // if (to.path.startsWith("/teacher") && judgeTeacher()) legal = true;
//     if (to.name === "CoursePeek") legal = true;
//     if (legal) {
//         next();
//     } else {
//         next({ name: "Login" });
//     }
// });

export default router;
