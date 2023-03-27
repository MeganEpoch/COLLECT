import axios from "axios";
import {REPORT_MODULE} from "./_prefix";


/**
 * 获取指定课程的贴子按照指定方式排序后的指定页所含贴子
 * @param {*} payload
 * @returns
 */
// 后端这一块儿的代码 让表格里是本来可以返回的数据
// @GetMapping("/{taskId}/{page}")
// public PageInfo<ReportVO> getReports(@PathVariable Integer taskId, @PathVariable Integer page, @RequestParam(required = false, defaultValue = "by-post-time") String sortMethod) {
//     return reportService.getReports(taskId, page, Constant.REPORT_PAGE_SIZE, sortMethod);
// }
export const getReports = payload => {
    const { taskId, page } = payload;
    return axios.get(`${REPORT_MODULE}/${taskId}/${page}`)
        // .then(res => Promise.all(res.data.list.map(post => setUserName(post))));
        // .then(res => res.data);
        .then(res =>{
            return res.data;
        })
};

/**
 * 获取指定任务报告的相似报告列表
 * @param {*} payload
 * @returns
 */
export const getSimilarReports = payload => {
    const { taskId, reportId, page} = payload;
    return axios.get(`${REPORT_MODULE}/${taskId}/${reportId}/${page}`)
        .then(res =>{
            return res.data;
        })
};

/**
 * 获取协作报告与原报告的相似度
 * @param {*} payload
 * @returns
 */
export const getSimilarReport = payload => {
    const { taskId, reportId, oldReportId} = payload;
    return axios.get(`${REPORT_MODULE}/cooperate/${taskId}/${reportId}/${oldReportId}`)
        .then(res =>{
            return res.data;
        })
};

/**
 * 根据报告id获取指定报告
 * @param {*} payload
 * @returns
 */
export const getReport = payload => {
    const {id} = payload;
    return axios.get(`${REPORT_MODULE}/by-id/${id}`)
        .then(res => res.data);
};

/**
 * 根据用户id和任务id获取指定报告
 * @param {*} payload
 * @returns
 */
export const getOldReport = payload => {
    const {employeeId,taskId} = payload;
    return axios.get(`${REPORT_MODULE}/oldReport/${employeeId}/${taskId}`)
        .then(res => res.data);
};

// 报告聚类
//    @GetMapping("/cluster/{id}")
//    public List getReportCluster(@PathVariable Integer id) {
//        return reportService.getReportCluster(id);
//    }
export const getReportCluster = payload => {
    const {id} = payload;
    return axios.get(`${REPORT_MODULE}/cluster/${id}`)
        .then(res => res.data)
}

// /**
//  * 发布/修改贴子、保存相关信息到数据库并返回用户发布/修改的贴子
//  * @param {*} payload
//  * @returns
//  */
// export const savePost = payload => {
//     // const { userId, } = payload;
//     return axios
//         .post(`${POST_MODULE}/save`, payload)
//         .then(res => res.data);
// };

/**
 * 发布/修改报告、保存相关信息到数据库并返回用户发布/修改的报告
 * @param {*} payload
 * @returns
 */
export const saveReport = payload => {
    return axios
        .post(`${REPORT_MODULE}/save`, payload)
        .then(res => res.data)
}

/**
 * 根据任务ID、类簇号 获取词云图
 */
export const getWordCloud = payload => {
    const { taskId, cluster } = payload;
    return axios
        .get(`${REPORT_MODULE}/word_cloud/${taskId}/${cluster}`)
        .then(res => res.data)
}

/**
 * 根据任务ID获取主报告的ID
 * @param {*} payload
 * @returns
 */
export const getMasterReport = payload => {
    const {taskId, cluster} = payload;
    return axios.get(`${REPORT_MODULE}/mix/${taskId}/${cluster}`)
        .then(res => res.data)
}

/**
 * 根据主报告id获取子报告列表
 * @param {*} payload
 * @returns
 */
export const getReportExtra = payload => {
    const {reportId} = payload;
    return axios.get(`${REPORT_MODULE}/mix/extra/${reportId}`)
        .then(res => res.data)
}

/**
 * 根据任务id获取树状视图展示
 * @param {*} payload
 * @returns
 */
export const getClustersTreeView = payload => {
    const {taskId} = payload;
    return axios.get(`${REPORT_MODULE}/cluster/tree/${taskId}`)
        .then(res => res.data)
}

