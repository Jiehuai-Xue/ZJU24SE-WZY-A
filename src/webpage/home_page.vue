<script>
  import { ref } from "vue";
  import { useRouter } from "vue-router";
  import avatar from "@/assets/default_avatar_boy.png";
  import announcement_list from "@/test_data/announcement";
  import { clicked_announcement_id } from "@/test_data/announcement";
  import post_list from "@/test_data/post";
  import trend_id_list, { in_trend } from "@/test_data/trend";
  import latest_list from "@/test_data/latest";
  import friend_list from "@/test_data/chat_friend_list";
  import { clicked_post_id,clicked_type } from "@/test_data/post";
  import { account_info, login_id } from "@/test_data/account";
  // import bus from '@/bus/bus';

  export default {
    computed: {
      announcement_list() {
        return announcement_list
      },
      post_list() {
        return post_list
      }
    },
    data() {
      return {
        path: avatar,
        msg_count: 0,
        timer: 0,
        trend_list: this.getTrendList(trend_id_list.value),
        latest_list,
        friend_list,
        account_id: '',
        account_privilege: '',
      }
    },
    methods: {
      polling_msg() {
        this.timer = setInterval(() => {
          setTimeout(() => {
            this.msg_count = friend_list.length;
          }, 0);
        }, 0);
      },
    },
    mounted() {
        this.polling_msg();
    },
    destroyed() {
      window.clearInterval(this.timer);
    },
    setup() {
      const router = useRouter();
      const message_jump = () => {
        setTimeout(() => { router.push({ name: 'message' }) },500);
      };
      const homepage_jump = () => {
        setTimeout(() => { router.push({ name: 'HomePage' }) },500);
      };
      const searchpage_jump = () => {
        setTimeout(() => { router.push({ name: 'SearchPage' }) },500);
      };
      const config_jump = () => {
        setTimeout(() => { router.push({ name: 'Setting' }) },500);
      };
      const postedit_jump = () => {
        setTimeout(() => { router.push({ name: 'Post_edit' }) }, 500);
      };
      const postpage_jump_post = (n) => {
        // n.post.viewCount += 1;
        clicked_post_id.value = n.post.id;
        clicked_type.value = n.post.type;
        query_trend(n.post.id);
        setTimeout(() => { router.push({ name: "Post" })},500);
      };
      const postpage_jump_announcement = (n) => {
        // n.post.viewCount += 1;
        clicked_announcement_id.value = n.post.id;
        clicked_type.value = n.post.type;
        setTimeout(() => { router.push({ name: 'Post' }) },500);
      };

      const getLoginAccount = (login_id) => {
        for(let i = 0; i < account_info.value.length; i++) {
          if(account_info.value[i].account_id === login_id.value) {
            return account_info.value[i];
          }
        }
        return undefined;
      };
      const login_Account = getLoginAccount(login_id);

      const getTrendList = (trend_id_list) => {
        let trend_list = [];
        for(let i = 0; i < trend_id_list.length; i++) {
          let trend = post_list.value.find(post => trend_id_list[i] === post.post.id);
          trend_list.push(trend);
        }
        return trend_list;
      }

      const query_trend = (post_id) => {
        in_trend.value = trend_id_list.value.includes(post_id);
      }

      return {
        message_jump,
        homepage_jump,
        postedit_jump,
        postpage_jump_post,
        postpage_jump_announcement,
        searchpage_jump,
        config_jump,
        login_Account,
        getTrendList,
        query_trend,
      }
    },
  }
</script>

<template>
  <body>
    <div class="top_bar">
      <VaButton
          preset="secondary"
          class="mr-6 mb-2 homepage_bar"
          style="border-radius: 10px;"
          @click="homepage_jump();"
      >
        <img src="../assets/homepage2.svg" alt="homepage" width="30" height="30"/>
        <p style="color: black;font-family: 'Microsoft YaHei UI';">
          &nbsp;&nbsp;首页
        </p>
      </VaButton>
      <v-btn rounded="xl" class="search_bar" @click="searchpage_jump();">
        <img src="../assets/search.svg" alt="SVG Image" width="20" height="20">
        <p style="position: relative;font-size: medium;height: 30px;margin-top: 8%;">
          &nbsp;&nbsp;搜索帖文
        </p>
      </v-btn>
      <v-btn class="text-none post_bar" @click="postedit_jump();">
        <img src="../assets/post.svg" alt="svg" width="25" height="25">
        <p>&nbsp;发布</p>
      </v-btn>
      <v-btn class="text-none msg_bar" @click="message_jump();">
        <img src="../assets/message.svg" alt="svg" width="30" height="30">
        <p>&nbsp;私信</p>
        <v-badge color="error" :content="this.msg_count" style="position: relative;right: 5px">
          <v-icon>mdi-store-outline</v-icon>
        </v-badge>
      </v-btn>
      <v-btn variant="tonal" class="config_button" @click="config_jump();">
        <img src="../assets/Settings.svg" alt="svg" width="20" height="20">
      </v-btn>
    </div>
    <div class="notice_bar">
      <div class="notice_top_bar">
        <img src="../assets/notice.svg" width="30" height="30" class="notice_img" alt="png">
        <p>&nbsp;&nbsp;&nbsp;&nbsp;公告</p>
      </div>
      <div class="notice_main_bar">
        <v-list lines="one" style="background: #23c18c;">
          <v-list-item
              v-for="n in announcement_list.value"
              :key="n"
              :title="n.post.title"
              :subtitle="'click:'+n.post.viewCount"
              :prepend-avatar="n.post.authorAvatar"
              :v-slot:subtitle="n.post.viewCount"
              style="background: #23c18c"
              @click="postpage_jump_announcement(n);"
          ></v-list-item>
        </v-list>
      </div>
    </div>
    <div class="trend_bar">
      <div class="trend_top_bar">
        <img src="../assets/trend.svg" width="30" height="30" class="trend_img">
        <p>&nbsp;&nbsp;&nbsp;&nbsp;热搜</p>
      </div>
      <div class="trend_main_bar">
        <v-list lines="one" style="background: #23c18c;">
          <v-list-item
              v-for="n in trend_list"
              :key="n"
              :title="n.post.title"
              :subtitle="'click:'+n.post.viewCount"
              :prepend-avatar="n.post.authorAvatar"
              style="background: #23c18c"
              @click="postpage_jump_post(n);"
          ></v-list-item>
        </v-list>
      </div>
    </div>
    <div class="latest_bar">
      <div class="latest_top_bar">
        <img src="../assets/latest.svg" width="30" height="30" class="latest_img">
        <p>&nbsp;&nbsp;&nbsp;&nbsp;最新</p>
      </div>
      <div class="latest_main_bar">
        <v-list lines="one" style="background: #23c18c;">
          <v-list-item
              v-for="n in post_list.value"
              :key="n"
              :title="n.post.title"
              :subtitle="'click:'+n.post.viewCount"
              :prepend-avatar="n.post.authorAvatar"
              style="background: #23c18c"
              @click="postpage_jump_post(n);"
          ></v-list-item>
        </v-list>
      </div>
    </div>
    <div style="width: 100%;height: 60px"></div>
  </body>
