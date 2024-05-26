<template>
    <div class="container">
        <h1>我的信息</h1>
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
                            <VaButton @click="changeState">修改信息</VaButton>
                        </div>
                    </div>

                    <div class="list" v-if="edit">
                        <div class="item">
                            <div class="item-name">姓名 </div>
                            <div class="item-content">{{ info.name }}</div>
                        </div>

                        <div class="item">
                            <div class="item-name">ID </div>
                            <div class="item-content">{{ info.id }}</div>
                        </div>
                        <div class="item">
                            <div class="item-name">身份 </div>
                            <div class="item-content">{{ info.status }}</div>
                        </div>

                        <div class="item">
                            <div class="item-name">邮箱 </div>
                            <input v-model="info.email">
                        </div>
                        <div class="item">
                            <div class="item-name">电话 </div>
                            <div class="item-content">{{ info.phone }}</div>
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
                    <VaImage
                    :ratio="1"
                    src="https://picsum.photos/1500"
                    style="align-self: center; width: 144px;height: 144px; border: 1px solid #ddd; border-radius: 4px;"
                    />
                    <VaFileUpload
                        v-model="info.photo"
                        uploadButtonText="修改头像"
                        type="gallery"
                        file-types="jpg,png"
                        v-if="edit"
                        style="align-self: center;"
                    />
                </div>
             
            </VaCard>

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
            value_oid: {
                type: Number,
                required: true
            }
        },
        data(){
            return {
                edit: false,
                uid: this.value_uid,
                oid: this.value_oid,
                info: {
                    name: "",
                    id: null,
                    status:'',
                    email: "",
                    phone:"",
                    address:"",
                    photo:[],
                }
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
            getInfo(){
                const body = {uid: this.uid, oid: this.oid};
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
                if(this.edit){
                    console.log("state changeed to edit");
                }else{
                    console.log("state changed to show");
                }
            }
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