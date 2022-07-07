import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/teacher',
    component: Layout,
    redirect: '/example/table',
    name: '讲师管理',
    meta: { title: '讲师管理', icon: 'example' },
    children: [
      {
        path: '/teacherList',
        name: '讲师列表',
        component: () => import('@/views/teacher/teacherList'),
        meta: { title: '讲师列表', icon: 'table' }
      },
      {
        path: '/addTeacher',
        name: '添加讲师',
        component: () => import('@/views/teacher/addTeacher'),
        meta: { title: '添加讲师', icon: 'tree' }
      },
      {
        path: '/addTeacher/:id',
        name: '修改讲师',
        component: () => import('@/views/teacher/addTeacher'),
        meta: { title: '修改讲师', icon: 'tree' },
        hidden: true
      }
    ]
  },
  {
    path: '/subject',
    component: Layout,
    redirect: '/example/table',
    name: '课程分类',
    meta: { title: '课程分类', icon: 'example' },
    children: [
      {
        path: '/subjectList',
        name: '课程列表',
        component: () => import('@/views/subject/subjectList'),
        meta: { title: '课程分类列表', icon: 'tree' }
      },
      {
        path: '/addSubject',
        name: '添加课程',
        component: () => import('@/views/subject/addSubject'),
        meta: { title: '添加课程分类', icon: 'tree' }
      }
    ]
  },
  {
    path: '/course',
    component: Layout,
    redirect: '/example/table',
    name: '课程管理',
    meta: { title: '课程管理', icon: 'example' },
    children: [
      {
        path: '/courseList',
        name: '课程列表',
        component: () => import('@/views/course/courseList'),
        meta: { title: '课程列表', icon: 'tree' }
      },
      {
        path: '/addCourse',
        name: '添加课程',
        component: () => import('@/views/course/addCourse'),
        meta: { title: '课程发布', icon: 'tree' }
      },
      {
        path: 'addCourse/:id',
        name: '课程信息修改',
        component: () => import('@/views/course/addCourse'),
        meta: { title: '编辑课程基本信息', noCache: true },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: '编辑课程大纲',
        component: () => import('@/views/course/chapter'),
        meta: { title: '编辑课程大纲', noCache: true },
        hidden: true
      },
      {
        path: 'publish/:id',
        name: '发布课程',
        component: () => import('@/views/course/publish'),
        meta: { title: '发布课程', noCache: true },
        hidden: true
      }
    ]
  },

  {
    path: '/sta',
    component: Layout,
    redirect: '/example/table',
    name: '统计分析',
    meta: { title: '统计分析', icon: 'example' },
    children: [
      {
        path: '/create',
        name: '生成统计数据',
        component: () => import('@/views/sta/create'),
        meta: { title: '生成统计数据', icon: 'tree' }
      },
      {
        path: '/show',
        name: '展示统计数据',
        component: () => import('@/views/sta/show'),
        meta: { title: '展示统计数据', icon: 'tree' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
