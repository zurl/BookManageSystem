<template>
    <div style="text-align: center">
        <h1>图书查询</h1>
        <el-form
                v-if="showForm"
                class="query-form"
                ref="form"
                :model="form"
                label-width="100px">
            <el-form-item label="图书名称">
                <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="图书类型">
                <el-input v-model="form.type"></el-input>
            </el-form-item>
            <el-form-item label="出版社">
                <el-input v-model="form.press"></el-input>
            </el-form-item>
            <el-form-item label="作者">
                <el-input v-model="form.author"></el-input>
            </el-form-item>
            <el-form-item label="价格最小值">
                <el-input v-model="form.priceLeft"></el-input>
            </el-form-item>
            <el-form-item label="价格最大值">
                <el-input v-model="form.priceRight"></el-input>
            </el-form-item>
            <el-form-item
                    label="年份最小值"
                    :rules="[
                      { type: 'number', message: '年份必须为数字值'}
                    ]">
                <el-input v-model.number="form.yearLeft"></el-input>
            </el-form-item>
            <el-form-item
                    label="年份最大值"
                    :rules="[
                      { type: 'number', message: '年份必须为数字值'}
                    ]">
                <el-input v-model.number="form.yearRight"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onQuery">查询</el-button>
            </el-form-item>
        </el-form>
        <div v-else>
            <div>
                <el-button type="primary" @click="onReturn">返回</el-button>
            </div>
            <el-table
                    v-loading.body="result == null"
                    :data="result != null ? result : []"
                    border
                    fit="true"
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
                        prop="type"
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
            </el-table>

        </div>
    </div>
</template>
<script>
    import {Network} from "../../network";
    export default {
        data(){
            return {
                showForm: true,
                form :{
                    type : '',
                    name : '',
                    yearLeft: '',
                    yearRight: '',
                    priceLeft: '',
                    priceRight: '',
                    author : '',
                    press : ''
                },
                result: null
            }
        },
        methods :{
            onQuery(){
                const queryString =
                    Object.keys(this.form)
                        .filter(x=>this.form[x])
                        .map(x=>`${x}=${this.form[x]}`)
                        .join('&');
                this.result = null;
                this.showForm = false;
                Network.get('/query?'+queryString,(result)=>{
                    this.result = result;
                });
            },
            onReturn(){
                this.result = null;
                this.showForm = true;
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