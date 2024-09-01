<template>
  <Editor
    :value="value"
    :mode="mode"
    :locale="locale"
    :plugins="plugins"
    @change="handleChange"
  />
</template>

<script setup lang="ts">
import gfm from "@bytemd/plugin-gfm";
import highlight from "@bytemd/plugin-highlight";
import { Editor, Viewer } from "@bytemd/vue-next";
import { ref, withDefaults, defineProps } from "vue";
import locale from "@/locales/zh_Hans.json";
import gfmLocale from "@bytemd/plugin-gfm/locales/zh_Hans.json";
import mathLocale from "@bytemd/plugin-gfm/locales/zh_Hans.json";
import math from "@bytemd/plugin-math";
/**
 * 定义组件属性类型
 */
interface Props {
  value: string;
  mode?: string;
  handleChange: (v: string) => void;
}

const plugins = [
  gfm({
    locale: gfmLocale,
  }),
  highlight(),
  math({
    locale: mathLocale,
  }),
  // Add more plugins here
];

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  mode: () => "split",
  handleChange: (v: string) => {
    console.log(v);
  },
});
</script>

<style>
.bytemd-toolbar-icon.bytemd-tippy.bytemd-tippy-right:last-child {
  display: none;
}
</style>
