<template>
  <div id="questionsView">
    <a-card class="questionList">
      <a-form :model="searchParams" layout="inline" class="searchParams">
        <a-form-item
          field="statue"
          label="状态"
          style="width: 130px"
          class="flex-1"
        >
          <a-select
            v-model="searchParams.state"
            placeholder="全部"
            :style="{ width: '160px' }"
          >
            <a-option value="">全部</a-option>
            <a-option value="1">未开始</a-option>
            <a-option value="3">已解答</a-option>
            <a-option value="2">尝试过</a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="difficulty" label="难度" style="min-width: 160px">
          <a-select
            v-model="searchParams.difficulty"
            placeholder="请选择难度"
            style="width: 100%"
            @change="doSubmit"
          >
            <a-option value="" class="difficulty-option">全部</a-option>
            <a-option value="简单" class="difficulty-option green"
              >简单</a-option
            >
            <a-option value="中等" class="difficulty-option orange"
              >中等</a-option
            >
            <a-option value="困难" class="difficulty-option red">困难</a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="tags" class="flex-2" style="min-width: 200px">
          <template #label>
            <div><icon-tags style="margin-right: 5px" />标签</div>
          </template>
          <a-select
            :style="{ width: '200px' }"
            placeholder="选择标签"
            multiple
            :fallback-option="fallback"
            v-model="searchParams.tags"
            allow-search
          >
            <a-option>数组</a-option>
            <a-option>字符串</a-option>
            <a-option>排序</a-option>
            <a-option>矩阵</a-option>
            <a-option>模拟</a-option>
            <a-option>枚举</a-option>
            <a-option>字符串匹配</a-option>
            <a-option>桶排序</a-option>
            <a-option>计数排序</a-option>
            <a-option>基数排序</a-option>
            <a-option>动态规划</a-option>
            <a-option>贪心</a-option>
            <a-option>深度优先搜索</a-option>
            <a-option>二分查找</a-option>
            <a-option>广度优先搜索</a-option>
            <a-option>回溯</a-option>
            <a-option>递归</a-option>
            <a-option>分治</a-option>
            <a-option>记忆化搜索</a-option>
            <a-option>归并排序</a-option>
            <a-option>快速选择</a-option>
            <a-option>哈希表</a-option>
            <a-option>树</a-option>
            <a-option>二叉树</a-option>
            <a-option>堆（优先队列）</a-option>
            <a-option>栈</a-option>
            <a-option>图</a-option>
            <a-option>链表</a-option>
            <a-option>单调栈</a-option>
            <a-option>有序集合</a-option>
            <a-option>队列</a-option>
            <a-option>二叉搜索树</a-option>
            <a-option>拓扑排序</a-option>
            <a-option>最短路</a-option>
            <a-option>单调队列</a-option>
            <a-option>双向链表</a-option>
            <a-option>最小生成树</a-option>
            <a-option>强连通分量</a-option>
            <a-option>欧拉回路</a-option>
            <a-option>双连通分量</a-option>
            <a-option>并查集</a-option>
            <a-option>字典树</a-option>
            <a-option>树状数组</a-option>
            <a-option>后缀数组</a-option>
            <a-option>位运算</a-option>
            <a-option>双指针</a-option>
            <a-option>前缀和</a-option>
            <a-option>计数</a-option>
            <a-option>滑动窗口</a-option>
            <a-option>状态压缩</a-option>
            <a-option>哈希函数</a-option>
            <a-option>滚动哈希</a-option>
            <a-option>扫描线</a-option>
            <a-option>数学</a-option>
            <a-option>数论</a-option>
            <a-option>几何</a-option>
            <a-option>组合数学</a-option>
            <a-option>博弈</a-option>
            <a-option>随机化</a-option>
            <a-option>概率与统计</a-option>
            <a-option>水塘抽样</a-option>
            <a-option>拒绝采样</a-option>
            <a-option>数据库</a-option>
            <a-option>设计</a-option>
            <a-option>数据流</a-option>
            <a-option>交互</a-option>
            <a-option>脑筋急转弯</a-option>
            <a-option>迭代器</a-option>
            <a-option>多线程</a-option>
            <a-option>shell</a-option>
          </a-select>
        </a-form-item>
        <a-form-item field="title" class="flex-2" style="min-width: 200px">
          <a-input-search
            v-model="title"
            placeholder="请输入搜索关键词"
            @keyup.enter="doSubmit"
            @blur="doSubmit"
            @search="doSubmit"
          />
        </a-form-item>
        <!-- 随机一题 -->
        <a-spin :loading="isLoading" hide-icon>
          <div class="random" @click="matchQuestion">
            <svg
              width="28.000000"
              height="28.000000"
              viewBox="0 0 36 36"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              xmlns:xlink="http://www.w3.org/1999/xlink"
            >
              <path
                id="random"
                d="M24.2787 14.1008L22.7837 14.085L20.1506 16.1889L18.458 14.7498L21.1185 12.6194L21.1318 12.6285C21.3785 12.4357 21.7454 12.2974 22.1764 12.302L24.3179 12.3246L23.9196 12.0629C23.6807 11.9005 23.5374 11.6769 23.5428 11.4282C23.5537 10.9398 24.1686 10.5466 24.9093 10.5544C25.2865 10.5584 25.6211 10.6597 25.86 10.8221L28.5145 12.6266C28.7534 12.7891 28.8967 13.0126 28.8912 13.2613C28.8857 13.5099 28.7327 13.7304 28.4867 13.8877L25.7537 15.6353C25.5077 15.7926 25.1688 15.8867 24.7917 15.8828C24.0509 15.8749 23.4537 15.4689 23.4645 14.9804C23.47 14.7318 23.623 14.5113 23.8824 14.3542L24.2787 14.1008ZM25.6641 19.7028C25.4252 19.5404 25.0906 19.4391 24.7134 19.4352C23.9727 19.4273 23.3577 19.8206 23.3469 20.309C23.3414 20.5576 23.4847 20.7812 23.7236 20.9436L24.1219 21.2054L22.627 21.1896L12.4446 12.5368L12.431 12.5366C12.193 12.3387 11.8324 12.1927 11.4015 12.1882L8.70764 12.1597C7.96692 12.1519 7.35193 12.5451 7.34119 13.0335C7.33044 13.522 7.92773 13.928 8.66846 13.9358L10.7157 13.9575L20.8981 22.6103L20.9117 22.6016C21.1497 22.8083 21.5103 22.9543 21.9413 22.9589L24.0828 22.9815L23.6865 23.2349C23.4271 23.3921 23.274 23.6125 23.2686 23.8611C23.2577 24.3496 23.855 24.7556 24.5958 24.7635C24.9729 24.7675 25.3118 24.6733 25.5577 24.5161L28.2908 22.7684C28.5367 22.6111 28.6898 22.3907 28.6953 22.1421C28.7008 21.8934 28.5575 21.6699 28.3186 21.5074L25.6641 19.7028ZM8.51135 21.0403L10.5587 21.0619L13.1918 18.9579L14.8842 20.4059L12.2238 22.5274L12.2106 22.5184C11.9637 22.7112 11.5969 22.8494 11.166 22.8448L8.47217 22.8163C7.73145 22.8085 7.13416 22.4025 7.1449 21.9141C7.15576 21.4257 7.77063 21.0324 8.51135 21.0403Z"
                clip-rule="evenodd"
                fill-rule="evenodd"
                fill="#0A65CC"
                fill-opacity="1.000000"
              />
              <circle
                id="椭圆 1"
                cx="18.000000"
                cy="18.000000"
                r="18.000000"
                fill="#0A65CC"
                fill-opacity="0.15"
              />
            </svg>
            <div class="random-text">随机一题</div>
          </div>
        </a-spin>
      </a-form>
      <a-space wrap>
        <a-tag
          v-for="tag of tags"
          :key="tag"
          closable
          @close="handleRemove(tag)"
        >
          {{ tag }}
        </a-tag>
      </a-space>
      <a-divider class="divider" :size="diverSize" />
      <a-table
        :ref="tableRef"
        :columns="columns"
        :data="dataList"
        :pagination="{
          showTotal: true,
          pageSize: searchParams.pageSize,
          current: searchParams.current,
          total,
        }"
        @page-change="onPageChange"
        bordered
        stripe
      >
        <template #tags="{ record }">
          <a-space wrap>
            <a-tag v-for="(tag, index) of record.tags" :key="index">{{
              tag
            }}</a-tag>
          </a-space>
        </template>
        <template #difficulty="{ record }">
          <a-space wrap>
            <a-tag
              :color="getDifficultyColor(cleanDifficulty(record.difficulty))"
            >
              {{ cleanDifficulty(record.difficulty) }}
            </a-tag>
          </a-space>
        </template>
        <template #acceptedRate="{ record }">
          <a-progress
            :percent="
              record.submitNum ? record.acceptedNum / record.submitNum : 0
            "
            :style="{ width: '50%' }"
            :color="{
              '0%': 'rgb(var(--danger-6))', // 红色
              '50%': 'rgb(var(--warning-6))', // 橙色
              '100%': 'rgb(var(--success-6))', // 绿色
            }"
          />
        </template>
        <template #statue="{ record }">
          <icon-minus-circle
            v-if="record.statue === 1"
            :style="{ fontSize: '24px', color: '#ffb800' }"
          />
          <icon-close-circle
            v-if="record.statue === 2"
            :style="{ fontSize: '24px', color: '#ff2d55' }"
          />
          <icon-check-circle
            v-if="record.statue === 3"
            :style="{ fontSize: '24px', color: '#00af9b' }"
          />
        </template>
        <template #optional="{ record }">
          <a-tooltip position="bottom" content="做题" mini>
            <icon-find-replace
              class="operate"
              @click="toQuestionPage(record)"
              :style="{ fontSize: '18px' }"
            />
          </a-tooltip>
        </template>
      </a-table>
    </a-card>
    进度表
    <a-card class="progress"
      >当前进度
      <div class="allNum">
        <a-space size="large">
          <a-progress
            type="circle"
            :percent="0.8"
            :style="{ width: '50%' }"
            :color="{
              '0%': 'rgb(var(--primary-6))',
              '100%': 'rgb(var(--success-6))',
            }"
          />
        </a-space>
      </div>
    </a-card>
  </div>
