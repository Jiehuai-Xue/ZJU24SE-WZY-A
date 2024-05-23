<script setup lang="ts">
import { ref, watchEffect } from 'vue'
import { useBreakpoint } from 'vuestic-ui'
import { useRouter } from "vue-router";
const breakpoints = useBreakpoint()

const isSidebarVisible = ref(breakpoints.mdUp)
const minimized = ref(breakpoints.mdDown)

const page = ref(1)

watchEffect(() => {
  isSidebarVisible.value = breakpoints.smUp
})

const router = useRouter();
const homepage_jump = () => {
  setTimeout(() => { router.push({ name: 'HomePage' }) },500);
};
const post_jump = () => {
  setTimeout(() => { router.push({ name: 'Post' }) }, 500);
};
</script>

<script lang="ts">

export default {
  data() {
    return {
      contacts: [
        {
          name: "张三",
          address: "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈",
          //require是node里的方法，用于vue动态加载图片
          // img: "./assets/default_avatar_boy",
          img: "https://randomuser.me/api/portraits/women/5.jpg",
        },
        {
          name: "Aguirre Klein",
          address: "626 Carroll Street, Roulette, Ohio, 1477",
          // img: "./assets/default_avatar_boy",
          img: "https://randomuser.me/api/portraits/men/1.jpg",
        },
        {
          name: "Tucker Kaufman",
          address: "887 Winthrop Street, Tryon, Florida, 3912",
          // img: "./assets/default_avatar_boy",
          img: "https://randomuser.me/api/portraits/men/3.jpg",
        },
        {
          name: "Herbert Keller",
          address: "286 NW. Shore St.Longwood, FL 32779",
          // img: "./assets/default_avatar_boy",
          img: "https://randomuser.me/api/portraits/men/5.jpg",
        },
      ],
      rules: [v => v.length <= 25 || '最多25字符'],
      inputValue: '',
    };
  },
  methods: {
    omission(value: string) {
      if (!value) return '';
      else if (value.length > 10) {
        return value.slice(0, 10) + '...';
      } else {
        return value;
      }
    },
    send() {
      // let text = document.querySelector('#text_input').textContent;
      let text = this.inputValue;
      //用innerhtml加载图片时,编译发生在根目录。注意图片路径应当以根目录为起点。
      let path = "src/assets/default_avatar_boy.png";
      if (!text) {
        alert('请输入内容');
        return;
      }
      let item = document.createElement('div');
      item.className = 'item item-right';
      item.innerHTML =
          `<div class="bubble bubble-left">${text}</div>
            <div class="avatar">
                <img src=${path} alt="png"/>
            </div>`;
      document.querySelector('.content').appendChild(item);
      this.inputValue = '';
      // document.getElementById('#text_input').focus();
      //滚动条置底
      let height = document.querySelector('.content').scrollHeight;
      document.querySelector(".content").scrollTop = height;
    },
  },
};
</script>

