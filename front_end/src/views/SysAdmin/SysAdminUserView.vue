<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import InfoPage from '@/components/InfoPage.vue';
  import { mapState } from 'vuex';
        export default {  
            name:'SysAdminUserView',
            components: {
                SideBar, // 注册SideBar组件
                InfoPage,
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
            this.$refs.sidebar.setChoice(4);
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