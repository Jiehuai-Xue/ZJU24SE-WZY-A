import { createRouter, createWebHashHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "home",
    // component: () => import("../views/Home.vue"),
    component: () => import("../views/MainPage.vue"),
  },
  {
    path: "/stu/plan",
    component: () => import("../views/Stu-Plan.vue"),
  },
  {
    path: "/stu/select",
    component: () => import("../views/Stu-CourseSelection.vue"),
  },
  {
    path: "/stu/query",
    component: () => import("../views/Stu-TimeTable.vue"),
  },
  {
    path: "/admin/settime",
    component: () => import("../views/Admin-SetTime.vue"),
  },
  {
    path: "/admin/byelection",
    component: () => import("../views/Admin-ByElection.vue"),
  },
  {
    path: "/teacher/schedule",
    component: () => import("../views/Teacher-Schedule.vue"),
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
