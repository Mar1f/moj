<template>
  <div id="questionsView">
    <a-form :model="searchParams" layout="inline">
      <a-form-item field="title" label="名称" style="min-width: 240px">
        <a-input v-model="searchParams.title" placeholder="请输入名称" />
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
          <a-option>基础</a-option>
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
      <a-form-item field="difficulty" label="难度" style="min-width: 160px">
        <a-select
          v-model="searchParams.difficulty"
          placeholder="请选择难度"
          style="width: 100%"
          @change="doSubmit"
        >
          <a-option value="" class="difficulty-option">全部</a-option>
          <a-option value="简单" class="difficulty-option green">简单</a-option>
          <a-option value="中等" class="difficulty-option orange"
            >中等</a-option
          >
          <a-option value="困难" class="difficulty-option red">困难</a-option>
        </a-select>
      </a-form-item>
      <a-form-item
        style="display: flex; align-items: center"
        @click="getRandomQuestion"
      >
        <icon-pen />
        <a-button
          type="primary"
          style="color: green; background-color: transparent; border: none"
        >
          手感火热，随机来一题
        </a-button>
      </a-form-item>
    </a-form>
    <a-divider size="0" />
    <a-table
      :ref="tableRef"
      :columns="columns"
      :data="dataList"
      :pagination="{
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
      }"
      @page-change="onPageChange"
    >
      <template #tags="{ record }">
        <a-space wrap>
          <a-tag
            v-for="(tag, index) of record.tags"
            :key="index"
            color="pink"
            >{{ tag }}</a-tag
          >
        </a-space>
      </template>
      <template #difficulty="{ record }">
        <a-space wrap>
          <a-tag :color="getDifficultyColor(record.difficulty)">
            {{ record.difficulty }}
          </a-tag>
        </a-space>
      </template>
      <template #acceptedRate="{ record }">
        <a-progress
          :percent="
            record.submitNum ? (record.acceptedNum / record.submitNum) * 100 : 0
          "
          :style="{ width: '50%' }"
          :color="{
            '0%': 'rgb(var(--danger-6))', // 红色
            '50%': 'rgb(var(--warning-6))', // 橙色
            '100%': 'rgb(var(--success-6))', // 绿色
          }"
        />
      </template>
      <template #createTime="{ record }">
        {{ moment(record.createTime).format("YYYY-MM-DD") }}</template
      >
      <template #optional="{ record }">
        <a-space>
          <icon-pen-fill @click="toQuestion(record)" style="cursor: pointer" />
        </a-space>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import {
  Page_Question_,
  Question,
  QuestionControllerService,
  QuestionQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";

const tableRef = ref();
const dataList = ref([]);
const total = ref(0);
const searchParams = ref<QuestionQueryRequest>({
  title: "",
  tags: [],
  difficulty: "",
  pageSize: 2,
  current: 1,
});
const loadData = async () => {
  const res = await QuestionControllerService.listQuestionVoByPageUsingPost(
    searchParams.value
  );
  if (res.code === 0) {
    dataList.value = res.data.records;
    total.value = res.data.total;
  } else {
    message.error("加载失败，" + res.message);
  }
};

/**
 * 监听searchParams变化
 */
watchEffect(() => {
  loadData();
});

/**
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});

const columns = [
  // {
  //   title: "题号",
  //   dataIndex: "id",
  // },
  {
    title: "状态",
    slotName: "",
  },
  {
    title: "题目名称",
    dataIndex: "title",
  },
  {
    title: "标签",
    slotName: "tags",
  },
  {
    title: "难度",
    slotName: "difficulty",
  },
  {
    title: "通过率",
    slotName: "acceptedRate",
    sortable: {
      sortDirections: ["ascend"],
    },
  },
  {
    title: "创建时间",
    slotName: "createTime",
  },
  {
    title: "做题",
    slotName: "optional",
  },
];

const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};

const router = useRouter();
/**
 * 跳转到做题页面
 * @param question
 */
const toQuestion = (question: Question) => {
  router.push({
    path: `/view/question/${question.id}`,
  });
};

const doSubmit = () => {
  searchParams.value = {
    ...searchParams.value,
    current: 1,
  };
  loadData();
};

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
// 标签
const fallback = (value: string) => {
  return {
    value,
    label: `++${value}++`,
  };
};

const getRandomQuestion = async () => {
  try {
    const result = await QuestionControllerService.getRandomQuestionUsingGet(); // 调用随机问题的 API
    const questionId = result.data.id; // 获取随机问题的 ID
    router.push(`view/question/${questionId}`); // 跳转到问题详情页
  } catch (error) {
    console.error("获取随机问题失败:", error);
  }
};
</script>

<style scoped>
#questionsView {
  max-width: 1280px;
  margin: 0 auto;
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
</style>
