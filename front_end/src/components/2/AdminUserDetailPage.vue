<template>
    <div class="container">
        <h1>用户管理 > {{  info.name  }}</h1>
        <div class="content">
            <VaCard style="padding: 20px; display: flex; flex-direction: row">
                <div style="width: 80%;">
                    <div class="list" v-if="edit==false">
                        <div class="item">
                            <div class="item-name">姓名 </div>
                            <div class="item-content">{{ info.name }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">ID </div>
                            <div class="item-content">{{ info.id }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">性别 </div>
                            <div class="item-content">{{ info.gender }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">身份 </div>
                            <div class="item-content">{{ info.status }}</div>
                        </div>                    
                        <div class="item">
                            <div class="item-name">邮箱 </div>
                            <div class="item-content">{{ info.email }}</div>
                        </div>
                        <div class="item"> 
                            <div class="item-name">电话 </div>
                            <div class="item-content">{{ info.phone }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">住址 </div>
                            <div class="item-content">{{ info.address }}</div>
                        </div>
                        <div class="item">
                            <VaButton @click="changeState" style="margin-right: 20px;" >修改信息</VaButton>
                            <VaButton preset="secondary" class="mr-6 mb-2" @click="this.showModel= true;" >
                                删除用户
                            </VaButton>
                        </div>
                    </div>

                    <div class="list" v-if="edit">
                        <div class="item">
                            <div class="item-name">姓名 </div>
                            <input v-model="info.name">
                        </div>

                        <div class="item">
                            <div class="item-name">ID </div>
                            <div class="item-content">{{ info.id }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">性别 </div>
                            <VaRadio v-model="info.gender" :options="['男', '女','其它']" />
                        </div>
                        <div class="item">
                            <div class="item-name">身份 </div>
                            <VaRadio v-model="info.status" :options="['学生', '教师']" /> 
                        </div>

                        <div class="item">
                            <div class="item-name">邮箱 </div>
                            <input v-model="info.email">
                        </div>
                        <div class="item">
                            <div class="item-name">电话 </div>
                            <input v-model="info.phone">
                        </div>
                        
                        <div class="item">
                            <div class="item-name">住址 </div>
                            <input v-model="info.address">
                        </div>

                        <div class="item">
                            <VaButton @click="submit()">保存修改</VaButton>
                        </div>
                    </div>           
                </div>
                <div style="width: 20%; padding: 20px;">
                    <img 
                    :src="url()" 
                    :style="imageStyle"
                    @error="onImageError"
                    />
                    <VaFileUpload
                        v-model="info.photo"
                        uploadButtonText="修改头像"
                        type="gallery"
                        file-types="jpg,png"
                        @fileAdded="handleUpload"
                        style="align-self: center;"
                    />
                </div>
             
            </VaCard>
            <VaModal
                v-model="this.showModel"
                ok-text="确定"
                :beforeOk="comfirmDelete"
            >
            <h3 class="va-h3">
            请谨慎操作
            </h3>
            <p style="margin-bottom: 10px;">
                    是否确定删除ID为{{ info.id }}的“{{ info.name }}”？
                </p>
                <p v-if="info.status=='教师'">该用户为教师，其教授的所有课程也将一并删除。</p>
                <p>该操作不可撤销。</p>
            </VaModal>
        </div>

    </div>
</template>

<script>
    import axios from '@/axios';
    export default {
        name: 'InfoPage',
        components:{
        },
        props: {
            value_uid: {
                type: Number,
                required: true
            },
        },
        data(){
            
            return {
                defaultImageUrl:'http://172.19.115.218:8080/user-profile/default.png',
                imageStyle: {
                    width: '144px',
                    height: '144px',
                    objectFit: 'cover', // 确保图片以压缩方式显示
                    border: '1px solid #ddd',
                    borderRadius: '4px'
                },
                edit: false,
                uid: this.value_uid,
                showModel: false,
                info: {
                    name: "",
                    id: null,
                    password:'',
                    age: null,
                    status:'',
                    gender: '',
                    email: "",
                    phone:"",
                    address:""
                }
            };
        },
        watch: {
            value_uid(x){
                console("change uid to ", x);
                this.uid = x;
            },
        },
        methods:{
            url(){
                return 'http://172.19.115.218:8080/user-profile/' + Math.trunc(Number(this.$route.params.oid) % 100000000).toString() + '.png';
            },
            onImageError(event){
                event.target.src = this.defaultImageUrl;
            },
            getInfo(){
                const body = {uid: this.uid, oid: Number(this.$route.params.oid)};
                console.log(body);
                axios.post("/get_info", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.info = {
                                name: response.data.data.name,
                                id: response.data.data.id,
                                status: response.data.data.status,
                                gender: response.data.data.gender,
                                email: response.data.data.email,
                                phone: response.data.data.phone,
                                address: response.data.data.address,
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
            changeState(){
                this.edit = !(this.edit);
                if(this.edit){
                    console.log("state changeed to edit");
                }else{
                    console.log("state changed to show");
                }
            },
            setUid(x){
                this.uid = x;
            },
            setOid(x){
                this.oid = x;
            },
            submit(){
                this.edit = !(this.edit);
                const body = {id: this.uid, user: this.info};
                console.log(body);
                axios.post("/modify_info", body)
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.getUsersDefault();
                            location.reload();
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
                const body = {uid: this.uid, oid: this.$route.params.oid};
                console.log(body);
                axios.post("/admin/user_management/delete", body) // 后端没处理/admin/show_course的请求，只有teacher/show_course的
                    .then(response =>{
                        console.log("得到回应", response.data);
                        if(response.data.code == "1"){
                            this.$router.push("/admin/user");
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
            async handleUpload(files) {
                const file = files[0];
                const formData = new FormData();
                formData.append('id', this.oid);
                formData.append('photo', file);
                try {
                    const response = await axios.post('/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                    });
                    if (response.status === 200) {
                        console.log('照片上传成功');
                    } else {
                        console.error('照片上传失败');
                    }
                } catch (error) {
                    console.error('Error uploading file:', error);
                }
             },
        },
        mounted(){
            this.uid = this.value_uid;
            this.oid = this.value_oid;
            this.getInfo();
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
        display: flex;
        flex-direction: column;
    }

    .item{
        margin: 20px;
        display: flex;
        flex-direction: row;
    }

    .item-name{
        margin-right: 10px;
        font-size: 20px;    
    }

    .item-content{
        font-size: 20px; 
        color: #666E75;
    }

    input{
        color: #666E75;
        border-color: rgb(208, 208, 208);
        border-width: 1px;
        border-radius: 5px;
    }

    .photo {
        width: 96px;
        height: 144px;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

</style>