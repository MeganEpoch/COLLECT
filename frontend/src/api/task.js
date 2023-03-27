import axios from "axios";
import { TASK_MODULE } from "./_prefix";


/**
 * 管理员浏览所有任务
 * @returns
 */
export const getAllTasks = () => {
    return axios.get(`${TASK_MODULE}/admin`).then(res => {
        return res.data;
    });
};


/**
 * 根据正在招募众包工人的任务（任务广场）
 * 如果带参数uid，则需要判断该用户是否接受过任务
 * @param uid 用户id
 * @returns
 */
export const getValidTasks = payload => {
    const {uid, page} = payload;
    return axios.get(`${TASK_MODULE}/all/${page}?uid=${uid}`)
        .then(res => {
            return res.data;
        });
};


/**
 * 获取任务 GET /task/{taskId}?uid={uid} 会包含工人是否接取的信息
 * @param {*} payload
 * @returns
 */
export const getTaskById = payload => {
    const { uid, taskId: taskId } = payload;
    return axios.get(`${TASK_MODULE}/${taskId}`, { uid, taskId }).then(res => {
        return res.data;
    });
};



/**
 *获取用户正在做的任务列表 GET task/eid/{uid}
 * @param {*} uid
 * @returns
 */
// export const getAcceptedTasks = uid => {
//     return axios.get(`${TASK_MODULE}/eid/${uid}`).then(res => {
//         return res.data;
//     });
// };

/**
 * 根据uid查询为用户推荐的任务 GET task/eid/{uid}
 * @param {*} eid
 * @returns
 */
export const getRecommendTasks = eid => {
    return axios.get(`${TASK_MODULE}/recommend/${eid}`).then(res => {
        return res.data;
    });
};

/**
 *获取发包方可管理的课程列表 GET task/rid/{uid}
 * @param {*} uid
 * @returns
 */
export const getManageableTasks = uid => {
    return axios.get(`${TASK_MODULE}/rid/${uid}`).then(res => {
        return res.data;
    });
};

/**
 * 创建任务 POST task/create
 * @param {*} payload
 * @returns
 */
export const createTask = payload => {
    const {
        id,
        name,
        type,
        intro,
        createTime,
        endTime,
        requiredNumber,
        employerId,
        employerName,
        accepted,
        manageable,
        fileName1,
        fileName2,
        status,
        device,
        difficulty
    } = payload;
    return axios
        .post(`${TASK_MODULE}/create`, {
            id,
            name,
            type,
            intro,
            createTime,
            endTime,
            requiredNumber,
            employerId,
            employerName,
            accepted,
            manageable,
            fileName1,
            fileName2,
            status,
            device,
            difficulty
        })
        .then(res => {
            return res.data;
        });
};

/**
 * 根据taskId获取当前任务状态（检查是否所有人均提交）
 * @param {*} taskId
 * @returns
 */
export const getTaskStatus = taskId => {
    return axios.get(`${TASK_MODULE}/status/${taskId}`).then(res => {
        return res.data;
    });
};

