<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view />
    </template>
    <template v-else>
      <BasicLayout />
    </template>
  </div>
</template>

<style>
#app {
  background: #fb9dc7;
}
</style>
<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout";
import { onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();

/**
 * 全局初始化函数，有全局单次调用的代码，都可以写到这里
 */
const doInit = () => {
  console.log(
    "                           _ooOoo_\n" +
      "                          o8888888o\n" +
      '                          88" . "88\n' +
      "                          (| -_- |)\n" +
      "                          O\\  =  /O\n" +
      "                       ____/`---'\\____\n" +
      "                     .'  \\\\|     |//  `.\n" +
      "                    /  \\\\|||  :  |||//  \\\n" +
      "                   /  _||||| -:- |||||-  \\\n" +
      "                   |   | \\\\\\  -  /// |   |\n" +
      "                   | \\_|  ''\\---/''  |   |\n" +
      "                   \\  .-\\__  `-`  ___/-. /\n" +
      "                 ___`. .'  /--.--\\  `. . __\n" +
      '              ."" \'<  `.___\\_<|>_/___.\'  >\'"".\n' +
      "             | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
      "             \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /\n" +
      "        ======`-.____`-.___\\_____/___.-`____.-'======\n" +
      "                           `=---='\n" +
      "        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n" +
      "                 佛祖保佑       永无BUG\n"
  );
};

onMounted(() => {
  doInit();
});
</script>
