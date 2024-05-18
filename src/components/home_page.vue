<script>
  import { ref } from "vue";

  export default {
    data() {
      return {
        path: "https://randomuser.me/api/portraits/women/8.jpg",
      }
    },
    methods: {
      omission(value) {
        if(!value) return '';
        else if (value.length > 10){
          return value.slice(0, 10) + '...';
        } else {
          return value;
        }
      },
    }
  }
</script>

<template>
  <body>
    <div class="top_bar">

      <button class="homepage_bar" @click="">
        <img src="../assets/homepage.svg" alt="SVG Image" width="30" height="30">
        <p>&nbsp;首页</p>
      </button>
      <v-btn rounded="xl" size="x-large" class="search_bar">
        <img src="../assets/search.svg" alt="SVG Image" width="20" height="20">
        <p style="font-size: medium">&nbsp;&nbsp;搜索</p>
      </v-btn>
      <v-btn class="text-none msg_bar">
        <img src="../assets/default_avatar_boy.png" alt="png" width="30" height="30">
        <p>&nbsp;私信</p>
        <v-badge color="error" content="9+" style="position: relative;right: 5px">
          <v-icon>mdi-store-outline</v-icon>
        </v-badge>
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
              v-for="n in 10"
              :key="n"
              :title="'Item ' + n"
              :subtitle="omission('Lorem ipsum dolor sit amet consectetur adipisicing elit')"
              :prepend-avatar="path"
              style="background: #23c18c"
          ></v-list-item>
        </v-list>
      </div>
    </div>
    <div class="trend_bar">
      <div class="trend_top_bar">
        <img src="../assets/trending.png" width="30" height="30" class="trend_img">
        <p>&nbsp;&nbsp;&nbsp;&nbsp;热搜</p>
      </div>
      <div class="trend_main_bar">
        <v-list lines="one" style="background: #23c18c;">
          <v-list-item
              v-for="n in 10"
              :key="n"
              :title="'Item ' + n"
              :subtitle="omission('Lorem ipsum dolor sit amet consectetur adipisicing elit')"
              :prepend-avatar="path"
              style="background: #23c18c"
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
              v-for="n in 10"
              :key="n"
              :title="'Item ' + n"
              :subtitle="omission('Lorem ipsum dolor sit amet consectetur adipisicing elit')"
              :prepend-avatar="path"
              style="background: #23c18c"
          ></v-list-item>
        </v-list>
      </div>
    </div>
    <div style="width: 100%;height: 60px"></div>
  </body>
</template>

<style scoped>
  .top_bar {
    display: flex;
    right: 0;
    left: 0;
    height: 50px;
    overflow: hidden;
    background: #93e1f4;
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

    border-radius: 10px;
    border: black;
    font-family: "Microsoft YaHei UI";
  }
  .homepage_bar:hover{
    cursor: pointer;
  }
  .search_bar {
    position: relative;
    width: 10%;
    height: 70%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: auto;
    margin-bottom: auto;
    margin-left: auto;
    right: -400px;

    font-family: "Microsoft YaHei UI";
  }
  .search_bar:hover{
    cursor: pointer;
  }
  .message_bar {
    width: 8%;
    height: 70%;
    background: #93e1f4;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: auto;
    margin-bottom: auto;
    position: relative;
    margin-left: auto;
    right: 10px;
    border-width: 2px;

    font-family: "Microsoft YaHei UI";
  }
  .message_bar:hover{
    cursor: pointer;
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
    right: 10px;
    margin-left: auto;
    
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