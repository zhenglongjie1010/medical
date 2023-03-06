const departs = {
    state:{
        listOfDeparts: []      //
    },
    getters:{
        listOfDeparts: state =>{
           let listOfDeparts = state.listOfDeparts;
           if(!listOfDeparts.length){
            listOfDeparts = JSON.parse(window.sessionStorage.getItem('listOfDeparts')||null)  ;
           }
           return listOfDeparts;
       }
    },
    mutations: {
        setListOfDeparts: (state,listOfDeparts) =>{
            state.listOfDeparts = listOfDeparts
            window.sessionStorage.setItem('listOfDeparts',JSON.stringify(listOfDeparts));
        }
    }

}

export default departs