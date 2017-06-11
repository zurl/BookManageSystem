<template>
    <div>
        元数据采集管理
        <hr />
        搜索引擎管理
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="name" label="名称" width="180">
            </el-table-column>
            <el-table-column prop="url" label="URL" width="180">
            </el-table-column>
            <el-table-column label="使用">
                <template scope="scope">
                    <el-switch
                            v-model="scope.row.on"
                            on-color="#13ce66"
                            off-color="#ff4949">
                    </el-switch>

                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog title="新建搜索引擎" v-model="dialogCreateVisible" size="large">
            <span>新建搜索引擎</span>
            <el-row>
                <el-col :span="6">
                    名称
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_name" placeholder="名称"></el-input>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    URL
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_url" placeholder="URL"></el-input>
                </el-col>
            </el-row>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogCreateVisible = false">关 闭</el-button>
                <el-button type="primary" @click="handleCreateTemplate()">保 存</el-button>
            </div>
        </el-dialog>
        <el-button @onclick="dialogCreateVisible = true">
            新建搜索引擎
        </el-button>

    </div>
</template>
<script>
    export default {
        data(){
            return {
                new_name : '',
                new_url : '',
                dialogCreateVisible : false,
                tableData : [
                    {
                        name : '百度',
                        url : 'baidu.com',
                        on : true,
                    },
                    {
                        name : '百度2',
                        url : 'baidu.com2',
                        on : false,
                    },
                    {
                        name : '百度3',
                        url : 'baidu.com3',
                        on : true,
                    }
                ]
            }
        },
        methods: {
            handleEdit(){

            },
            handleDelete(){

            },
            handleCreate(){

            }
        }
    }
</script>
<style>

</style>