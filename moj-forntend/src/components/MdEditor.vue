<template>
  <Editor :value="value" :plugins="plugins" @change="handleChange" />
</template>

<script setup lang="ts">
import gfm from "@bytemd/plugin-gfm";
import highlight from "@bytemd/plugin-highlight";
import { Editor, Viewer } from "@bytemd/vue-next";
import { ref, withDefaults, defineProps } from "vue";

interface Props {
  value: string;
  handelChange: (v: string) => void;
}
const plugins = [
  gfm(),
  highlight(),
  // Add more plugins here
];

const props = withDefaults(defineProps<Props>(), {
  value: () => " ",
  handelChange: (v: string) => {
    console.log(v);
  },
});
const value = ref("");
const handleChange = (v: string) => {
  value.value = v;
};
</script>
<style scoped></style>
