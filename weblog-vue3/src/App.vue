<template>
    <el-config-provider :locale="locale">
        <router-view></router-view>
    </el-config-provider>
</template>

<script setup>
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
const locale = zhCn
</script>

<style>
/* 自定义顶部加载 Loading 颜色 */
#nprogress .bar {
    background: #409eff!important;
}
</style>