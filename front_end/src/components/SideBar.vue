<template>
    <div style="height: 100vh">
      <VaSidebar>
        
        <div class="profile-box">
            <VaAvatar
                    :src="url()"
                    fallbackSrc="http://172.31.171.9:8080/user-profile/default.png"
                    class="mr-6"
                    font-size="30px"
                    size="large"
                    color="#FFFFFF"
            />       
        </div>
             

          
        <VaSidebarItem :active="choice==1" @click="setChoice(1)">
          <VaSidebarItemContent>
            <VaIcon name="home" />
            <VaSidebarItemTitle>首页</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem>

        <VaSidebarItem :active="choice==2" @click="setChoice(2)">
          <VaSidebarItemContent> <!-- to create paddings -->
            <VaIcon name="article" />
            <VaSidebarItemTitle>我的信息</VaSidebarItemTitle> <!-- to fill all remaining width -->
          </VaSidebarItemContent>
        </VaSidebarItem>

        <VaSidebarItem v-if="permission==1" :active="choice==3" @click="setChoice(3)">
          <VaSidebarItemContent>
            <VaIcon name="book" />
            <VaSidebarItemTitle>日志</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem>     

        <VaSidebarItem v-if="permission<=2" :active="choice==4" @click="setChoice(4)">
          <VaSidebarItemContent>
            <VaIcon name="person" />
            <VaSidebarItemTitle>用户管理</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem>   

        <VaSidebarItem v-if="permission<=2" :active="choice==5" @click="setChoice(5)">
          <VaSidebarItemContent>
            <VaIcon name="book" />
            <VaSidebarItemTitle>课程管理</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem>

        <VaSidebarItem v-if="permission<=2" :active="choice==6" @click="setChoice(6)">
          <VaSidebarItemContent>
            <VaIcon name="room" />
            <VaSidebarItemTitle>教室管理</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem> 

        <VaSidebarItem v-if="permission==3" :active="choice==7" @click="setChoice(7)">
          <VaSidebarItemContent>
            <VaIcon name="book" />
            <VaSidebarItemTitle>我的授课</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem>

        <VaSidebarItem v-if="permission==4" :active="choice==8" @click="setChoice(8)">
          <VaSidebarItemContent>
            <VaIcon name="book" />
            <VaSidebarItemTitle>我的课程</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem>

  
        <VaSpacer />
  
        <VaSidebarItem :active="choice==9" @click="setChoice(9)">
          <VaSidebarItemContent>
            <VaIcon name="settings" />
            <VaSidebarItemTitle>设置</VaSidebarItemTitle>
          </VaSidebarItemContent>
        </VaSidebarItem>
      </VaSidebar>
    </div>
  </template>

<script>
import CourseSelectionListPage from '@/components/4/CourseSelectionListPage.vue'
import { mapState } from 'vuex';
export default{
    name: 'SideBar',
    components:{
      CourseSelectionListPage
    },
    data() {
      return {
        permission: 0,
        choice: 0,
      };
    },
    computed: {
            ...mapState(['id']), // 映射state中的id到本组件的computed属性id
    },    
    methods: {
      url(){
        return 'http://172.31.171.9:8080/user-profile/' + (this.id % 100000000).toString() + '.png';
      },
      setPermission(x){
        console.log("permission set to ", x);
        this.permission = x;
      },
      setChoice(x) {
        console.log("set choice to ", x);
        this.$emit('choice-changed', x);
        this.choice = x;
        if(this.choice == 1){
          if(this.permission == 1){
            this.$router.push("/sys_admin");
          }else if(this.permission == 2){
            this.$router.push("/admin");
          }else if(this.permission == 3){
            this.$router.push("/teacher");
          }else if(this.permission == 4){
            this.$router.push("/student");
          }
        }else if(this.choice == 2){
          if(this.permission == 1){
            this.$router.push("/sys_admin/info");
          }else if(this.permission == 2){
            this.$router.push("/admin/info");
          }else if(this.permission == 3){
            this.$router.push("/teacher/info");
          }else if(this.permission == 4){
            console.log("push to /student/info");
            this.$router.push("/student/info");
          }            
        }else if(this.choice == 3){
          console.log("push to /sys_admin/log");
            this.$router.push("/sys_admin/log");
        }else if(this.choice == 4){
          if(this.permission == 1){
            console.log("push to /sys_admin/user");
            this.$router.push("/sys_admin/user");
          }else if(this.permission == 2){
            console.log("push to /admin/user");
            this.$router.push("/admin/user");
          }          
        }else if(this.choice == 5){
          if(this.permission == 1){
            console.log("push to /sys_admin/course");
            this.$router.push("/sys_admin/course");
          }else if(this.permission == 2){
            console.log("push to /admin/course");
            this.$router.push("/admin/course");
          }
        }else if(this.choice == 6){
          console.log("push to /admin/classroom");
            this.$router.push("/admin/classroom");
        }else if(this.choice == 7){
          console.log("push to /teacher/course");
          this.$router.push("/teacher/course");
        }else if(this.choice == 8){
          console.log("push to /student/course");
          this.$router.push("/student/course");
        }
      },
      setHighlight(x){
        this.choice = x;
      }
    }
};
</script>

<style>
  .profile-box{
    align-self: center;
    align-items: center;
    margin: 30px;
  }
</style>
  