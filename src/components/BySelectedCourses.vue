<template>
  <div class="selected-courses">
    <!-- <h3>选课结果</h3> -->
    <table class="selected-courses-table">
      <thead>
      <tr>
        <th>序号</th>
        <th>是否选上</th>
        <th>课程代码</th>
        <th>课程名称</th>
        <th>教师</th>
        <th>上课时间</th>
        <th>上课地点</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
<!--      <tr v-for="(course, index) in selectedCourses" :key="course.details[0].id">-->
      <tr v-for="course in this.courses" :key="course.details[0].id">
        <td>{{ index + 1 }}</td>
        <td>是</td>
        <td>{{ course.code }}</td>
        <td>{{ course.name }}</td>
        <td>{{ course.details[0].teacher }}</td>
        <td>
          <div v-for="time in course.details[0].times" :key="time">{{ time }}</div>
        </td>
        <td>
          <div v-for="location in course.details[0].locations" :key="location">{{ location }}</div>
        </td>
        <td>
          <button @click="showApplication()">补选</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
  <MDBContainer
      class="border border-primary"
      style="
        height: 60%; width: 60%; padding: 2rem;
        position: fixed; top: 20%; left: 20%; background-color: white;
        text-align: center;
      "
      v-if="show"
  >
    <StuApplication />
    <MDBBtn color="success" @click="Submit()">提交</MDBBtn>
    <MDBBtn color="primary" @click="show=!show">关闭</MDBBtn>
  </MDBContainer>
  <MDBContainer
      class="border border-success"
      style="
        height: 20%; width: 20%; padding: 2rem;
        position: fixed; top: 40%; left: 40%; background-color: white;
        text-align: center;
      "
      v-if="showSuc"
  >
    申请提交成功，请等候审核！
    <MDBBtn color="primary" @click="showSuc=false">关闭</MDBBtn>
  </MDBContainer>
</template>

<script>
// import { addCourseSelect, deleteCourseSelect, getCourseByName } from '/src/api/courses.js';

import StuApplication from "./StuApplication.vue";
import {MDBBtn, MDBContainer} from "mdb-vue-ui-kit";


