
const doctor = {
    state:{
        listOfDoctors: [],      //当前医生列表
        tempList:{}            //点击后的医生信息
    },
    getters:{
        listOfDoctors: state =>{
           let listOfDoctors = state.listOfDoctors;
           if(!listOfDoctors.length){
            listOfDoctors = JSON.parse(window.sessionStorage.getItem('listOfDoctors')||null)  ;
           }
           return listOfDoctors;
       },
       tempList: state =>{
        let tempList = state.tempList;
        if(!tempList.length){
            tempList = JSON.parse(window.sessionStorage.getItem('tempList')||null)  ;
        }
        return tempList;
    },

    },
    mutations: {
        setListOfDoctors: (state,listOfDoctors) =>{
            console.log('我进来了',listOfDoctors);
            state.listOfDoctors = listOfDoctors
            window.sessionStorage.setItem('listOfDoctors',JSON.stringify(listOfDoctors));
        },
        setTempList: (state,setTempList) =>{
            state.tempList = setTempList
            console.log('state.tempList',state.tempList);
            window.sessionStorage.setItem('setTempList',JSON.stringify(setTempList));
        },
    }

}

export default doctor