</template>

<script setup lang="ts">
import {
  IconFindReplace,
  IconCheckCircle,
  IconMinusCircle,
  IconCloseCircle,
  IconTags,
} from "@arco-design/web-vue/es/icon";
import message from "@arco-design/web-vue/es/message";
import "@arco-design/web-vue/es/message/style/css.js";
import {
  Question,
  QuestionControllerService,
  QuestionQueryRequest,
} from "../../../generated";
import { ref, onMounted, watch, computed } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
const store = useStore();
const tableRef = ref();
const dataList = ref([]);
const total = ref(0);
const searchParams = ref<QuestionQueryRequest>({
  pageSize: 10,
  current: 1,
  // statue: 0,
  tags: [],
  title: "",
  difficulty: "",
});
const title = ref<string>();
const tags = ref<string[]>([]);
const diverSize = 0;
const isLoading = ref(false);

const pageSize = computed(() => searchParams.value.pageSize);
const current = computed(() => searchParams.value.current);

watch([pageSize, current], () => {
  loadData();
});

const loadData = async () => {
  store.commit("loading/showLoading", true);
  const res = await QuestionControllerService.listQuestionVoByPageUsingPost(
    searchParams.value
  );
  if (res.code === 0) {
    // 处理 difficulty 字段，移除多余引号
    res.data.records.forEach((record) => {
      record.difficulty = cleanDifficulty(record.difficulty);
    });
    dataList.value = res.data.records;
    total.value = res.data.total;
  } else {
    message.error("加载失败，" + res.message);
  }
  store.commit("loading/showLoading", false);
};

