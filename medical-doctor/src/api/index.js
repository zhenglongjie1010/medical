
import {get,post} from "./http";


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

//根据医生姓名进行模糊查询
export const DoctorOfName = (keywords) => get(`/doctor/DoctorOfName?name=${keywords}`);
//根据主键查询相应的医生信息
export const selectByDoctorKey = (id)  => get(`/doctor/selectByPrimaryKey?id=${id}`)

//bingren
export const selectByKey = (id) => get(`/patient/kKey?id=${id}`)


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

//根据主键查询相应的病人信息
export const selectByPatientKey = (id)  => get(`/patient/selectByPatientKey?id=${id}`)


//注册
export const SignUp =(params) => post('/doctor/addDcotor',params);

//医生信息登录
export const loginIn =(params) => post('/doctor/login',params);




//提交评论
export const setComment =(params) => post('/comment/adddoctorComment',params);
//返回当前的评论列表
export const getAllCommentByid = (id,idd) =>get(`/comment/commentOfPatient?id=${id}&idd=${idd}`);

export const setRecord = (params) => post(`/record/addRecord`,params);