<template>
  <MDBContainer class="d-flex flex-column align-items-center mt-4">
    <MDBRow class="w-100 justify-content-center">
      <MDBCol md="12">
        <MDBCard text="center">
          <MDBCardBody>
            <form @submit.prevent="handleSubmit">
              <MDBRow class="align-items-center justify-content-center">
                <MDBCol md="4" class="d-flex align-items-center justify-content-center">
                  <label for="academicYear" class="form-label mb-0 me-2">学年</label>
                  <select id="academicYear" v-model="selectedYear" class="form-select">
                    <option v-for="year in academicYears" :key="year" :value="year">{{ year }}</option>
                  </select>
                </MDBCol>
                <MDBCol md="4" class="d-flex align-items-center justify-content-center">
                  <label for="term" class="form-label mb-0 me-2">学期</label>
                  <select id="term" v-model="selectedTerm" class="form-select">
                    <option v-for="term in terms" :key="term" :value="term">{{ term }}</option>
                  </select>
                </MDBCol>
                <MDBCol md="4" class="d-flex justify-content-center align-items-center">
                  <MDBBtn :color="view === 'table' ? 'primary' : 'light'" @click="switchView('table')">表格</MDBBtn>
                  <MDBBtn :color="view === 'list' ? 'primary' : 'light'" @click="switchView('list')" class="ms-2">列表</MDBBtn>
                </MDBCol>
              </MDBRow>
            </form>
          </MDBCardBody>
        </MDBCard>
      </MDBCol>
    </MDBRow>
    <MDBRow class="w-100 justify-content-center mt-4">
      <MDBCol md="12">
        <h3 class="text-center">{{ selectedYear }} 学年 {{ selectedTerm }} 学期张三的课表</h3>
        <div v-if="view === 'table'">
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead>
              <tr>
                <th scope="col" rowspan="2">时间</th>
                <th scope="col" colspan="2">星期一</th>
                <th scope="col" colspan="2">星期二</th>
                <th scope="col" colspan="2">星期三</th>
                <th scope="col" colspan="2">星期四</th>
                <th scope="col" colspan="2">星期五</th>
                <th scope="col" colspan="2">星期六</th>
                <th scope="col" colspan="2">星期日</th>
              </tr>
              <tr>
                <th scope="col">单</th>
                <th scope="col">双</th>
                <th scope="col">单</th>
                <th scope="col">双</th>
                <th scope="col">单</th>
                <th scope="col">双</th>
                <th scope="col">单</th>
                <th scope="col">双</th>
                <th scope="col">单</th>
                <th scope="col">双</th>
                <th scope="col">单</th>
                <th scope="col">双</th>
                <th scope="col">单</th>
                <th scope="col">双</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(row, index) in timetable" :key="index">
                <td>{{ row.time }}</td>
                <td v-for="(day, i) in row.days" :key="i" :colspan="day.colspan || 1" v-html="day.content"></td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div v-else-if="view === 'list'">
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead>
              <tr>
                <th>课程代码</th>
                <th>课程名称</th>
                <th>教师姓名</th>
                <th>学期</th>
                <th>上课时间</th>
                <th>上课地点</th>
                <th>选课时间</th>
                <th>选课志愿</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(course, index) in courseList" :key="index">
                <td>{{ course.code }}</td>
                <td>{{ course.name }}</td>
                <td>{{ course.teacher }}</td>
                <td>{{ course.term }}</td>
                <td v-html="course.time"></td>
                <td v-html="course.location"></td>
                <td>{{ course.selectionTime }}</td>
                <td>{{ course.preference }}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </MDBCol>
    </MDBRow>
  </MDBContainer>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { MDBContainer, MDBRow, MDBCol, MDBCard, MDBCardBody, MDBBtn } from 'mdb-vue-ui-kit';

const academicYears = ref(['2022-2023', '2023-2024', '2024-2025']);
const terms = ref(['春', '夏', '秋', '冬']);

