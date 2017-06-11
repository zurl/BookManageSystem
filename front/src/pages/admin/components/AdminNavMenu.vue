<template>
    <el-menu mode="vertical"
             default-active="1"
             class="nav-menu"
             theme="dark"
             style="padding-left: 0;">
        <el-menu-item-group title="图书管理">
            <router-link to="/admin/put-book">
                <el-menu-item index="1"><i class="el-icon-edit"></i>图书入库</el-menu-item>
            </router-link>
            <router-link to="/admin/manage-book">
                <el-menu-item index="2"><i class="el-icon-circle-check"></i>图书借还</el-menu-item>
            </router-link>
            <router-link to="/admin/query">
                <el-menu-item index="3"><i class="el-icon-search"></i>图书查询</el-menu-item>
            </router-link>
        </el-menu-item-group>
        <el-menu-item-group title="系统管理">
            <router-link to="/admin/manage-card">
                <el-menu-item index="4"><i class="el-icon-information"></i>借书证管理</el-menu-item>
            </router-link>
            <router-link to="/admin/manage-admin">
                <el-menu-item index="5"><i class="el-icon-setting"></i>管理员管理</el-menu-item>
            </router-link>
        </el-menu-item-group>
    </el-menu>
</template>
<script>

</script>
<style>
    .nav-menu{
        padding-top: 20px;
        padding-bottom: 360px;
        padding-left: 10px;

    }
    a:link {
        text-decoration: none;
    }

    a:visited {
        text-decoration: none;
    }

    a:hover {
        text-decoration: none;
    }

    a:active {
        text-decoration: none;
    }

    .router-link-active li {
        background-color: green;
    }
</style>
