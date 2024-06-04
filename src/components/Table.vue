<template>
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
      <td>
        <MDBBtn outline="primary" rounded size="sm" @click="c1=!c1" v-if="c1">添加</MDBBtn>
        <MDBBtn color="danger" rounded size="sm" @click="c1=!c1" v-if="!c1">移除</MDBBtn>
      </td>
<!--      <td><Switch/></td>-->
    </tr>
    <tr>
      <th scope="row">软件工程</th>
      <td>21120492</td>
      <td>必修课程</td>
      <td>无方向</td>
      <td>2.5</td>
      <td>
        <MDBBtn outline="primary" rounded size="sm" @click="c2=!c2" v-if="c2">添加</MDBBtn>
        <MDBBtn color="danger" rounded size="sm" @click="c2=!c2" v-if="!c2">移除</MDBBtn>
      </td>
    </tr>
    <tr>
      <th scope="row">计算机组成</th>
      <td>21120493</td>
      <td>必修课程</td>
      <td>无方向</td>
      <td>4.5</td>
      <td>
        <MDBBtn outline="primary" rounded size="sm" @click="c3=!c3" v-if="c3">添加</MDBBtn>
        <MDBBtn color="danger" rounded size="sm" @click="c3=!c3" v-if="!c3">移除</MDBBtn>
      </td>
    </tr>
    </tbody>
  </MDBTable>
</template>
<script setup lang="ts">
import {
  MDBTable, MDBBtn
} from 'mdb-vue-ui-kit';
import Switch from "./Switch.vue";
import Application from "./Application.vue";
import {ref} from 'vue'
const c1=ref(true)
const c2=ref(true)
const c3=ref(false)

</script>