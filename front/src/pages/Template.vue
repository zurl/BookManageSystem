<template>
    <div>
        采集模板管理
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="name" label="名称" width="180">
            </el-table-column>
            <el-table-column prop="entryURL" label="入口" width="180">
            </el-table-column>
            <el-table-column prop="createdDate" label="创建时间">
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="small" @click="handleDetail(scope.$index, scope.row)">详情</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button @click="openCreateTemplate()">
            新建模板
        </el-button>
        <!-- create new template -->
        <el-dialog title="新建模板" v-model="dialogCreateVisible" size="large">
            <span>新建模板 {{ dialog_status }}</span>
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
                    入口URL
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_entry_url" placeholder="入口URL"></el-input>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    URL匹配正则表达式
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_entry_regex" placeholder="URL匹配正则表达式"></el-input>

                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    标题XPath
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_title_xpath" placeholder="标题XPath"></el-input>

                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    内容XPath
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_content_xpath" placeholder="内容XPath"></el-input>
                </el-col>
            </el-row>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogCreateVisible = false">关 闭</el-button>
                <el-button type="primary" @click="handleCreateTemplate()">保 存</el-button>
            </div>
        </el-dialog>
        <!-- create new template -->
        <el-dialog title="详细信息" v-model="dialogDetailVisible" size="large">
            <span>这是一个详细信息 </span>
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
                    入口URL
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_entry_url" placeholder="入口URL"></el-input>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    URL匹配正则表达式
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_entry_regex" placeholder="URL匹配正则表达式"></el-input>

                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    标题XPath
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_title_xpath" placeholder="标题XPath"></el-input>

                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    内容XPath
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_content_xpath" placeholder="内容XPath"></el-input>
                </el-col>
            </el-row>

            <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogDetailVisible = false">关 闭</el-button>
                    <el-button type="primary" @click="handleSaveTemplate()">保 存</el-button>
                  </div>
        </el-dialog>
    </div>
</template>
<script>
    import {Network} from "../network";
    export default {
        data() {
            const dataObject = {
                dialogCreateVisible: false,
                dialogDetailVisible: false,
                new_name: '',
                new_entry_url: '',
                new_entry_regex: '',
                new_title_xpath: '',
                new_content_xpath: '',
                dialog_status : '',
                onDialogId : 0,
                tableData: []
            };
            Network.get('/CrawlTemplate', (json)=>{
                dataObject.tableData = json["_embedded"]["CrawlTemplate"];
            });
            return dataObject;
        },
        methods: {
            refresh( callback ){
                Network.get('/CrawlTemplate', (json)=>{
                    this.tableData = json["_embedded"]["CrawlTemplate"];
                    callback();
                });
            },
            openCreateTemplate(){
                this.new_name = '';
                this.new_entry_url = '';
                this.new_entry_regex = '';
                this.new_title_xpath = '';
                this.new_content_xpath = '';
                this.dialogCreateVisible = true;
            },
            handleCreateTemplate(){
                this.dialog_status = '创建中';
                Network.post('/CrawlTemplate', {
                    "name" : this.new_name,
                    "entryURL" : this.new_entry_url,
                    "urlpattern" : this.new_entry_regex,
                    "titleXPath" : this.new_title_xpath,
                    "contentXPath" : this.new_content_xpath
                }, (json) => {
                    this.dialog_status = '创建成功';
                    this.refresh(()=>{
                        this.dialogCreateVisible = false;
                        this.dialog_status = '';
                    });
                }, ( error ) => {
                    alert('internal error' + error);
                });
            },
            handleSaveTemplate(){
                this.dialog_status = '保存中';
                Network.put(Network.getURL(this.tableData[this.onDialogId]["_links"]["self"]["href"]), {
                    "name" : this.new_name,
                    "entryURL" : this.new_entry_url,
                    "urlpattern" : this.new_entry_regex,
                    "titleXPath" : this.new_title_xpath,
                    "contentXPath" : this.new_content_xpath
                }, (json) => {
                    this.dialog_status = '保存成功';
                    this.refresh(()=>{
                        this.dialogDetailVisible = false;
                        this.dialog_status = '';
                    });
                }, ( error ) => {
                    alert('internal error' + error);
                });
                this.dialogCreateVisible = false
            },
            handleDetail(index, row) {
                this.new_name = this.tableData[index].name;
                this.new_entry_url = this.tableData[index].entryURL;
                this.new_entry_regex = this.tableData[index].urlpattern;
                this.new_title_xpath = this.tableData[index].titleXPath;
                this.new_content_xpath = this.tableData[index].contentXPath;
                this.dialogDetailVisible = true;
                this.onDialogId = index;
                console.log(index, row);
            },
            handleDelete(index, row) {
                Network.del(Network.getURL(this.tableData[index]["_links"]["self"]["href"]),
                    () => {
                        this.refresh(()=>{});
                    });
                console.log(index, row);
            }
        }
    }
</script>