<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item key="0">
          <div class="title-bar">
            <img src="../../assets/logo_light.svg" v-if="theme === 'light'" />
            <img src="../../assets/logo_dark.svg" v-else />
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          <component :is="item.meta?.icon"> </component>
          <span>{{ item.name }}</span>
        </a-menu-item>
      </a-menu>
    </a-col>
    <!--    <a-col flex="50px"> <LightSetting /> </a-col>-->
    <a-col flex="100px">
      <userAvatar />
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import { routes } from "@/router/routes";
import { ref, computed } from "vue";
import { useStore } from "vuex";
import userAvatar from "@/components/setting/userAvatar.vue";
import checkAccess from "@/access/checkAccess";
import LightSetting from "@/components/setting/LightSetting.vue";

const store = useStore();
const router = useRouter();

const user = store.state.user;
const theme = computed(() => store.state.theme.theme);
const visibleRoutes = computed(() => {
  const loginUser = user.loginUser;
  return routes.filter((item, index) => {
    if (item.meta?.hideInMenu || item.path === "/user") {
      return false;
    }
    return checkAccess(loginUser, item.meta?.access as string);
  });
});
// 默认主页
const selectedKeys = ref(["/"]);

// 路由跳转后，更新选中的菜单项的高亮
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});
const doMenuClick = (key: string) => {
  if (key === "0") {
    router.push({
      path: "/",
    });
  } else {
    router.push({
      path: key,
    });
  }
};
</script>

<style scoped>
#globalHeader {
  height: 70px;
  border-bottom: 1px solid #eee;
}
.title-bar {
  padding-top: 10px;
}

:deep(.arco-menu-horizontal .arco-menu-inner) {
  height: 70px;
  padding: 0px 20px;
}
</style>
