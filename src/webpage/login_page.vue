<!--template:html,style:CSS,script:javascript*/-->
<script>
//export这玩意不能出现在script setup里面
  import { ref,onMounted } from "vue";
  import { useRouter } from "vue-router";
  import { getUserInfoById } from "@/api/api";
  import { account_info,login_id } from "@/test_data/account";
  // import bus from "@/bus/bus";

export default {
    data() {
      return {
        title: "2024 SE论坛登录",
        login: "登录",
        emptyAccount: "账号不可为空",
        emptyPassword: "密码不可为空",
        accountNotExist: "账号不存在",
        passwordError: "密码错误",
      }
    },
    methods: {

    },
    setup() {
      //使用setup方法时，不要再额外定义script setup结点了
      const click = ref(false);
      const login_handle = () => {
        click.value = !click.value;
      };

      const input_account = ref('');
      const input_password = ref('');
      const account_empty = ref(false);
      const password_empty = ref(false);
      const account_not_exist = ref(false);
      const password_error = ref(false);
      const account_privilege = ref('');
      const info_check = () => {
        if (click.value === true) {
          //作信息检查时阻塞按钮
          button_disabled.value = true;
          account_empty.value = input_account.value.length === 0;
          password_empty.value = input_password.value.length === 0;
          if (!account_empty.value && !password_empty.value) {
            for (let i = 0; i < account_info.value.length; i++) {
              if (input_account.value === account_info.value[i].account_id) {
                //账号ID若存在,检查密码
                password_error.value = input_password.value !== account_info.value[i].password;
                if (password_error.value === false) {
                  account_privilege.value = account_info.value[i].privilege;
                  login_id.value = input_account.value;
                  setTimeout(jump_to_homepage, 500);
                  return;
                }
              }
            }
            if (password_error.value === false) account_not_exist.value = true;
          }else if(!account_empty.value && password_empty.value){
            account_not_exist.value = true;
            for (let i = 0; i < account_info.value.length; i++) {
              if(input_account.value === account_info.value[i].account_id) {
                account_not_exist.value = false;
                break;
              }
            }
          }
          setTimeout(enable_button, 500);
        }
      };

      const button_disabled = ref(false);
      const enable_button = () => {
        if (click.value === true) {
          button_disabled.value = false;
          click.value = false;
        }
      };

      const router = useRouter();
      const jump_to_homepage = () => {
        //编程式跳转
        //setup中无法使用this.$router.因为this无法访问.
        router.push({name: 'HomePage'});
      };

      return {
        input_account,
        input_password,
        account_privilege,
        click,
        account_empty,
        password_empty,
        login_handle,
        info_check,
        button_disabled,
        account_not_exist,
        password_error,
      }
    },
  };
</script>

<template>
  <body style="justify-content: flex-end;">
  <h1 class="title">{{title}}</h1>
  <div class="container">
    <div class="group">
      <img src="../assets/ZJU_badge.png" alt="png" class="image">
      <div style="text-align: center;vertical-align: center;top:5px;position: relative">
        <VaInput
            v-model="input_account"
            label="账号"
            placeholder="Error with account id"
            immediate-validation
            error
            :error-messages="emptyAccount"
            v-if="account_empty === true"
        />
        <VaInput
            v-model="input_account"
            label="账号"
            placeholder="Error with account id"
            immediate-validation
            error
            :error-messages="accountNotExist"
            v-else-if="account_not_exist === true && account_empty === false"
        />
        <VaInput
            v-model="input_account"
            label="账号"
            placeholder="Enter your account id"
            immediate-validation
            v-else
        />
      </div>
      <div style="text-align: center;vertical-align: center;top:10px;position: relative">
        <VaInput
            v-model="input_password"
            label="密码"
            placeholder="Error with password"
            immediate-validation
            error
            :error-messages="emptyPassword"
            v-if="password_empty === true"
            type="password"
        />
        <VaInput
            v-model="input_password"
            label="密码"
            placeholder="Error with password"
            immediate-validation
            error
            :error-messages="passwordError"
            v-else-if="password_error === true && password_empty === false"
            type="password"
        />
        <VaInput
            v-model="input_password"
            label="密码"
            immediate-validation
            placeholder="Enter your password"
            v-else
            type="password"
        />
      </div>
      <div style="vertical-align: center;">
        <v-container class="text-center">
          <v-row justify="center" style="width:100px;height:50px;position: relative;
              margin-left: auto;margin-right: auto;top: 10px">
              <v-btn rounded="xl" size="x-large" block
                     style="position: relative;top:10px;min-width: 200px;background: green;color: white;height: 50px;"
                     @click="login_handle();info_check();" :disabled="button_disabled">
                <p v-if="click === false">{{ login }}</p>
                <v-progress-circular
                    color="primary"
                    indeterminate
                    v-else
                ></v-progress-circular>
              </v-btn>
          </v-row>
        </v-container>
      </div>
    </div>
  </div>
  </body>
</template>

<style scoped>
  body{
    background: white;
    background-size: cover;
    position: relative;
    right: 0;
    left: 0;
    top:0;
    bottom:0;
    overflow: hidden;
    min-height: 100vh;
  }
  .title {
    position: relative;
    top: 40px;
    text-align: center;
    font-size: 50px;
    font-family: "Microsoft YaHei UI", monospace;
    color: #23c18c;
  }
  .container {
    position: relative;
    display: flex;
    justify-items: center;
    width: 30%;
    height: 400px;
    margin-top: 60px;
    margin-left: auto;
    margin-right: auto;

    background: white;
    border-radius: 30px;
    border: solid black;
  }
  .group {
    width: 200px;
    height: 200px;
    position: relative;
    vertical-align: center;
    margin-left: auto;
    margin-right: auto;
  }
  .style1 {
    position: relative;
    margin-top: 15px;
    height: 20px;
    font-size: large;
    text-align: center;
    font-family: "Microsoft YaHei UI",monospace;
  }
  .input_blank {
    background: white;
  }
  .Login_button {
    border: black;
    background-color: black;
    border-radius: 15px;
    top: 40px;
    width: 100px;
    height: 30px;
    font-family: "Microsoft YaHei UI",monospace;
    font-size: 15px;
    color: white;
    margin-top: 20px;
    position: relative;
  }
  .Login_button:hover{
    background-color: #6e6d6d;
    cursor: pointer;
  }
  .line{
    display: block;
    width: 400px;
    height: 1.5px;
    background-color: black;
    position: relative;
    top: -60px;
    margin-left: auto;
    margin-right: auto;

    transform: translateY(-50%);
  }
  .image {
    position: relative;
    top: 20px;
    overflow: hidden;
    width: 200px;
    height: 150px;
    display: block;
    margin-left: auto;
    margin-right: auto;
  }
</style>