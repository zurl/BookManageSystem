<template>
    <el-row>
        <el-col :span="6">
            <admin-nav-menu></admin-nav-menu>
        </el-col>
        <el-col :span="18">
            <router-view class="admin-page-body">
            </router-view>
        </el-col>
    </el-row>
</template>
<script>
    import AdminNavMenu from './AdminNavMenu.vue'
    export default {
        components : {
            'admin-nav-menu': AdminNavMenu,
        }
    }
</script>
<style>
    .content{
        min-height: 100%;
    }

    .admin-page-body{
        padding: 20px;
    }
</style>