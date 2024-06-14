<template>
    <div class="container">
        <h1>日志查询</h1>
        <div class="content">

            <VaTabs v-model="value" style="margin-bottom: 10px;">
                <template #tabs>
                    <VaTab icon="book" label="筛选与查询" @click="select"/>
                </template>
            </VaTabs>
            <div class="tool-detail" v-if="action==3">
                <div class="select-item">
                    <div class="add-item-name">该功能尚未实现 </div>
                </div>
            </div>
            <VaCard style="margin-bottom: 10px; margin-top: 10px;">
                <VaCardContent>
                    <div class="va-table-responsive">
                        <table class="va-table va-table--hoverable va-table--clickable">
                        <thead>
                            <tr>
                            <th>时间</th>
                            <th>操作者ID</th>
                            <th>操作内容</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                            v-for="log in logs"
                            :key="log.uid"
                            >
                            <td>{{ log.time }}</td>
                            <td>{{ log.uid }}</td>
                            <td style="text-align: left;">{{ log.content }}</td>
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
        name: 'AdminCourseListPage',
        data(){
            return {
                action: 0,
                logs: [],
            };
        },
        methods:{
            getLogsDefault(){
                const body = {uid: this.uid, data: "default"};
                console.log(body);
                axios.post("/sys_admin/log", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.logs = response.data.logs;
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
                console.log('push to /admin/classroom/detail...');
                this.$router.push("/admin/classroom/detail");
            },
            add(){
                if(this.action != 1){
                    this.action = 1;
                }else{
                    this.action = 0;
                }
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
            this.getLogsDefault();
        }
    };
</script>

<style scoped lang="scss">
	table {
		width: 100%;
	}
	
	th, td{
		text-align: center;
		padding: 5px;
	}
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