<template>
  <div style="position: fixed; right: 1rem; bottom: 1rem;">
    <MDBBtn outline="success" floating @click="show_chat()" v-if="!show">
      <MDBIcon icon="star"></MDBIcon>
    </MDBBtn>
    <MDBBtn color="success" floating @click="show_chat()" v-if="show">
      <MDBIcon icon="star"></MDBIcon>
    </MDBBtn>
  </div>

  <MDBContainer
      class="border border-primary"
      style="
        z-index: 999;
        height: 60%; width: 60%; padding: 2rem;
        position: fixed; top: 20%; left: 20%; background-color: white;
        text-align: center;
      "
      background-color="white"
      v-if="show"
  >
    <h2> 你好，我是智能助手 </h2>
    <div style="position: absolute; bottom: 1rem; left: 1rem; right: 1rem;">
      <MDBInput label="请输入"/>
    </div>

  </MDBContainer>
</template>

<script setup lang="ts">
import {MDBContainer, MDBBtn, MDBIcon, MDBInput} from 'mdb-vue-ui-kit'
import {ref} from "vue";

const show = ref(false);
function show_chat() {
  show.value = !show.value;
}

</script>

<style scoped>

</style>