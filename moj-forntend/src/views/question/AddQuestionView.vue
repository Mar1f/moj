<template>
  <div id="addQuestionView">
    <h2>编辑题目</h2>
    <a-form :model="form" label-align="left">
      <a-form-item field="title" label="标题">
        <a-input v-model="form.title" placeholder="请输入标题" />
      </a-form-item>
      <a-form-item field="tags" label="标签">
        <a-select
          :style="{ width: '320px' }"
          placeholder="选择标签"
          multiple
          :fallback-option="fallback"
          v-model="form.tags"
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
      <a-form-item field="difficulty" label="难度">
        <a-select
          v-model="form.difficulty"
          placeholder="请选择难度"
          style="width: 120px"
        >
          <a-option value="简单">
            <span style="color: green">简单</span>
          </a-option>
          <a-option value="中等">
            <span style="color: orange">中等</span>
          </a-option>
          <a-option value="困难">
            <span style="color: red">困难</span>
          </a-option>
        </a-select>
      </a-form-item>
      <a-form-item field="content" label="题目内容">
        <MdEditor
          @click="
            contentZIndex = 2;
            answerZIndex = 1;
          "
          :style="`z-index: ${contentZIndex}`"
          mode="split"
          :value="form.content"
          :handle-change="onContentChange"
        />
      </a-form-item>
      <a-form-item field="answer" label="答案">
        <MdEditor
          @click="
            contentZIndex = 1;
            answerZIndex = 2;
          "
          :style="`z-index: ${answerZIndex}`"
          mode="split"
          :value="form.answer"
          :handle-change="onAnswerChange"
        />
      </a-form-item>
      <a-form-item label="判题配置" :content-flex="false" :merge-props="false">
        <a-space direction="vertical" style="min-width: 480px">
          <a-form-item field="judgeConfig.timeLimit" label="时间限制">
            <a-input-number
              v-model="form.judgeConfig.timeLimit"
              placeholder="请输入时间限制"
              mode="button"
              min="0"
              size="large"
            />
          </a-form-item>
          <a-form-item field="judgeConfig.memoryLimit" label="内存限制">
            <a-input-number
              v-model="form.judgeConfig.memoryLimit"
              placeholder="请输入内存限制"
              mode="button"
              min="0"
              size="large"
            />
          </a-form-item>
          <a-form-item field="judgeConfig.stackLimit" label="堆栈限制">
            <a-input-number
              v-model="form.judgeConfig.stackLimit"
              placeholder="请输入堆栈限制"
              mode="button"
              min="0"
              size="large"
            />
          </a-form-item>
        </a-space>
      </a-form-item>
      <a-form-item
        label="测试用例配置"
        :content-flex="false"
        :merge-props="false"
      >
        <a-form-item
          v-for="(judgeCaseItem, index) of form.judgeCase"
          :key="index"
          no-style
        >
          <a-space direction="vertical" style="min-width: 640px">
            <a-form-item
              :field="`form.judgeCase[${index}].input`"
              :label="`第${index + 1}个输入用例`"
              :key="index"
            >
              <a-input
                v-model="judgeCaseItem.input"
                placeholder="请输入测试输入用例"
              />
            </a-form-item>
            <a-form-item
              :field="`form.judgeCase[${index}].output`"
              :label="`第${index + 1}个输出用例  `"
              :key="index"
            >
              <a-input
                v-model="judgeCaseItem.output"
                placeholder="请输入测试输出用例"
              />
            </a-form-item>
            <a-button status="danger" @click="handleDelete(index)">
              删除
            </a-button>
          </a-space>
        </a-form-item>
        <div style="margin-top: 32px">
          <a-button @click="handleAdd" type="outline" status="success"
            >新增测试用例
          </a-button>
        </div>
      </a-form-item>
      <div style="margin-top: 16px" />
      <a-form-item>
        <a-button type="primary" style="min-width: 200px" @click="doSubmit"
          >提交
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import MdEditor from "@/components/MdEditor.vue";
import { QuestionControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRoute } from "vue-router";

const contentZIndex = ref(1);
const answerZIndex = ref(1);

const route = useRoute();
// 如果页面地址包含 update，视为更新页面
const updatePage = route.path.includes("update");

let form = ref({
  title: "",
  tags: [],
  difficulty: "",
  answer: "",
  content: "",
  judgeConfig: {
    memoryLimit: 1000,
    stackLimit: 1000,
    timeLimit: 1000,
  },
  judgeCase: [
    {
      input: "",
      output: "",
    },
  ],
});

/**
 * 根据题目 id 获取老的数据
 */
const loadData = async () => {
  const id = route.query.id;
  if (!id) {
    return;
  }
  const res = await QuestionControllerService.getQuestionByIdUsingGet(
    id as any
  );
  if (res.code === 0) {
    form.value = res.data as any;
    // json 转 js 对象
    if (!form.value.judgeCase) {
      form.value.judgeCase = [
        {
          input: "",
          output: "",
        },
      ];
    } else {
      form.value.judgeCase = JSON.parse(form.value.judgeCase as any);
    }
    if (!form.value.judgeConfig) {
      form.value.judgeConfig = {
        memoryLimit: 1000,
        stackLimit: 1000,
        timeLimit: 1000,
      };
    } else {
      form.value.judgeConfig = JSON.parse(form.value.judgeConfig as any);
    }
    if (!form.value.tags) {
      form.value.tags = [];
    } else {
      form.value.tags = JSON.parse(form.value.tags as any);
    }
  } else {
    message.error("加载失败，" + res.message);
  }
};

onMounted(() => {
  loadData();
});

const doSubmit = async () => {
  console.log(form.value);
  // 区分更新还是创建
  if (updatePage) {
    const res = await QuestionControllerService.updateQuestionUsingPost(
      form.value
    );
    if (res.code === 0) {
      message.success("更新成功");
    } else {
      message.error("更新失败，" + res.message);
    }
  } else {
    const res = await QuestionControllerService.addQuestionUsingPost(
      form.value
    );
    if (res.code === 0) {
      message.success("创建成功");
    } else {
      message.error("创建失败，" + res.message);
    }
  }
};

/**
 * 新增判题用例
 */
const handleAdd = () => {
  form.value.judgeCase.push({
    input: "",
    output: "",
  });
};

/**
 * 删除判题用例
 */
const handleDelete = (index: number) => {
  form.value.judgeCase.splice(index, 1);
};

const onContentChange = (value: string) => {
  form.value.content = value;
};

const onAnswerChange = (value: string) => {
  form.value.answer = value;
};

// 标签选择
const fallback = (value: string) => {
  return {
    value,
    label: `++${value}++`,
  };
};
</script>

<style scoped>
#addQuestionView {
}
</style>
