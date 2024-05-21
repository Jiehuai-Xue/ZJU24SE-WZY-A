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
      <tr v-for="(course, index) in selectedCourses" :key="course.details[0].id">
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
          <button @click="dropCourse(course.details[0].selectId)">退课</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
// import { addCourseSelect, deleteCourseSelect, getCourseByName } from '/src/api/courses.js';

export default {
  name: 'SelectedCourses',
  props: {
    selectedCoursesWrapper: {
      type: Array,
      required: true
    }
  },
  created() {
    this.selectedCourses = this.selectedCoursesWrapper;
    console.log("aaa" + JSON.stringify(this.selectedCoursesWrapper));
  },
  methods: {
    dropCourse(selectId) {
      //刪除
      let deleteData = {}
      deleteData.idList = [];
      deleteData.idList.push(selectId);
      deleteCourseSelect(deleteData).then(response => {
        this.$emit('update:selectedCourses', selectId);
        // this.$emit("update:selectedCourses", "remove");
      }).catch();
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
  background-color: #FF4C4C;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 3px;
  font-size: 14px;
}
</style>
