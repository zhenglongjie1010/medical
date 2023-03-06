import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/pages/Home'
import MyPatient from '@/pages/MyPatient'
import SignUp from '@/pages/SignUp'
import LoginIn from '@/pages/LoginIn'
import Setting from '@/pages/Setting'
import PatientAlbum from '@/pages/PatientAlbum'
Vue.use(Router)

export default new Router({
  routes: [    
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/my-patient',
      name: 'my-patient',
      component: MyPatient
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
      path: '/patient-album/:id',
      name: 'patient-album',
      component: PatientAlbum
    },
  ],
  scrollBehavior (to, from, savedPosition) {
    return { x: 0, y: 0 }
  }
})
