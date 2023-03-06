import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/Home'
import MyDoctor from '@/pages/MyDoctor'
import Doctor from '@/pages/Doctor'
import Search from '@/pages/Search'
import SignUp from '@/pages/SignUp'
import LoginIn from '@/pages/LoginIn'
import Setting from '@/pages/Setting'
import DoctorAlbum from '@/pages/DoctorAlbum'

Vue.use(Router)

export default new Router({
  routes: [    
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/my-doctor',
      name: 'my-doctor',
      component: MyDoctor
    },
    {
      path: '/doctor',
      name: 'doctor',
      component: Doctor
    },
    {
      path: '/search',
      name: 'search',
      component: Search
    },
    {
      path: '/sign-up',
      name: 'sign-up',
      component: SignUp
    },
    {
      path: '/login-in',
      name: 'login-in',
      component: LoginIn
    },
    {
      path: '/setting',
      name: 'setting',
      component: Setting
    },
    {
      path: '/doctor-album/:id',
      name: 'doctor-album',
      component: DoctorAlbum
    },

  ],
  scrollBehavior (to, from, savedPosition) {
    return { x: 0, y: 0 }
  }
})
