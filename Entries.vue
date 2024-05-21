<template>
  <MDBRow :cols="['1', 'md-4']" class="g-4" v-if="id=='student'">
<!--    student-->
    <MDBCol>
      <MDBCard text="center">
        <!--        <div class="ratio ratio-1x1" style="width: 70%; align-self: center">-->
        <!--          <MDBCardImg-->
        <!--            class="rounded-circle"-->
        <!--            style="object-fit: cover;"-->
        <!--            src="https://mdbootstrap.com/img/new/standard/city/041.webp"-->
        <!--            top-->
        <!--            alt="..."-->
        <!--          />-->
        <!--        </div>-->
        <MDBCardBody>
          <i class="fas fa-user-pen fa-5x" style="padding: 2rem" />
          <!--          <MDBCardTitle>Card title</MDBCardTitle>-->
          <MDBBtn tag="a" href="#/stu/select" color="primary" >自主选课</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
    <MDBCol>
      <MDBCard text="center">
        <!--        <div class="ratio ratio-1x1" style="width: 70%; align-self: center">-->
        <!--          <MDBCardImg-->
        <!--              src="https://mdbootstrap.com/img/new/standard/city/042.webp"-->
        <!--              top-->
        <!--              alt="..."-->
        <!--          />-->
        <!--        </div>-->
        <MDBCardBody>
          <i class="fas fa-address-book fa-5x" style="padding: 2rem"/>
          <MDBBtn tag="a" href="#stu/plan" color="primary" >培养方案</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
    <MDBCol>
      <MDBCard text="center">
        <MDBCardBody>
          <i class="fas fa-calendar-days fa-5x" style="padding: 2rem" />
          <MDBBtn tag="a" href="#stu/query" color="primary">查询课表</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
    <MDBCol>
      <MDBCard text="center">
        <MDBCardBody>
          <i class="fas fa-address-book fa-5x" style="padding: 2rem"/>
          <MDBBtn tag="a" href="#!" color="dark" >未知</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
  </MDBRow>


  <MDBRow :cols="['1', 'md-4']" class="g-4" v-if="id=='admin'">
<!--    teacher-->
    <MDBCol>
      <MDBCard text="center">
        <MDBCardBody>
          <i class="fas fa-user-pen fa-5x" style="padding: 2rem" />
          <!--          <MDBCardTitle>Card title</MDBCardTitle>-->
          <MDBBtn tag="a" href="#admin/settime" color="primary" >设置时间</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
    <MDBCol>
      <MDBCard text="center">
        <MDBCardBody>
          <i class="fas fa-address-book fa-5x" style="padding: 2rem"/>
          <MDBBtn tag="a" href="#admin/byelection" color="primary" >补选管理</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
  </MDBRow>

  <MDBRow :cols="['1', 'md-4']" class="g-4" v-if="id=='teacher'">
    <!--    teacher-->
    <MDBCol>
      <MDBCard text="center">
        <MDBCardBody>
          <i class="fas fa-user-pen fa-5x" style="padding: 2rem" />
          <!--          <MDBCardTitle>Card title</MDBCardTitle>-->
          <MDBBtn tag="a" href="#teacher/schedule" color="primary" >查询课表</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
  </MDBRow>
</template>

<script setup lang="ts">

import { MDBCol, MDBRow, MDBCardGroup, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImg, MDBBtn } from "mdb-vue-ui-kit";

const props = defineProps(['id'])

console.log(props.id)
</script>


<style>

</style>