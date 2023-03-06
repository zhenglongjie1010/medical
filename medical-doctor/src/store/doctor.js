
const doctor = {
    state:{
        doctorId: '', // 用户Id
        name:'' ,  //用户账号
        picture: '',  //用户头像
    },
    getters:{
        doctorId: state =>{
           let doctorId = state.doctorId
           if(!doctorId){
            doctorId = JSON.parse(window.sessionStorage.getItem('doctorId'))  
           }
           return doctorId
       },
       name: state =>{
           let name = state.name
           if(!name){
            name = JSON.parse(window.sessionStorage.getItem('name'))
           }
           return name
       },
       picture: state =>{
        let picture = state.picture
        if(!picture){
            picture = JSON.parse(window.sessionStorage.getItem('picture'))
        }
        return picture
        },
        
    },
    mutations: {
        setDoctorId: (state,doctorId) =>{
            state.doctorId = doctorId
            window.sessionStorage.setItem('doctorId',JSON.stringify(doctorId))
        },
        setName: (state,name) =>{
            state.name = name
            window.sessionStorage.setItem('name',JSON.stringify(name))
        },
        setPicture: (state,picture) =>{
            state.picture = picture
            window.sessionStorage.setItem('picture',JSON.stringify(picture))
        },
       


    }

}

export default doctor