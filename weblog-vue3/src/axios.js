import axios from "axios";
import { getToken, removeToken } from "./composables/cookie";
import { showMessage } from "./composables/util";
import { useUserStore } from "./stores/user";

// 创建axios实例
const instance = axios.create({
    baseURL: "/api",
    timeout: 7000 //请求超时时间
})

// 添加请求拦截器
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    const token = getToken()
    // console.log("统一添加请求头中的Token: " + token)

    // 当token不为空时
    if (token) {
        // 添加请求头 key 为 Authorization value值为 Bearer + token
        config.headers['Authorization'] = 'Bearer ' + token
    }
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
})

//添加响应拦截器
instance.interceptors.response.use(function (response) {
    // 对响应数据做点什么
    return response.data;
}, function (error) {
    // 对响应错误做点什么
    let status = error.response.status

    // 状态码 401
    if (status == 401) {
        // 退出登录
        let userStore = useUserStore()
        userStore.logout()
        // 刷新页面
        location.reload()
    }

    // 若后台服务器挂了 默认请求失败
    let errorMsg = error.response.data.message || '请求失败'

    // 弹出错误提示
    showMessage(errorMsg, 'error')

    return Promise.reject(error)
})

export default instance;