<template>
  <MDBSwitch
    label=""
    v-model="switch1"
  />
</template>


<script setup lang="ts">
import { MDBSwitch } from "mdb-vue-ui-kit";
import { ref } from "vue";

const switch1 = ref(false);
</script>