<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <AdminCourseListPage :value_uid="id"></AdminCourseListPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import AdminCourseListPage from '@/components/2/AdminCourseListPage.vue';
  import { mapState } from 'vuex';
        export default {  
            name:'SysAdminCourseView',
            components: {
                SideBar, // 注册SideBar组件
                AdminCourseListPage
            },
        data(){
            return{
                choice: null
            };
        },
        computed: {
            ...mapState(['id']), // 映射state中的id到本组件的computed属性id
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setChoice(5);
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