<script>
import { ref, computed, onMounted } from 'vue';
import records from '@/test_data/records';
import post_list from "@/test_data/post";
import { clicked_post_id,clicked_type } from "@/test_data/post";
import { useRouter } from "vue-router";
import trend_id_list, { in_trend } from "@/test_data/trend";

export default {
  data() {

  },
  methods: {

  },
  setup() {
    const searchQuery = ref('');
    const searchHistory = ref([]);

    // 加载历史记录
    onMounted(() => {
      const storedHistory = localStorage.getItem('searchHistory');
      if (storedHistory) {
        searchHistory.value = JSON.parse(storedHistory);
      }
    });

    // 搜索操作
    const handleSearch = () => {
      get_postlist();
      filteredPosts();
      const query = searchQuery.value.trim();
      if (query && !searchHistory.value.includes(query)) {
        searchHistory.value.push(query);
        localStorage.setItem('searchHistory', JSON.stringify(searchHistory.value));
      }
    };

    // 设置搜索框
    const setSearchQuery = (item) => {
      searchQuery.value = item;
      handleSearch();
    };

    // 清空历史记录
    const clearHistory = () => {
      searchHistory.value = [];
      localStorage.removeItem('searchHistory');
    };

    const router = useRouter();
    const homepage_jump = () => {
      setTimeout(() => { router.push({ name: 'HomePage' }) }, 500);
    };
    const post_jump = (post) => {
      clicked_post_id.value = post.post.id;
      clicked_type.value = post.post.type;
      query_trend(post.post.id);
      setTimeout(() => { router.push({ name: 'Post' })},500);
    };

    const searchPost = ref([]);
    //将记录中对应的帖文全部导入
    const get_postlist = () => {
      for(let j = 0; j < post_list.value.length; j++) {
          searchPost.value.push(post_list.value[j]);
      }
    };

    const filterResult = ref([]);
    // 过滤后的帖子列表
    const filteredPosts = () => {
      const query = searchQuery.value.toLowerCase();
      for(let i = 0; i < searchPost.value.length; i++) {
        let condition = (searchPost.value[i].post.title.includes(query) ||
            searchPost.value[i].post.content.includes(query)) &&
            query.length !== 0;
        if(condition && !is_in_filterResult(filterResult,searchPost.value[i].post.id))
          filterResult.value.push(searchPost.value[i]);
      }
    };
    const is_in_filterResult = (filterResult,post_id) => {
      for(let i = 0; i < filterResult.value.length;i++){
        if(filterResult.value[i].post.id === post_id) return true;
      }
      return false;
    };

    const query_trend = (post_id) => {
      in_trend.value = trend_id_list.value.includes(post_id);
    }

    return {
      searchQuery,
      filterResult,
      searchHistory,
      handleSearch,
      setSearchQuery,
      clearHistory,
      homepage_jump,
      post_jump,
      searchPost,
      query_trend,
    };
  },
};
</script>

<template>
  <div class="page-container">
    <v-btn variant="tonal" style="width: 50px;min-width: 50px;background: none;"
           @click="homepage_jump();">
      <img src="../assets/back.svg" alt="svg" width="20" height="20">
    </v-btn>
    <div class="search-posts">
      <!-- 搜索框 -->
      <v-text-field
          v-model="searchQuery"
          @keyup.enter="handleSearch"
          placeholder="请输入想搜索的内容"
          rounded
          outlined
          prepend-inner-icon="mdi-magnify"
          @click:prepend-inner="handleSearch"
          class="search-input"
      />

      <!-- 历史搜索记录 -->
      <div class="search-history" v-if="searchHistory.length > 0">
        <h4>搜索历史</h4>
        <ul>
          <li
              v-for="(item, index) in searchHistory"
              :key="index"
              @click="setSearchQuery(item)"
          >
            {{ item }}
          </li>
        </ul>
        <v-btn
            color="deep-purple"
            outlined
            class="clear-history-button"

            @click="clearHistory"
        >
          清空历史记录
        </v-btn>
      </div>

      <!-- 过滤后的帖子列表 -->
      <ul>
        <li v-for="post in filterResult" :key="post" @click="post_jump(post);">
          <h3>{{ post.post.title }}</h3>
          <p>{{ post.post.content }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.search-posts {
  padding: 20px;
  background-color: #ffffff; /* 浅黄 */
}

.search-input {
  width: 100%;
  border-radius: 50px; /* 椭圆形 */
}

.search-history {
  margin-top: 10px;
}

.search-history ul {
  list-style: none;
  padding: 0;
}

.search-history li {
  cursor: pointer;
  color: blueviolet;
}

.search-history li:hover {
  text-decoration: underline;
}

.clear-history-button {
  margin-top: 10px;
  color:brown;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  padding: 10px;
  border: 1px solid #ddd;
  margin-top: 10px;
}

h3 {
  margin: 0;
}
</style>
