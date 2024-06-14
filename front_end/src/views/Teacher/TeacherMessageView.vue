<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <TeacherMessagePage></TeacherMessagePage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import { mapState } from 'vuex';
  import TeacherMessagePage from '@/components/3/TeacherMessagePage.vue';
        export default {  
            name:'TeacherMessageView',
            components: {
                SideBar, // 注册SideBar组件
                TeacherMessagePage
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
            this.$refs.sidebar.setChoice(12);
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