<template>
  <div id="userLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <a-space>
          <img src="../assets/oj-logo.svg" class="logo" />
          <div>M OJ</div>
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a @click="showModal">点击联系作者：MAR1</a>
        <a-modal
          v-model:visible="isModalVisible"
          title="MAR1"
          @ok="handleOk"
          @cancel="handleCancel"
        >
          <img src="../assets/mar1.jpg" alt="Image" style="width: 100%" />
        </a-modal>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const isModalVisible = ref(false);

    const showModal = () => {
      isModalVisible.value = true;
    };

    const handleOk = () => {
      isModalVisible.value = false;
    };

    const handleCancel = () => {
      isModalVisible.value = false;
    };

    return {
      isModalVisible,
      showModal,
      handleOk,
      handleCancel,
    };
  },
};
</script>

<style scoped>
/* 设置整个布局背景颜色为 #fb9dc7 */
#userLayout {
  text-align: center;
  background-color: #282828; /* 设置主背景为 #fb9dc7 */
  min-height: 100vh;
}

/* logo 样式 */
#userLayout .logo {
  background-color: #282828; /* 统一背景颜色 */
  width: 64px;
  height: 64px;
}

/* header 样式 */
#userLayout .header {
  background-color: #282828; /* 统一背景颜色 */
  margin-top: 16px;
  margin-bottom: 48px;
  text-align: center;
}

/* content 样式 */
#userLayout .content {
  background-color: #282828; /* 统一背景颜色 */
  margin-bottom: 16px;
  padding: 20px;
  text-align: center;
}

/* footer 样式 */
#userLayout .footer {
  background-color: #282828; /* 统一背景颜色 */
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}

/* 确保 modal 的背景颜色和整体背景颜色一致 */
a-modal {
  background-color: #282828 !important;
}
</style>
