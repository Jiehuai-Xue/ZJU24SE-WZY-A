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
        <li v-for="post in filteredPosts" :key="post.id">
          <h3>{{ post.title }}</h3>
          <p>{{ post.content }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue';
import { posts } from '@/posts';
import { useRouter } from "vue-router";

export default {
  methods:{

  },
  setup() {
    const searchQuery = ref('');
    const searchHistory = ref([]);

    // 过滤后的帖子列表
    const filteredPosts = computed(() => {
      const query = searchQuery.value.toLowerCase();
      return posts.filter(
          (post) =>
              post.title.toLowerCase().includes(query) ||
              post.content.toLowerCase().includes(query)
      );
    });

    // 加载历史记录
    onMounted(() => {
      const storedHistory = localStorage.getItem('searchHistory');
      if (storedHistory) {
        searchHistory.value = JSON.parse(storedHistory);
      }
    });

    // 搜索操作
    const handleSearch = () => {
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

    return {
      searchQuery,
      filteredPosts,
      searchHistory,
      handleSearch,
      setSearchQuery,
      clearHistory,
      homepage_jump,
    };
  },
};
</script>

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
