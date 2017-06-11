<template>
    <div style="text-align: center">
        <h1>管理员管理</h1>
        <div>
            <el-tabs v-model="activeName" >
                <el-tab-pane label="增加管理员" name="first">
                    <el-form
                            class="query-form"
                            ref="form"
                            :model="form"
                            label-width="100px">
                        <el-form-item label="用户名">
                            <el-input v-model="form.username"></el-input>
                        </el-form-item>
                        <el-form-item label="姓名">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        </el-form-item>
                        <el-form-item label="联系方式">
                            <el-input v-model="form.contact"></el-input>
                        </el-form-item>
                        <el-form-item label="密码">
                            <el-input
                                    type="password"
                                    v-model="form.password"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onAdd">增加</el-button>
                        </el-form-item>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="管理员管理" name="second">
                    <el-table
                            v-loading.body="result == null && activeName == 'second'"
                            :data="result != null ? result : []"
                            border
                            :fit="true"
                            style="width: 100%"
                            :default-sort = "{prop: 'name', order: 'descending'}"
                    >
                        <el-table-column
                                prop="username"
                                label="用户名"
                                sortable>
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="姓名"
                                sortable
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="contact"
                                label="联系方式"
                                sortable
                                width="100">
                        </el-table-column>
                        <el-table-column label="操作">
                            <template scope="scope">
                                <el-button
                                        size="small"
                                        type="danger"
                                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>
            <el-alert
                    v-if="status == 'success'"
                    :title="statusInfo"
                    type="success">
            </el-alert>
            <el-alert
                    v-if="status == 'failed'"
                    :title="statusInfo"
                    type="error">
            </el-alert>
        </div>
    </div>
</template>
<script>
    import {Network} from "../../../network";
    export default {
        data(){
            return {
                form :{
                    username: '',
                    password: '',
                    name: '',
                    contact: ''
                },
                result : null,
                activeName: 'first',
                status: '',
                statusInfo: ''
            }
        },
        mounted(){
            this.loadAdmins();
        },
        methods: {
            loadAdmins(){
                const that = this;
                Network.get('/admin/admin',(result)=>{
                    that.result = result;
                });
            },
            onAdd(){
                const that = this;
                Network.put(`/admin/admin?username=${this.form.username}&password=${this.form.password}&name=${this.form.name}&contact=${this.form.contact}`,{},(ret)=>{
                    console.log(ret);
                    that.loadAdmins();
                    that.status = 'success';
                    that.statusInfo = 'success';
                },(message)=>{
                    that.status = 'failed';
                    that.statusInfo = message;
                })
            },
            handleDelete(ix, row){
                const that = this;
                Network.del(`/admin/admin?username=${row.username}`,(ret)=>{
                    that.loadAdmins();
                    that.status = 'success';
                    that.statusInfo = 'success';
                },(message)=>{
                    that.status = 'failed';

                    that.statusInfo = message;
                })
            }
        }
    }
</script>
<style>
    .query-form{
        margin: 0 auto;
        width: 460px;
    }
</style>