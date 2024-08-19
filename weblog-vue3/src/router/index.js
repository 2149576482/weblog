import Login from "@/pages/admin/login.vue";
import Index from "@/pages/frontend/index.vue";
import { createRouter, createWebHashHistory } from "vue-router";

// 统一声明所有路由
const routes = [
    {
        path: '/', // 路由地址
        component: Index, // 对应组件
        meta: { // meta信息
            title: 'Weblog 首页' // 页面标题
        }
    },
    {
        path: '/login', // 登录页
        component: Login,
        meta: {
            title: 'Weblog 登录页'
        }
    }
]

// 创建路由
const router = createRouter({
    // 指定路由的历史管理方式 hash模式指的是 URL 的路径 是通过hash符号(#)进行标识
    history: createWebHashHistory(),
    // routes reoutes的缩写
    routes,
})

// ES6 模块导出语句 用于将router对象导入 以便其他文件可以使用
export default router