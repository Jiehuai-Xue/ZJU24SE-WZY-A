<script>

	import axios from '@/axios';
	import { mapState, mapActions } from 'vuex';
	console.log('app is running...')
	
	const LoginView = {
		components:{
		},
		data() {
			return {
				id: null,
				password: '',
				if_message: false
			};
		},
		computed: {
			...mapState(['id']), // 映射state中的id到本组件的computed属性id
		},
		methods: {
			...mapActions(['updateId']), // 映射action到本组件的方法
			handleLoginRequest() {
				console.log('handling a login request with id = ', this.id, ", password = ", this.password);
				const data = {
					id: this.id,
					password: this.password
				};
				axios.post('/login', data)
					.then(response => {
						console.log('登录成功', response.data);
						const code = response.data.code; // 更新store中的id
						if(code == "1"){
							this.updateId(data.id);
							if(response.data.privilege == "SYS_ADMIN"){
								this.$router.push('/sys_admin');
							}else if(response.data.privilege == "ADMIN"){
								this.$router.push('/admin');
							}else if(response.data.privilege == "T"){
								this.$router.push('/teacher');
							}else if(response.data.privilege == "S"){
								this.$router.push('/student');
							}							
						}else if(code == "-2"){
							this.if_message = true;
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
				this.id = null;
				this.password = '';
			},
			handleForgetRequest(){
				console.log('handling a forget request...');
			}
		}
	}

	export default LoginView
</script>

<template>
	<div class="background">
		<div class="login-global">
			<div class="box">
				<h2>登录</h2>
				<div class="login-field">
					<input v-model="id" type="number" placeholder="请输入用户名">
				</div>
				<div class="login-field">
					<input v-model="password" type="password" placeholder="请输入密码">
				</div>
				<div class="message" v-if="if_message">用户名或密码错误，请重试</div>		
				<button @click="handleLoginRequest()">登录</button>			
			</div>
			<div style="display: flex; flex-direction: column;">
				<div class="title">
					STSS
				</div>
				<div class="sub-title">
					智慧教学管理系统
				</div>
				<div class="slogen">
					让学习更智能，让管理更高效
				</div>
			</div>	
		</div>



	</div>
</template>

<style>
.login-global{
	margin-top: 120px;
	display: flex;
	flex-direction: row;
	justify-content: center;
}
.title{
	font-weight: 1000;
	letter-spacing: 20px;
	padding-left: 40px;
	padding-top: 100px;
	padding-bottom: 40px;
	font-size: 100px;
	color: #414141;
}
.sub-title{
	font-weight: 1000;
	letter-spacing: 20px;
	padding-left: 40px;
	font-size: 60px;
	color: #414141;
	padding-bottom: 40px;
}
.slogen{
	font-weight: 5000;
	letter-spacing: 15px;
	padding-left: 40px;
	font-size: 25px;
	color: #414141;
}
.background {
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	background-size: cover;
	background-position: center 0; 
	background-repeat: no-repeat;  
	background-attachment: fixed; 
	-webkit-background-size: cover;  
	-o-background-size: cover;  
	-moz-background-size: cover;  
	-ms-background-size: cover;

}
.box{
	width: 400px;
	padding:40px;
	padding:40px;
	background: rgba(255, 255, 255, 0.7);
	margin-top: 200px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	border-radius: 10px;
	box-shadow: 0 15px  25px rgba(0,0,0,.5);
	margin: 100px;
}

.box h2{
	letter-spacing: 20px;
	margin: 0 0 30px;
	padding: 0;
	text-align: center;
	color: #000000;
}
.box .login-field{
	position: relative;
}
.box .login-field  input{
	width: 100%;
	padding: 10px 0;
	font-size: 16px;
	color: #000000;
	margin-bottom: 30px;
	border: none;
	border-bottom: 1px solid #b5b5b5;
	outline: none;
	background: transparent;
}
.box .login-field  label{
	position: absolute;
	top: 0;
	left: 0;
	letter-spacing: 1px;
	padding: 10px 0;
	font-size: 16px;
	color: #000000;
	pointer-events: none;
	transition: .5s;
}
.box .login-field input:focus ~ label,
.box .login-field input:valid ~ label{
	top: -23px;
	left: 0;
	color: #ffffff;
	font-size: 12px;
}
.box button{
	align-self: center;
	width: 320px;
	background: transparent;
	border: none;
	outline: none;
	color: #fff;
	background: #000066;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 5px;
}
.box button:hover{
	background: transparent;
	border: none;
	outline: none;
	color: #6699ff;
	background: #000066;
	padding: 10px 20px;
	cursor: pointer;
	border-radius: 5px;
}

.message{
	font-size: 12px;
	margin-bottom: 20px;
	background: transparent;
	border: none;
	outline: none;
	color: #9e9e9e;
	text-align:center;
}

</style>