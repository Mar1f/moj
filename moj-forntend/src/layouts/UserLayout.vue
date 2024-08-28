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
#userLayout {
  text-align: center;
  background: url("https://gw.alipayobjects.com/zos/rmsportal/FfdJeJRQWjEeGTpqgBKj.png")
    0% 0% / 100% 100%;
}

#userLayout .logo {
  width: 64px;
  height: 64px;
}

#userLayout .header {
  margin-top: 16px;
  margin-bottom: 48px;
}

#userLayout .content {
  margin-bottom: 16px;
  padding: 20px;
}

#userLayout .footer {
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
