<template>
  <NavBar title="智能选课"/>
  <div class="search-wrapper">
      <input type="text" v-model="searchTerm" placeholder="课程标签" @input="onSearch"/>
  </div>
  <div class="labels">
    <span class="label">课程名称</span>
    <span class="label">课程代码</span>
    <span class="label">教师</span>
    <span class="label">上课时间</span>
    <span class="label">上课地点</span>
  </div>
  <div v-if="searchTerm && filteredCourses.length">
    <div v-for="course in filteredCourses" :key="index" class="course-info">
      <span class="info">{{ course.name }}</span>
      <span class="info">{{ course.code }}</span>
      <span class="info">{{ course.teacher }}</span>
      <span class="info">{{ course.time }}</span>
      <span class="info">{{ course.location }}</span>
    </div>
  </div>
</template>

<script>
import NavBar from "../components/NavBar.vue"

export default {
  name: 'App',
  components: {
    NavBar
  },
  data() {
    return {
      searchTerm: '',
      courses: [
        { key: '计算机', name: '软件工程', code: '21120492', teacher: '王老师', time: '春夏(第1-8周|周一3,4节)', location: '玉泉曹光彪大楼西楼-104' },
        { key: '经济学', name: '会计学', code: '21220214', teacher: '李教授', time: '春夏(第1-8周|周二1,2节)', location: '紫金港西二教学楼-203' },
        { key: '计算机', name: '数字金融科技', code: '21120500', teacher: '刘老师', time: '春夏(第1-8周|周五3,4节)', location: '玉泉曹光彪大楼西楼-204'},
        { key: '经济学', name: '数字金融科技', code: '21120500', teacher: '刘老师', time: '春夏(第1-8周|周五3,4节)', location: '玉泉曹光彪大楼西楼-204'},
      ],
      displayCourses: []
    };
  },
  computed: {
    filteredCourses() {
      return this.courses.filter(course => course.key.toLowerCase().includes(this.searchTerm.toLowerCase()));
    }
  },
  methods: {
    onSearch() {
    }
  }
};
</script>

<style scoped>
.search-wrapper {
    padding: 20px;
}

input[type="text"] {
    width: 100%;
    padding: 8px;
    margin: 10px 0;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
}
.labels {
    display: flex;
    justify-content: space-between;
    padding: 0 20px;
    margin-bottom: 20px;
}
/* Adjust the label style for proper spacing and alignment */
.label {
    flex-grow: 1;
    text-align: center;
}
.course-info {
  display: flex;
  justify-content: space-between;
  padding: 0 20px;
}
.info {
  flex-grow: 1;
  text-align: center;
  margin-top: 10px; /* 为信息文本和标签之间提供一些空间 */
}
/* Consider adding a responsive aspect to your design if needed */
@media (max-width: 600px) {
  .label {
    font-size: 14px;
  }
}
</style>