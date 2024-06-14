<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <LogPage></LogPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import LogPage from '@/components/1/LogPage.vue';
        export default {  
            name:'SysAdminInfoView',
            components: {
                SideBar,
                LogPage,
            },
        data(){
            return{
                choice: null,
                logs:[]
            };
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setChoice(3);
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