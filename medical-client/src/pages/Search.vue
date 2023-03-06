<template>
    <div class="search">
        <nav class="searchList-nav" ref="change">
            <span :class="{isActive: toggle=='Doctors'}" @click="handleChangeView('Doctors')">医生</span>
            <span :class="{isActive: toggle=='Departs'}" @click="handleChangeView('Departs')">科室</span>
        </nav>
        <component :is="currentView"></component>
    </div>
</template>

<script>
import searchDoctors from '../components/search/SearchDoctors'
import searchDeparts from '../components/search/SearchDeparts'
export default {
    name: 'search',
    components: {
        searchDoctors,
        searchDeparts
    },
    data(){
        return {
            toggle: 'Doctors',
            currentView: 'searchDoctors'
        }
    },
    methods:{
        //切换组件
        handleChangeView(component){
            this.currentView = 'search' + component;
            this.toggle = component;
        }
    }
    
}
</script>


<style lang="scss" scoped>
@import '../assets/css/search.scss';
</style>