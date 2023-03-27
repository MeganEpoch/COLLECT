import axios from "axios";
import {TODOTASK_MODULE} from "./_prefix";

/**
 * 获取任务 GET /task/{taskId}?uid={uid}
 * @param {*} payload
 * @returns
 */
export const createTodoTask = payload => {
    const { uid, taskId: taskId } = payload;
    return axios.get(`${TODOTASK_MODULE}/${taskId}?uid=${uid}`).then(res => {
        return res.data;
    });
};


/**  1
 * 根据uid查询工人所有的任务 GET task/eid/{uid}
 * @param {*} uid
 * @returns
 */
export const getTodoTasks = uid => {
    return axios.get(`${TODOTASK_MODULE}/todo/${uid}`).then(res => {
        return res.data;
    });
};

/**  1
 * 根据uid查询工人历史完成的任务 GET task/eid/{uid}
 * @param {*} uid
 * @returns
 */
export const getFinishedTasks = uid => {
    return axios.get(`${TODOTASK_MODULE}/finished/${uid}`).then(res => {
        return res.data;
    });
};

/**
 * 根据taskId查询接受任务的工人 GET task/taskId/{taskId}
 * @param {*} taskId
 * @returns
 */
export const getTodoTaskById = taskId => {
    return axios.get(`${TODOTASK_MODULE}/${taskId}`).then(res => {
        return res.data;
    });
};


/**
 * 任务选取：众包工人选择正在招募众包工人的众测任务并接受任务
 * @param {*} taskId employeeId
 * @returns
 */
export const selectTodoTask = (taskId, employeeId) =>{
    return axios
        .post(`${TODOTASK_MODULE}/select?taskId=${taskId}&employeeId=${employeeId}`)
        .then(res => {
            return res.data;
        });
}

/**
 * 提价任务完成请求
 * @param {*} (todoTaskId, employeeId)
 * @returns
 */
export const finishTask = (employeeId, todoTaskId) =>{
    return axios
        .post(`${TODOTASK_MODULE}/finish?uid=${employeeId}&todoTaskId=${todoTaskId}`)
        .then(res => {
            return res.data;
        });
}