<template>
  <div class="post-detail-container">
    <div class="post-detail" v-if="post.id">
      <v-btn icon to="/previous-page" class="back-button" @click="homepage_jump();">
        <v-icon color="primary" size="24">mdi-arrow-left</v-icon>
      </v-btn>
      <div class="post-header">
        <img :src="post.authorAvatar || defaultAvatar" alt="作者头像" class="avatar">
        <div class="post-info">
          <h2>{{ post.title }}</h2>
          <button v-if="isPostOwner" @click="deletePost">删除帖子</button>
        </div>
      </div>
      <div class="post-content">
        <p>{{ post.content }}</p>
      </div>
      <div class="post-meta">
        <p class="author">作者: {{ post.authorName }}</p>
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
            <button @click="setReplyTo(comment.id)">回复</button>
            <button v-if="comment.userId === currentUserId" @click="deleteComment(comment.id)">删除</button>
            <button v-if="!comment.reported" @click="reportComment(comment.id)">举报</button>
            <span v-else>已举报</span>
            <div v-if="comment.replies.length > 0" class="replies">
              <div v-for="reply in comment.replies" :key="reply.id" class="reply">
                <img :src="reply.userAvatar || defaultAvatar" alt="用户头像" class="avatar">
                <div class="reply-info">
                  <p class="reply-user">{{ reply.userName }}</p>
                  <p class="reply-time">{{ reply.publishedAt }}</p>
                  <p class="reply-content">{{ reply.content }}</p>
                  <button v-if="reply.userId === currentUserId" @click="deleteComment(reply.id)">删除</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <form @submit.prevent="addComment">
          <h3>添加留言</h3>
          <div class="form-group">
            <textarea v-model="newComment" placeholder="输入你的留言"></textarea>
          </div>
          <button type="submit">提交留言</button>
        </form>
        <form v-if="replyToCommentId" @submit.prevent="addReply">
          <h3>回复评论</h3>
          <div class="form-group">
            <textarea v-model="newReply" placeholder="输入你的回复"></textarea>
          </div>
          <button type="submit">提交回复</button>
          <button type="button" @click="cancelReply">取消</button>
        </form>
        <div v-if="isAdmin">
          <h3>待审核的举报评论</h3>
          <div v-for="comment in reportedComments" :key="comment.id" class="reported-comment">
            <p>{{ comment.content }}</p>
            <button @click="reviewComment(comment.id, false)">删除</button>
            <button @click="reviewComment(comment.id, true)">保留</button>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <p>帖子已删除或不存在。</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      post: {
        id: "1",
        title: "求助帖",
        content: "请问谁知道章老师的免疫学论文的字数要求",
        authorId: "123",
        authorName: "张三",
        authorAvatar: "./src/assets/avator1.jpg",
        publishedAt: "2024-05-20 14:00",
        status: "正常",
        viewCount: 0
      },
      comments: [
        {
          id: "1",
          userId: "234",
          userName: "李四",
          userAvatar: "./src/assets/avator2.jpg",
          content: "应该是3000字",
          publishedAt: "2024-05-20 15:00",
          replies: [
            {
              id: "1-1",
              userId: "456",
              userName: "张三",
              userAvatar: "./src/assets/avator1.jpg",
              content: "谢谢你的回答！",
              publishedAt: "2024-05-20 16:30"
            }
          ],
          reported: false
        },
        {
          id: "2",
          userId: "345",
          userName: "王五",
          userAvatar: "./src/assets/avator3.jpg",
          content: "我也不记得了",
          publishedAt: "2024-05-20 16:00",
          replies: [],
          reported: false
        }
      ],
      newComment: "",
      replyToCommentId: null,
      newReply: "",
      defaultAvatar: "./src/assets/avator1.jpg",
      isAdmin: true, // 当前用户是否是管理员
      currentUserId: "456" // 当前登录用户的ID
    };
  },
  created() {
    this.incrementViewCount();
  },
  methods: {
    incrementViewCount() {
      this.post.viewCount += 1;
    },
    addComment() {
      if (this.newComment.trim() !== "") {
        const newComment = {
          id: String(this.comments.length + 1),
          userId: this.currentUserId,
          userName: "当前用户",
          userAvatar: this.defaultAvatar,
          content: this.newComment,
          publishedAt: new Date().toISOString().slice(0, 19).replace('T', ' '),
          replies: [],
          reported: false
        };
        this.comments.push(newComment);
        this.newComment = "";
      } else {
        alert("留言内容不能为空");
      }
    },
    setReplyTo(commentId) {
      this.replyToCommentId = commentId;
    },
    cancelReply() {
      this.replyToCommentId = null;
      this.newReply = "";
    },
    addReply() {
      if (this.newReply.trim() !== "") {
        const reply = {
          id: `${this.replyToCommentId}-${this.comments.find(comment => comment.id === this.replyToCommentId).replies.length + 1}`,
          userId: this.currentUserId,
          userName: "当前用户",
          userAvatar: this.defaultAvatar,
          content: this.newReply,
          publishedAt: new Date().toISOString().slice(0, 19).replace('T', ' ')
        };
        const comment = this.comments.find(comment => comment.id === this.replyToCommentId);
        if (comment) {
          comment.replies.push(reply);
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
      // 清空帖子数据
      this.post = {};
      this.comments = [];
      // 例如，重定向到帖子列表页
      // window.location.href = "/posts";
    },
    deleteComment(commentId) {
      this.comments = this.comments.filter(comment => comment.id !== commentId);
      // 如果是回复，还需要在父评论的replies中删除
      this.comments.forEach(comment => {
        comment.replies = comment.replies.filter(reply => reply.id !== commentId);
      });
    },
    homepage_jump() {
      setTimeout(() => { this.router.push({ name: 'HomePage' }) }, 500);
    },
  },
  computed: {
    reportedComments() {
      return this.comments.filter(comment => comment.reported);
    },
    isPostOwner() {
      return this.post.authorId === this.currentUserId;
    }
  }
};
</script>

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

.replies {
  margin-left: 2rem;
  background-color: #fff2b3;
  padding: 1rem;
  border-radius: 0.5rem;
}

.reply {
  display: flex;
  align-items: flex-start;
  margin-bottom: 1rem;
  background-color: #fffbcc;
  padding: 1rem;
  border-radius: 0.5rem;
}

.reply .avatar {
  width: 2.5rem;
  height: 2.5rem;
}

.reply-info {
  margin-left: 1rem;
  flex-grow: 1;
}

.reply-info .reply-user {
  font-weight: bold;
}

.reply-info .reply-time {
  font-size: 0.8em;
  color: #888;
}

.reply-info .reply-content {
  margin-top: 0.5rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

textarea {
  width: 100%;
  padding: 0.8rem;
  box-sizing: border-box;
}

button {
  display: block;
  width: 100%;
  padding: 1rem;
  font-size: 1rem;
  background-color: #e6b800;
  color: #fff;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #cc9900;
}

.reported-comment {
  background-color: #ffe6e6;
  padding: 1rem;
  border-radius: 0.5rem;
  margin-bottom: 1rem;
}

/* Media queries for responsiveness */
@media (max-width: 1200px) {
  .post-detail {
    width: 90%;
  }
}

@media (max-width: 768px) {
  .post-detail {
    width: 100%;
    padding: 1rem;
  }

  .post-header,
  .post-content,
  .post-meta,
  .comments,
  .comment,
  .replies,
  .reply,
  .reported-comment {
    padding: 1rem;
  }

  .avatar {
    width: 6rem;
    height: 6rem;
  }

  .comment .avatar,
  .reply .avatar {
    width: 3rem;
    height: 3rem;
  }

  .post-header {
    flex-direction: column;
    align-items: flex-start;
  }

  .post-info {
    margin-left: 0;
    margin-top: 1rem;
  }

  .post-meta {
    flex-direction: column;
    align-items: flex-start;
  }
}
</style>