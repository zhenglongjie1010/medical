import Vue from 'vue'
import Vuex from 'vuex'
import configure from './configure'
import user from './user'
import doctor from './doctor'
import departs from './departs'
import patient from './patient'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        configure,
        user,
        doctor,
        patient,
        departs
    }
})

export default store