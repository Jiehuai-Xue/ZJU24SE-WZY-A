<template>
  <div class="message-container">
    <el-form ref="messageForm" :model="messageForm" :rules="messageRules" class="message-form" auto-complete="on" label-position="left">

      <div class="title-container">
        <h3 class="title">Send Message to Admin</h3>
      </div>

      <el-form-item label="Subject" prop="subject">
        <el-input
          v-model="messageForm.subject"
          placeholder="Subject"
          name="subject"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item label="Message" prop="message">
        <el-input
          v-model="messageForm.message"
          type="textarea"
          rows="10"
          placeholder="Message"
          name="message"
          tabindex="2"
        />
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="handleSend">Send</el-button>

    </el-form>
  </div>
</template>

<script>
export default {
  name: 'TeacherMessagePage',
  data() {
    return {
      messageForm: {
        subject: '',
        message: ''
      },
      messageRules: {
        subject: [{ required: true, message: 'Please enter the subject', trigger: 'blur' }],
        message: [{ required: true, message: 'Please enter the message', trigger: 'blur' }]
      },
      loading: false
    }
  },
  methods: {
    handleSend() {
      this.$refs.messageForm.validate(valid => {
        if (valid) {
          this.loading = true
          setTimeout(() => {
            this.loading = false
            this.$message({
              message: 'Message sent successfully',
              type: 'success'
            })
            this.messageForm.subject = ''
            this.messageForm.message = ''
          }, 2000) // 模拟2秒的发送时间
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style>
.message-container {
  padding: 30px;
  background-color: #f5f7fa;
}

.message-form {
  width: 600px;
  margin: 0 auto;
}

.title-container {
  text-align: center;
  margin-bottom: 20px;
}

.title {
  font-size: 24px;
  color: #333;
}
</style>
