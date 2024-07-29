<template>
  <div id="app">
    <BasicLayout />
  </div>
</template>

<style>
#app {
}
</style>
<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { onMounted } from "vue";

/**
 * 全局初始化函数，有全局单词调用的代码，
 */
const doInit = () => {
  console.log("hello 这里是mar1");
};
onMounted(() => {
  doInit();
});
const router = useRouter();
const store = useStore();

router.beforeEach((to, from, next) => {
  //判断是否有管理员权限
  if (to.meta?.access === "canAdmin") {
    if (store.state.user.loginUser?.role !== "admin") {
      next("/noAuth");
      return;
    }
  }
  next();
  // console.log(to);
});
</script>
