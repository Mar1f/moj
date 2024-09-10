<template>
  <div id="basicLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <GlobalHeader />
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
import GlobalHeader from "@/components/header/GlobalHeader.vue";
import { ref } from "vue";

export default {
  components: { GlobalHeader },
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
/* 整体布局背景颜色 */
#basicLayout {
  background-color: #282828; /* 统一背景颜色为 #fb9dc7 */
  min-height: 100vh;
}

/* 头部背景颜色 */
#basicLayout .header {
  background-color: #282828; /* 头部背景 */
  margin-bottom: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15); /* 添加阴影效果 */
}

/* 内容区域背景颜色 */
#basicLayout .content {
  background-color: #282828; /* 内容区域背景 */
  margin-bottom: 16px;
  padding: 20px;
  min-height: 80vh; /* 确保内容区域的最小高度 */
}

/* 页脚背景颜色 */
#basicLayout .footer {
  background-color: #282828; /* 页脚背景 */
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
