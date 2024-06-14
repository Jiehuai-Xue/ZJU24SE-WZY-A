<template>
	<div class="container">
		<h1>课程管理</h1>
		<div class="content">
			<VaTabs v-model="value" style="margin-bottom: 10px;">
				<template #tabs>
					<VaTab icon="va-plus" label="添加课程" @click="add" />
					<VaTab icon="va-minus" label="删除所有" @click="deleteAll" />
					<VaTab icon="book" label="筛选与查询" @click="select" />
				</template>
			</VaTabs>
			<div class="tool-detail" v-if="action==1">
				<div style="display: flex; flex-direction: row;">
					<div style="width: 35%;">
						<div class="add-item">
							<div class="add-item-name">名称 </div>
							<input v-model="new_course.name" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">学分 </div>
							<input v-model="new_course.credit" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">教师ID </div>
							<input v-model="new_course.teacherId" required="required">
						</div>

						<div class="add-item">
							<div class="add-item-name">教室ID </div>
							<input v-model="new_course.classroomId" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">课程容量</div>
							<input v-model="new_course.capacity" required="required">
						</div>
						<div class="add-item">
							<div class="add-item-name">是否必修</div>
							<VaRadio v-model="new_course.compulsory" :options="[
                                {
                                    text: '是',
                                    value: true,
                                },
                                {
                                    text: '否',
                                    value: false,
                                },
                                ]" value-by="value" />
						</div>
					</div>
					<div style="width: 65%;">
						<div class="add-item" style="display: flex; flex-direction: column;">
							<div class="add-item-name" style="margin-bottom: 10px;">时间 </div>
							<div style="display: flex; flex-direction: row;">
								<VaOptionList v-model="time"
									:options="['周一1', '周一2', '周一3', '周一4','周一5', '周一6', '周一7', '周一8']"
									style="margin-right: 30px;" />
								<VaOptionList v-model="time"
									:options="['周二1', '周二2', '周二3', '周二4','周二5', '周二6', '周二7', '周二8']"
									style="margin-right: 30px;" />
								<VaOptionList v-model="time"
									:options="['周三1', '周三2', '周三3', '周三4','周三5', '周三6', '周三7', '周三8']"
									style="margin-right: 30px;" />
								<VaOptionList v-model="time"
									:options="['周四1', '周四2', '周四3', '周四4','周四5', '周四6', '周四7', '周四8']"
									style="margin-right: 30px;" />
								<VaOptionList v-model="time"
									:options="['周五1', '周五2', '周五3', '周五4','周五5', '周五6', '周五7', '周五8']"
									style="margin-right: 30px;" />
							</div>
						</div>
					</div>
				</div>
				<div class="add-item">
					<div class="add-item-name">课程评价方法</div>
					<input v-model="new_course.AssessmentMethod" style="width: 84%;" placeholder="请输入评价方法">
				</div>
				<div class="add-item">
					<VaButton @click="comfirmAdd" :disabled="!isValid">确认</VaButton>
				</div>

			</div>
			<div class="tool-detail" v-if="action==2">
				<div class="delete-item">
					<div class="add-item-name">该功能尚不支持 </div>
					<VaButton @click="comfirmAdd" style="width: 60px; margin-left: 10px;">确认</VaButton>
				</div>
			</div>
			<div class="tool-detail" v-if="action==3">
				<div class="select-item">
					<VaInput v-model="condition" placeholder="请输入课程名称" />
					<VaButton @click="comfirmSelect" style="width: 100px; margin-left: 10px;">确认查询</VaButton>
				</div>
			</div>
			<VaCard style="margin-bottom: 10px; margin-top: 10px;">
				<VaCardContent>

					<div class="va-table-responsive">
						<table class="va-table va-table--hoverable va-table--clickable">
							<thead>
								<tr>
									<th>名称</th>
									<th>ID</th>
									<th>学分</th>
									<th>教师</th>
									<th>教室</th>
									<th>时间</th>
								</tr>
							</thead>
							<tbody>
								<tr v-for="course in courses" :key="course.id" @click="showDetail(course.id)">
									<td>{{ course.name }}</td>
									<td>{{ course.id }}</td>
									<td>{{ course.credit }}</td>
									<td>{{ course.teacher }}</td>
									<td>{{ course.classroom }}</td>
									<td>{{ course.time }}</td>
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
		computed: {
			isValid() {
				return this.new_course.name && this.new_course.credit && this.new_course.teacherId && this.new_course.classroomId && this.new_course.capacity && this.new_course.AssessmentMethod && this.time.length!=0;
			}
		},
		props: {
			value_uid: {
				type: Number,
				required: true
			},
		},
		data() {
			return {
				uid: this.value_uid,
				action: 0,
				new_course: {
					name: '',
					credit: null,
					time: '',
					classroomId: null,
					classroomName: '',
					campusName: '',
					teacherId: null,
					teacherName: '',
					capacity: null,
					size: 0,
					compulsory: false,
					AssessmentMethod: '',
				},
				time: [],
				courses: [],
				condition: '请输入课程名称',
			};
		},
		methods: {
			getCoursesDefault() {
				const body = {
					uid: this.uid,
					data: "default"
				};
				console.log(body);
				axios.post("/admin/course_management/", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data.code == "1") {
							this.courses = response.data.courses;
						} else if (response.data.code == "-1") {
							console.log(response.data.message);
						} else {
							console.log("unhandled code, ", response.data.code);
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
			add() {
				if (this.action != 1) {
					this.action = 1;
				} else {
					this.action = 0;
				}
			},
			comfirmAdd() {
				if (!this.isValid) {
					this.$alert('请填写所有必填字段后再尝试保存。', '提示', {
						confirmButtonText: '确认',
					});
					return;
				}
				this.new_course.time = '[';
				for (var i = 0; i < this.time.length; i++) {
					this.new_course.time = this.new_course.time + '\"';
					this.new_course.time = this.new_course.time + this.time[i];
					if (i != this.time.length - 1) {
						this.new_course.time = this.new_course.time + '\", ';
					} else {
						this.new_course.time = this.new_course.time + '\"]';
					}
				}
				console.log("new_course.time:", this.new_course.time);
				const body = {
					id: this.uid,
					course: this.new_course
				};
				console.log(body);
				axios.post("/admin/course_management/add", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data.code == "1") {
							this.getCoursesDefault();
							this.new_course = {
								name: '',
								credit: null,
								time: '',
								classroomId: null,
								classroomName: '',
								teacherId: null,
								teacherName: '',
								capacity: null,
								size: 0,
								compulsory: false,
								AssessmentMethod: '',
							};
							this.time = [];
						} else if (response.data.code == "-1") {
							console.log(response.data.message);
						} else {
							console.log("unhandled code, ", response.data.code);
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
			deleteAll() {
				if (this.action != 2) {
					this.action = 2;
				} else {
					this.action = 0;
				}
			},
			select() {
				if (this.action != 3) {
					this.action = 3;
				} else {
					this.action = 0;
				}
			},
			comfirmSelect() {
				const body = {
					uid: this.uid,
					data: this.condition
				};
				console.log(body);
				axios.post("/admin/course_management/get_by_name", body)
					.then(response => {
						console.log("得到回应", response.data);
						if (response.data.code == "1") {
							this.courses = response.data.courses;
						} else if (response.data.code == "-1") {
							console.log(response.data.message);
						} else {
							console.log("unhandled code, ", response.data.code);
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
			showDetail(oid) {
				console.log('push to /admin/course/detail...');
				this.$router.push({
					path: `/admin/course/detail/${oid}`,
				});
			}
		},
		mounted() {
			this.getCoursesDefault();
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
		height: 100vh;
		/* 使容器高度等于视口高度 */
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

	.tool-detail {
		padding-top: 10px;
		padding-bottom: 10px;
		display: block;
	}

	.add-item {
		margin: 20px;
		display: flex;
		flex-direction: row;
	}

	.add-item-name {
		margin-right: 10px;
	}

	.delete-item {
		margin: 20px;
		display: flex;
		flex-direction: row;
	}

	.select-item {
		margin: 20px;
		display: flex;
		flex-direction: row;
	}

	.list {
		padding-top: 10px;
		padding-bottom: 10px;
	}

	.item {
		border-radius: 10px;
		margin: 10px;
	}

	.item :hover {
		cursor: pointer;
		background-color: aliceblue;
	}

	.course-name {
		padding: 10px;
		font-size: 20px;
	}

	.caption {
		padding: 10px;
		font-size: 12px;
		color: rgb(176, 176, 176);
	}
</style>