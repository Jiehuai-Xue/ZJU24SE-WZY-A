<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <HomePage></HomePage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import HomePage from '@/components/HomePage.vue';
        export default {  
            name:'SysAdminView',
            components: {
                SideBar,
                HomePage,
            },
        data(){
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setChoice(1);
            this.$refs.sidebar.setPermission(1);
        }
    }
</script>

<style scoped>
.container {
    display: flex;
    background-color: rgb(255, 255, 255);
}

va-sidebar {
    width: 250px;
}

.content {
    flex: 1;
}
</style>