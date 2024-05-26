<template>
    <div class="container">
        <h1>用户管理</h1>
        <div class="content">

            <VaTabs v-model="value" style="margin-bottom: 10px;">
                <template #tabs>
                    <VaTab icon="va-plus" label="添加用户" @click="add"/>
                    <VaTab icon="va-minus" label="删除所有" @click="deleteAll"/>
                    <VaTab icon="book" label="筛选与查询" @click="select"/>
                </template>
            </VaTabs>
            <div class="tool-detail" v-if="action==1">
                <div style="display: flex;">
                    <div class="left">
                        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-4 items-end">
                            <div class="add-item">
                                <div class="add-item-name">姓名 </div>
                                <input v-model="new_user.name">
                            </div>
                            <div class="add-item">
                                <div class="add-item-name">身份 </div>
                                <VaRadio v-model="new_user.status" :options="['学生', '教师']" /> 
                            </div>
                            <div class="add-item">
                                <div class="add-item-name">邮箱 </div>
                                <input v-model="new_user.email">
                            </div>
                            <div class="add-item">
                                <div class="add-item-name">电话 </div>
                                <input v-model="new_user.phone">
                            </div>
                            <div class="add-item">
                                <div class="add-item-name">住址 </div>
                                <input v-model="new_user.address">
                            </div>  
                            <div class="add-item">
                                <VaButton @click="comfirmAdd">确认</VaButton>
                            </div>                                                  
                        </div>
                    </div>
                    <div class="right">
                        <VaFileUpload v-model="new_user.photo" dropzone file-types="jpg,png" 
                        dropZoneText=""
                        uploadButtonText="上传头像"
                        style="width: 90%; height: 100%; align-self: center;"/>
                    </div>                
                </div>
            </div>
            <div class="tool-detail" v-if="action==2">
                <div class="delete-item">
                    <div class="add-item-name">是否确定删除该列表中的所有用户？ </div>
                </div>

                <div class="delete-item">
                    <VaButton @click="comfirmAdd" style="width: 60px;">确认</VaButton>
                </div>
                
            </div>
            <div class="tool-detail" v-if="action==3">
                <div class="select-item">
                    <div class="add-item-name">按姓名查询 </div>
                    <input v-model="condition.name">
                </div>
                <div class="select-item">
                    <div class="add-item-name">按性别筛选 </div>
                    <VaRadio v-model="condition.gender" :options="['男', '女','其它']" /> 
                </div>
                <div class="delete-item">
                    <VaButton @click="comfirmAdd" style="width: 120px;">确认查询</VaButton>
                </div>
            </div>
            <VaCard style="margin-bottom: 10px; margin-top: 10px;">
                <VaCardContent>
                    <div class="va-table-responsive">
                        <table class="va-table va-table--hoverable va-table--clickable">
                        <thead>
                            <tr>
                            <th>姓名</th>
                            <th>ID</th>
                            <th>身份</th>
                            <th>性别</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                            v-for="user in users"
                            :key="user.id"
                            @click="showDetail()"
                            >
                            <td>{{ user.name }}</td>
                            <td>{{ user.id }}</td>
                            <td>{{ user.status }}</td>
                            <td>{{ user.gender }}</td>
                            </tr>
                        </tbody>
                        </table>
                    </div>  
                </VaCardContent>
            </VaCard>
         
        </div>

    </div>
</template>

<script>
    import axios from '@/axios';
    export default {
        name: 'AdminUserListPage',
        props:{
            value_uid: {
                type: Number,
                required: true
            },
        },
        data(){
            return {
                uid: this.value_uid,
                action: 0,
                users: [],
                new_user:{
                    name: '',
                    status:'',
                    email:'',
                    phone:'',
                    address:'',
                    photo:[],
                },
                condition:{
                    name:'请输入姓名',
                    gender:'',
                }
            };
        },
        watch:{
            value_uid(x){
                this.uid = x;
            },            
        },
        methods:{
            getUsersDefault(){
                const body = {uid: this.uid, data: "default"};
                console.log(body);
                axios.post("/admin/user_management/", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.users = response.data.data;
                        }else if(response.data.code == "-1"){
                            console.log(response.data.message);
                        }else{
                            console.log("unhandled code, ",response.data.code);
                        }
                    })
					.catch(error => {
						if (error.response) {
							// 根据错误响应状态码进行处理
								console.log(error.response.data);
								console.log(error.response.status);
								console.log(error.response.headers);
							} else if (error.request) {
							// 处理没有收到响应的情况
								console.log(error.request);
							} else {
							// 处理其他错误情况
								console.log('Error', error.message);
							}
							console.log(error.config);
					});   
            },
            showDetail(){
                console.log('push to /admin/user/detail...');
                this.$router.push("/admin/user/detail");
            },
            add(){
                if(this.action != 1){
                    this.action = 1;
                }else{
                    this.action = 0;
                }
            },
            comfirmAdd(){
                const body = {id: this.uid, user: this.new_user};
                console.log(body);
                axios.post("/admin/user_management/add", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.getUsersDefault();
                            this.new_user = {
                                name: '',
                                status:'',
                                email:'',
                                phone:'',
                                address:'',
                                photo:[],
                            };
                        }else if(response.data.code == "-1"){
                            console.log(response.data.message);
                        }else{
                            console.log("unhandled code, ",response.data.code);
                        }
                    })
					.catch(error => {
						if (error.response) {
							// 根据错误响应状态码进行处理
								console.log(error.response.data);
								console.log(error.response.status);
								console.log(error.response.headers);
							} else if (error.request) {
							// 处理没有收到响应的情况
								console.log(error.request);
							} else {
							// 处理其他错误情况
								console.log('Error', error.message);
							}
							console.log(error.config);
					});                 
            },
            uploadPhoto(){

            },
            deleteAll(){
                if(this.action != 2){
                    this.action = 2;
                }else{
                    this.action = 0;
                }                
            },
            select(){
                if(this.action != 3){
                    this.action = 3;
                }else{
                    this.action = 0;
                }
            }
        },
        mounted(){
            this.uid = this.value_uid;
            this.getUsersDefault();
        }
    };
</script>

<style scoped lang="scss">
    .container {
        display: flex;
        flex-direction: column;
        background-color: rgb(255, 255, 255);
        height: 100vh; /* 使容器高度等于视口高度 */
    }

    h1 {
        padding: 60px;
        font-size: 24px;
    }

    .content {
        padding-left: 60px;
        padding-right: 60px;
        color: rgb(72, 72, 72);
        font-size: 16px;
    }

    .tool-detail{
        padding-top: 10px;
        padding-bottom: 10px;
        display: block;
    }

    .left{
        display: grid;
        
        width: 80%;
    }

    .right{
        display: flex;
        flex-direction: column;
        width: 20%;
        margin: 40px;
    }

    .photo {
        width: 96px;
        height: 144px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    .add-item{
        margin: 20px;
        display: flex;
        flex-direction: row;
    }

    .add-item-name{
        margin-right: 10px;
    }

    .delete-item{
        margin: 20px;
        display: flex;
        flex-direction: column;        
    }

    .select-item{
        margin: 20px;
        display: flex;
        flex-direction: row;        
    }

    .tool :hover{
        cursor: pointer;
        background-color:aliceblue;
    }

</style>