/**
 * 监听 searchParams 变量，改变时触发页面的重新加载
 */
watch(
  () => searchParams.value,
  () => {
    loadData();
  },
  { deep: true }
);
/**
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});

const columns = [
  {
    title: "状态",
    slotName: "statue",
  },
  {
    title: "题目",
    dataIndex: "title",
  },
  {
    title: "标签",
    slotName: "tags",
  },
  {
    title: "通过率",
    slotName: "acceptedRate",
    sortable: {
      sortDirections: ["ascend"],
    },
  },
  {
    title: "难度",
    slotName: "difficulty",
  },
  {
    slotName: "optional",
  },
];

const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};

const matchQuestion = async () => {
  try {
    const result = await QuestionControllerService.getRandomQuestionUsingGet(); // 调用随机问题的 API
    const questionId = result.data.id; // 获取随机问题的 ID
    console.log(questionId);
    console.log(router.push(`/view/question/${questionId}`));
    // router.push(`view/question/${questionId}`); // 跳转到问题详情页
  } catch (error) {
    console.error("获取随机问题失败:", error);
  }
};

const handleRemove = (key: string) => {
  tags.value = tags.value.filter((tag) => tag !== key);
};

const router = useRouter();

/**
 * 跳转到做题页面
 * @param question
 */
const toQuestionPage = (question: Question) => {
  router.push({ path: `/view/question/${question.id}` });
};

const doSubmit = () => {
  searchParams.value = {
    ...searchParams.value,
    title: title.value,
    current: 1,
  };
};

// 标签
const fallback = (value: string) => {
  return {
    value,
    label: `++${value}++`,
  };
};

// 处理难度多带""
const cleanDifficulty = (difficulty) => {
  return difficulty.replace(/['"]/g, ""); // 移除引号
};

//难度颜色
const getDifficultyColor = (difficulty: string): string => {
  switch (difficulty) {
    case "简单":
      return "green";
    case "中等":
      return "orange";
    case "困难":
      return "red";
    default:
      return "default"; // 默认颜色
  }
};
// 处理进度表
const percent = ref(0.2);
</script>

<style scoped>
:deep(.arco-table-th.arco-table-col-sorted) {
  background-color: #0a65cc;
}
.flex-1 {
  flex: 1;
}
.flex-2 {
  flex: 2;
}
#questionsView {
  max-width: 1440px;
  margin: 0 auto;
  display: flex;
  flex-direction: row;
}
.divider {
  margin: 10px 0 20px 0;
}
.questionList {
  flex: 1;
  margin-right: 8px;
}
.progress {
  width: 250px;
  height: 150px;
}

.searchParams {
  display: flex;
}
.operate {
  cursor: pointer;
}
.random {
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  padding-bottom: 10px;
}
.random-text {
  display: inline-block;
  transform: rotate(0.61deg);
  color: rgb(10, 101, 204);
  font-family: 思源黑体;
  font-size: 14px;
  font-weight: 400;
  line-height: 23px;
  letter-spacing: 0px;
  text-align: left;
  margin-left: 10px;
  margin-right: 25px;
}
.difficulty-option.green {
  color: green;
}

.difficulty-option.orange {
  color: orange;
}

.difficulty-option.red {
  color: red;
}
.allNum {
  margin-top: 26px;
  margin-left: 40px;
}
</style>
