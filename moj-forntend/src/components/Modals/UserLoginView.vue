<template>
  <div class="box">
    <div class="content">
      <h2>登录</h2>
      <div>
        <a-input v-model="form.userAccount" placeholder="请输入用户名" />
      </div>
      <div>
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </div>
      <div>
        <input type="submit" value="登录" @click.prevent="handleSubmit" />
      </div>
    </div>
    <a href="#" class="click" @click.prevent="showModal = true">联系站长</a>
    <a href="#" class="click register" @click.prevent="handleRegister"
      >去注册</a
    >
    <a-modal
      v-model:visible="showModal"
      title="联系站长"
      @ok="handleOk"
      @cancel="handleCancel"
    >
      <img
        :src="contactImage"
        alt="联系站长图片"
        style="width: 50vw; height: auto; max-width: 100%"
      />
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { reactive } from "vue";
import { UserControllerService, UserLoginRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { ref } from "vue";
import store from "../../store";
// eslint-disable-next-line @typescript-eslint/no-var-requires
const contactImage = require("@/assets/mar1.jpg");
const form = reactive({
  userAccount: "",
  userPassword: "",
} as UserLoginRequest);

const showModal = ref(false);
const router = useRouter();

const handleSubmit = async () => {
  const res = await UserControllerService.userLoginUsingPost(form);
  if (res.code === 0) {
    message.success(
      `登录成功！欢迎${
        res.data.userName ? `【${res.data.userName}】` : ""
      }来到Moj系统`
    );

    await store.dispatch("user/getLoginUser");

    // 使用 router.push 进行跳转
    router.push({ path: "/", replace: true }).then(() => {
      // 跳转后刷新页面
      // window.location.reload();
    });
  } else {
    message.error("登陆失败，" + res.message);
  }
};

const handleRegister = async () => {
  store.dispatch("auth/toggleSignUp");
};

const handleOk = () => {
  showModal.value = false;
};

const handleCancel = () => {
  showModal.value = false;
};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  height: 100vh;
  background: #eff0f4;
  overflow: hidden;
}

.box {
  position: relative;
  display: flex;
  justify-content: space-between;
  margin: 100px auto;
  width: 470px;
}

.box .content {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  width: 350px;
  height: 350px;
  padding: 60px 20px;
  box-shadow: inset 20px 20px 20px rgba(0, 0, 0, 0.05),
    25px 35px 20px rgba(0, 0, 0, 0.05), 25px 30px 30px rgba(0, 0, 0, 0.05),
    inset -20px -20px 25px rgba(255, 255, 255, 0.9);
  border-radius: 52% 48% 33% 67% / 38% 45% 55% 62%;
  transition: 0.5s;
}

.box .content:hover {
  border-radius: 50%;
}

.box .content::before {
  content: "";
  position: absolute;
  top: 50px;
  left: 85px;
  width: 35px;
  height: 35px;
  border-radius: 50%;
  background: #fff;
  opacity: 0.9;
}

.box .content::after {
  content: "";
  position: absolute;
  top: 90px;
  left: 110px;
  width: 15px;
  height: 15px;
  border-radius: 50%;
  background: #fff;
  opacity: 0.9;
}

.box .content div {
  position: relative;
  width: 225px;
  border-radius: 25px;
  box-shadow: inset 2px 5px 10px rgba(0, 0, 0, 0.1),
    inset -2px -5px 10px rgba(255, 255, 255, 1),
    15px 15px 10px rgba(0, 0, 0, 0.05), 15px 10px 15px rgba(0, 0, 0, 0.025);
}

.box .content div::before {
  content: "";
  position: absolute;
  top: 8px;
  left: 50%;
  transform: translateX(-50%);
  width: 65%;
  height: 5px;
  background: rgba(255, 255, 255, 0.5);
  border-radius: 5px;
}

.box .content input {
  width: 100%;
  border: none;
  outline: none;
  background: transparent;
  font-size: 16px;
  padding: 10px 15px;
}

.box .content input[type="submit"] {
  color: #fff;
  cursor: pointer;
}

.box .content div:last-child {
  width: 120px;
  background: #ff0f5b;
  box-shadow: inset 2px 5px 10px rgba(0, 0, 0, 0.1),
    15px 15px 10px rgba(0, 0, 0, 0.05), 15px 10px 15px rgba(0, 0, 0, 0.025);
  transition: 0.5s;
}

.box .content div:last-child:hover {
  width: 150px;
}

.click {
  position: absolute;
  right: 0;
  bottom: 0;
  width: 120px;
  height: 120px;
  background: #c61dff;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  text-decoration: none;
  color: #fff;
  font-size: 14px;
  box-shadow: inset 10px 10px 10px rgba(190, 1, 254, 0.05),
    15px 25px 10px rgba(190, 1, 254, 0.1), 15px 20px 20px rgba(190, 1, 254, 0.1),
    inset -10px -10px 15px rgba(255, 255, 255, 0.5);
  border-radius: 44% 56% 65% 35% / 57% 58% 42% 43%;
}

.register {
  bottom: 150px;
  right: 0px;
  width: 80px;
  height: 80px;
  border-radius: 49% 51% 52% 48% / 63% 59% 41% 37%;
  background: #01b4ff;
  box-shadow: inset 10px 10px 10px rgba(1, 180, 255, 0.05),
    15px 25px 10px rgba(1, 180, 255, 0.1), 15px 20px 20px rgba(1, 180, 255, 0.1),
    inset -10px -10px 15px rgba(255, 255, 255, 0.5);
}

.click::before {
  content: "";
  position: absolute;
  top: 15px;
  left: 30px;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #fff;
  opacity: 0.45;
}

.register::before {
  left: 20px;
  width: 15px;
  height: 15px;
}

.click {
  transition: 0.25s;
}

.click:hover {
  border-radius: 50%;
}
</style>
