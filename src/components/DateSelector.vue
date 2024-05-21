<template>
  <!-- Primary -->
  <MDBDropdown btnGroup v-model="dropdown3">
    <MDBDropdownToggle @click="dropdown3 = !dropdown3">
      年
    </MDBDropdownToggle>
    <MDBDropdownMenu>
      <MDBDropdownItem href="#">2024</MDBDropdownItem>
      <MDBDropdownItem href="#">2023</MDBDropdownItem>
      <MDBDropdownItem href="#">Something else here</MDBDropdownItem>
      <MDBDropdownItem divider />
      <MDBDropdownItem href="#">Separated link</MDBDropdownItem>
    </MDBDropdownMenu>
  </MDBDropdown>
  <!-- Secondary -->
  <MDBDropdown btnGroup v-model="dropdown4">
    <MDBDropdownToggle
        @click="dropdown4 = !dropdown4"
        color="secondary"
    >
      Action
    </MDBDropdownToggle>
    <MDBDropdownMenu>
      <MDBDropdownItem href="#">Action</MDBDropdownItem>
      <MDBDropdownItem href="#">Another Action</MDBDropdownItem>
      <MDBDropdownItem href="#">Something else here</MDBDropdownItem>
      <MDBDropdownItem divider />
      <MDBDropdownItem href="#">Separated link</MDBDropdownItem>
    </MDBDropdownMenu>
  </MDBDropdown>
  <!-- Secondary -->
  <MDBDropdown btnGroup v-model="dropdown5">
    <MDBDropdownToggle
        @click="dropdown5 = !dropdown5"
        color="secondary"
    >
      Action
    </MDBDropdownToggle>
    <MDBDropdownMenu>
      <MDBDropdownItem href="#">Action</MDBDropdownItem>
      <MDBDropdownItem href="#">Another Action</MDBDropdownItem>
      <MDBDropdownItem href="#">Something else here</MDBDropdownItem>
<!--      <MDBDropdownItem divider />-->
      <MDBDropdownItem href="#">Separated link</MDBDropdownItem>
    </MDBDropdownMenu>
  </MDBDropdown>
</template>

<script setup lang="ts">
import { MDBDropdown, MDBDropdownToggle, MDBDropdownMenu, MDBDropdownItem } from "mdb-vue-ui-kit";
import { ref } from 'vue';

const dropdown3 = ref(false);
const dropdown4 = ref(false);
const dropdown5 = ref(false);
const dropdown6 = ref(false);
const dropdown7 = ref(false);
const dropdown8 = ref(false);
const dropdown9 = ref(false);
const dropdown10 = ref(false);
const dropdown11 = ref(false);
const dropdown12 = ref(false);
</script>