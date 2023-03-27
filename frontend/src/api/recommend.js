import axios from "axios";
import {RECOMMEND_MODULE} from "./_prefix";


/**
 * 指定生效规则 把规则转变状态存在表里 get任务方法中在表里查是哪种规则，再调用相关方法
 * @param {*} payload
 * @returns
 */
export const chooseValidRule = payload => {
    return axios
        .post(`${RECOMMEND_MODULE}/choose`, payload)
        .then(res => res.data)
}

/**
 * 获取现有规则列表
 * @returns
 */
export const getRules = () => {
    return axios.get(`${RECOMMEND_MODULE}/rules`).then(res => {
        return res.data;
    });
};

/**
 * 添加规则
 * @param {*} payload
 * @returns
 */
export const addRule = payload => {
    return axios.post(`${RECOMMEND_MODULE}/add`, payload)
        .then(res => res.data)
}

/**
 * 获取现在正在执行的规则
 * @returns
 */
export const getCurrentRule = () => {
    return axios.get(`${RECOMMEND_MODULE}/current_rule`).then(res => {
        return res.data;
    });
    // return Promise.resolve({
    //     "code": 1,
    //     "msg": "信息返回成功",
    //     "data": {
    //         "id": 1,
    //         "content":"基于任务内容的协同过滤推荐",
    //         "status":1,
    //         "type":1,
    //         "changeTime": 1647050400000
    //     }
    // });
};
