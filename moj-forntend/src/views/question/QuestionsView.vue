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
        <a-input
          @keyup.enter="handleAdd"
          @blur="handleAdd"
          v-model.trim="inputVal"
          placeholder="请输入标签"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="doSubmit">搜索</a-button>
      </a-form-item>
    </a-form>
    <a-divider size="0" />
    <a-table
      :ref="tableRef"
      :columns="columns"
      :data="dataList"
      :pagination="{
        // showTotal: true,
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
        <a-space
          ><icon-pen-fill @click="toQuestion(record)" style="cursor: pointer" />
        </a-space>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch, watchEffect } from "vue";
import {
  Page_Question_,
  Question,
  QuestionControllerService,
  QuestionQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";
const show = ref(true);
const tableRef = ref();
const tags = ref<string[]>([]);
const inputVal = ref("");
const dataList = ref([]);
const total = ref(0);
const searchParams = ref<QuestionQueryRequest>({
  title: "",
  tags: [],
  pageSize: 2,
  current: 1,
});
const handleAdd = () => {
  if (inputVal.value) {
    tags.value.push(inputVal.value);
    inputVal.value = "";
  }
};
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

// {id: "1", title: "A+ D", content: "新的题目内容", tags: "["二叉树"]", answer: "新的答案", submitNum: 0,…}

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
  // loadData();
};
const getDifficultyColor = (difficulty: string): string => {
  if (difficulty === "简单") {
    return "green";
  } else if (difficulty === "中等") {
    return "orange";
  } else if (difficulty === "困难") {
    return "red";
  } else {
    return "default"; // 默认颜色
  }
};
</script>

<style scoped>
#questionsView {
  max-width: 1280px;
  margin: 0 auto;
}
</style>
