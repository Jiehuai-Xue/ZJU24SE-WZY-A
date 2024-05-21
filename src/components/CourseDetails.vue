<template>
  <div>
    <div v-if="courseType == '搜索引擎'" class="search-bar">
      <div style="display:flex">
        <input type="text" placeholder="搜索" v-model="searchValue" @input="search">
        <select clearable v-model="searchType" style="width:150px">
          <option key="0" :value="0">按照课程名称搜索</option>
          <option key="1" :value="1">按照老师姓名搜索</option>
        </select>
      </div>
      <button @click="search">搜索</button>
    </div>
    <div class="courses-list">
      <div v-for="(course) in myCourses" :key="course.id" class="course-container">
        <div class="course-header" @click="toggle(course.id)">
          <div class="header-left">
            <span>{{ course.code }} {{ course.name }}</span>
            <span>{{ course.credits }} 学分</span>
            <span>课程类别: {{ course.type }}</span>
            <span>开课学院: {{ course.department }}</span>
            <span>状态: {{ course.status }}</span>
          </div>
          <button class="toggle-button">
            <span v-if="expandedCourse == course.id">▲</span>
            <span v-else>▼</span>
          </button>
        </div>
        <transition name="fade">
          <div v-if="expandedCourse == course.id" class="course-details">
            <table class="course-table">
              <thead>
              <tr>
                <th>教师</th>
                <th>学期</th>
                <th>上课时间</th>
                <th>上课地点</th>
                <th>考试时间</th>
                <th>教学方式</th>
                <th>余量/容量</th>
                <th>本专业特定人数</th>
                <th>所有特定人数</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(detail) in course.details" :key="detail.id"
                  :class="{ mySelect: detail.selectFlag, myNoSelect: !detail.selectFlag }">
                <td><a href="#">{{ detail.teacher }}</a></td>
                <td>{{ detail.term }}</td>
                <td>
                  <div v-for="time in detail.times" :key="time">{{ time }}</div>
                </td>
                <td>
                  <div v-for="location in detail.locations" :key="location">{{ location }}</div>
                </td>
                <td>{{ detail.examTime }}</td>
                <td>{{ detail.teachingMethod }}</td>
                <td>{{ detail.available }}/{{ detail.capacity }}</td>
                <td>{{ detail.majorSpecific }}</td>
                <td>{{ detail.totalSpecific }}</td>
                <td>
                  <button :class="{ 'selected': detail.selectFlag }" @click="selectCourse(detail)">
                    {{ detail.selectFlag ? '退课' : '选课' }}
                  </button>
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
// import { addCourseSelect, deleteCourseSelect, getCourseByName } from '@/api/courses.js';

