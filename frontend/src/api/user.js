import axios from "axios";
import { USER_MODULE } from "./_prefix";

/**
 * 用户注册 POST /user/register
 * @param {*} payload
 * @returns
 */
export const register = payload => {
    console.log(payload);
    const { uname, phone, password, userRole = "EMPLOYEE" } = payload;
    return axios
        .post(`${USER_MODULE}/register`, {
            uname,
            phone,
            password,
            userRole
        })
        .then(res => {
            return res.data;
        });

    /** 前后端分离开发时 前端可以自己写简易的 mock  */
    // return Promise.resolve({
    //   "code": 1,
    //   "msg": "账号注册成功！",
    //   "data": {
    //     "id": 3,
    //     "uname": "小张同学",
    //     "phone": "102123456789",
    //     "password": null,
    //     "picture": "1.png",
    //     "balance": 0,
    //     "userRole": "STUDENT",
    //     "createTime": "2021-03-13T01:45:48.246+00:00"
    //   }
    // });
};

/**
 *  用户登录 POST /user/login
 * @param {*} payload
 * @returns
 */
export const login = payload => {
    console.log(payload);
    const { phone, password } = payload;
    return axios.post(`${USER_MODULE}/login`, { phone, password }).then(res => {
        console.log(res.data);
        return res.data;
    });
    //    return Promise.resolve({
    //        "code": 1,
    //        "msg": "账号登录成功！",
    //        "data": {
    /*            "id": 3,
                "uname": "小张同学",
                "phone": "102123456789",
                "password": null,
    //         "picture": "1.png",
    //         "balance": 0,
                "userRole": "ADMIN",
                "createTime": "2021-03-13T01:45:48.246+00:00"
            }
        });*/
};

/**
 *  获取用户 GET /user/{uid}
 * @param {*} uid
 * @returns
 */
export const getUser = uid => {
    console.log(uid);
    return axios.get(`${USER_MODULE}/${uid}`).then(res => {
        return res.data;
    });
};

// /** getUserLabel
//  *  获取用户基础信息设置 GET /userLabel/{uid}
//  * @param {*} uid
//  * @returns
//  */
// export const getUserLabel = uid => {
//     console.log(uid);
//     return axios.get(`${USER_MODULE}/userLabel/${uid}`).then(res => {
//         return res.data;
//     });
//     // return Promise.resolve({
//     //     ability: "小学",
//     //     equipment: "Android设备",
//     //     activity: "一周五天",
//     //     type: "功能测试",
//     // });
// };

/** saveUserLabel
 *  保存用户基础信息设置 POST /prefer
 * @returns
 */
export const choosePreference = payload => {
    return axios.post(`${USER_MODULE}/prefer`,payload)
        .then(res => res.data)
}

// /** getUserSystemLabel
//  *  获取用户系统偏好 GET /userSystemLabel/{uid}
//  * @param {*} uid
//  * @returns
//  */
// export const getUserSystemLabel = uid => {
//     console.log(uid);
//     return axios.get(`${USER_MODULE}/userSystemLabel/${uid}`).then(res => {
//         return res.data;
//     });
//     // return Promise.resolve({
//     //     preference: "STUDENT",
//     //     recommendation: "2021-03-13T01:45:48.246+00:00"
//     // });
// };
