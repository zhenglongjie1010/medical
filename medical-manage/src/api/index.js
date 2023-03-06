
import {get,post} from "./http";

// 判断管理员是否登录成功
export const getLoginStatus = (params) => post(`/manager/login/status`,params);

//查询医生
export const getAllDoctor = () => get(`/doctor/alldoctor`);

//添加医生
export const setDoctor = (params) => post(`/doctor/addDcotor`,params);

//编辑医生
export const updateDoctor = (params) => post(`/doctor/updateDcotor`,params);

//删除医生
export const delDoctor = (id) => get(`/doctor/deleteDcotor?id=${id}`);

//根据医生部门对应的不同科室进行查询形成饼状图   根据医生部门模糊查询
export const DoctorOfDepart = (depart) => get(`/doctor/DoctorOfDepart?depart=${depart}`);

//根据医生id获取该医生的信息
export const selectByKey = (id) => get(`/doctor/selectByPrimaryKey?depart=${id}`)



//=======对就诊人信息的管理==============
//
//查询就诊人
export const getAllPatient = () => get(`/patient/allpatient`);

//添加就诊人
export const setPatient = (params) => post(`/patient/addPatient`,params);

//在某个界面只显示有该医生下的就诊人的信息
export const selectByPrimaryKey = (id)  => get(`/patient/selectByPrimaryKey?id=${id}`);

//编辑就诊人
export const updatePatient = (params) => post(`/patient/updatePatient`,params);

//删除就诊人
export const delPatient = (id) => get(`/patient/deletePatient?id=${id}`);

//=======对药品信息的管理==============
//
//查询药品
export const getAllDrug = () => get(`/drug/allDrug`);

//添加药品
export const setDrug = (params) => post(`/drug/addDrug`,params);

//编辑药品信息
export const updateDrug = (params) => post(`/drug/updateDrug`,params);

//删除药品
export const delDrug = (id) => get(`/drug/deleteDrug?id=${id}`);


//=======对病历信息的管理==============
//
//查询药品
export const getAllRecord = () => get(`/record/allRecord`);

//添加药品
export const setRecord = (params) => post(`/record/addRecord`,params);

//编辑药品信息
export const updateRecord = (params) => post(`/record/updateRecord`,params);

//删除药品
export const delRecord = (id) => get(`/record/deleteRecord?id=${id}`);