export default {
  name: 'CourseDetails',
  props: ["courses", "courseType", "expandedCourseWrapper"],
  data() {
    return {
      expandedCourse: null,
      searchQuery: '',
      searchValue: '',
      searchType: '',
      selectedCourses: new Set(),
      myCourses: [],
      // courses: [
      //   {
      //     id: 1,
      //     code: '(21120251)',
      //     name: '线性代数',
      //     credits: 4.0,
      //     type: '专业课',
      //     department: '计算机科学与技术学院',
      //     status: '未选',
      //     details: [
      //       {
      //         id: 1,
      //         teacher: '王章毅',
      //         term: '春夏',
      //         times: ['周一第1-2节 (春1-8周；夏1-7周)', '周三第11-12节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-104', '玉泉曹光彪东-503'],
      //         examTime: '2024年06月27日 (10:30-12:30)',
      //         teachingMethod: '双语',
      //         available: 6,
      //         capacity: 84,
      //         majorSpecific: 0,
      //         totalSpecific: 0,
      //         conflict: true
      //       },
      //       {
      //         id: 2,
      //         teacher: '李教授',
      //         term: '春夏',
      //         times: ['周二第1-2节 (春1-8周；夏1-7周)', '周四第11-12节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-204', '玉泉曹光彪东-303'],
      //         examTime: '2024年06月28日 (10:30-12:30)',
      //         teachingMethod: '单语',
      //         available: 10,
      //         capacity: 60,
      //         majorSpecific: 2,
      //         totalSpecific: 5,
      //         conflict: false
      //       }
      //     ]
      //   },
      //   {
      //     id: 2,
      //     code: '(21120241)',
      //     name: '操作系统',
      //     credits: 5.0,
      //     type: '专业课',
      //     department: '计算机科学与技术学院',
      //     status: '未选',
      //     details: [
      //       {
      //         id: 3,
      //         teacher: '刘玉生',
      //         term: '春夏',
      //         times: ['周一第3-4节 (春1-8周；夏1-7周)', '周三第9-10节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-101', '玉泉曹光彪东-503'],
      //         examTime: '2024年06月27日 (10:30-12:30)',
      //         teachingMethod: '双语',
      //         available: 3,
      //         capacity: 80,
      //         majorSpecific: 0,
      //         totalSpecific: 0,
      //         conflict: true
      //       }
      //     ]
      //   },
      //   {
      //     id: 3,
      //     code: '(21120231)',
      //     name: '编译原理',
      //     credits: 3.5,
      //     type: '专业课',
      //     department: '计算机科学与技术学院',
      //     status: '未选',
      //     details: [
      //       {
      //         id: 4,
      //         teacher: '李教授',
      //         term: '春夏',
      //         times: ['周二第1-2节 (春1-8周；夏1-7周)', '周四第11-12节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-204', '玉泉曹光彪东-303'],
      //         examTime: '2024年06月28日 (10:30-12:30)',
      //         teachingMethod: '单语',
      //         available: 10,
      //         capacity: 60,
      //         majorSpecific: 2,
      //         totalSpecific: 5,
      //         conflict: false
      //       },
      //       {
      //         id: 5,
      //         teacher: '王章毅',
      //         term: '春夏',
      //         times: ['周一第1-2节 (春1-8周；夏1-7周)', '周三第11-12节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-104', '玉泉曹光彪东-503'],
      //         examTime: '2024年06月27日 (10:30-12:30)',
      //         teachingMethod: '双语',
      //         available: 6,
      //         capacity: 84,
      //         majorSpecific: 0,
      //         totalSpecific: 0,
      //         conflict: true
      //       },
      //       {
      //         id: 6,
      //         teacher: '张教授',
      //         term: '春夏',
      //         times: ['周五第5-6节 (春1-8周；夏1-7周)', '周日第7-8节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-304', '玉泉曹光彪东-403'],
      //         examTime: '2024年07月01日 (10:30-12:30)',
      //         teachingMethod: '双语',
      //         available: 8,
      //         capacity: 70,
      //         majorSpecific: 1,
      //         totalSpecific: 4,
      //         conflict: false
      //       }
      //     ]
      //   },
      //   {
      //     id: 4,
      //     code: '(21120221)',
      //     name: '人工智能',
      //     credits: 2.5,
      //     type: '专业课',
      //     department: '计算机科学与技术学院',
      //     status: '已选',
      //     details: [
      //       {
      //         id: 7,
      //         teacher: '张老师',
      //         term: '春夏',
      //         times: ['周三第3-4节 (春1-8周；夏1-7周)', '周五第9-10节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-304', '玉泉曹光彪东-403'],
      //         examTime: '2024年06月29日 (10:30-12:30)',
      //         teachingMethod: '双语',
      //         available: 5,
      //         capacity: 70,
      //         majorSpecific: 0,
      //         totalSpecific: 0,
      //         conflict: false
      //       }
      //     ]
      //   },
      //   {
      //     id: 5,
      //     code: '(21120211)',
      //     name: '计算理论',
      //     credits: 2.0,
      //     type: '专业课',
      //     department: '计算机科学与技术学院',
      //     status: '未选',
      //     details: [
      //       {
      //         id: 8,
      //         teacher: '王老师',
      //         term: '春夏',
      //         times: ['周四第1-2节 (春1-8周；夏1-7周)', '周六第11-12节 (春,夏1,3,5,7周)'],
      //         locations: ['玉泉曹光彪东楼西-104', '玉泉曹光彪东-503'],
      //         examTime: '2024年06月30日 (10:30-12:30)',
      //         teachingMethod: '单语',
      //         avacilable: 8,
      //         capacity: 90,
      //         majorSpecific: 1,
      //         totalSpecific: 3,
      //         conflict: true
      //       }
      //     ]
      //   }
      // ]
    };
  },
  // computed: {
  //   filteredCourses() {
  //     debugger
  //     console.log(this.courses);
  //     console.log(this.courseType);
  //     if (!this.searchQuery) {
  //       return this.courses;
  //     }
  //     return this.courses.filter(course =>
  //       course.name.includes(this.searchQuery) ||
  //       course.code.includes(this.searchQuery)
  //     );
  //   },
  // },
  created() {
    if (this.courseType != '搜索引擎') {
      this.myCourses = this.courses;
    }

    this.expandedCourse = this.expandedCourseWrapper;

    console.log(this.expandedCourse);
  },
  methods: {
    toggle(id) {
      this.expandedCourse = this.expandedCourse === id ? null : id;
    },
    selectCourse(detail) {
      if (detail.selectFlag) {
        //刪除
        let deleteData = {}
        deleteData.idList = [];
        deleteData.idList.push(detail.selectId);
        deleteCourseSelect(deleteData).then(response => {
          this.$emit("update:Courses", {
            expandedCourseWrapper: detail.courseId
          });
        }).catch();
      } else {
        //插入
        let addData = {
          courseId: "",
          courseDetailId: "",
        }
        addData.courseId = detail.courseId;
        addData.courseDetailId = detail.id
        addCourseSelect(addData).then(response => {
          this.$emit("update:courses", {
            expandedCourseWrapper: detail.courseId
          });
        }).catch();
      }
    },
    search() {
      getCourseByName(this.searchValue, this.searchType).then(response => {
        this.myCourses = response.result;
        this.itemKey++;
      }).catch();
    }
  }
};
</script>

<style scoped>
.courses-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.course-container {
  border: 1px solid #ddd;
  border-radius: 5px;
}

.course-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f0f0f0;
  padding: 10px;
  cursor: pointer;
}

.header-left {
  display: flex;
  flex-wrap: wrap;
}

.header-left span {
  margin-right: 10px;
}

.toggle-button {
  background: none;
  border: none;
  font-size: 16px;
  cursor: pointer;
}

.course-details {
  padding: 10px;
}

.course-table {
  width: 100%;
  border-collapse: collapse;
  font-size: 14px;
}

.course-table th,
.course-table td {
  border: 1px solid #ddd;
  padding: 5px;
  text-align: center;
}

.course-table th {
  background-color: #f8f8f8;
}

.conflict {
  background-color: #f8d7da;
}

.mySelect {
  background-color: #FFC107;
}

.myNoSelect {
  background-color: #28A745;
}

.course-table a {
  color: #007BFF;
  text-decoration: none;
}

.course-table button {
  padding: 5px 10px;
  border: none;
  cursor: pointer;
  border-radius: 3px;
  font-size: 14px;
}

.course-table button.selected {
  background-color: #FF4C4C;
  color: white;
}

.course-table button:not(.selected) {
  background-color: #007BFF;
  color: white;
}
</style>
