import { createVuestic } from 'vuestic-ui'
import 'vuestic-ui/styles/essential.css'
import 'vuestic-ui/styles/typography.css'
import "vuestic-ui/css"
import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

const app = createApp(App) // 传给createApp的参数用于配置根组件

app.use(router).use(createVuestic()).use(store)

app.mount('#app')
