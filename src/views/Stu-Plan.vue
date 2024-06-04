<template>
<!--  <h1>Hello World!</h1>-->
  <NavBar title='制定培养方案' />
  <MDBContainer style="padding: 2rem;">
<!--    <div class="p-3 mb-2 bg-primary bg-gradient text-white">培养方案</div>-->
<!--    <FlexBox />-->
<!--    <ButtonGroup />-->
<!--    <Dropdowns />-->
    <Accordion />
  </MDBContainer>
  <AiBot />
  <!--  <router-view />-->
</template>

<script setup>
import AppHeader from '../components/AppHeader.vue'
import Accordion from '../components/Accordion.vue'
import FlexBox from '../components/FlexBox.vue'
import ButtonGroup from '../components/ButtonGroup.vue'
import Dropdowns from '../components/Dropdowns.vue'
import NavBar from "../components/NavBar.vue";
import AiBot from "../components/AiBot.vue";
import { MDBContainer, MDBCol, MDBRow, MDBCardGroup, MDBCard, MDBCardBody, MDBCardTitle, MDBCardText, MDBCardImg, MDBBtn } from "mdb-vue-ui-kit";
</script>

<style scoped>
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
}
</style>
