<template>
  <!-- Primary -->
  <MDBDropdown btnGroup v-model="dropdown3">
    <MDBDropdownToggle @click="dropdown3 = !dropdown3">
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
  <!-- Success -->
  <MDBDropdown btnGroup v-model="dropdown5">
    <MDBDropdownToggle
        @click="dropdown5 = !dropdown5"
        color="success"
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
  <!-- Info -->
  <MDBDropdown btnGroup v-model="dropdown6">
    <MDBDropdownToggle @click="dropdown6 = !dropdown6" color="info">
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
  <!-- Warning -->
  <MDBDropdown btnGroup v-model="dropdown7">
    <MDBDropdownToggle
        @click="dropdown7 = !dropdown7"
        color="warning"
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
  <!-- Danger -->
  <MDBDropdown btnGroup v-model="dropdown8">
    <MDBDropdownToggle @click="dropdown8 = !dropdown8" color="danger">
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
  <!-- Dark -->
  <MDBDropdown btnGroup v-model="dropdown9">
    <MDBDropdownToggle @click="dropdown9 = !dropdown9" color="dark">
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
  <!-- Light -->
  <MDBDropdown btnGroup v-model="dropdown10">
    <MDBDropdownToggle
        @click="dropdown10 = !dropdown10"
        color="light"
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
  <!-- Link -->
  <MDBDropdown btnGroup v-model="dropdown11">
    <MDBDropdownToggle @click="dropdown11 = !dropdown11" color="link">
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