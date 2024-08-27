import '@/assets/main.css'
import 'animate.css'
// 导入nprogress
import 'nprogress/nprogress.css'

import { createApp } from 'vue'

// 引入全局状态管理 pinia
import pinia from '@/stores'
import App from '@/App.vue'

// 导入路由
import router from '@/router'

import '@/permission'
// 导入element plus 图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)

// 应用pinia
app.use(pinia)
// 应用路由
app.use(router)
app.mount('#app')

// 引入图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}