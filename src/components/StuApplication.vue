<template>
  <MDBContainer class="border border-primary" style="padding: 1rem;">
    <MDBRow>
      <MDBCol md="2">补选课程</MDBCol>
      <MDBCol md="2">软件工程</MDBCol>
      <MDBCol md="2">课程编号</MDBCol>
      <MDBCol md="2">21120491</MDBCol>
      <MDBCol md="2">课程类型</MDBCol>
      <MDBCol md="2">必修</MDBCol>
    </MDBRow>
    <MDBRow>
      <MDBCol md="2">教学班编号</MDBCol>
      <MDBCol md="2">XXX</MDBCol>
      <MDBCol md="2">教师</MDBCol>
      <MDBCol md="2">王五</MDBCol>
    </MDBRow>
    <MDBRow>
      潜在课程信息
    </MDBRow>
    <MDBRow>
      <MDBCol md="2">教室余量/容量</MDBCol>
      <MDBCol md="2">10/60</MDBCol>

      <MDBCol>
      </MDBCol>
    </MDBRow>
    <MDBRow class="row">
      <MDBCol md="2">申请内容</MDBCol>
<!--      <MDBContainer class="border border-primary" style="height: 15rem; width:96%;">-->
      <MDBTextarea label="请输入申请内容" rows="10"/>
<!--      </MDBContainer>-->
    </MDBRow>
  </MDBContainer>

</template>

<script setup lang="ts">
import {MDBBtn, MDBContainer, MDBRow, MDBCol, MDBTextarea} from "mdb-vue-ui-kit";
</script>


<style scoped>

</style>