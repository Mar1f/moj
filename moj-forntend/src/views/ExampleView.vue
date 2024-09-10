<template>
  <div class="home">
    <MdEditor :value="value" :handel-change="onChange" />
    <CodeEditor />
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import MdEditor from "@/components/markdown/MdEditor.vue";
import CodeEditor from "@/components/codeEditor/CodeEditor.vue"; // @ is an alias to /src

const value = ref();
const onChange = (v: string) => {
  value.value = v;
};
</script>
<style>
.bytemd-toolbar-icon.bytemd-tippy.bytemd-tippy-right:last-child {
  display: none;
}
</style>
