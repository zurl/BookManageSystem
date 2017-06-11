<template>
    <div >
        全局设置
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="name" label="设置" width="180">
            </el-table-column>
            <el-table-column prop="note" label="说明" width="180">
            </el-table-column>
            <el-table-column prop="value" label="值" width="180">
            </el-table-column>
        </el-table>
        <el-button>
            保存
        </el-button>
        <el-button type="danger">
            放弃修改
        </el-button>
    </div>

</template>
<script>
    export default {
        data() {
            return {
                tableData: [{
                    name: 'max-thread',
                    note: '最大线程数',
                    value : 24
                }, {
                    name: 'max-task',
                    note: '最大任务数',
                    value : 12
                }
                ]
            }
        }
    }
</script>
<style>

</style>