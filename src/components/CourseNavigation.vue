<template>
  <div>
    <div class="course-navigation">
      <button style="margin-right: 200px;" v-for="tab in tabs" :key="tab" @click="selectTab(tab)"
              :class="{ active: tab === selectedTab }">
        {{ tab }}
      </button>
    </div>
    <div class="selected-course-info-container">
      <div class="selected-course-info">
        <span class="selected-tab"> {{ selectedTab }}</span>
      </div>
      <div class="course-status-legend">
        <div class="status-item">
          <span class="status-box selectable"></span> 可选
        </div>
        <div class="status-item">
          <span class="status-box conflict"></span> 冲突
        </div>
        <div class="status-item">
          <span class="status-box insufficient"></span> 余量不足
        </div>
        <div class="status-item">
          <span class="status-box unselected"></span> 未选
        </div>
        <div class="status-item">
          <span class="status-box selected"></span> 已选
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'CourseNavigation',
  data() {
    return {
      tabs: ['专业必修课', '专业选修课', '公选课', '搜索引擎', '选课结果'],
      selectedTab: '专业必修课'
    };
  },
  created() {
  },
  methods: {
    selectTab(tab) {
      this.selectedTab = tab;
      console.log(this.selectedTab);
      this.$emit("select:selectedNavigation", tab);
    }
  }
};
</script>

<style scoped>
.course-navigation {
  display: flex;
  justify-content: center;
  background-color: #F8F9FA;
  padding: 10px;
}

.course-navigation button {
  background: none;
  border: none;
  color: #333;
  margin: 0 10px;
  cursor: pointer;
  font-size: 16px;
}

.course-navigation button.active {
  border-bottom: 2px solid #007BFF;
}

.selected-course-info-container {
  display: flex;
  justify-content: space-between;
  margin: 10px 0;
  padding: 0 20px;
}

.selected-course-info {
  color: red;
  font-weight: bold;
}

.course-status-legend {
  display: flex;
  align-items: center;
}

.status-item {
  display: flex;
  align-items: center;
  margin-left: 20px;
}

.status-box {
  display: inline-block;
  width: 15px;
  height: 15px;
  margin-right: 5px;
  border-radius: 3px;
}

.status-box.selectable {
  background-color: #28a745;
  /* Green */
}

.status-box.conflict {
  background-color: #dc3545;
  /* Red */
}

.status-box.insufficient {
  background-color: #6c757d;
  /* Grey */
}

.status-box.unselected {
  background-color: #17a2b8;
  /* Blue */
}

.status-box.selected {
  background-color: #ffc107;
  /* Yellow */
}
</style>
