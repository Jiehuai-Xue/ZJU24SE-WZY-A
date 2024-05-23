import home_page from "@/webpage/home_page.vue";
import message from "@/webpage/message.vue";
import login_page from "@/webpage/login_page.vue";
import post from "@/webpage/post.vue";
import {createRouter, createMemoryHistory, createWebHistory, createWebHashHistory} from "vue-router";

const routes = [
    {
        path: '/',
        name: 'Login',
        component: login_page,
    },
    {
        path: '/home_page',
        name: 'HomePage',
        component: home_page,
    },
    {
        path: '/message',
        name: 'message',
        component: message,
    },
    {
        path: '/post',
        name: 'Post',
        component: post,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;