const selectedYear = ref(academicYears.value[0]);
const selectedTerm = ref(terms.value[0]);
const view = ref('table');

const switchView = (newView: string) => {
  view.value = newView;
};

const handleSubmit = () => {
  // Handle form submission logic here, if needed
  console.log('Selected Year:', selectedYear.value);
  console.log('Selected Term:', selectedTerm.value);
};

// Timetable data as an example, should be replaced with actual data
const timetable = ref([
  {
    time: '1',
    days: [
      { content: '' }, { content: '' },
      { content: '<div>编译原理<br>春夏(第1-8周|2节/单周<br>刘老师<br>玉泉曹光彪大楼西楼-104<br>2024年06月30日(10:30-12:30)</div>' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '2',
    days: [
      { content: '' }, { content: '' },
      { content: '<div>编译原理<br>春夏(第1-8周|2节/单周<br>刘老师<br>玉泉曹光彪大楼西楼-104<br>2024年06月30日(10:30-12:30)</div>' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '3',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '4',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '5',
    days: [
      { content: '<div>软件工程<br>春夏(第1-8周|2节/周)<br>王老师<br>玉泉曹光彪大楼西楼-104<br>2024年06月27日(10:30-12:30)</div>', colspan: 2 },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '6',
    days: [
      { content: '<div>软件工程<br>春夏(第1-8周|2节/周)<br>王老师<br>玉泉曹光彪大楼西楼-104<br>2024年06月27日(10:30-12:30)</div>', colspan: 2 },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '7',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '8',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '9',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '10',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '<div>人工智能<br>春夏(第1-8周|2节/双周<br>陈老师<br>玉泉曹光彪大楼西楼-104<br>2024年06月29日(10:30-12:30)</div>' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '11',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '<div>人工智能<br>春夏(第1-8周|2节/双周<br>陈老师<br>玉泉曹光彪大楼西楼-104<br>2024年06月29日(10:30-12:30)</div>' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '12',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  },
  {
    time: '13',
    days: [
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' },
      { content: '' }, { content: '' }
    ]
  }
  // Add all other rows similarly
]);

// Course list data as an example, should be replaced with actual data
const courseList = ref([
  {
    code: '21120261',
    name: '软件工程',
    teacher: '王老师',
    term: '春夏',
    time: '周一第5,6节',
    location: '玉泉曹光彪大楼西楼-104',
    selectionTime: '2023-12-18 11:00:56',
    preference: '1'
  },
  {
    code: '21120471',
    name: '编译原理',
    teacher: '刘老师',
    term: '春夏',
    time: '周二第1,2节(单周)',
    location: '玉泉曹光彪大楼西楼-104',
    selectionTime: '2023-12-18 11:10:56',
    preference: '1'
  },
  {
    code: '21191890',
    name: '人工智能',
    teacher: '陈老师',
    term: '春夏',
    time: '周四第10,11节(双周)',
    location: '玉泉曹光彪大楼西楼-104',
    selectionTime: '2023-12-22 20:22:52',
    preference: '1'
  }
  // Add all other courses similarly
]);
</script>

<style>
.form-label {
  font-weight: bold;
  margin-right: 0.5rem;
  white-space: nowrap; /* Prevents the label from breaking into multiple lines */
}

.mb-0 {
  margin-bottom: 0;
}

.me-2 {
  margin-right: 0.5rem; /* Adjust the space as needed */
}

.ms-2 {
  margin-left: 0.5rem; /* Adjust the space between buttons as needed */
}

.d-flex {
  display: flex;
  align-items: center;
}

.table-responsive {
  max-width: 100%;
  overflow-x: auto;
}

.table-bordered {
  border: 1px solid #dee2e6;
}

.table th,
.table td {
  vertical-align: middle;
  text-align: center;
  white-space: pre-line; /* To keep newlines in the content */
}
</style>
