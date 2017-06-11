<template>
    <div>
        采集任务管理
        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column prop="priority" label="优先级" width="120">
            </el-table-column>
            <el-table-column prop="name" label="任务名称" width="120">
            </el-table-column>
            <el-table-column label="任务状态" width="120">
                <template scope="scope">
                    {{
                        scope.row.status == 0? "停止" :
                        scope.row.status == 1? "运行中" :
                        scope.row.status == 2? "队列中" :
                        scope.row.status == 3? "准备停止" :
                        scope.row.status == 4? "暂停" :"结束"
                    }}
                </template>
            </el-table-column>

            <el-table-column prop="total" label="已采集数量" width="120">
            </el-table-column>
            <el-table-column prop="templateId" label="采集模板" width="120">
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button size="small" @click="handleView(scope.$index, scope.row)">查看数据</el-button>
                    <el-button size="small" type="info" @click="handleOutput(scope.$index, scope.row)">导出数据
                    </el-button>
                    <el-button size="small" type="warning" @click="handleBegin(scope.$index, scope.row)">开始</el-button>
                    <el-button size="small" type="danger" @click="handleStop(scope.$index, scope.row)">停止</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-button @click="openCreateTemplate()">
            新建采集任务
        </el-button>

        <el-dialog title="新建任务" v-model="dialogCreateVisible" size="large">
            <span>新建任务 {{ dialog_status }}</span>
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
                    任务模板
                </el-col>
                <el-col :span="18">
                    <el-select v-model="new_template" placeholder="">
                        <el-option
                                v-for="item in templates"
                                :label="item.entryURL"
                                :value="item._links.self.href">
                        </el-option>
                    </el-select>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    线程数量
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_thread" placeholder="线程数量"></el-input>

                </el-col>
            </el-row>
            <el-row>
                <el-col :span="6">
                    优先级
                </el-col>
                <el-col :span="18">
                    <el-input v-model="new_priority" placeholder="优先级"></el-input>

                </el-col>
            </el-row>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogCreateVisible = false">关 闭</el-button>
                <el-button type="primary" @click="handleCreateTemplate()">保 存</el-button>
            </div>
        </el-dialog>

        <!-- -->
        <el-dialog title="查看数据" v-model="dialogDataVisible" size="large">
            <span>查看数据 {{ dialog_status }} </span>
            <el-table :data="resultData" stripe style="width: 100%">
                <el-table-column type="expand" label="内容">
                    <template scope="props">
                        <div>
                            {{ props.row.content }}
                        </div>
                    </template>
                </el-table-column>
                <el-table-column prop="url" label="URL" width="180">
                </el-table-column>
                <el-table-column prop="title" label="标题" width="180">
                </el-table-column>
                <el-table-column prop="createdDate" label="创建时间">
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button size="small" type="danger" @click="handleDeleteItem(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogDataVisible = false">关 闭</el-button>

            </div>
        </el-dialog>

    </div>
</template>
<script>
    import {Network} from "../network";
    export default {
        data() {
            const dataObject = {
                new_priority : '',
                new_name: '',
                new_template: '',
                new_thread: '',
                dialog_status : '',
                templates : [],
                tableData: [],
                resultData: [],
                dialogCreateVisible : false
            };
            const template = Network.get('/CrawlTemplate', (json)=>{
                dataObject.templates = json["_embedded"]["CrawlTemplate"];
            });
            const tasks = Network.get('/Task', (json)=>{
                dataObject.tableData = json["_embedded"]["Task"];
            });
            return dataObject;
        },
        methods: {
            refresh( callback ){
                Network.get('/Task', (json)=>{
                    this.tableData = json["_embedded"]["Task"];
                    callback();
                });
            },
            handleCreateTemplate(){
                this.dialog_status = '创建中';
                const t = this.new_template.split('/');
                Network.post('/Task', {
                    "name" : this.new_name,
                    "thread": this.new_thread,
                    "templateId" : t[t.length - 1],
                    "status" : 0,
                    "priority" : this.new_priority,
                    "total": 0
                }, (json) => {
                    if(json.hasOwnProperty("_links")){
                        console.log(json);
                        this.dialog_status = '创建成功';
                        this.refresh(()=>{
                            this.dialogCreateVisible = false;
                            this.dialog_status = '';
                        });
                    }else{
                        alert('illegal argument!!!\n' + error);
                    }

                }, ( error ) => {
                    alert('internal error' + error);
                });
            },
            openCreateTemplate(){
                this.new_priority = '';
                this.new_name = '';
                this.new_thread = '';
                this.new_template = '';
                this.dialogCreateVisible = true;
            },
            handleView(index, row) {
                Network.get(`/DataStorage/search/findByTaskId?taskId=${Network.getId(row)}`,
                    (json) =>{
                        this.resultData = json["_embedded"]["DataStorage"];
                        this.dialogDataVisible = true;
                    });
                console.log(index, row);
            },
            handleOutput(index, row) {
                console.log(index, row);
            },
            handleBegin(index, row) {
                if(row.status == 0 ){
                    row.status = 2;
                    Network.put(Network.getURL(row["_links"]["self"]["href"]),
                        row, (json) => {
                            this.refresh(()=>{
                            });
                        }, ( error ) => {
                            alert('internal error' + error);
                        });
                }else{
                    alert("错误的状态");
                }
            },
            handleStop(index, row) {
                if(row.status == 1 ){
                    row.status = 3;
                    Network.put(Network.getURL(row["_links"]["self"]["href"]),
                        row, (json) => {
                        this.refresh(()=>{
                        });
                    }, ( error ) => {
                        alert('internal error' + error);
                    });
                }else{
                    alert("不在运行");
                }
            },
            handleDelete(index, row) {
                if( row.status == 1){
                    alert('stop first!');
                    return;
                }
                Network.del(Network.getURL(this.tableData[index]["_links"]["self"]["href"]),
                    () => {
                        this.refresh(()=>{});
                    });
                console.log(index, row);
            },
            handleDeleteItem(index, row){

            }
        }
    }
</script>