<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        theme="dark"
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
        class="menu"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px', color: '#282828' }"
          disabled
        >
          <div class="title-bar">
            <!--            <img alt="logo" class="logo" src="../assets/logo.svg" />-->
            <div class="title">M OJ</div>
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="100px">
      <div class="userAvatar">
        <a-dropdown trigger="hover">
          <a-avatar shape="circle">
            <template
              v-if="loginUser && loginUser.userRole as string !== ACCESS_ENUM.NOT_LOGIN"
            >
              <template v-if="loginUser.userAvatar">
                <img
                  class="avatarImg"
                  alt="avatar"
                  :src="loginUser.userAvatar"
                />
              </template>
              <template v-else>
                <a-avatar>
                  <IconUser />
                </a-avatar>
              </template>
            </template>
            <template v-else>
              <a-avatar>未登录</a-avatar>
            </template>
          </a-avatar>
          <template #content>
            <template
              v-if="loginUser && loginUser.userRole as string !== ACCESS_ENUM.NOT_LOGIN"
            >
              <a-doption>
                <template #icon>
                  <icon-idcard />
                </template>
                <template #default>
                  <a-anchor-link
                    @click="
                      router.push({
                        path: '/about',
                      })
                    "
                    >个人信息
                  </a-anchor-link>
                </template>
              </a-doption>
              <a-doption>
                <template #icon>
                  <icon-poweroff />
                </template>
                <template #default>
                  <a-anchor-link @click="logout">退出登录</a-anchor-link>
                </template>
              </a-doption>
            </template>
            <template v-else>
              <a-doption>
                <template #icon>
                  <icon-user />
                </template>
                <template #default>
                  <a-anchor-link
                    @click="
                      router.push({
                        path: '/user/login',
                      })
                    "
                    >用户登录
                  </a-anchor-link>
                </template>
              </a-doption>
              <a-doption>
                <template #icon>
                  <icon-user-add />
                </template>
                <template #default>
                  <a-anchor-link
                    @click="
                      router.push({
                        path: '/user/register',
                      })
                    "
                    >用户注册
                  </a-anchor-link>
                </template>
              </a-doption>
            </template>
          </template>
        </a-dropdown>
      </div>
    </a-col>
  </a-row>
</template>

<script lang="ts" setup>
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import { checkAccess } from "@/access/checkAccess";
import { LoginUserVO, UserControllerService } from "../../generated";
import { ACCESS_ENUM } from "@/access/accessEnum";

const router = useRouter();
const store = useStore();
const selectedKeys = ref(["/"]);

const loginUser: LoginUserVO = computed(
  () => store.state.user?.loginUser
) as LoginUserVO;
const visibleRoutes = computed(() =>
  routes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    return checkAccess(
      store.state.user?.loginUser,
      item.meta?.access as string
    );
  })
);

const logout = () => {
  UserControllerService.userLogoutUsingPost().then(() => {
    router.replace({ path: "user/login" }).then(() => {
      location.reload();
    });
  });
};

router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
  console.log(from, failure);
});
const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
</script>

<style scoped>
/* 整个页面背景颜色设置为#fb9dc7 */
body {
  background-color: #282828;
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

/* 顶部菜单栏背景颜色设置为#333 */
#globalHeader {
  background-color: #282828; /* 修改导航栏背景色 */
  padding: 10px 20px;
}

/* 设置菜单项的背景颜色和文字颜色 */
.menu {
  background-color: #282828; /* 修改导航栏菜单背景色 */
}

.menu .ant-menu-item {
  color: #282828; /* 菜单项文字颜色 */
}

.menu .ant-menu-item:hover {
  background-color: #282828; /* 鼠标悬停时的背景色 */
}

/* 用户头像样式 */
.userAvatar {
  cursor: pointer;
}

/* 用户头像图片样式 */
.avatarImg {
  background: #090a0a;
  height: 160px;
  width: 160px;
  border-radius: 50%;
  object-fit: cover;
}

/* 标题栏背景颜色设置为#333 */
.title-bar {
  background: #282828;
  display: flex;
  align-items: center;
}

.title {
  color: #090a0a; /* 标题文字颜色 */
  margin-right: 16px;
}

/* Logo样式 */
.logo {
  height: 48px;
}
:root {
  --arco-menu-item-color: #3491fa; /* 修改菜单项文字颜色 */
  --arco-menu-item-hover-color: #3491fa; /* 修改菜单项悬停颜色 */
}

.custom-menu {
  --arco-menu-item-color: var(--arco-menu-item-color);
  --arco-menu-item-hover-color: var(--arco-menu-item-hover-color);
}
/* 去除菜单项的默认背景色、边框和按钮样式 */
.menu .ant-menu-item {
  background-color: transparent !important; /* 默认背景色设置为透明 */
  border: none !important; /* 去除边框 */
  box-shadow: none !important; /* 去除阴影 */
}

/* 去除悬停时的背景色和按钮效果 */
.menu .ant-menu-item:hover {
  background-color: transparent !important; /* 鼠标悬停时背景色设置为透明 */
  border: none !important; /* 去除边框 */
  box-shadow: none !important; /* 去除阴影 */
}

/* 去除选中项的背景色和按钮效果 */
.menu .ant-menu-item-selected {
  background-color: transparent !important; /* 选中项的背景色设置为透明 */
  border: none !important; /* 去除边框 */
  box-shadow: none !important; /* 去除阴影 */
}
</style>
