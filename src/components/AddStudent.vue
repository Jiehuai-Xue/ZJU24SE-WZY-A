<template>
  <h2>
    手动添加补选
  </h2>

  <MDBContainer>
    <div class="w-25 p-3 d-inline-block">
      <MDBInput label="请输入课程代码" v-model="inputCourseCode" />
    </div>
    <div class="w-25 p-3 d-inline-block">
      <MDBBtn color="primary" size="sm" @click="queryCourse()">查询</MDBBtn>
    </div>
  </MDBContainer>

  <MDBContainer>
    <MDBTable>
      <thead>
      <tr>
        <th scope="col" style="text-align: center;">无符合条件课程！</th>
      </tr>
      </thead>
    </MDBTable>
    <MDBTable>
      <thead>
      <tr>
        <th scope="col">课程名称</th>
        <th scope="col">课程代码</th>
        <th scope="col">选必修</th>
        <th scope="col">专业方向</th>
        <th scope="col">学分</th>
        <th scope="col">是否添加</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <th scope="row">高级数据结构</th>
        <td>21120491</td>
        <td>必修课程</td>
        <td>无方向</td>
        <td>4.0</td>
        <td><Switch/></td>
      </tr>
      <tr>
        <th scope="row">软件工程</th>
        <td>21120492</td>
        <td>必修课程</td>
        <td>无方向</td>
        <td>2.5</td>
        <td>
          <MDBBtn outline="primary" rounded size="sm">添加</MDBBtn>
        </td>
      </tr>
      <tr>
        <th scope="row">计算机组成</th>
        <td>21120493</td>
        <td>必修课程</td>
        <td>无方向</td>
        <td>4.5</td>
        <td>
          <MDBBtn color="danger" rounded size="sm">移除</MDBBtn>
        </td>
      </tr>
      </tbody>
    </MDBTable>

  </MDBContainer>
</template>

<script setup lang="ts">
import {MDBTable, MDBContainer, MDBBtn, MDBInput} from "mdb-vue-ui-kit"
import Switch from "./Switch.vue";
import { ref } from 'vue';

const inputCourseCode = ref('');

const courseList = ref([])

function queryCourse() {
  if (inputCourseCode.value=='21120492') {
    let x = [];
    let y = new Map([
        ['courseName', '软件工程'],
        ['courseId', '21120492'],
        ['teacherName', '王五'],
        ['选必修', '必修课程'],
        ['学分', '2.5']
    ]);
    x.push(y);
    y = new Map([
      ['courseName', '软件工程'],
      ['courseId', '21120492'],
      ['teacherName', '赵六'],
      ['选必修', '必修课程'],
      ['学分', '2.5']
    ]);
    x.push(y);
  }
}

</script>

<style scoped>

</style>