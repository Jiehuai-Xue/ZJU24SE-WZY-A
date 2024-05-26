<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <TeacherCourseDetailPage></TeacherCourseDetailPage>
        </div>
    </div>
</template>  
    
<script>
    import SideBar from '@/components/SideBar.vue';
    import TeacherCourseDetailPage from '@/components/3/TeacherCourseDetailPage.vue';
        export default {  
            name:'TeacherCourseDetailView',
            components: {
                SideBar, 
                TeacherCourseDetailPage
            },
        data(){
            return{
                choice: null
            };
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setPermission(3);
            this.$refs.sidebar.setHighlight(7);
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