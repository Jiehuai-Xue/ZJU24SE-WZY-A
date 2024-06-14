<template>
    <div class="container">
        <h1>我的课程 > {{ course.name }}</h1>
        <div class="content">
            <VaCard style="margin-bottom: 10px; margin-top: 10px;">
                <VaCardContent>
                    <div class="detail-info">
                        <div class="attribute">
                            <div class="attribute-name">课程名称</div>
                            <div class="attribute-content">{{ course.name }}</div>
                        </div> 
                        <div class="attribute">
                            <div class="attribute-name">课程ID</div>
                            <div class="attribute-content">{{ course.id }}</div>
                        </div> 
                        <div class="attribute">
                            <div class="attribute-name">学分</div>
                            <div class="attribute-content">{{ course.credit }}</div>
                        </div> 
                        <div class="attribute">
                            <div class="attribute-name">教室</div>
                            <div class="attribute-content">{{ course.campusName }}</div>
                            <div class="attribute-content">{{ course.classroomName }}(教室ID：{{ course.classroomId }})</div>
                        </div> 
                        <div class="attribute">
                            <div class="attribute-name">教师</div>
                            <div class="attribute-content">{{ course.teacherName }}(教师ID：{{ course.teacherId }})</div>
                        </div> 
                        <div class="attribute">
                            <div class="attribute-name">时间</div>
                            <div class="attribute-content">{{ course.time }}</div>
                        </div> 
                        <div class="attribute">
                            <div class="attribute-name">选课人数/课程容量</div>
                            <div class="attribute-content">{{ course.size }} / {{ course.capacity }}</div>
                        </div> 
                        <div class="attribute-long">
                            <div class="attribute-name" style="margin-bottom: 10px;">评估方法</div>
                            <div class="attribute-content">{{ course.assessmentMethod }}</div>
                        </div>          
                    </div>
                </VaCardContent>             
            </VaCard>

        </div>

    </div>
</template>

<script>
    import axios from '@/axios';
    import { mapState } from 'vuex';
    export default {
        name: 'StudentCourseDetailPage',
        data(){
            return {
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
                axios.post("/student/show_course", body)
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