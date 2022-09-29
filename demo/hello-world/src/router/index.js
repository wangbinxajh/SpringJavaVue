import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/Manager.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Manager.vue'),
    redirect: "/home",
    children:[
      {path: 'Home', name: '首页', component: ()=>import('../views/Home.vue')},
      {path: 'User', name: '用户管理', component: ()=>import('../views/User.vue')},
      {path: 'person', name: '个人信息', component: ()=>import('../views/Person.vue')},
    ]
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next)=>{
  // console.log(from)
  localStorage.setItem("pathname",to.name)
  next()
})
export default router
