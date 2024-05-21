<template>
  <!--  <h1>Hello World!</h1>-->
  <NavBar title='查询课表' />
  <MDBContainer style="padding: 2rem;">
    <!--    <div class="p-3 mb-2 bg-primary bg-gradient text-white">培养方案</div>-->
    <!--    <FlexBox />-->
    <!--    <ButtonGroup />-->
    <!--    <Dropdowns />-->
    <Timetable />
  </MDBContainer>
  <!--  <router-view />-->
</template>

<script setup>
import AppHeader from '../components/AppHeader.vue'
import Timetable from '../components/TimeTable.vue'
import FlexBox from '../components/FlexBox.vue'
import ButtonGroup from '../components/ButtonGroup.vue'
import Dropdowns from '../components/Dropdowns.vue'
import NavBar from "../components/NavBar.vue";
import { MDBContainer, MDBCol, MDBRow, MDBCardGroup, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImg, MDBBtn } from "mdb-vue-ui-kit";
</script>

<style scoped>
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
}
</style>
