<script>
import { useRouter } from "vue-router";
import avatar from "@/assets/default_avatar_boy.png";
import { account_info,login_id  } from "@/test_data/account";
import announcement_list from "@/test_data/announcement";
import post_list from "@/test_data/post";
// import bus from "@/webpage/bus";

export default {
  data() {
    return {
      title: "", //输入的标题
      content: "", //输入的内容
      file: null,
      admin_items: ["帖文","公告"],
      normal_items: ["帖文"],
      titleEmpty: false,
      success: false,
      contentEmpty: false,
      contentClass: "帖文",
      title_rules: [this.titleEmpty || '请输入标题'],
      content_rules: [this.contentEmpty || '请输入内容'],
      account_privilege: this.login_Account.privilege,
    };
  },
  methods: {
    handleFileUpload(event) {
      this.file = event.target.files[0];
    },
    examine_title_empty() {
      this.titleEmpty = this.title.length === 0;
    },
    examine_content_empty() {
      this.contentEmpty = this.content.length === 0;
    },
    create() {
      this.titleEmpty = this.title.length === 0;
      this.contentEmpty = this.content.length === 0;
      if(!this.titleEmpty && !this.contentEmpty) {
        alert(this.contentClass + "发布成功");
        if(this.contentClass === '公告') this.publish_announcement();
        else this.publish_post();
        this.success = true;
      }else {
        let msg = "请输入" +
            (this.titleEmpty ? ("标题" + (this.contentEmpty ? "和内容" : ""))
            : (this.contentEmpty ? "内容" : ""));
        alert(msg);
        this.success = false;
      }
      this.title = "";
      this.content = "";
    },
    publish_announcement() {
      if(this.contentClass === '公告'){
        let id = announcement_list.value[announcement_list.value.length - 1].post.id + 1;
        const today = new Date();
        let new_announcement = {
          post: {
            id: id,
            type: 'announcement',
            title: this.title,
            content: this.content,
            authorId: login_id.value,
            authorName: this.login_Account.name,
            authorAvatar: this.login_Account.img_path,
            publishedAt: today.toISOString().slice(0, 10)+" "+today.toTimeString().slice(0,8),
            status: "正常",
            viewCount: 0,
          },
          comments: [],
        };
        announcement_list.value.push(new_announcement);
        for(let i = 0; i < account_info.value.length; i++) {
          if(account_info.value[i].account_id === new_announcement.post.authorId)
            account_info.value[i].post_number += 1;
        }
      }
    },
    publish_post (){
      if(this.contentClass === '帖文'){
        let id = post_list.value[post_list.value.length - 1].post.id + 1;
        const today = new Date();
        let new_post = {
          post: {
            id: id,
            type: 'post',
            title: this.title,
            content: this.content,
            authorId: login_id.value,
            authorName: this.login_Account.name,
            authorAvatar: this.login_Account.img_path,
            publishedAt: today.toISOString().slice(0, 10)+" "+today.toTimeString().slice(0,8),
            status: "正常",
            viewCount: 0,
          },
          comments: [],
        };
        post_list.value.push(new_post);
        for(let i=0;i < account_info.value.length;i++){
          if(account_info.value[i].account_id === new_post.post.authorId)
            account_info.value[i].post_number += 1;
        }
      }
    },
  },
  mounted() {
    this.timer = setInterval(() => {
      setTimeout(() => {
        this.examine_title_empty();
        this.examine_content_empty();
      },0)
    },0);
  },
  destroyed() {
    window.clearInterval(this.timer);
  },
  setup() {
    const router = useRouter();
    const homepage_jump = () => {
      setTimeout(() => { router.push({ name: 'HomePage' }) }, 500);
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

    return {
      homepage_jump,
      login_Account,
    }
  }
};
</script>

<template>
  <div class="create-post">
    <div style="display: flex;">
      <v-btn variant="tonal" style="width: 50px;min-width: 50px;background: none;"
             @click="homepage_jump();">
        <img src="../assets/back.svg" alt="svg" width="20" height="20">
      </v-btn>
      <h2 style="margin-left: 40%;margin-right: auto;">发布新帖子</h2>
    </div>
    <v-responsive
        class="mx-auto"
        min-width="400"
    >
      <v-text-field
          v-model="title"
          label="标题"
          clearable
          :rules="title_rules"
          id="title_input"
      ></v-text-field>
    </v-responsive>
    <v-textarea
        label="内容"
        v-model="content"
        id="content_input"
        :rules="content_rules"
    ></v-textarea>
    <input type="file" @change="handleFileUpload" accept="image/*, .pdf, .doc, .docx">
    <v-select
        v-if="account_privilege === 'admin'"
        :items="admin_items"
        density="compact"
        label="发布类别"
        v-model="contentClass"
    ></v-select>
    <v-select
        v-else-if="account_privilege === 'normal'"
        :items="normal_items"
        density="compact"
        label="发布类别"
        v-model="contentClass"
    ></v-select>
    <VaButton class="mr-6 mb-2" color="myCoolColor"
              style="display: flex;text-align: center;justify-content: center;
      height: 50px;min-height: 50px;"
      @click="create();">
      <p style="font-weight: normal;">发布{{contentClass}}</p>
    </VaButton>
  </div>
</template>

<style scoped>
.create-post {
  min-width: 400px;
  width: 70%;
  margin-left: auto;
  margin-right: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-top: 30px;
}

.create-post h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.create-post input,
.create-post textarea,
.create-post select,
.create-post button {
  display: block;
  margin-bottom: 10px;
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.create-post button {
  background-color: #007bff;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
}

.create-post button:hover {
  background-color: #0056b3;
}

.success-message,
.error-message {
  margin-top: 10px;
  padding: 10px;
  border-radius: 5px;
  text-align: center;
}

.success-message {
  background-color: #28a745;
  color: #fff;
}

.error-message {
  background-color: #dc3545;
  color: #fff;
}
</style>