<template>
    <div>
        代理IP管理
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="ip" label="IP地址" width="120">
            </el-table-column>
            <el-table-column prop="status" label="状态" width="120">
            </el-table-column>
            <el-table-column prop="count" label="使用次数" width="120">
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="small" type="info" @click="handleUse(scope.$index, scope.row)">使用</el-button>
                    <el-button size="small" type="danger" @click="handleStop(scope.$index, scope.row)">禁用</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-button>
            添加代理IP
        </el-button>
        <el-button type="info">
            导入代理IP
        </el-button>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                tableData: [{
                    count: 124,
                    priority: 0,
                    ip : '192.168.0.1',
                    template: 'a',
                    status: '使用中'
                }, {
                    count: 0,
                    ip : '192.168.0.4',
                    template: 'a',
                    priority: 0,
                    status: '使用中'
                }, {
                    count: 0,
                    priority: 0,
                    ip : '192.168.0.3',
                    template: 'a',
                    status: '禁用'
                }, {
                    count: 0,
                    ip : '192.168.0.2',
                    template: 'a',
                    priority: 100,
                    status: '空闲'
                }]
            }
        },
        methods: {
            handleUse(index, row) {
                console.log(index, row);
            },
            handleStop(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            }
        }
    }
</script>