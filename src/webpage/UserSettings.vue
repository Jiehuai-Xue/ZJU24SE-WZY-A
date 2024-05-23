<template>
  <div class="user-settings">
    <h2>个人设置</h2>
    <div class="profile">
      <img :src="user.avatar || defaultAvatar" alt="用户头像" class="avatar">
      <div class="info">
        <div class="form-group">
          <label for="id">用户 ID</label>
          <input type="text" id="id" v-model="user.id" disabled>
        </div>
        <div class="form-group">
          <label for="role">身份</label>
          <input type="text" id="role" v-model="user.role" disabled>
        </div>
      </div>
    </div>
    <form @submit.prevent="validateForm">
      <h3>修改资料</h3>
      <div class="form-group" :class="{ 'has-error': errors.name }">
        <label for="name">名称</label>
        <input type="text" id="name" v-model="user.name">
        <p v-if="errors.name" class="error-message">{{ errors.name }}</p>
      </div>
      <div class="form-group">
        <label for="avatar">头像</label>
        <input type="file" id="avatar" @change="onFileChange">
      </div>
      <div class="form-group" :class="{ 'has-error': errors.password }">
        <label for="password">密码</label>
        <input type="password" id="password" v-model="user.password">
        <p v-if="errors.password" class="error-message">{{ errors.password }}</p>
      </div>
      <button type="submit">提交更改</button>
    </form>
    <button @click="logout" class="logout">退出登录</button>
    <button @click="deleteAccount" class="delete-account">注销账号</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        id: "12345",
        name: "张三",
        avatar: "",
        password: "123456",
        status: "online",
        role: "student"
      },
      defaultAvatar: "./src/assets/avator1.jpg", // 默认头像
      errors: {
        name: "",
        password: ""
      }
    };
  },
  methods: {
    onFileChange(e) {
      const file = e.target.files[0];
      const reader = new FileReader();

      reader.onload = (event) => {
        this.user.avatar = event.target.result;
      };

      reader.readAsDataURL(file);
    },
    validateForm() {
      this.errors.name = this.user.name.trim() === "" ? "名称不能为空" : "";
      this.errors.password = this.user.password.trim() === "" ? "密码不能为空" : this.user.password.length < 6 ? "密码不能少于6位" : "";

      if (!this.errors.name && !this.errors.password) {
        this.updateSettings();
      }
    },
    updateSettings() {
      alert("设置已更新");
      console.log("Updated user settings:", this.user);
    },
    logout() {
      alert("已退出登录");
    },
    deleteAccount() {
      if (confirm("确定要注销账号吗？此操作无法撤销。")) {
        alert("账号已注销");
      }
    }
  }
};
</script>
<style scoped>
body {
  background-color: #e0ffff; /* 浅青色 */
  margin: 0;
  font-family: Arial, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}

.user-settings {
  width: 100%;
  max-width: 600px;
  margin: 20px;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  box-sizing: border-box;
  margin-left: auto;
  margin-right: auto;
}

.user-settings h2 {
  text-align: center;
  margin-bottom: 20px;
}

.profile {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  width: 100%;
  flex-wrap: wrap;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}

.info {
  flex-grow: 1;
  margin-left: 20px;
}

.form-group {
  margin-bottom: 15px;
  width: 100%;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

.form-group.has-error input {
  border-color: red;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-top: 5px;
}

button {
  display: block;
  width: 100%;
  padding: 10px;
  margin-top: 10px;
  font-size: 16px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.logout {
  background-color: #f0ad4e;
}

.logout:hover {
  background-color: #ec971f;
}

.delete-account {
  background-color: #d9534f;
}

.delete-account:hover {
  background-color: #c9302c;
}

/* 响应式设计 */
@media (max-width: 600px) {
  .profile {
    flex-direction: column;
    align-items: center;
  }

  .info {
    margin-left: 0;
    margin-top: 20px;
  }
}
</style>