<template>
  <VaLayout
      :top="{ fixed: true, order: 2 }"
      :left="{ fixed: true, absolute: breakpoints.smDown, order: 1, overlay: breakpoints.smDown && isSidebarVisible }"
      @left-overlay-click="isSidebarVisible = false"
  >
    <template #top>
      <VaNavbar shadowed style="background:rgba(35,193,140,0.25)">
        <template #left>
          <VaButton
              preset="secondary"
              :icon="minimized ? 'menu_open' : 'menu'"
              @click="minimized = !minimized"
              class="mt-2"
              style="color: black"
          />
          <VaButton
              preset="secondary"
              class="homepage_bar"
              style="border-radius: 10px;"
              @click="homepage_jump();"
          >
            <img src="../assets/homepage.svg" alt="homepage" width="20" height="20"/>
          </VaButton>
          <VaButton
              preset="secondary"
              style="border-radius: 10px;"
              @click="post_jump();"
          >
            <img src="../assets/post.svg" alt="post" width="20" height="20"/>
          </VaButton>
        </template>
      </VaNavbar>
    </template>

    <template #left>
      <div class="h-52">
        <VaSidebar v-model="isSidebarVisible" width="300px"
                   height="100%"
          :minimized="minimized" minimized-width="64px"
                   style="overflow: hidden"
          >
          <VaSidebarItem
            v-for="(contact, index) in contacts"
            :key="index"
            class="list__item "
            style="width: 300px;overflow-x: hidden"
            :active="page === index + 1"
            @click="page = index + 1"
            active-color="messageColor"
          >
            <div style="display: flex">
              <!--左边消息栏-->
              <VaListItemSection avatar>
                <VaAvatar style="position: relative;left:5px;top:50%">
                <img :src="contact.img" :alt="contact.name">
                </VaAvatar>
              </VaListItemSection>

              <!--账号名称与最新一条聊天信息-->
              <VaListItemSection style="position: relative;left:20px;top:20px">
                <VaListItemLabel style="font-size: 20px">
                  {{ contact.name }}
                </VaListItemLabel>

                <VaListItemLabel caption style="color: rgba(0,0,0,0.36)">
                  {{ omission(contact.address) }}
                </VaListItemLabel>
              </VaListItemSection>
            </div>

            <VaListItemSection icon>
              <VaIcon
                  name="remove_red_eye"
                  color="background-element"
              />
            </VaListItemSection>
          </VaSidebarItem>
        </VaSidebar>
      </div>
    </template>

    <template #content>
      <main
          v-if="page === 1"
          class="p-4"
      >
        <h3 class="va-h3">
          Page 1
        </h3>
        <p>Page content must be wrapped in main tag. You must do it manually. Here you can place any blocks you need in your application.</p>

        <p>For example, you can place here your router view, add sidebar with navigation in #left slot.</p>
        <p>If you're using VaSidebar for page navigation don't forget to wrap it in nav tag.</p>
      </main>
      <main
          v-else-if="page === 2"
          class="p-4"
      >
        <h3 class="va-h3">
          Page 2
        </h3>
        <p>Page content must be wrapped in main tag. You must do it manually. Here you can place any blocks you need in your application.</p>

        <p>For example, you can place here your router view, add sidebar with navigation in #left slot.</p>
        <p>If you're using VaSidebar for page navigation don't forget to wrap it in nav tag.</p>
      </main>
      <main v-else-if="page === 3">
        <div class="container" style="position: relative;margin-left: auto; margin-right: auto;">
          <div class="content">
            <div class="item item-center"><span>昨天 12:35</span></div>
            <div class="item item-center">
              <span>你已添加了凡繁烦，现在可以开始聊天了。</span>
            </div>
            <div class="item item-left">
              <div class="avatar">
                <img src="../assets/default_avatar_boy.png" alt="png"/>
              </div>
              <div class="bubble bubble-left">
                您好,我在武汉，你可以直接送过来吗，我有时间的话，可以自己过去拿<br/>！！！<br/>123
              </div>
            </div>

            <div class="item item-right">
              <div class="bubble bubble-right">
                hello<br/>你好呀
              </div>
              <div class="avatar">
                <img src="../assets/default_avatar_boy.png" alt="png"/>
              </div>
            </div>
            <div class="item item-center"><span>昨天 13:15</span></div>
            <div class="item item-right">
              <div class="bubble bubble-right">刚刚不在，不好意思</div>
              <div class="avatar">
                <img src="../assets/default_avatar_boy.png" alt="png"/>
              </div>
            </div>
            <div class="item item-left">
              <div class="avatar">
                <img src="../assets/default_avatar_boy.png" alt="png"/>
              </div>
              <div class="bubble bubble-left">没事<br/>你继续！</div></div>
            <div class="item item-left">
              <div class="avatar">
                <img src="../assets/default_avatar_boy.png" alt="png"/>
              </div>
              <div class="bubble bubble-left">看到了<br/>在下面</div>
            </div>
            <div class="item item-left">
              <div class="avatar">
                <img src="../assets/default_avatar_boy.png" alt="png"/>
              </div>
              <div class="bubble bubble-left">你发一个<br/>刚刚网卡了</div>
            </div>
            <div class="item item-right">
              <div class="bubble bubble-right">可以<br/>一会儿再发给你</div>
              <div class="avatar">
                <img src="../assets/default_avatar_boy.png" alt="png"/>
              </div>
            </div>
          </div>
          <div class="input-area">
            <v-container fluid>
              <v-textarea
                  v-model="inputValue"
                  :rules="rules"
                  label="请输入你的信息"
                  counter
                  id="text_input"
              >
              </v-textarea>
              <VaButton class="mr-3 mb-2 button-area" color="myCoolColor"
                @click="send()">
                发送
              </VaButton>
            </v-container>
          </div>
        </div>
      </main>
    </template>
  </VaLayout>
