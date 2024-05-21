<template>
  <h1>Application</h1>
  <MDBTable>
    <thead>
    <tr>
      <th scope="col">课程代码</th>
      <th scope="col">课程名称</th>
      <th scope="col">选必修</th>
      <th scope="col">学生姓名</th>
      <th scope="col">学生学号</th>
      <th scope="col">申请内容</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>21120491</td>
      <th scope="row">高级数据结构</th>
      <td>必修课程</td>
      <td>张三</td>
      <td>3210109999</td>
      <td><MDBBtn color="primary" size="sm" @click="showInfo()">查看</MDBBtn></td>
    </tr>
    <tr>
      <td>21120491</td>
      <th scope="row">高级数据结构</th>
      <td>必修课程</td>
      <td>张三</td>
      <td>3210109999</td>
      <td><MDBBtn color="primary" size="sm">查看</MDBBtn></td>
    </tr>
    </tbody>
  </MDBTable>

  <MDBContainer
      class="border border-primary"
      style="
        height: 60%; width: 60%; padding: 2rem;
        position: fixed; top: 20%; left: 20%; background-color: white;
        text-align: center;
      "
      v-if="show"
  >
    <Application />
    <MDBBtn color="primary" @click="closeInfo">关闭</MDBBtn>
  </MDBContainer>

</template>

<script setup lang="ts">

import {MDBTable, MDBBtn, MDBContainer} from "mdb-vue-ui-kit";
import Switch from "./Switch.vue";
import Application from "./Application.vue";
import {ref} from "vue";

const show = ref(false);

function showInfo() {
  show.value = true;
}
function closeInfo() {
  show.value = false;
}
</script>

<style scoped>
</style>