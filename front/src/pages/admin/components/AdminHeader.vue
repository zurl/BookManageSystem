<template>
    <el-row class="page-header">
        <el-col :span="12">
            <div class="grid-content">
                图书管理系统
            </div>
        </el-col>
        <el-col :span="12">
            <div class="header-right">
                <el-dropdown>
                      <span style="color: white; font-size: 18px">
                        楼学庆<i class="el-icon-caret-bottom el-icon--right"></i>
                      </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>修改密码</el-dropdown-item>
                        <el-dropdown-item divided>退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-col>
    </el-row>
</template>
<script>
    export default {

    }
</script>
<style>
    .header-right{
        text-align: right;
        vertical-align: middle;
        line-height: 26px;
        padding-right: 20%;
    }
    .page-header {
        background-color: #1D8CE0;
        color: #FFFFFF;
        padding: 30px 20px 30px 50px;
        font-size: 20px;
    }
</style>