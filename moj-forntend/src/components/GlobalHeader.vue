<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="title-bar">
            <img alt="logo" class="logo" src="../assets/logo.svg" />
            <div class="title">程崎 OJ</div>
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
    if (item.meta?.hiddenInMenu) {
      return false;
    }
    return checkAccess(
      store.state.user?.loginUser,
      item.meta?.access as string
    );
  })
);

const logout = () => {
  UserControllerService.userLogoutUsingPost();
  location.reload();
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
.userAvatar {
  cursor: pointer;
}

.avatarImg {
  height: 160px;
  width: 160px;
  border-radius: 50%;
  object-fit: cover;
}

.title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: #444;
  margin-right: 16px;
}

.logo {
  height: 48px;
}
</style>