export default {
  name: 'SelectedCourses',
  components: {MDBContainer, MDBBtn, StuApplication},
  data() {

    return {
      expandedCourse: null,
      searchQuery: '',
      searchValue: '',
      searchType: '',
      selectedCourses: new Set(),
      myCourses: [],
      courses: [
        {
          id: 1,
          code: '(21120251)',
          name: '线性代数',
          credits: 4.0,
          type: '专业课',
          department: '计算机科学与技术学院',
          status: '未选',
          details: [
            {
              id: 1,
              teacher: '王章毅',
              term: '春夏',
              times: ['周一第1-2节 (春1-8周；夏1-7周)', '周三第11-12节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-104', '玉泉曹光彪东-503'],
              examTime: '2024年06月27日 (10:30-12:30)',
              teachingMethod: '双语',
              available: 6,
              capacity: 84,
              majorSpecific: 0,
              totalSpecific: 0,
              conflict: true
            },
            {
              id: 2,
              teacher: '李教授',
              term: '春夏',
              times: ['周二第1-2节 (春1-8周；夏1-7周)', '周四第11-12节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-204', '玉泉曹光彪东-303'],
              examTime: '2024年06月28日 (10:30-12:30)',
              teachingMethod: '单语',
              available: 10,
              capacity: 60,
              majorSpecific: 2,
              totalSpecific: 5,
              conflict: false
            }
          ]
        },
        {
          id: 2,
          code: '(21120241)',
          name: '操作系统',
          credits: 5.0,
          type: '专业课',
          department: '计算机科学与技术学院',
          status: '未选',
          details: [
            {
              id: 3,
              teacher: '刘玉生',
              term: '春夏',
              times: ['周一第3-4节 (春1-8周；夏1-7周)', '周三第9-10节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-101', '玉泉曹光彪东-503'],
              examTime: '2024年06月27日 (10:30-12:30)',
              teachingMethod: '双语',
              available: 3,
              capacity: 80,
              majorSpecific: 0,
              totalSpecific: 0,
              conflict: true
            }
          ]
        },
        {
          id: 3,
          code: '(21120231)',
          name: '编译原理',
          credits: 3.5,
          type: '专业课',
          department: '计算机科学与技术学院',
          status: '未选',
          details: [
            {
              id: 4,
              teacher: '李教授',
              term: '春夏',
              times: ['周二第1-2节 (春1-8周；夏1-7周)', '周四第11-12节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-204', '玉泉曹光彪东-303'],
              examTime: '2024年06月28日 (10:30-12:30)',
              teachingMethod: '单语',
              available: 10,
              capacity: 60,
              majorSpecific: 2,
              totalSpecific: 5,
              conflict: false
            },
            {
              id: 5,
              teacher: '王章毅',
              term: '春夏',
              times: ['周一第1-2节 (春1-8周；夏1-7周)', '周三第11-12节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-104', '玉泉曹光彪东-503'],
              examTime: '2024年06月27日 (10:30-12:30)',
              teachingMethod: '双语',
              available: 6,
              capacity: 84,
              majorSpecific: 0,
              totalSpecific: 0,
              conflict: true
            },
            {
              id: 6,
              teacher: '张教授',
              term: '春夏',
              times: ['周五第5-6节 (春1-8周；夏1-7周)', '周日第7-8节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-304', '玉泉曹光彪东-403'],
              examTime: '2024年07月01日 (10:30-12:30)',
              teachingMethod: '双语',
              available: 8,
              capacity: 70,
              majorSpecific: 1,
              totalSpecific: 4,
              conflict: false
            }
          ]
        },
        {
          id: 4,
          code: '(21120221)',
          name: '人工智能',
          credits: 2.5,
          type: '专业课',
          department: '计算机科学与技术学院',
          status: '已选',
          details: [
            {
              id: 7,
              teacher: '张老师',
              term: '春夏',
              times: ['周三第3-4节 (春1-8周；夏1-7周)', '周五第9-10节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-304', '玉泉曹光彪东-403'],
              examTime: '2024年06月29日 (10:30-12:30)',
              teachingMethod: '双语',
              available: 5,
              capacity: 70,
              majorSpecific: 0,
              totalSpecific: 0,
              conflict: false
            }
          ]
        },
        {
          id: 5,
          code: '(21120211)',
          name: '计算理论',
          credits: 2.0,
          type: '专业课',
          department: '计算机科学与技术学院',
          status: '未选',
          details: [
            {
              id: 8,
              teacher: '王老师',
              term: '春夏',
              times: ['周四第1-2节 (春1-8周；夏1-7周)', '周六第11-12节 (春,夏1,3,5,7周)'],
              locations: ['玉泉曹光彪东楼西-104', '玉泉曹光彪东-503'],
              examTime: '2024年06月30日 (10:30-12:30)',
              teachingMethod: '单语',
              avacilable: 8,
              capacity: 90,
              majorSpecific: 1,
              totalSpecific: 3,
              conflict: true
            }
          ]
        }
      ],
      show: false,
      showSuc: false,
    };
  },
  created() {
    // this.selectedCourses = this.selectedCoursesWrapper;
    // console.log(1111);
    // this.selectedCourses = this.courses;
    // console.log(this.selectedCourses);
    // console.log("aaa" + JSON.stringify(this.selectedCoursesWrapper));
  },
  methods: {
    showApplication() {
      this.show = true
    },
    Submit() {
      this.show = false
      this.showSuc = true
    }
  }
};
</script>

<style scoped>
.selected-courses {
  margin-top: 20px;
}

.selected-courses-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.selected-courses-table th,
.selected-courses-table td {
  border: 1px solid #ddd;
  padding: 5px;
  text-align: center;
}

.selected-courses-table th {
  background-color: #f8f8f8;
}

.selected-courses-table button {
  padding: 5px 10px;
  background-color: #004fdf;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 3px;
  font-size: 14px;
}
</style>
