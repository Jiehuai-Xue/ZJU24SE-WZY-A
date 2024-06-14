<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <AdminClassroomListPage :value_uid="id"></AdminClassroomListPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import AdminClassroomListPage from '@/components/2/AdminClassroomListPage.vue';
  import { mapState } from 'vuex';
        export default {  
            name:'AdminClassroomView',
            components: {
                SideBar,
                AdminClassroomListPage
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
            this.$refs.sidebar.setPermission(2);
            this.$refs.sidebar.setChoice(6);
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