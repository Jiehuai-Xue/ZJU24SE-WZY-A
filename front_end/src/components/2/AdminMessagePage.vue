<template>
    <div class="message-container">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>教师请求处理</span>
          <el-button type="primary" @click="fetchRequests" style="float: right;">刷新请求</el-button>
        </div>
        <div v-if="newRequests.length">
          <el-alert title="您有新的教师请求" type="info" show-icon />
          <el-table :data="newRequests" border style="width: 100%; margin-top: 20px;">
            <el-table-column prop="id" label="请求ID" width="150" />
            <el-table-column prop="teacherName" label="教师名称" width="150" />
            <el-table-column prop="content" label="请求内容" />
            <el-table-column label="操作" width="120">
              <template slot-scope="scope">
                <el-button @click="markAsRead(scope.row)" type="primary" size="small">标记为已读</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div v-else>
          <el-alert title="当前没有新的教师请求" type="success" show-icon />
        </div>
  
        <div v-if="historyRequests.length" style="margin-top: 20px;">
          <el-alert title="历史教师请求" type="info" show-icon />
          <el-table :data="historyRequests" border style="width: 100%; margin-top: 20px;">
            <el-table-column prop="id" label="请求ID" width="150" />
            <el-table-column prop="teacherName" label="教师名称" width="150" />
            <el-table-column prop="content" label="请求内容" />
          </el-table>
        </div>
      </el-card>
    </div>
  </template>
  
  <script>
  export default {
    name: 'AdminMessagePage',
    data() {
      return {
        newRequests: [],
        historyRequests: []
      }
    },
    mounted() {
      console.log('Component mounted')
      this.resetLocalStorage()
      this.loadRequests()
    },
    methods: {
      resetLocalStorage() {
        console.log('Resetting localStorage...')
        // 模拟请求数据
        const initialRequests = [
          { id: 1, teacherName: 'Teacher A', content: 'Request 1' },
          { id: 2, teacherName: 'Teacher B', content: 'Request 2' }
        ]
        localStorage.setItem('newRequests', JSON.stringify(initialRequests))
        localStorage.setItem('historyRequests', JSON.stringify([]))
        console.log('LocalStorage reset complete')
      },
      loadRequests() {
        console.log('Loading requests...')
        const newRequests = JSON.parse(localStorage.getItem('newRequests')) || []
        const historyRequests = JSON.parse(localStorage.getItem('historyRequests')) || []
        this.newRequests = newRequests
        this.historyRequests = historyRequests
        console.log('Requests loaded', { newRequests, historyRequests })
      },
      fetchRequests() {
        this.$message({
          message: 'Fetching requests...',
          type: 'info'
        })
  
        // 模拟从本地存储获取新请求
        const requests = [
          { id: 1, teacherName: '李老师', content: '我周三下午有事，没办法上课，需要调整' },
          { id: 2, teacherName: '周老师', content: '我每周五有组会，请换个时间' }
        ]
  
        this.newRequests = requests.filter(req => !this.historyRequests.some(histReq => histReq.id === req.id))
        localStorage.setItem('newRequests', JSON.stringify(this.newRequests))
        this.$message({
          message: 'Requests fetched successfully',
          type: 'success'
        })
      },
      markAsRead(request) {
        console.log('Marking request as read:', request)
        this.historyRequests.push(request)
        this.newRequests = this.newRequests.filter(req => req.id !== request.id)
        localStorage.setItem('newRequests', JSON.stringify(this.newRequests))
        localStorage.setItem('historyRequests', JSON.stringify(this.historyRequests))
        this.$message({
          message: 'Request marked as read',
          type: 'success'
        })
      }
    }
  }
  </script>
  
  <style scoped>
  .message-container {
    padding: 30px;
    background-color: #f5f7fa;
  }
  
  .box-card {
    margin-top: 20px;
  }
  </style>
  