import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

export default new VueRouter({
  routes:[
    {
      path: '/',
      component: resolve => require(['../pages/Login.vue'], resolve)
    },
    {
      path: '/Home',
      component: resolve => require(['../components/Home.vue'], resolve),
      children:[
        {
          path: '/Info',//系统首页
          component: resolve => require(['../pages/InfoPage.vue'], resolve)
        },
        {
          path: '/Doctor',//医生管理界面
          component: resolve => require(['../pages/DoctorPage.vue'], resolve)
        },
        {
          path: '/Patient',//就诊人管理界面
          component: resolve => require(['../pages/PatientPage.vue'], resolve)
        },
        {
          path: '/Medicine',//药品信息管理界面
          component: resolve => require(['../pages/MedicinePage.vue'], resolve)
        },
        {
          path: '/Medical_records',//病历表管理界面
          component: resolve => require(['../pages/Medical_recordsPage.vue'], resolve)
        },
        {
          path: '/Prescript',//处方管理界面
          component: resolve => require(['../pages/PrescriptPage.vue'], resolve)
        },
        {
          path: '/Pati',//医生名下就诊人界面
          component: resolve => require(['../pages/PatiPage.vue'], resolve)
        }
      ]

    }
  ]
})
