<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <AdminUserDetailPage :value_uid="id"></AdminUserDetailPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import AdminUserDetailPage from '@/components/2/AdminUserDetailPage.vue';
  import { mapState } from 'vuex';
        export default {  
            name:'AdminInfoView',
            components: {
                SideBar,
                AdminUserDetailPage,
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
            this.$refs.sidebar.setHighlight(4);
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