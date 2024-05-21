<template>
  <NavBar title="补选" />
  <MDBContainer>

    <MDBContainer style="margin-top: 1rem; margin-bottom: 3rem;">
      <MDBBtnGroup aria-label="Basic example">
        <MDBBtn color="primary" @click="setApplication()">申请管理</MDBBtn>
        <MDBBtn color="primary" @click="setAdd()">手动添加</MDBBtn>
      </MDBBtnGroup>
    </MDBContainer>

    <MDBContainer style="margin-top: 1rem;" v-if="module=='application'">
      <ApplicationManage />
    </MDBContainer>
    <MDBContainer style="margin-top: 1rem;" v-if="module=='add'">
      <AddStudent />
    </MDBContainer>

  </MDBContainer>
</template>

<script setup lang="ts">

import {ref} from 'vue';
import NavBar from "../components/NavBar.vue";
import {MDBContainer, MDBBtn, MDBInput, MDBRow, MDBBtnGroup} from "mdb-vue-ui-kit";
import DateSelector from "../components/DateSelector.vue";
import ApplicationManage from "../components/ApplicationManage.vue";
import AddStudent from "../components/AddStudent.vue";

const module = ref("application");
function setApplication() {
  module.value="application";
}
function setAdd() {
  module.value="add";
}
</script>


<style scoped>

</style>