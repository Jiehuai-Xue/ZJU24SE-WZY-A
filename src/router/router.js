import home_page from "@/webpage/home_page.vue";
import message from "@/webpage/message.vue";
import login_page from "@/webpage/login_page.vue";
import post_edit from "@/webpage/post_edit.vue";
import post from "@/webpage/Post.vue";
import search from "@/webpage/SearchPosts.vue";
import setting from "@/webpage/UserSettings.vue";
import {createRouter, createMemoryHistory, createWebHistory, createWebHashHistory} from "vue-router";

import announcement from "@/test_data/announcement";

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
        path: '/post_edit',
        name: 'Post_edit',
        component: post_edit,
    },
    {
        path: '/Post',
        name: 'Post',
        component: post,
    },
    {
        path: '/SearchPage',
        name: 'SearchPage',
        component: search,
    },
    {
        path: '/Setting',
        name: 'Setting',
        component: setting,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;