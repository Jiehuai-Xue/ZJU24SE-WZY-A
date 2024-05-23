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

import App from '@/App.vue'
// import Vue from "@vitejs/plugin-vue";

const config = {
    colors: {
        presets: {
            light: {
                myCoolColor: '#007bff',
                messageColor: 'rgba(35,193,140,0.49)',
                none: '#FFFFFF',
            }
        }
    },
}
createApp(App).use(createVuestic({config})).use(vuetify).use(router).mount("#app");