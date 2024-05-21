<template>
<!--  <Plan />-->
<!--  <MainPage />-->

  <!--  <SetTime />-->

<!--  <strong>Current route path:</strong> {{ $route.fullPath }}-->
  <router-view />
</template>

<script setup>
import AppHeader from './components/AppHeader.vue'
import Accordion from './components/Accordion.vue'
import FlexBox from './components/FlexBox.vue'
import ButtonGroup from './components/ButtonGroup.vue'
import Dropdowns from './components/Dropdowns.vue'
import Plan from "./views/Stu-Plan.vue"
import MainPage from "./views/MainPage.vue"
import SetTime from "./views/Admin-SetTime.vue";
</script>

<style scoped>
#app {
  font-family: Roboto, Helvetica, Arial, sans-serif;
}
</style>
