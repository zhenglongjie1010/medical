import Vue from 'vue'
import Vuex from 'vuex'
import configure from './configure'

import doctor from './doctor'
import patient from './patient'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        configure,
        
        doctor,
        patient,
        
    }
})

export default store