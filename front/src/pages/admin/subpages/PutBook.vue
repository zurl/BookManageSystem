<template>
    <div style="text-align: center">
        <h1>图书录入</h1>
        <el-tabs v-model="activeName">
            <el-tab-pane label="增加新书" name="first">
                <el-form
                        class="query-form"
                        ref="form"
                        :model="form"
                        label-width="100px">
                    <el-form-item label="图书编号">
                        <el-input v-model="form.bookid"></el-input>
                    </el-form-item>
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
                    <el-form-item label="价格">
                        <el-input v-model="form.price"></el-input>
                    </el-form-item>
                    <el-form-item label="总数">
                        <el-input v-model="form.count"></el-input>
                    </el-form-item>
                    <el-form-item
                            label="年份"
                            :rules="[
                      { type: 'number', message: '年份必须为数字值'}
                    ]">
                        <el-input v-model.number="form.year"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="onNew">入库</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="增加已有书籍" name="second">
                <el-input placeholder="请输入图书编号" v-model="bookid">
                    <template slot="prepend">图书编号</template>
                </el-input>
                <el-input
                        placeholder="输入出入库数量"
                        v-model="count">
                    <template slot="prepend">出入库数量</template>
                    <el-button
                            @click="onChange"
                            slot="append"
                            icon="search"
                    >出入库</el-button>
                </el-input>
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
</template>
<script>
    import {Network} from "../../../network";
    export default {
        data(){
            return {
                activeName : 'first',
                bookid: null,
                count: 0,
                form :{
                    bookid: '',
                    name : '',
                    type : '',
                    year: '',
                    price: '',
                    author : '',
                    press : '',
                    count: ''
                },
                result: [],
                status: null,
                statusInfo: ''
            }
        },
        methods:{
            onNew(){
                const that = this;
                const queryString = Object
                        .keys(this.form)
                        .map(key=>`${key}=${this.form[key]}`)
                        .join('&');
                Network.put(`/admin/book?${queryString}`,{},(ret)=>{
                    that.status = 'success';
                    that.statusInfo = 'success';
                },(message)=>{
                    that.status = 'failed';
                    that.statusInfo = message;
                })
            },
            onChange(){
                const that = this;
                Network.post(`/admin/book?bookid=${this.bookid}&count=${this.count}`,{},(ret)=>{
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

</style>