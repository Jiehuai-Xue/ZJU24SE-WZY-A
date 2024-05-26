<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <AdminUserListPage :value_uid="id"></AdminUserListPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import AdminUserListPage from '@/components/2/AdminUserListPage.vue';
  import { mapState } from 'vuex';
        export default {  
            name:'AdminUserView',
            components: {
                SideBar,
                AdminUserListPage
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
            this.$refs.sidebar.setChoice(4);
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