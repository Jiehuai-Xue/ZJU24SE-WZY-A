<template>
  <NavBar :title="title" />
  <MDBContainer style="padding: 10rem">
    <Entries :id="userId" />
  </MDBContainer>
</template>

<script setup lang="ts">

import NavBar from "../components/NavBar.vue";
import { MDBContainer } from 'mdb-vue-ui-kit';
import { ref } from 'vue';
import Entries from "../components/Entries.vue";

// const userInfo = getUserInfo();
const userId = "student";
// const userId = 'admin';
// const userId = 'teacher';

const get_title = function(x:String) {
  if (x=='student'){
    return "你好，张三同学！"
  }
  if (x=='admin'){
    return "你好，管理员李四"
  }
  if (x=='teacher'){
    return "你好，老师王五"
  }

}

let title = get_title(userId);

</script>

<style scoped>

</style>