</template>

<style lang="scss">
  .list__item + .list__item {
    margin-top: 20px;
  }
  .vaSidebarItem_bg_color:hover {
    background: black;
  }

  .container{
    height: 650px;
    width: 1000px;
    border-radius: 4px;
    border: 0.5px solid #e0e0e0;
    background-color: #f5f5f5;
    display: flex;
    flex-flow: column;
    overflow: hidden;
    position: fixed;
  }
  .content{
    width: calc(100% - 40px);
    padding: 20px;
    overflow-y: scroll;
    flex: 1;
  }
  .content:hover::-webkit-scrollbar-thumb{
    background:rgba(0,0,0,0.1);
  }
  .bubble{
    max-width: 400px;
    padding: 10px;
    border-radius: 5px;
    position: relative;
    color: #000;
    word-wrap:break-word;
    word-break:normal;
  }
  .item-left .bubble{
    margin-left: 15px;
    background-color: #fff;
  }
  .item-left .bubble:before{
    content: "";
    position: absolute;
    width: 0;
    height: 0;
    border-left: 10px solid transparent;
    border-top: 10px solid transparent;
    border-right: 10px solid #fff;
    border-bottom: 10px solid transparent;
    left: -20px;
  }
  .item-right .bubble{
    margin-right: 15px;
    background-color: #9eea6a;
  }
  .item-right .bubble:before{
    content: "";
    position: absolute;
    width: 0;
    height: 0;
    border-left: 10px solid #9eea6a;
    border-top: 10px solid transparent;
    border-right: 10px solid transparent;
    border-bottom: 10px solid transparent;
    right: -20px;
  }
  .item{
    margin-top: 15px;
    display: flex;
    width: 100%;
  }
  .item.item-right{
    justify-content: flex-end;
  }
  .item.item-center{
    justify-content: center;
  }
  .item.item-center span{
    font-size: 12px;
    padding: 2px 4px;
    color: #fff;
    background-color: #dadada;
    border-radius: 3px;
    -moz-user-select:none; /*火狐*/
    -webkit-user-select:none; /*webkit浏览器*/
    -ms-user-select:none; /*IE10*/
    -khtml-user-select:none; /*早期浏览器*/
    user-select:none;
  }
  .avatar img{
    width: 42px;
    height: 42px;
    border-radius: 50%;
  }
  .input-area{
    //border-top:0.5px solid #e0e0e0;
    height: 200px;
    //display: flex;
    flex-flow: column;
    //background-color: #fff;
  }
  textarea{
    flex: 1;
    padding: 5px;
    font-size: 14px;
    border: none;
    cursor: pointer;
    overflow-y: auto;
    overflow-x: hidden;
    outline:none;
    resize:none;
  }
  .button-area{
    position: relative;
    display: flex;
    height: 40px;
    width: 80px;
    margin-left: auto;
    right: 10px;
    line-height: 40px;
    padding: 5px;
    justify-content: center;
  }
  .button-area button{
    width: 80px;
    border: none;
    outline: none;
    border-radius: 4px;
    float: right;
    cursor: pointer;
  }

  /* 设置滚动条的样式 */
  ::-webkit-scrollbar {
    width:10px;
  }
  /* 滚动槽 */
  ::-webkit-scrollbar-track {
    //-webkit-box-shadow:inset006pxrgba(0,0,0,0.3);
    border-radius:8px;
  }
  /* 滚动条滑块 */
  ::-webkit-scrollbar-thumb {
    border-radius:10px;
    background:rgba(0,0,0,0);
    //-webkit-box-shadow:inset006pxrgba(0,0,0,0.5);
  }
</style>
