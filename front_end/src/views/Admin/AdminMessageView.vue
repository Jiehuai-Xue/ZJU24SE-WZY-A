<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <AdminMessagePage></AdminMessagePage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import { mapState } from 'vuex';
  import AdminMessagePage from '@/components/2/AdminMessagePage.vue';
        export default {  
            name:'AdminMessagePage',
            components: {
                SideBar, // 注册SideBar组件
                AdminMessagePage
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