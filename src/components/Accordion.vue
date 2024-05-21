<template>
    <MDBContainer>
      <section class="text-center">
        <div class="d-flex flex-row mb-3">
          <div class="col">
<!--          <div class="col-lg-2 col-md-6 mb-5 mb-md-5 mb-lg-0 position-relative">-->
<!--            <i class="fas fa-cubes fa-3x text-primary mb-4"></i>-->
            <h5 class="text-primary fw-bold mb-3">学号</h5>
            <h6 class="fw-normal mb-0">3210109999</h6>
            <div class="vr vr-blurry position-absolute my-0 h-100 d-none d-md-block top-0 end-0"></div>
          </div>

          <div class="col">
<!--          <div class="col-lg-2 col-md-6 mb-5 mb-md-0 position-relative">-->
<!--            <i class="fas fa-layer-group fa-3x text-primary mb-4"></i>-->
            <h5 class="text-primary fw-bold mb-3">姓名</h5>
            <h6 class="fw-normal mb-0">张三</h6>
            <div class="vr vr-blurry position-absolute my-0 h-100 d-none d-md-block top-0 end-0"></div>
          </div>

          <div class="col">
            <h5 class="text-primary fw-bold mb-3">学院</h5>
            <h6 class="fw-normal mb-0">计算机科学与技术学院</h6>
          </div>

          <div class="col">
            <h5 class="text-primary fw-bold mb-3">专业</h5>
            <h6 class="fw-normal mb-0">软件工程</h6>
          </div>

          <div class="col">
            <h5 class="text-primary fw-bold mb-3">当前学分</h5>
            <h6 class="fw-normal mb-0">120.5+6+6+4</h6>
          </div>
        </div>
      </section>
    </MDBContainer>

  <MDBAccordion v-model="activeItem" style="margin-top: 2rem">
    <MDBAccordionItem
        headerTitle="总学分要求"
        collapseId="collapseOne"
        class="border border-2 border-primary"
    >
      你一共需要获得150+4+2+2的学分
    </MDBAccordionItem>
    <MDBAccordionItem
        headerTitle="思政类 - 必修课程"
        collapseId="collapseTwo"
        class="border border-2 border-primary border-top-0"
    >
      <Table />
<!--        <div class="text-primary">This is default primary text</div>-->
<!--        <div class="bg-success p-2 text-white">This is default success background</div>-->
<!--        <div class="bg-success p-2" style="&#45;&#45;mdb-bg-opacity: 0.5;">This is 50% opacity success background</div>-->
    </MDBAccordionItem>
    <MDBAccordionItem
        headerTitle="专业必修课程"
        collapseId="collapseThree"
        class="border border-2 border-primary border-top-0"
    >
      <Table />
    </MDBAccordionItem>
    <MDBAccordionItem
        headerTitle="专业选修课程"
        collapseId="collapseFour"
        class="border border-2 border-primary border-top-0"
    >
      <Table />
    </MDBAccordionItem>
  </MDBAccordion>
</template>
<script setup lang="ts">
import Table from './Table.vue'
import { MDBAccordion, MDBAccordionItem, MDBContainer } from "mdb-vue-ui-kit";
import { ref } from "vue";

const activeItem = ref('collapseOne');
</script>
<style>
</style>