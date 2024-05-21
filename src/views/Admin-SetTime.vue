<template>
  <NavBar title="设置选课时间"/>
  <MDBContainer style="padding: 10rem">

    <MDBContainer style="padding: 2rem">
      <h1 class="text-dark fw-bold mb-3">
        管理员李四，你好，请设置选课时间！
      </h1>
    </MDBContainer>


    <MDBContainer style="padding: 1rem;">
      <div>
        <h3 class="text-primary fw-bold mb-3 d-inline-block">
          选课开始时间:
        </h3>
        <div class="w-25 p-3 d-inline-block">
          <MDBInput label="年" v-model="input1" />
        </div>
        <div class="w-25 p-3 d-inline-block">
          <MDBInput label="月" v-model="input2" />
        </div>
        <div class="w-25 p-3 d-inline-block">
          <MDBInput label="日" v-model="input3" />
        </div>
      </div>
    </MDBContainer>
    <MDBContainer style="padding: 1rem;">
      <div>
        <h3 class="text-primary fw-bold mb-3 d-inline-block">
          选课结束时间:
        </h3>
        <div class="w-25 p-3 d-inline-block">
          <MDBInput label="年" v-model="input1" />
        </div>
        <div class="w-25 p-3 d-inline-block">
          <MDBInput label="月" v-model="input2" />
        </div>
        <div class="w-25 p-3 d-inline-block">
          <MDBInput label="日" v-model="input3" />
        </div>
      </div>
    </MDBContainer>



    <MDBContainer style="padding-right: 8rem; text-align: right;">
      <MDBBtn tag="a" href="#!" color="primary" >设置</MDBBtn>
    </MDBContainer>

  </MDBContainer>
</template>

<script setup lang="ts">

import NavBar from "../components/NavBar.vue";
import {MDBContainer, MDBBtn, MDBInput, MDBRow} from "mdb-vue-ui-kit";
import DateSelector from "../components/DateSelector.vue";
</script>


<style scoped>

</style>