</template>

<style scoped>
  body {
    flex-shrink: 0;
  }
  .top_bar {
    display: flex;
    right: 0;
    left: 0;
    height: 50px;
    overflow: hidden;
    background: #93e1f4;
    justify-content: flex-end;
  }
  .homepage_bar{
    width: 8%;
    height: 70%;
    background: #23c18c;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: auto;
    margin-bottom: auto;
    margin-left: 30px;
    min-width: 100px;

    border-radius: 10px;
    border: black;
    font-family: "Microsoft YaHei UI";
  }
  .homepage_bar:hover{
    cursor: pointer;
  }
  .search_bar {
    position: relative;
    width: 20%;
    display: flex;
    justify-content: left;
    align-items: center;
    margin-top: auto;
    margin-bottom: auto;
    margin-left: 10px;
    min-width: 150px;

    font-family: "Microsoft YaHei UI",serif;
  }
  .search_bar:hover{
    cursor: pointer;
  }
  .post_bar {
    position: relative;
    background: #93e1f4;
    color: black;
    min-width: 100px;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 8%;
    height: 70%;
    margin-top: auto;
    margin-bottom: auto;
    margin-left: 10px;

    font-family: "Microsoft YaHei UI",serif;
  }
  .config_button {
    position: relative;
    width: 2%;
    min-width: 40px;
    height: 70%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: auto;
    margin-bottom: auto;
    right: 20px;
  }
  .msg_bar {
    position: relative;
    background: #93e1f4;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 8%;
    height: 70%;
    margin-top: auto;
    margin-bottom: auto;
    right: 30px;
    margin-left: auto;
    min-width: 100px;
    
    font-family: "Microsoft YaHei UI",serif;
  }
  .notice_bar {
    position: relative;
    width: 45%;
    height: 400px;
    margin-top: 30px;
    margin-left: 30px;
    background: #23c18c;
    float: left;
  }
  .notice_top_bar {
    display: flex;
    position: relative;
    width: 100%;
    height: 40px;
    background: #93e1f4;
    margin-left: auto;
    align-items: center;
  }
  .notice_img {
    position: relative;
    margin-left: 15px;
    margin-top: auto;
    margin-bottom: auto;
  }
  .notice_main_bar {
    position: relative;
    width: 100%;
    height: 360px;
    overflow-y: scroll;
  }
  .trend_bar {
    position: relative;
    width: 45%;
    height: 400px;
    margin-top: 30px;
    margin-right: 30px;
    background: #23c18c;
    float: right;
  }
  .trend_top_bar {
    position: relative;
    width: 100%;
    height: 40px;
    background: #93e1f4;
    margin-right: auto;
    display: flex;
    align-items: center;
  }
  .trend_img {
    position: relative;
    margin-left: 15px;
    margin-top: auto;
    margin-bottom: auto;
  }
  .trend_main_bar {
    position: relative;
    width: 100%;
    height: 360px;
    overflow-y: scroll;
  }
  .latest_bar {
    position: relative;
    width: 97%;
    height: 300px;
    overflow: hidden;
    background: #23c18c;
    top: 30px;
    margin-left: auto;
    margin-right: auto;
  }
  .latest_top_bar {
    display: flex;
    position: relative;
    width: 100%;
    height: 40px;
    background: #93e1f4;
    align-items: center;
  }
  .latest_img {
    position: relative;
    margin-left: 15px;
    margin-top: auto;
    margin-bottom: auto;
  }
  .latest_main_bar {
    position: relative;
    width: 100%;
    height: 260px;
    overflow-y: scroll;
  }
</style>