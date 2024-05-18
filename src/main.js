import { createApp } from 'vue';
import router from '@/router/router';

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
const vuetify = createVuetify({
    components,
    directives,
})
// vuestic
import { createVuestic } from "vuestic-ui";
import "vuestic-ui/css";

import App from './components/login_page.vue'
// import Vue from "@vitejs/plugin-vue";

createApp(App).use(createVuestic()).use(vuetify).use(router).mount("#app");