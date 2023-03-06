
const patient = {
    state:{
        listOfPatients: [],      
        tempList:{}            
    },
    getters:{
        listOfPatients: state =>{
           let listOfPatients = state.listOfPatients;
           if(!listOfPatients.length){
            listOfPatients = JSON.parse(window.sessionStorage.getItem('listOfPatients')||null)  ;
           }
           return listOfPatients;
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
        setListOfPatients: (state,listOfPatients) =>{
            //console.log('我进来了',listOfDoctors);
            state.listOfPatients = listOfPatients
            window.sessionStorage.setItem('listOfPatients',JSON.stringify(listOfPatients));
        },
        setTempList: (state,setTempList) =>{
            state.tempList = setTempList
            //console.log('state.tempList',state.tempList);
            window.sessionStorage.setItem('setTempList',JSON.stringify(setTempList));
        },
    }

}

export default patient