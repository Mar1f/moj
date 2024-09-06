<template>
  <div id="questionSubmitView">
    <a-form :model="searchParams" layout="inline">
      <a-form-item field="questionId" label="题号" style="min-width: 240px">
        <a-input v-model="searchParams.questionId" placeholder="请输入" />
      </a-form-item>
      <a-form-item field="language" label="编程语言" style="min-width: 240px">
        <a-select
          v-model="searchParams.language"
          :style="{ width: '320px' }"
          placeholder="选择编程语言"
        >
          <a-option>java</a-option>
          <a-option>cpp</a-option>
          <a-option>go</a-option>
          <a-option>html</a-option>
        </a-select>
      </a-form-item>
      <a-form-item>
        <a-button type="primary" @click="doSubmit">搜索</a-button>
      </a-form-item>
    </a-form>
    <a-divider size="0" />
    <div class="table-container">
      <a-table
        :ref="tableRef"
        :columns="columns"
        :data="dataList"
        :bordered="true"
        :wrapper="true"
        :cell="true"
        :pagination="{
          showTotal: true,
          pageSize: searchParams.pageSize,
          current: searchParams.current,
          total,
        }"
        @page-change="onPageChange"
      >
        <template #judgeInfo="{ record }">
          <div v-if="record.judgeInfo">
            <div v-if="typeof record.judgeInfo === 'string'">
              <div>判题状态: {{ JSON.parse(record.judgeInfo).message }}</div>
              <div>内存: {{ JSON.parse(record.judgeInfo).memory }} KB</div>
              <div>时间: {{ JSON.parse(record.judgeInfo).time }} ms</div>
            </div>
            <div v-else>
              <div>判题状态: {{ record.judgeInfo.message }}</div>
              <div>内存: {{ record.judgeInfo.memory }} KB</div>
              <div>时间: {{ record.judgeInfo.time }} ms</div>
            </div>
          </div>
        </template>
        <template #createTime="{ record }">
          {{ moment(record.createTime).format("YYYY-MM-DD") }}
        </template>
      </a-table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import {
  Question,
  QuestionControllerService,
  QuestionSubmitQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";

const tableRef = ref();

const dataList = ref([]);
const total = ref(0);
const searchParams = ref<QuestionSubmitQueryRequest>({
  questionId: undefined,
  language: undefined,
  pageSize: 10,
  current: 1,
});

const loadData = async () => {
  const res = await QuestionControllerService.listQuestionSubmitByPageUsingPost(
    {
      ...searchParams.value,
      sortField: "createTime",
      sortOrder: "descend",
    }
  );
  if (res.code === 0) {
    dataList.value = res.data.records;
    total.value = res.data.total;
  } else {
    message.error("加载失败，" + res.message);
  }
};

/**
 * 监听 searchParams 变量，改变时触发页面的重新加载
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
  {
    title: "提交号",
    dataIndex: "id",
    headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" }, // 表头样式
  },
  {
    title: "提交者 id",
    dataIndex: "userId",
    headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
  },
  {
    title: "题目 id",
    dataIndex: "questionId",
    headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
  },
  {
    title: "判题信息",
    dataIndex: "judgeInfo",
    slots: { customRender: "judgeInfo" }, // 这个自定义渲染 slot
    headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
    children: [
      {
        title: "状态",
        dataIndex: "judgeInfo.message", // 你可以从 judgeInfo 中提取数据
        headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
      },
      {
        title: "时间(ms)",
        dataIndex: "judgeInfo.time",
        headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
      },
      {
        title: "内存(KB)",
        dataIndex: "judgeInfo.memory",
        headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
      },
    ],
  },
  {
    title: "判题状态",
    dataIndex: "status",
    headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
  },
  {
    title: "编程语言",
    dataIndex: "language",
    headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
  },
  {
    title: "创建时间",
    slotName: "createTime",
    headerCellStyle: { backgroundColor: "#FB9DC7", color: "#333" },
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
const toQuestionPage = (question: Question) => {
  router.push({
    path: `/view/question/${question.id}`,
  });
};

/**
 * 确认搜索，重新加载数据
 */
const doSubmit = () => {
  // 这里需要重置搜索页号
  searchParams.value = {
    ...searchParams.value,
    current: 1,
  };
};
</script>

<style scoped>
#questionSubmitView {
  max-width: 1280px;
  margin: 0 auto;
  background-color: #fb9dc7; /* 设置页面的背景颜色 */
  padding: 20px;
  border-radius: 10px; /* 圆角 */
}
.table-container {
  border: 1px solid #fb9dc7;
  border-radius: 10px; /* 添加圆角 */
  overflow: hidden; /* 防止子元素溢出显示 */
}
body {
  background-color: #fb9dc7; /* 页面整体背景颜色 */
}
</style>
