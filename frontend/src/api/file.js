import axios from "axios";
import {FILE_MODULE} from "./_prefix";

export const FILE_BASE_PATH = "http://124.221.207.249:8081/api/file/download";  //新服务器地址
//export const FILE_BASE_PATH = "http://124.222.219.151:8081/api/file/download";
//export const FILE_BASE_PATH = "http://localhost:8081/api/file/download";

/**
 * 图片上传到阿里云存储oss，返回值为`url:****`
 * @param payload
 * @returns {Promise<AxiosResponse<any>>}
 */
export const oss = payload => {
    return axios.post(`${FILE_MODULE}/oss`, payload).then(res => {
        return res.data;
    });
};

/**
 * 文件仍存储在后端代码中
 * @param payload
 * @returns {Promise<AxiosResponse<any>>}
 */
export const uploadFile = payload => {
    return axios.post(`${FILE_MODULE}/upload`, payload).then(res => {
        return res.data;
    });
};

export const preDownload = payload => {
    const { taskId:taskId, FLAG } = payload;
    return axios.get(`${FILE_MODULE}/download/${taskId}/${FLAG}`).then(res => {
        return res.data;
    });
};
