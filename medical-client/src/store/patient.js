
const patient = {
    state:{
        patientId: '', // 用户Id
        name:'' ,  //用户账号
        picture: '',  //用户头像
    },
    getters:{
        patientId: state =>{
           let patientId = state.patientId
           if(!patientId){
            patientId = JSON.parse(window.sessionStorage.getItem('patientId'))  
           }
           return patientId
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
        setPatientId: (state,patientId) =>{
            state.patientId = patientId
            window.sessionStorage.setItem('patientId',JSON.stringify(patientId))
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

export default patient