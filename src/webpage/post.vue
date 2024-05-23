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
          v-model="postTitle"
          label="标题"
          clearable
      ></v-text-field>
    </v-responsive>
    <v-textarea label="内容" v-model="postContent"></v-textarea>
    <input type="file" @change="handleFileUpload" accept="image/*, .pdf, .doc, .docx">
    <v-select
        :items="items"
        density="compact"
        label="发布类别"
        v-model="contentClass"
    ></v-select>
<!--    <button @click="createPost">发布帖子</button>-->
    <VaButton class="mr-6 mb-2" color="myCoolColor" @click="createPost"
      style="display: flex;text-align: center;justify-content: center;
      height: 50px;min-height: 50px;">
      <p style="font-weight: normal;">发布帖子</p>
    </VaButton>
  </div>
</template>

<script>
import { useRouter } from "vue-router";
export default {
  data() {
    return {
      postTitle: "",
      postContent: "",
      file: null,
      postType: "normal",
      items: ["帖文","公告"],
      titleEmpty: false,
      success: false,
      contentEmpty: false,
      contentClass: "帖文",
      router: useRouter(),
    };
  },
  methods: {
    handleFileUpload(event) {
      this.file = event.target.files[0];
    },
    createPost() {
      this.titleEmpty = this.postTitle.length === 0;
      this.contentEmpty = this.postContent.length === 0;
      if(!this.titleEmpty && !this.contentEmpty) {
        alert(this.contentClass + "发布成功");
        this.success = true;
      }else {
        let msg = "请输入" +
            (this.titleEmpty ? ("标题" + (this.contentEmpty ? "和内容" : ""))
            : (this.contentEmpty ? "内容" : ""));
        alert(msg);
        this.success = false;
      }
      this.postTitle = "";
      this.postContent = "";
    },
    validatePost() {
      // 一个简单的帖子验证函数,需要验证哪些内容让其能正常发布

      return true;
    },
    homepage_jump() {
      setTimeout(() => { this.router.push({ name: 'HomePage' }) }, 500);
    },
  }
};
</script>

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