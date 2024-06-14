<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <StudentCourseDetailPage></StudentCourseDetailPage>
        </div>
    </div>
</template>  
    
<script>
    import SideBar from '@/components/SideBar.vue';
    import StudentCourseDetailPage from '@/components/4/StudentCourseDetailPage.vue';
        export default {  
            name:'StudentCourseDetailView',
            components: {
                SideBar, // 注册SideBar组件
                StudentCourseDetailPage
            },
        data(){
            return{
                choice: null
            };
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setPermission(4);
            this.$refs.sidebar.setHighlight(8);
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