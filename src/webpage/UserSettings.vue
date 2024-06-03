<script>
import { ref } from 'vue';
import { useRouter } from "vue-router";
import avatar from "@/assets/default_avatar_boy.png";
import { account_info,login_id } from "@/test_data/account";
import router from "@/router/router";

export default {
  data() {
    return {
      input_name: "",
      input_password: "",
      user: {
        name: this.login_Account === undefined ? undefined : this.login_Account.name,
        id: login_id,
        role: this.login_Account === undefined ? undefined : this.login_Account.identity,
        post_number: this.login_Account === undefined ? undefined : this.login_Account.post_number,
      },
      defaultAvatar: avatar, // 默认头像
      errors: {
        name: "",
        password: ""
      },
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
      // this.errors.name = this.user.name.trim() === "" ? "名称不能为空" : "";
      this.errors.password = this.user.password.trim() === "" ? "密码不能为空" : this.user.password.length < 6 ? "密码不能少于6位" : "";

      if (!this.errors.password) {
        this.updateSettings();
      }
    },
    updateSettings() {
      alert("设置已更新,请重新登录");
      console.log("Updated user settings:", this.user);
      // this.user.name = this.input_name;
      this.user.password = this.input_password;
      for(let i=0; i < account_info.value[i].length; i++) {
        if(account_info.value[i].account_id === login_id.value){
          account_info.value[i].password = this.input_password;
          break;
        }
      }
      // this.user.input_name = "";
      this.user.input_password = "";
      this.logout();
    },
    deleteAccount() {
      if (confirm("确定要注销账号吗？此操作无法撤销。")) {
        alert("账号已注销");
      }
    },
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

    const logout = () => {
      setTimeout(() => { router.push({ name: "Login" }); },500);
    }

    return {
      homepage_jump,
      login_Account,
      logout,
    }
  },
};
</script>

<template>
  <div class="page-container">
    <v-btn variant="tonal" style="width: 50px;min-width: 50px;background: none;"
           @click="homepage_jump();" class="back-button">
      <img src="../assets/back.svg" alt="svg" width="20" height="20">
    </v-btn>
    <div class="user-settings">
      <div class="profile-section">
        <h2>个人设置</h2>
        <div class="profile">
          <img src="@/assets/default_avatar_boy.png" alt="用户头像" class="avatar">
          <div class="info">
            <div class="form-group">
              <label for="id">用户 ID</label>
              <input type="text" id="id" v-model="user.id" disabled>
            </div>
<!--            <div class="form-group">-->
<!--              <label for="name">名称</label>-->
<!--              <input type="text" id="role" v-model="user.name" disabled>-->
<!--            </div>-->
            <div class="form-group">
              <label for="role">身份</label>
              <input type="text" id="role" v-model="user.role" disabled>
            </div>
            <div class="form-group">
              <label for="role">累计发文数量</label>
              <input type="text" id="role" v-model="user.post_number" disabled>
            </div>
          </div>
        </div>

      </div>
      <div class="edit-section">
        <form>
          <h3>修改资料</h3>
<!--          <div class="form-group" :class="{ 'has-error': errors.name }">-->
<!--            <label for="name">名称</label>-->
<!--            <input type="text" id="name" v-model="input_name" class="highlight-input">-->
<!--            <p v-if="errors.name" class="error-message">{{ errors.name }}</p>-->
<!--          </div>-->
          <div class="form-group">
            <label for="avatar">头像</label>
            <input type="file" id="avatar" @change="onFileChange">
          </div>
          <div class="form-group" :class="{ 'has-error': errors.password }">
            <label for="password">密码</label>
            <input type="password" id="password" v-model="input_password" class="highlight-input">
            <p v-if="errors.password" class="error-message">{{ errors.password }}</p>
          </div>
          <button type="submit" @click="validateForm();">提交更改</button>
        </form>
      </div>
      <button @click="logout();" class="logout">退出登录</button>
      <button @click="deleteAccount" class="delete-account">注销账号</button>
    </div>
  </div>
</template>

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

.page-container {
  width: 100%;
  max-width: 1200px;
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 25vh;
  margin-left: auto;
  margin-right: auto;
}

.back-button {
  position: absolute;
  top: 20px;
  left: 20px;
}

.user-settings {
  width: 100%;
  margin: 20px;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column; /* 将方向改为列方向 */
  box-sizing: border-box;
}

.profile-section, .edit-section {
  margin-bottom: 20px; /* 底部间距 */
}

.profile-section h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333; /* 更改颜色为黑色 */
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

.highlight-input {
  background-color: #f0f0f0; /* Light grey background */
  border: 1px solid #ccc;
}

.highlight-input:focus {
  background-color: #e0e0e0; /* Slightly darker grey on focus */
  border-color: #007bff; /* Blue border on focus */
}

/* 响应式设计 */
@media (max-width: 600px) {
  .user-settings {
    flex-direction: column; /* Stack sections vertically on small screens */
  }

  .profile {
    flex-direction: column;
    align-items: center;
  }

  .info {
    margin-left: 0;
    margin-top: 20px;
    color: #333;
    border: 1px solid #333; /* 添加边框 */
    padding: 5px; /* 添加内边距 */
    border-radius: 5px; /* 圆角边框 */
  }
}
</style>
