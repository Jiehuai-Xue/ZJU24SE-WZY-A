import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import SysAdminView from '../views/SysAdmin/SysAdminView.vue'
import SysAdminInfoView from '../views/SysAdmin/SysAdminInfoView.vue'
import LogView from '../views/SysAdmin/LogView.vue'
import AdminView from '../views/Admin/AdminView.vue'
import AdminInfoView from '../views/Admin/AdminInfoView.vue'
import AdminUserView from '../views/Admin/AdminUserView.vue'
import AdminUserDetailView from '../views/Admin/AdminUserDetailView.vue'
import AdminClassroomView from '../views/Admin/AdminClassroomView.vue'
import AdminCourseView from '../views/Admin/AdminCourseView.vue'
import AdminCourseDetailView from '../views/Admin/AdminCourseDetailView.vue'
import TeacherView from '../views/Teacher/TeacherView.vue'
import TeacherInfoView from '../views/Teacher/TeacherInfoView.vue'
import TeacherCourseView from '../views/Teacher/TeacherCourseView.vue'
import TeacherCourseDetailView from '../views/Teacher/TeacherCourseDetailView.vue'
import StudentView from '../views/Student/StudentView.vue'
import StudentInfoView from '../views/Student/StudentInfoView.vue'
import StudentCourseView from '../views/Student/StudentCourseView.vue'
import StudentCourseDetailView from '../views/Student/StudentCourseDetailView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login' // 默认跳转至登录页
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path:'/sys_admin',
      name:'sys_admin',
      component: SysAdminView
    },
    {
      path:'/sys_admin/info',
      name:'sys_admin-info',
      component: SysAdminInfoView
    },
    {
      path:'/sys_admin/log',
      name:'sys_admin-log',
      component: LogView
    },
    {
    path:'/admin',
    name:'admin',
    component: AdminView
    },
    {path:'/admin/info',
    name:'admin-info',
    component: AdminInfoView
    },
    {
    path:'/admin/user',
    name:'admin-user',
    component: AdminUserView
    },
    {
      path:'/admin/user/detail',
      name:'admin-user-detail',
      component: AdminUserDetailView
    },
    {
    path:'/admin/classroom',
    name:'admin-classroom',
    component: AdminClassroomView
    },
    {
    path:'/admin/info',
    name:'admin-info',
    component: AdminInfoView
    },
    {path:'/admin/course',
    name:'admin-course',
    component: AdminCourseView
    },
    {path:'/admin/course/detail/:oid',
    name:'admin-course-detail',
    component: AdminCourseDetailView
    },
    {
      path:'/teacher',
      name:'teacher',
      component: TeacherView
    },
    {
      path: '/teacher/info',
      name: 'teacher-info',
      component: TeacherInfoView
    },
    {
      path: '/teacher/course',
      name: 'teacher-course',
      component: TeacherCourseView
    },
    {
      path: '/teacher/course/detail/:oid',
      name: 'teacher-course-detail',
      component: TeacherCourseDetailView
    },
    {
      path: '/student',
      name: 'student',
      component: StudentView
    },
    {
      path: '/student/info',
      name: 'student-info',
      component: StudentInfoView
    },
    {
      path: '/student/course',
      name: 'student-course',
      component: StudentCourseView
    },
    {
      path: '/student/course/detail/:oid',
      name: 'student-course-detail',
      component: StudentCourseDetailView,
    }
  ]
})

export default router;
