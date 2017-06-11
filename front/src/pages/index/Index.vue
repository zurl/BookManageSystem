<template>
    <div id="index-container">

        <h1 id="title">
            启真图管
        </h1>
        <p class="sub-title">
            现代化的图书管理系统
        </p>
        <p>
            <el-button size="large" class="query-button" type="primary" >
                <router-link class="link-button" to="/query">图书查询</router-link></el-button>
            <el-button size="large" class="query-button" type="primary" @click="dialogVisible = true">管理登录</el-button>
        </p>
        <el-dialog title="管理员登录" v-model="dialogVisible" size="tiny">
                <el-form ref="form" :model="form">
                    <el-form-item>
                        <el-input
                                size="large"
                                placeholder="用户名"
                                v-model="form.username"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-input
                                size="large"
                                placeholder="密码"
                                v-model="form.password" type="password"></el-input>
                    </el-form-item>
                </el-form>
                <el-alert
                        v-if="status == 'failed'"
                        :title="statusInfo"
                        type="error">
                </el-alert>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button
                                type="primary"
                                @click="onLogin">
                            登录
                        </el-button>

              </span>
        </el-dialog>
    </div>
</template>
<script>
    import {Network} from "../../network";
    export default {
        data(){
            return {
                form:{
                    username: '',
                    password: ''
                },
                dialogVisible: false,
                status: null,
                statusInfo: ''
            }
        },
        methods:{
            onLogin(){
                const that = this;
                Network.post(`/login?username=${this.form.username}&password=${this.form.password}`,{},()=>{
                    window.location.href += "admin";
                },()=>{
                    that.status = "failed";
                    that.statusInfo = "Login failed";
                });
            }
        }
    }
</script>
<style>
    .login-form{
        color: white !important;
        border-color: white !important;
    }
    .link-button{
        color: white !important;
    }
    .link-button:hover{
        color: #2C98E3 !important;
    }

    .query-button{
        background-color: inherit !important;
        border-color: white !important;
        margin : 30px;
    }
    .query-button:hover{
        color: #2C98E3 !important;
        background-color: aliceblue !important;
    }
    #title   {
        padding: 20px;
        margin: 0 auto;
        text-align: center;
        color: white;
        font-size: 56px;
    }
    .sub-title{
        font-size: 20px;
        color: rgba(255, 255, 255, 0.7);
    }
    #index-container{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100%;
        background: #2C98E3 url(../../assets/splash-background.svg);
    }
</style>