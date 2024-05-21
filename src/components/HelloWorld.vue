<template>
  <MDBContainer>
    <div
      class="d-flex justify-content-center align-items-center"
      style="height: 100vh"
    >
      <div class="text-center">
        <img
          class="mb-4"
          src="https://mdbootstrap.com/img/logo/mdb-transparent-250px.png"
          style="width: 250px; height: 90px"
        />
        <h5 class="mb-3">{{ msg }}</h5>
        <p class="mb-3">MDB Team</p>
        <a
          class="btn btn-primary btn-lg"
          href="https://mdbootstrap.com/docs/vue/"
          target="_blank"
          role="button"
          >MDB VUE DOCS</a
        >
      </div>
    </div>
  </MDBContainer>
</template>

<script setup lang="ts">
import { MDBContainer } from "mdb-vue-ui-kit";

defineProps<{ msg: string }>();
</script>
