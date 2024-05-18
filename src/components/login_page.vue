<!--template:html,style:CSS,script:javascript*/-->
<script>
//export这玩意不能出现在script setup里面
  import { ref } from "vue";
  import { useRouter,useRoute } from "vue-router";

export default {
    data() {
      return {
        title: "2024SE论坛登录",
        login: "登录",
        emptyAccount: "大哥，输账号啊",
        emptyPassword: "兄啊，你要免密登录吗",
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
      const info_check = () => {
        if (click.value === true) {
          //作信息检查时阻塞按钮
          button_disabled.value = true;
          account_empty.value = input_account.value.length === 0;
          password_empty.value = input_password.value.length === 0;
          setTimeout(enable_button, 2000);
          if (!account_empty.value && !password_empty.value) {
            setTimeout(jump_to_homepage, 2000);
          }
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
        router.push('/home_page');
      };

      return {
        input_account,
        input_password,
        click,
        account_empty,
        password_empty,
        login_handle,
        info_check,
        button_disabled,
      }
    },
  };
</script>

<template>
  <body>
  <h1 class="title">{{title}}</h1>
  <div class="container">
    <div class="group">
      <img src="../assets/ZJU_badge.jpg" alt="" class="image" >
      <div style="text-align: center;vertical-align: center;top:5px;position: relative">
        <VaInput
            v-model="input_account"
            label="请输入账号"
            placeholder="Enter your account id"
            immediate-validation
            v-if="account_empty === false"
        />
        <VaInput
            v-model="input_account"
            label="请输入账号"
            placeholder="Error with account id"
            immediate-validation
            error
            :error-messages="emptyAccount"
            v-else
        />
      </div>
      <div style="text-align: center;vertical-align: center;top:10px;position: relative">
        <VaInput
            v-model="input_password"
            label="请输入密码"
            immediate-validation
            placeholder="Enter your password"
            v-if="password_empty === false"
        />
        <VaInput
            v-model="input_password"
            label="请输入密码"
            placeholder="Error with password"
            immediate-validation
            error
            :error-messages="emptyPassword"
            v-else
        />
      </div>
      <div style="vertical-align: center;">
        <v-container class="text-center">
          <v-row justify="center" style="width:100px;height:50px;position: relative;
              margin-left: auto;margin-right: auto;top: 10px">
              <v-btn rounded="xl" size="x-large" block style="position: relative;top:10px"
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
    background: url(../assets/ZJU_ZJG.jpg) no-repeat center;
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
    top: 50px;
    text-align: center;
    font-size: 50px;
    font-family: "Microsoft YaHei UI", monospace;
    color: #23c18c;
  }
  .container {
    position: relative;
    display: flex;
    justify-items: center;
    width: 400px;
    height: 400px;
    margin-top: 60px;
    margin-left: auto;
    margin-right: auto;

    background: white;
    border-radius: 50px;
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
    position: relative;
    text-align: center;
    height: 25px;
    width: 180px;
    margin-top: 10px;
    border-radius: 15px;
    font-style: normal;
    border: solid black;
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

  .v-progress-circular {
    margin: 1rem;
  }
</style>