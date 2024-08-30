<template>
  <div class="box">
    <div class="content">
      <h2>用户注册</h2>
      <div>
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </div>
      <div>
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </div>
      <div>
        <a-input-password
          v-model="form.checkPassword"
          placeholder="请再次输入密码"
        />
      </div>
      <div>
        <input type="submit" value="注册" @click.prevent="handleSubmit" />
      </div>
    </div>
    <a href="#" class="click" @click.prevent="showModal = true">联系站长</a>
    <a href="#" class="click register" @click.prevent="handleLogin">去登录</a>
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

<script lang="ts" setup>
import { reactive } from "vue";
import { UserControllerService, UserRegisterRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { ref } from "vue";
// eslint-disable-next-line @typescript-eslint/no-var-requires
const contactImage = require("@/assets/mar1.jpg");

const showModal = ref(false);
const router = useRouter();

const form = reactive<UserRegisterRequest>({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
});

const handleSubmit = async () => {
  if (form.userAccount.length < 4 || form.userPassword.length < 6) {
    message.error("账号或密码长度不符合要求");
    return;
  }
  if (form.checkPassword !== form.userPassword) {
    message.error("两次输入密码不一致");
    return;
  }

  const res = await UserControllerService.userRegisterUsingPost(form);
  if (res.code === 0) {
    message.success("注册成功！");
    await router.push({ path: "login", replace: true });
  } else {
    message.error(res.message);
  }
};

const handleLogin = async () => {
  router.push({ path: "login", replace: true });
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
  margin: 150px auto;
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

.click {
  transition: 0.25s;
}

.click:hover {
  border-radius: 50%;
}
</style>
