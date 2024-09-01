<script setup lang="ts">
function clickButton() {
  console.log("Button clicked");
}
</script>

<template>
  <div class="typewriter">
    <h1 class="typing">Enjoy Coding And Get Happiness.</h1>
    <a-button class="button" type="outline" @click="clickButton"
      >开始刷题</a-button
    >
  </div>
</template>

<style scoped>
/* 打字机动画 */
@keyframes typing {
  from {
    width: 0;
  }
  to {
    width: 100%;
  }
}

/* 光标动画 */
@keyframes blink-caret {
  from,
  to {
    border-color: transparent;
  }
  50% {
    border-color: #0a65cc;
  }
}

/* 按钮动画 */
@keyframes slidein {
  0% {
    opacity: 0;
    transform: translateY(100%);
  }
  100% {
    opacity: 1;
    pointer-events: auto; /*当按钮出现时才可以点击*/
    transform: translateY(0%);
  }
}

.typewriter {
  margin-bottom: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.typewriter .typing {
  color: var(--color-text-1);
  font-family: Menlo, Monaco, Consolas, "Courier New", monospace;
  overflow: hidden; /* 保证文字在动画之前隐藏 */
  border-right: 0.15em solid #0a65cc; /* 使用边框实现光标 */
  white-space: nowrap;
  margin-bottom: 32px;
  letter-spacing: 0.15em;
  animation: typing 4s steps(32, end), blink-caret 0.5s step-end infinite;
}

.typewriter .button {
  opacity: 0;
  pointer-events: none; /*当按钮未出现时不可以点击*/
  color: #0a65cc;
  animation: slidein 1s ease-in 4s forwards;
}
</style>
