<template>
  <NavBar title="自主选课"/>
  <CourseNavigation @select:selectedNavigation="selectedNavigation" />
  <CourseDetails @update:courses="updateCourses" :courseType="courseType" :courses="courses"
                 :expandedCourseWrapper="expandedCourseWrapper" :key='itemKey' />
  <SelectedCourses v-if="courseType == '选课结果'" @update:selectedCourses="updateSelectedCourses"
                   :selectedCoursesWrapper="selectedCoursesWrapper" :key='itemKey' />
</template>


<script>
import NavBar from "../components/NavBar.vue";
import CourseNavigation from '../components/CourseNavigation.vue';
import CourseDetails from '../components/CourseDetails.vue';
import SelectedCourses from '../components/SelectedCourses.vue';

import { getCourse, getCourseBySelect } from '/src/api/courses.js';

export default {
  name: 'App',
  components: {
    NavBar,
    CourseNavigation,
    CourseDetails,
    SelectedCourses
  },
  created() {
    this.getList();
  },
  data() {
    return {
      selectedCoursesWrapper: [],
      courseType: "专业必修课",
      courses: [],
      expandedCourse: '',
      itemKey: 0
    };
  },
  methods: {
    getList() {
      // getCourse(this.courseType).then(response => {
      //   this.courses = response.result;
      //   getCourseBySelect(this.courseType).then(response => {
      //     debugger
      //     this.selectedCoursesWrapper = response.result;
      //     this.itemKey++;
      //   }).catch();
      // }).catch();
    },
    updateCourses(data) {
      // this.expandedCourseWrapper = data.expandedCourseWrapper;
      // this.getList();
    },
    updateSelectedCourses(selectId) {
      // debugger
      // this.getList();
    },
    selectedNavigation(courseType) {
      // this.courseType = courseType;
      //
      // this.getList();
    }
  }
};
</script>

<style scoped>

</style>