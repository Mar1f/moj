<template>
  <a-row id="questionHeader" align="center" :wrap="false">
    <a-col :span="4">
      <div class="title-bar" @click="doTitleClick">
        <img src="@/assets/logo_light.svg" v-if="theme === 'light'" />
        <img src="@/assets/logo_dark.svg" v-else />
      </div>
    </a-col>
    <a-col :span="8" :offset="6">
      <div class="start">
        <a-space size="medium">
          <!-- <a-button size="mini">题库</a-button> -->
          <a-button size="mini" type="outline" @click="getPrevQuestion">
            <icon-left />
            <p>上一题</p>
          </a-button>
          <a-button size="mini" type="outline" @click="getNextQuestion">
            <p>下一题</p>
            <icon-right />
          </a-button>
          <a-spin :loading="isLoading" hide-icon>
            <div class="random" @click="matchQuestion">
              <svg
                width="24.000031"
                height="24.002396"
                viewBox="0 0 36 36.0024"
                fill="none"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
              >
                <!--                <desc>Created with Pixso.</desc>-->
                <!--                <defs />-->
                <path
                  id="random"
                  d="M24.2787 14.1017L22.7837 14.0859L20.1506 16.19L18.458 14.7508L21.1186 12.6203L21.1318 12.6294C21.3786 12.4366 21.7454 12.2983 22.1764 12.3029L24.3179 12.3255L23.9196 12.0637C23.6806 11.9013 23.5374 11.6777 23.5429 11.429C23.5536 10.9405 24.1686 10.5473 24.9094 10.5551C25.2865 10.5591 25.6211 10.6604 25.86 10.8228L28.5145 12.6275C28.7534 12.7899 28.8967 13.0135 28.8912 13.2622C28.8857 13.5109 28.7326 13.7314 28.4867 13.8886L25.7537 15.6364C25.5077 15.7937 25.1689 15.8878 24.7917 15.8838C24.0509 15.876 23.4537 15.4699 23.4644 14.9814C23.4699 14.7328 23.623 14.5123 23.8824 14.3552L24.2787 14.1017ZM25.664 19.7041C25.4251 19.5417 25.0905 19.4405 24.7134 19.4365C23.9726 19.4286 23.3577 19.8219 23.3469 20.3103C23.3414 20.559 23.4847 20.7826 23.7236 20.945L24.122 21.2068L22.627 21.191L12.4445 12.5377L12.4311 12.5375C12.193 12.3396 11.8324 12.1936 11.4014 12.1891L8.70767 12.1606C7.96689 12.1527 7.35196 12.546 7.34119 13.0344C7.33038 13.5229 7.92767 13.929 8.66846 13.9368L10.7157 13.9584L20.8981 22.6118L20.9118 22.6031C21.1497 22.8099 21.5102 22.9559 21.9412 22.9604L24.0828 22.9831L23.6865 23.2365C23.4271 23.3936 23.274 23.6141 23.2685 23.8628C23.2577 24.3513 23.855 24.7573 24.5958 24.7651C24.9729 24.7691 25.3118 24.675 25.5577 24.5177L28.2907 22.77C28.5367 22.6127 28.6898 22.3922 28.6953 22.1436C28.7007 21.8949 28.5575 21.6713 28.3186 21.5089L25.664 19.7041ZM8.51138 21.0417L10.5587 21.0634L13.1917 18.9593L14.8842 20.4073L12.2238 22.5289L12.2105 22.5199C11.9637 22.7127 11.597 22.851 11.166 22.8464L8.47217 22.8179C7.73138 22.8101 7.13412 22.404 7.1449 21.9156C7.1557 21.4271 7.7706 21.0338 8.51138 21.0417Z"
                  clip-rule="evenodd"
                  fill-rule="evenodd"
                  fill="#0A65CC"
                  fill-opacity="1.000000"
                />
                <rect
                  id="矩形 1"
                  x="0.000031"
                  y="0.000031"
                  rx="9.000000"
                  width="36.000000"
                  height="36.000000"
                  transform="rotate(-0.000113785 0.000031 0.000031)"
                  fill="#0A65CC"
                  fill-opacity="0.15"
                />
              </svg>
            </div>
          </a-spin>
        </a-space>
      </div>
    </a-col>
    <a-col :span="4" :offset="2">
      <div class="end">
        <a-space size="medium">
          <div class="startTimer" v-if="!showTimer" @click="startTimer">
            <a-tooltip position="bottom" content="开始计时" mini>
              <icon-clock-circle
                :style="{
                  fontSize: '22px',
                  color: '#0A65CC',
                  marginTop: '2px',
                }"
              />
            </a-tooltip>
          </div>
          <div class="stopTimer" v-if="showTimer" @click="stopTimer">
            <div class="timerText">{{ formatTime(time) }}</div>
            <icon-loop class="stopTimerIcon" :style="{ color: '#0A65CC' }" />
          </div>
          <userAvatar />
        </a-space>
      </div>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { useRouter, useRoute } from "vue-router";
