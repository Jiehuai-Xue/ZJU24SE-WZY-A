<script>
import {useRouter} from "vue-router";
import post_list from "@/test_data/post";
import avatar from "@/assets/default_avatar_boy.png";
import { account_info, login_id } from "@/test_data/account";
import { clicked_post_id,clicked_type } from "@/test_data/post";
import announcement_list, { clicked_announcement_id } from "@/test_data/announcement";
import post from "@/test_data/post";
import trend_id_list, { in_trend } from "@/test_data/trend";
import { max_trend_number,trend_number } from "@/test_data/trend";

export default {
  data() {
    return {
      post: clicked_type.value === 'post' ? this.click_post.post :
        clicked_type.value === 'announcement' ? this.click_announcement.post : undefined,
      comments: clicked_type.value === 'post' ? this.click_post.comments :
        clicked_type.value === 'announcement' ? this.click_announcement.comments : undefined,
      newComment: "",
      replyToCommentId: null,
      replyToReplyId: null,
      newReply: "",
      defaultAvatar: avatar,
      isAdmin: this.login_Account.privilege === 'admin',
      currentUserId: login_id.value,
      isAddingComment: false,
      isAddingReply: false,
    };
  },
  created() {
    this.incrementViewCount();
  },
  methods: {
    post() {
      return post
    },
    incrementViewCount() {
      this.post.viewCount += 1;
    },
    addComment() {
      const today = new Date();
      if (this.newComment.trim() !== "") {
        const newComment = {
          id: String(this.comments.length + 1),
          userId: this.currentUserId,
          userName: this.login_Account.name,
          userAvatar: this.defaultAvatar,
          content: this.newComment,
          publishedAt: today.toISOString().slice(0, 10)+" "+today.toTimeString().slice(0,8),
          replies: [],
          reported: false
        };
        this.comments.push(newComment);
        this.newComment = "";
        this.isAddingComment = false;
      } else {
        alert("留言内容不能为空");
      }
    },
    setReplyTo(commentId, replyId) {
      this.replyToCommentId = commentId;
      this.replyToReplyId = replyId;
    },
    cancelReply() {
      this.replyToCommentId = null;
      this.replyToReplyId = null;
      this.newReply = "";
      this.isAddingReply = false;
    },
    addReply() {
      const today = new Date();
      if (this.newReply.trim() !== "") {
        if (this.replyToReplyId) {
          const parentComment = this.comments.find(comment => comment.id === this.replyToCommentId);
          const reply = parentComment.replies.find(reply => reply.id === this.replyToReplyId);
          if (reply) {
            const newReply = {
              id: `${this.replyToCommentId}-${parentComment.replies.length + 1}`,
              userId: this.currentUserId,
              userName: this.login_Account.name,
              userAvatar: this.defaultAvatar,
              content: this.newReply,
              replyTo: reply.userName,
              publishedAt: today.toISOString().slice(0, 10)+" "+today.toTimeString().slice(0,8),
            };
            parentComment.replies.push(newReply);
          }
        } else {
          const comment = this.comments.find(comment => comment.id === this.replyToCommentId);
          if (comment) {
            const newReply = {
              id: `${this.replyToCommentId}-${comment.replies.length + 1}`,
              userId: this.currentUserId,
              userName: this.login_Account.name,
              userAvatar: this.defaultAvatar,
              content: this.newReply,
              replyTo: comment.userName,
              publishedAt: today.toISOString().slice(0, 10)+" "+today.toTimeString().slice(0,8),
            };
            comment.replies.push(newReply);
          }
        }
        this.cancelReply();
      } else {
        alert("回复内容不能为空");
      }
    },
    reportComment(commentId) {
      const comment = this.comments.find(comment => comment.id === commentId);
      if (comment) {
        comment.reported = true;
      }
    },
    reviewComment(commentId, retain) {
      const commentIndex = this.comments.findIndex(comment => comment.id === commentId);
      if (commentIndex !== -1) {
        if (!retain) {
          this.comments.splice(commentIndex, 1);
        } else {
          this.comments[commentIndex].reported = false;
        }
      }
    },
    deletePost() {
      alert("帖子已删除");
      if(this.post.type === 'post'){
        for(let i = post_list.value.length - 1; i >= 0; i--){
          if(post_list.value[i].post.id === this.post.id){
            //需要删除热搜列表
            for(let j = trend_id_list.value.length - 1;j >= 0; j--){
              if(trend_id_list.value[j] === this.post.id)
                trend_id_list.value.splice(j,1);
            }
            post_list.value.splice(i,1);
          }
        }
      }else if(this.post.type === 'announcement'){
        for(let i = announcement_list.value.length - 1; i >= 0; i--){
          if(announcement_list.value[i].post.id === this.post.id){
            announcement_list.value.splice(i,1);
          }
        }
      }
      this.homepage_jump();
    },
    deleteComment(commentId) {
      this.comments = this.comments.filter(comment => comment.id !== commentId);
      this.comments.forEach(comment => {
        comment.replies = comment.replies.filter(reply => reply.id !== commentId);
      });
    }
  },
  computed: {
    in_trend() {
      return in_trend
    },
    reportedComments() {
      return this.comments.filter(comment => comment.reported);
    },
    isPostOwner() {
      return this.post.authorId === this.currentUserId;
    }
  },
  setup() {
    const router = useRouter();
    const homepage_jump = () => {
      setTimeout(() => { router.push({ name: 'HomePage' }); },500);
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

    const getPostInfo = (clicked_post_id) => {
      for(let i = 0; i < post_list.value.length; i++) {
        if(clicked_post_id.value === post_list.value[i].post.id) {
          return post_list.value[i];
        }
      }
      return undefined;
    }
    const click_post = getPostInfo(clicked_post_id);

    const getAnnouncementInfo = (clicked_post_id) => {
      for(let i = 0; i < announcement_list.value.length; i++) {
        if(clicked_post_id.value === announcement_list.value[i].post.id) {
          return announcement_list.value[i];
        }
      }
      return undefined;
    }
    const click_announcement = getAnnouncementInfo(clicked_announcement_id);

    const get_post_number = (author_id) => {
      for(let i = 0; i < account_info.value.length; i++) {
        if(author_id === account_info.value[i].account_id) {
          return account_info.value[i].post_number;
        }
      }
      return undefined;
    }

    const set_trend = (post_id) => {
      if(in_trend.value === false &&
          trend_number.value < max_trend_number.value) {
        trend_id_list.value.push(post_id);
        trend_number.value += 1;
        in_trend.value = true;
      }
    }
    const remove_trend = (post_id) => {
      if(in_trend.value === true){
        for(let i = trend_id_list.value.length - 1; i >= 0; i--){
          if(trend_id_list.value[i] === post_id){
            trend_id_list.value.splice(i,1);
            break;
          }
        }
        trend_number.value -= 1;
        in_trend.value = false;
      }
    };

    return {
      homepage_jump,
      login_Account,
      click_post,
      click_announcement,
      get_post_number,
      set_trend,
      remove_trend,
    }
  },
};
</script>

<template>
  <div class="post-detail-container">
    <div class="post-detail" v-if="post.id">
      <v-btn variant="tonal" style="width: 50px;min-width: 50px;background: none;"
             @click="homepage_jump();" class="back-button">
        <img src="../assets/back.svg" alt="svg" width="20" height="20">
      </v-btn>
      <div class="post-header">
        <img :src="post.authorAvatar || defaultAvatar" alt="作者头像" class="avatar">
        <div class="post-info">
          <h2>{{ post.title }}</h2>
          <div v-if="login_Account.privilege === 'admin' && post.type === 'post'">
            <button type="submit" @click="set_trend(post.id);"
                  v-if="in_trend.value === false" class="small-button">
              设为热搜
            </button>
            <button type="submit" @click="remove_trend(post.id);"
                    class="small-button" v-else>
              移除热搜
            </button>
          </div>
          <button v-if="isPostOwner || login_Account.privilege === 'admin'" style="margin-top: 3px;"
                  class="small-button" @click="deletePost">
            删除帖子
          </button>
        </div>
      </div>
      <div class="post-content">
        <p>{{ post.content }}</p>
      </div>
      <div class="post-meta">
        <p class="author">作者: {{ post.authorName }}</p>
        <p class="post_number">累计发布数量: {{ get_post_number(post.authorId) }}</p>
        <p class="time">发布时间: {{ post.publishedAt }}</p>
        <p class="views">浏览量: {{ post.viewCount }}</p>
      </div>
      <div class="comments">
        <h3>留言</h3>
        <div v-for="comment in comments" :key="comment.id" class="comment">
          <img :src="comment.userAvatar || defaultAvatar" alt="用户头像" class="avatar">
          <div class="comment-info">
            <p class="comment-user">{{ comment.userName }}</p>
            <p class="comment-time">{{ comment.publishedAt }}</p>
            <p class="comment-content">{{ comment.content }}</p>
            <div class="comment-buttons">
              <button class="small-button" @click="setReplyTo(comment.id, null)">回复</button>
              <button v-if="comment.userId === currentUserId" class="small-button" @click="deleteComment(comment.id)">删除</button>
              <button v-if="!comment.reported" class="small-button red-button" @click="reportComment(comment.id)">举报</button>
              <span v-else class="reported-text">已举报</span>
            </div>
            <div v-if="replyToCommentId === comment.id && replyToReplyId === null" class="reply-form">
              <form @submit.prevent="addReply">
                <div class="form-group">
                  <textarea v-model="newReply"  class="small-button textarea-white" :class="{ 'error-border': isAddingReply }" placeholder="输入你的回复"></textarea>
                </div>
                <button type="submit" class="small-button" @click="isAddingReply = true">提交回复</button>
                <button type="button" class="small-button" @click="cancelReply">取消</button>
              </form>
            </div>
            <div v-if="comment.replies.length > 0" class="replies">
              <div v-for="reply in comment.replies" :key="reply.id" class="reply">
                <img :src="reply.userAvatar || defaultAvatar" alt="用户头像" class="avatar">
                <div class="reply-info">
                  <p class="reply-user">{{ reply.userName }} 回复 {{ reply.replyTo }}</p>
                  <p class="reply-time">{{ reply.publishedAt }}</p>
                  <p class="reply-content">{{ reply.content }}</p>
                  <div class="reply-buttons">
                    <button class="small-button" @click="setReplyTo(comment.id, reply.id)">回复</button>
                    <button v-if="reply.userId === currentUserId" class="small-button" @click="deleteComment(reply.id)">删除</button>
                  </div>
                  <div v-if="replyToReplyId === reply.id" class="reply-form">
                    <form @submit.prevent="addReply">
                      <div class="form-group">
                        <textarea v-model="newReply"  class="small-button textarea-white" :class="{ 'error-border': isAddingReply }" placeholder="输入你的回复"></textarea>
                      </div>
                      <button type="submit" class="small-button" @click="addReply();">提交回复</button>
                      <button type="button" class="small-button" @click="cancelReply">取消</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <form @submit.prevent="addComment">
          <h3>添加留言</h3>
          <div class="form-group" :class="{ 'has-error': isAddingComment }">
            <textarea v-model="newComment" :class="{ 'highlight-input': isAddingComment }" placeholder="输入你的留言"></textarea>
          </div>
          <button type="submit" class="small-button" @click="isAddingComment = true">提交留言</button>
        </form>
        <div v-if="isAdmin">
          <h3>待审核的举报评论</h3>
          <div v-for="comment in reportedComments" :key="comment.id" class="comment reported-comment">
            <img :src="comment.userAvatar || defaultAvatar" alt="用户头像" class="avatar">
            <div class="comment-info">
              <p class="comment-user">{{ comment.userName }}</p>
              <p class="comment-time">{{ comment.publishedAt }}</p>
              <p class="comment-content">{{ comment.content }}</p>
            </div>
            <div class="comment-buttons">
              <button @click="reviewComment(comment.id, false)">删除</button>
              <button @click="reviewComment(comment.id, true)">保留</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <p>帖子已删除或不存在。</p>
    </div>
  </div>
</template>

<style scoped>
body {
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f0f0; /* 背景颜色 */
}

.post-detail-container {
  width: 100%;
  display: flex;
  justify-content: center;
}

.post-detail {
  width: 80%;
  max-width: 1200px;
  min-width: 600px;
  padding: 2rem;
  background-color: #fff;
  box-shadow: 0 0 1rem rgba(0, 0, 0, 0.1);
}

.back-button {
  position: absolute;
  top: 1rem;
  left: 1rem;
}

.post-header {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
  background-color: #fffbcc;
  padding: 1rem;
  border-radius: 0.5rem;
}

.avatar {
  width: 8rem;
  height: 8rem;
  border-radius: 50%;
  object-fit: cover;
}

.post-info {
  margin-left: 1.5rem;
  flex-grow: 1;
}

.post-info h2 {
  margin: 0;
}

.post-content {
  margin: 2rem 0;
  text-align: center;
  background-color: #fffbcc;
  padding: 2rem;
  border-radius: 0.5rem;
}

.post-meta {
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
  padding: 0 1rem;
  background-color: #fffbcc;
  border-radius: 0.5rem;
}

.comments {
  margin-top: 2rem;
  background-color: #fff2b3;
  padding: 2rem;
  border-radius: 0.5rem;
}

.comment {
  display: flex;
  align-items: flex-start;
  margin-bottom: 1rem;
  background-color: #fffbcc;
  padding: 1rem;
  border-radius: 0.5rem;
}

.comment .avatar {
  width: 4rem;
  height: 4rem;
}

.comment-info {
  margin-left: 1rem;
  flex-grow: 1;
}

.comment-info .comment-user {
  font-weight: bold;
}

.comment-info .comment-time {
  font-size: 0.9em;
  color: #888;
}

.comment-info .comment-content {
  margin-top: 0.5rem;
}

.comment-buttons {
  display: flex;
  align-items: center; /* 修改这里，使按钮垂直居中对齐 */
}

.small-button {
  padding: 0.2rem 0.5rem;
  font-size: 0.8rem;
  background-color: #e6b800;
  color: #fff;
  border: none;
  cursor: pointer;
}

.small-button:hover {
  background-color: #cc9900;
}

.red-button {
  background-color: red;
  color: white;
}

textarea {
  width: 100%; /* 文本框宽度设置为100% */
  padding: 0.8rem;
  box-sizing: border-box;
  background-color: white;
}

.textarea-white {
  background-color: white;
  color: black; /* 添加这行确保文本颜色为黑色 */
}

.textarea-white:focus {
  background-color: white;
  color: black; /* 添加这行确保在聚焦时文本颜色仍为黑色 */
}

button {
  padding: 0.5rem; /* 按钮的上下内边距减小 */
  font-size: 0.8rem;
  background-color: #e6b800;
  color: #fff;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #cc9900;
}

.reported-text {
  display: inline-block;
  padding: 0.2rem 0.5rem;
  font-size: 0.8rem;
  background-color: red; /* 修改为红色背景 */
  color: white; /* 修改为白色文字 */
  border: none;
  cursor: pointer;
  border-radius: 0.25rem; /* 添加圆角 */
  margin-left: 0.5rem; /* 添加一些左边距 */
}

.error-border {
  border: 2px solid red;
}

.reply-form {
  margin-top: 1rem; /* 增加回复表单和上面留言之间的间距 */
}

.replies {
  margin-left: 2rem;
  background-color: #fff2b3;
  padding: 1rem;
  border-radius: 0.5rem;
}

.reply {
  display: flex;
  align-items: flex-start;
  margin-bottom: 0.5rem;
  background-color: #fffbcc;
  padding: 0.5rem;
  border-radius: 0.5rem;
}

.reply .avatar {
  width: 3rem;
  height: 3rem;
}

.reply-info {
  margin-left: 0.5rem;
  flex-grow: 1;
}

.reply-info .reply-user {
  font-weight: bold;
}

.reply-info .reply-time {
  font-size: 0.9em;
  color: #888;
}

.reply-info .reply-content {
  margin-top: 0.5rem;
}

.reply-buttons {
  display: flex;
  gap: 0.5rem;
}

.has-error textarea {
  border: 2px solid red;
}

.highlight-input {
  border: 2px solid #ffcc00;
}

</style>








