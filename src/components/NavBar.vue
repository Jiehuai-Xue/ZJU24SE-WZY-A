<template>
  <MDBNavbar expand="lg" light bg="light" container class="bg-danger">
    <MDBNavbarBrand href="#">本科教学管理信息服务平台</MDBNavbarBrand>
    <MDBNavbarToggler
        @click="collapse1 = !collapse1"
        target="#navbarSupportedContent"
    ></MDBNavbarToggler>
    <MDBCollapse v-model="collapse1" id="navbarSupportedContent">
      <MDBNavbarNav class="mb-2 mb-lg-0">
        <MDBNavbarItem to="#" active>
          {{ props.title }}
        </MDBNavbarItem>
<!--        <MDBNavbarItem href="#">-->
<!--          Link-->
<!--        </MDBNavbarItem>-->
<!--        <MDBNavbarItem>-->
<!--          &lt;!&ndash; Navbar dropdown &ndash;&gt;-->
<!--          <MDBDropdown class="nav-item" v-model="dropdown1">-->
<!--            <MDBDropdownToggle-->
<!--                tag="a"-->
<!--                class="nav-link"-->
<!--                @click="dropdown1 = !dropdown1"-->
<!--            >Dropdown</MDBDropdownToggle-->
<!--            >-->
<!--            <MDBDropdownMenu aria-labelledby="dropdownMenuButton">-->
<!--              <MDBDropdownItem href="#">Action</MDBDropdownItem>-->
<!--              <MDBDropdownItem href="#">Another Action</MDBDropdownItem>-->
<!--              <MDBDropdownItem href="#"-->
<!--              >Something else here</MDBDropdownItem-->
<!--              >-->
<!--            </MDBDropdownMenu>-->
<!--          </MDBDropdown>-->
<!--        </MDBNavbarItem>-->
<!--        <MDBNavbarItem to="#" disabled>-->
<!--          Disabled-->
<!--        </MDBNavbarItem>-->
      </MDBNavbarNav>
      <!-- Search form -->
<!--      <form class="d-flex input-group w-auto">-->
<!--        <input-->
<!--            type="search"-->
<!--            class="form-control"-->
<!--            placeholder="Type query"-->
<!--            aria-label="Search"-->
<!--        />-->
<!--        <MDBBtn outline="primary">-->
<!--          Search-->
<!--        </MDBBtn>-->
<!--      </form>-->
      <Avatar/>
    </MDBCollapse>
  </MDBNavbar>
</template>

<script setup lang="ts">
import {
  MDBBtn,
  MDBNavbar,
  MDBNavbarToggler,
  MDBNavbarBrand,
  MDBNavbarNav,
  MDBNavbarItem,
  MDBCollapse,
  MDBDropdown,
  MDBDropdownToggle,
  MDBDropdownMenu,
  MDBDropdownItem
} from 'mdb-vue-ui-kit';
import { ref } from 'vue';
import Avatar from "./Avatar.vue";

const collapse1 = ref(false);
const dropdown1 = ref(false);

const props = defineProps(['title'])

</script>