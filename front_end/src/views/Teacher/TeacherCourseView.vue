<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <TeacherCourseListPage :value_uid="id" :value_oid="id"></TeacherCourseListPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import HomePage from '@/components/HomePage.vue';
  import InfoPage from '@/components/InfoPage.vue';
  import TeacherCourseListPage from '@/components/3/TeacherCourseListPage.vue';
  import { mapState } from 'vuex';
        export default {  
            name:'TeacherCourseView',
            components: {
                SideBar, // 注册SideBar组件
                HomePage,
                InfoPage,
                TeacherCourseListPage
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
            this.$refs.sidebar.setPermission(3);
            this.$refs.sidebar.setChoice(7);
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