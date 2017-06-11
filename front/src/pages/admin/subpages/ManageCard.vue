<template>
    <div style="text-align: center">
        <h1>借书证管理</h1>
        <div>
            <el-tabs v-model="activeName" @tab-click="handleClick">
                <el-tab-pane label="增加借书证" name="first">
                    <el-form
                            class="query-form"
                            ref="form"
                            :model="form"
                            label-width="100px">
                        <el-form-item label="姓名">
                            <el-input v-model="form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="单位">
                            <el-input v-model="form.unit"></el-input>
                        </el-form-item>
                        <el-form-item label="类别">
                            <el-input v-model="form.type"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="onAdd">增加</el-button>
                        </el-form-item>
                    </el-form>
                </el-tab-pane>
                <el-tab-pane label="借书证管理" name="second">
                    <el-table
                            v-loading.body="result == null && activeName == 'second'"
                            :data="result != null ? result : []"
                            border
                            :fit="true"
                            style="width: 100%"
                            :default-sort = "{prop: 'name', order: 'descending'}"
                    >
                        <el-table-column
                                prop="cardid"
                                label="借书证编号"
                                sortable>
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="姓名"
                                sortable
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="unit"
                                label="单位"
                                sortable
                                width="100">
                        </el-table-column>
                        <el-table-column
                                prop="type"
                                label="类别"
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
                    name: '',
                    unit: '',
                    type: ''
                },
                result : null,
                activeName: 'first',
                status: null,
                statusInfo: ''
            }
        },
        mounted(){
            this.loadCards();
        },
        methods: {
            loadCards(){
                const that = this;
                Network.get('/admin/card',(result)=>{
                    that.result = result;
                },(message)=>{
                    that.status = 'failed';
                    that.statusInfo = message;
                });
            },
            onAdd(){
                const that = this;
                Network.put(`/admin/card?name=${this.form.name}&type=${this.form.type}&unit=${this.form.unit}`,{},(ret)=>{
                    that.loadCards();
                    that.status = 'success';
                    that.statusInfo = `success!: cardid:${ret["code"]}`;
                },(message)=>{
                    that.status = 'failed';
                    that.statusInfo = message;
                })
            },
            handleClick(){
                this.status = '';
            },
            handleDelete(ix, row){
                const that = this;
                Network.del(`/admin/card?cardid=${row.cardid}`,()=>{
                    that.loadCards();
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