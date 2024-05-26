<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <CourseSelectionListPage :value_uid="id" :value_oid="id"></CourseSelectionListPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import { mapState } from 'vuex';
  import CourseSelectionListPage from '@/components/4/CourseSelectionListPage.vue';
        export default {  
            name:'StudentCourseView',
            components: {
                SideBar, // 注册SideBar组件
                CourseSelectionListPage
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
            this.$refs.sidebar.setChoice(8);
            this.$refs.sidebar.setPermission(4);
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