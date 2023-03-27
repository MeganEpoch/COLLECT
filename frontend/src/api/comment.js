import axios from "axios";
import {COMMENT_MODULE} from "./_prefix";

/**
 * 获取某个报告的所有评论
 * @param {*} id
 * @returns
 */
export const getComments = payload => {
    const {id} = payload;
    return axios.get(`${COMMENT_MODULE}/${id}`)
        .then(res => res.data);
};

/**
 * 获取某个报告的综合评分
 * @param {*} id
 * @returns
 */
export const getScore = payload => {
    const {id} = payload;
    return axios.get(`${COMMENT_MODULE}/score/${id}`)
        .then(res => res.data);
};

/**
 * 返回是否成功发布评论的消息，若成功将该条comment写入数据库，若失败显示错误信息
 * @param {*} payload
 * @returns
 */
export const createComment = payload => {
    return axios.post(`${COMMENT_MODULE}/create`, payload).then(res => res.data);
};

