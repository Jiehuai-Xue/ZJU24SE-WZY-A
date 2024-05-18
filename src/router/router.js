import home_page from "@/components/home_page.vue";
import message from "@/components/message.vue";
import { createRouter,createMemoryHistory } from "vue-router";

const routes = [
    {
        path: '/home_page',
        name: 'HomePage',
        component: home_page,
    },
    {
        path: '/message',
        name: 'message',
        component: message,
    }
];

const router = createRouter({
    history: createMemoryHistory(),
    routes,
});

export default router;