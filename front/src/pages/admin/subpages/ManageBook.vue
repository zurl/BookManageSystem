<template>
    <div style="text-align: center">
        <h1> 图书租借管理 </h1>
        <el-input placeholder="请输入借书证编号" v-model="cardid">
            <template slot="prepend">借书证编号</template>
            <el-button
                    @click="onQuery"
                    slot="append"
                    icon="search"
            >查询</el-button>
        </el-input>
        <el-input placeholder="请输入书号" v-model="bookid">
            <template slot="prepend">书号</template>
            <el-button
                       @click="onBorrow"
                    slot="append"
                    icon="search"
            >租借</el-button>
        </el-input>
        <el-table
                :data="result"
                border
                :fit="true"
                style="width: 100%"
                :default-sort = "{prop: 'name', order: 'descending'}"
        >
            <el-table-column
                    prop="bookid"
                    label="书号"
                    sortable>
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="书名"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="类型"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="press"
                    label="出版社"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="year"
                    label="年份"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="作者"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="价格"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="count"
                    label="库存量"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="current"
                    label="余量"
                    sortable
                    width="100">
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button
                            size="small"
                            type="danger"
                            @click="onReturn(scope.$index, scope.row)">归还</el-button>
                </template>
            </el-table-column>
        </el-table>
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
</template>
<script>
    import {Network} from "../../../network";
    export default {
        data(){
            return {
                cardid : '',
                bookid: '',
                data:[],
                result: null,
                status: null,
                statusInfo: ''
            }
        },
        methods: {
            onQuery(){
                const that = this;
                Network.get(`/admin/record?cardid=${this.cardid}`,(ret)=>{
                    that.result = ret;
                },(message)=>{
                    that.status = 'failed';
                    that.statusInfo = message;
                })
            },
            onBorrow(){
                const that = this;
                Network.put(`/admin/record?cardid=${this.cardid}&bookid=${this.bookid}`,{},(ret)=>{
                    that.status = 'success';
                    that.statusInfo = 'success';
                    that.onQuery();
                },(message)=>{
                    that.status = 'failed';
                    that.statusInfo = message;
                })
            },
            onReturn(ix, row){
                const that = this;
                Network.del(`/admin/record?cardid=${this.cardid}&bookid=${row.bookid}`,(ret)=>{
                    that.status = 'success';
                    that.statusInfo = 'success';
                    that.onQuery();
                },(message)=>{
                    that.status = 'failed';
                    that.statusInfo = message;
                })
            }
        }
    }
</script>
<style>

</style>