<template>
    <div class="container">
        <h1>我的课程</h1>
        <div class="content">
            <VaCard style="margin-bottom: 10px; margin-top: 10px;">
                <VaCardContent>
                    <div class="list">
                        <div class="item" v-for="(course, index) in courses"
                        :key="index"
                        @click="showDetail(course.id)">
                            <VaListItemSection>
                                <div class="course-name">{{ course.name }}</div>
                                <div class="caption">学分：{{ course.credit }}&emsp;&emsp;时间{{ course.time }}&emsp;&emsp;教室：{{ course.classroom }}&emsp;&emsp;教师姓名：{{ course.teacher }}</div>
                            </VaListItemSection>       
                        </div>
                    </div> 
                </VaCardContent>             
            </VaCard>

        </div>

    </div>
</template>

<script>
    import axios from '@/axios';
    export default {
        name: 'CoursePreviewPage',
        props: {
            value_uid: {
                type: Number,
                required: true
            },
            value_oid: {
                type: Number,
                required: true
            }
        },
        data(){
            return {
                uid: this.value_uid,
                oid: this.value_oid,                
                courses: [],
                selected_id: null,
            };
        },
        watch: {
            value_uid(x){
                console("change uid to ", x);
                this.uid = x;
            },
            value_oid(x) {
                console("change oid to ", x);
                this.oid = x;
            },
        },
        methods:{
            getCourses(){
                const body = {uid: this.uid, oid: this.oid};
                console.log(body);
                axios.post("/student/show_courses", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.courses = response.data.courses;
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
            showDetail(oid){
                this.selected_id = oid;
                console.log('push to /student/course/detail... course id = ', oid);
                this.$router.push({path:`/student/course/detail/${oid}`,});
            }
        },
        mounted(){
            this.uid = this.value_uid;
            this.oid = this.value_oid;
            this.getCourses();
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

    .list{
        
    }

    .item{
        border-radius:10px;
        margin: 10px;
    }

    .item :hover{
        cursor: pointer;
        background-color:aliceblue;
    }

    .course-name{
        padding: 10px;
        font-size: 20px;    
    }

    .caption{
        padding: 10px;
        font-size: 12px;    
        color: rgb(176, 176, 176);
    }

</style>