import userAvatar from "@/components/setting/userAvatar.vue";
import { QuestionControllerService } from "../../../generated";
import { ref, watch, onUnmounted, computed } from "vue";
import { Message } from "@arco-design/web-vue";
import "@arco-design/web-vue/es/message/style/css.js";
import { useStore } from "vuex";
import {
  IconLoop,
  IconClockCircle,
  IconRight,
  IconLeft,
} from "@arco-design/web-vue/es/icon";

const store = useStore();
const theme = computed(() => store.state.theme.theme);
const showTimer = ref(false);
const time = ref(0);
let intervalId: any = null;
const router = useRouter();
const route = useRoute();
const isLoading = ref(false);
const questionId = ref(route.params.id);
const prevQuestionId = Number(route.params.id);
watch(route, () => {
  questionId.value = route.params.id as string;
});

const doTitleClick = () => {
  router.push({
    path: "/",
    replace: true,
  });
};
const getPrevQuestion = async () => {
  try {
    // 调用获取上一题的 API
    const result = await QuestionControllerService.getPreviousQuestionUsingGet(
      prevQuestionId
    );
    console.log(result.id);
    // console.log(prevQuestionId);
    // 检查是否有 data 和 id 字段
    const previousQuestionId = result?.id;

    if (previousQuestionId) {
      // 跳转到上一题的页面
      router.push(`/view/question/${previousQuestionId}`);
    } else {
      console.error("未找到上一题的ID");
      alert("已经是第一题了！");
    }
  } catch (error) {
    console.error("获取上一题失败:", error);
    alert("获取上一题失败！");
  }
};

const getNextQuestion = async () => {
  try {
    // 调用获取下一题的 API
    const result = await QuestionControllerService.getNextQuestionUsingGet(
      prevQuestionId
    );
    console.log(result.id);
    console.log(prevQuestionId);
    // 检查是否有 data 和 id 字段
    const nextQuestionId = result?.id;

    if (nextQuestionId) {
      // 跳转到下一题的页面
      router.push(`/view/question/${nextQuestionId}`);
    } else {
      console.error("未找到下一题的ID");
      alert("已经是最后一题了！");
    }
  } catch (error) {
    console.error("获取下一题失败:", error);
    alert("获取下一题失败！");
  }
};

const matchQuestion = async () => {
  try {
    const result = await QuestionControllerService.getRandomQuestionUsingGet(); // 调用随机问题的 API
    const questionId = result.data.id; // 获取随机问题的 ID
    router.push(`/view/question/${questionId}`);
    // 跳转到问题详情页
  } catch (error) {
    console.error("获取随机问题失败:", error);
  }
};
const formatTime = (time: number): string => {
  const hours = Math.floor(time / 3600);
  const minutes = Math.floor((time % 3600) / 60);
  const seconds = time % 60;

  return `${hours < 10 ? "0" + hours : hours}:${
    minutes < 10 ? "0" + minutes : minutes
  }:${seconds < 10 ? "0" + seconds : seconds}`;
};

const startTimer = () => {
  showTimer.value = true;
};
const stopTimer = () => {
  showTimer.value = false;
  time.value = 0;
};

watch(
  () => showTimer.value,
  (newVal: boolean) => {
    if (newVal) {
      intervalId = setInterval(() => {
        time.value++;
      }, 1000);
    } else {
      clearInterval(intervalId);
    }
  }
);

onUnmounted(() => {
  clearInterval(intervalId);
});
</script>

<style scoped>
#questionHeader {
  height: 40px;
  padding: 0 20px;
  border-bottom: 1px solid #cdcdcd;
}
.title-bar {
  cursor: pointer;
}
:deep(.arco-menu-horizontal .arco-menu-inner) {
  padding: 0px 25px;
}
.start {
  display: flex;
}
.random {
  margin-top: 2px;
  cursor: pointer;
}
.end {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding-right: 20px;
}

.startTimer {
  padding: 5px;
  font-size: 20px;
  cursor: pointer;
}
.startTimer:hover {
  background-color: var(--color-neutral-3);
}
.stopTimer {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: rgba(10, 101, 204, 0.15);
  border-radius: 5px;
  padding: 5px 10px;
}
.stopTimerIcon {
  font-size: 20px;
  cursor: pointer;
  margin-top: "2px";
}
.timerText {
  color: var(--color-text-1);
}
</style>
