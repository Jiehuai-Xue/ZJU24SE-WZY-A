<template>  
    <div class="container">
        <SideBar ref="sidebar"/>
        <div class="content">
            <InfoPage :value_uid="id" :value_oid="id"></InfoPage>
        </div>
    </div>
</template>  
    
<script>
  import SideBar from '@/components/SideBar.vue';
  import InfoPage from '@/components/InfoPage.vue';
  import { mapState } from 'vuex';
        export default {  
            name:'StudentInfoView',
            components: {
                SideBar, // 注册SideBar组件
                InfoPage,
            },
        data(){
            return{
                choice: null,
            };
        },
        computed: {
            ...mapState(['id']), // 映射state中的id到本组件的computed属性id
        },
        methods: {
        },
        mounted() {
            this.$refs.sidebar.setChoice(2);
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