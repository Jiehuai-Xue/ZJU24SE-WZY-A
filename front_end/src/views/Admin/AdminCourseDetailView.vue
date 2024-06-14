<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <AdminCourseDetailPage></AdminCourseDetailPage>
        </div>
    </div>
</template>  
    
<script>
    import SideBar from '@/components/SideBar.vue';
    import AdminCourseDetailPage from '@/components/2/AdminCourseDetailPage.vue';
        export default {  
            name:'AdminrCourseDetailView',
            components: {
                SideBar, 
                AdminCourseDetailPage
            },
        data(){
            return{
                choice: null
            };
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setPermission(2);
            this.$refs.sidebar.setHighlight(5);
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