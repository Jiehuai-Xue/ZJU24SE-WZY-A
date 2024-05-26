<template>
    <div class="container">
        <h1>课程管理 > {{ course.name }}</h1>
        <div class="content">
            <VaCard style="margin-bottom: 10px; margin-top: 10px;">
                <VaCardContent style="display: flex; flex-direction: row;">
                    <div style="width: 92%;">
                        <div class="detail-info">
                            <div class="attribute">
                                <div class="attribute-name">课程名称</div>
                                <div class="attribute-content" v-if="edit==false">{{ course.name }}</div>
                                <input v-model="course.name" class="attribute-content" v-if="edit">
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">课程ID</div>
                                <div class="attribute-content">{{ course.id }}</div>
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">学分</div>
                                <div class="attribute-content" v-if="edit==false">{{ course.credit }}</div>
                                <input v-model="course.credit" class="attribute-content" v-if="edit">
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">教师</div>
                                <div class="attribute-content" v-if="edit==false">{{ course.teacherName }}(ID:{{ course.teacherId }})</div>
                                <input v-model="course.teacherId" class="attribute-content" v-if="edit">
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">教室</div>
                                <div class="attribute-content">{{ course.campusName }}</div>
                                <div class="attribute-content" v-if="edit==false">{{ course.classroomName }}(ID:{{ course.classroomId }})</div>
                                <input v-model="course.classroomId" class="attribute-content" v-if="edit">
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">时间</div>
                                <div class="attribute-content" v-if="edit==false">{{ course.time }}</div>
                                <input v-model="course.time" class="attribute-content" v-if="edit">
                            </div> 
                            <div class="attribute">
                                <div class="attribute-name">选课人数/课程容量</div>
                                <div class="attribute-content">{{ course.size }} / {{ course.capacity }}</div>
                            </div> 
                            <div class="attribute-long">
                                <div class="attribute-name" style="margin-bottom: 10px;">评估方法</div>
                                <div class="attribute-content" v-if="edit==false" style="margin-bottom: 10px;">{{ course.assessmentMethod }}</div>
                                <input v-model="course.assessmentMethod" class="attribute-content" v-if="edit" style="margin-bottom: 10px;">
                                <VaButton v-if="edit==false" @click="edit=true;" style="width: 60px;">编辑</VaButton>
                                <VaButton v-if="edit" @click="edit=false;" style="width: 100px;">保存修改</VaButton>
                            </div>
                        </div>
                    </div>
                    <div>
                        <VaButton size="small" color="danger" style="width: 60px;" @click="showModal=true;">删除</VaButton>
                    </div>

                </VaCardContent>             
            </VaCard>
            <VaModal
                v-model="showModal"
                ok-text="确定"
                :beforeOk="comfirmDelete"

            >
            <h3 class="va-h3">
            请谨慎操作
            </h3>
            <p style="margin-bottom: 10px;">
                    是否确定删除ID为{{ course.id }}的“{{ course.name }}”？
                </p>
                <p>
                    该操作不可撤销。
                </p>
            </VaModal>
        </div>

    </div>
</template>

<script>
    import axios from '@/axios';
    import { mapState } from 'vuex';
    export default {
        name: 'AdminCourseDetailPage',
        data(){
            return {
                edit: false,
                showModal:false,
                course:{
                    id:null,
                    name: "",
                    credit: null,
                    time:"",
                    campusName:"",
                    classroomId:null,
                    classroomName:"",
                    teacherId:null,
                    teacherName: "",
                    capacity:null,
                    size:null,
                    compulsory:false,
                    assessmentMethod:"",                    
                }
            };
        },
        computed: {
            ...mapState(['id']), // 映射state中的id到本组件的computed属性id
        },
        methods:{
            getCourseByDefault(){
                const body = {uid: this.id, oid: this.$route.params.oid};
                console.log(body);
                axios.post("/teacher/show_course", body) // 后端没处理/admin/show_course的请求，只有teacher/show_course的
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.course = response.data.course;
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
            comfirmDelete(){
                const body = {uid: this.id, oid: this.$route.params.oid};
                console.log(body);
                axios.post("/admin/course_management/delete", body) // 后端没处理/admin/show_course的请求，只有teacher/show_course的
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.$router.push("/admin/course");
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
            }
        },
        mounted(){
            console.log('Received oid:', this.$route.params.oid);
            this.getCourseByDefault();            
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

    .detail-info{

    }

    .attribute{
        margin: 20px;
        display: flex;
        flex-direction: row;
    }

    .attribute-name{
        margin-right: 10px;
        font-size: 20px;  
    }

    .attribute-content{
        font-size: 20px; 
        color: #666E75;        
    }

    .attribute-long{
        margin: 20px;
        display: flex;
        flex-direction: column;        
    }
</style>