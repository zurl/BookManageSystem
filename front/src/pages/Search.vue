<template>
    <div style="display:inline">
        <!-- input area -->
        <el-row>
            <el-col :span="2">
                关键字
            </el-col>
            <el-col :span="6">
                <el-input
                        size="middle"
                        placeholder="请输入内容"
                        v-model="input6">
                </el-input>
            </el-col>
            <el-col :span="2">
                域名
            </el-col>
            <el-col :span="6">
                <el-input
                        size="middle"
                        placeholder="请输入内容"
                        v-model="input6">
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-button> 搜索</el-button>
            </el-col>

        </el-row>
        <!-- input area -->
        <hr/>
        搜索引擎设置

        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="title" label="设置" width="180">
            </el-table-column>
            <el-table-column prop="url" label="网址" width="180">
            </el-table-column>
            <el-table-column prop="time" label="时间" width="180">
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="small" @click="handleView(scope.$index, scope.row)">查看详情</el-button>
                    <el-button size="small" type="danger" @click="handleGo(scope.$index, scope.row)">转到</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination
                    @current-change="handlePageChange"
                    layout="prev, pager, next"
                    :total="50">
            </el-pagination>
        </div>

        <!--  view diaglog -->
        <el-dialog title="提示" v-model="dialogVisible" size="tiny">
            <span>这是一段信息 {{ dialogId}}</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
              </span>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        data(){
            return {
                tableData: [{
                    count: 124,
                    priority: 0,
                    url: 'www.baidu.com',
                    time: '2016-10-10 10:12:23',
                    title: 'asd'
                }],
                tableDataStore: [{
                    count: 124,
                    priority: 0,
                    url: 'www.baidu.com',
                    time: '2016-10-10 10:12:23',
                    title: 'asd'
                }, {
                    count: 0,
                    url: '192.168.0.4',
                    template: 'a',
                    priority: 0,
                    time: '2016-10-10 10:12:43',
                    title: 'asdddd'
                }, {
                    count: 0,
                    priority: 0,
                    url: '192.168.0.3',
                    time: '2016-10-10 10:12:13',
                    template: 'a',
                    title: 'asdd'
                }, {
                    count: 0,
                    url: '192.168.0.2',
                    time: '2016-10-10 10:12:33',
                    template: 'a',
                    priority: 100,
                    title: 'aaa'
                }],
                dialogVisible: false,
                dialogId: 0,
            }
        },
        methods: {
            handlePageChange(page){
                this.tableData =
                    this.tableDataStore.filter((x, ix) => ix >= page * 2 && ix <= page * 2 + 1)
            },
            handleView(index, row){
                this.dialogId = index;
                this.dialogVisible = true;
            },
            handleDelete(){

            },
            handleGo(){

            },
        }

    }
</script>
<style>
    .el-input {
        display: inline;
    }
</style>