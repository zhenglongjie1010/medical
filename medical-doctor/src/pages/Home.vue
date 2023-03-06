<template>
  <div class="home">
    <swiper />
    <div class="section" v-for="(item,index) in patientsList" :key="index">
    <div class="section-title">{{item.name}}</div>
    <content-list :contentList="item.list"></content-list>
    </div>
  </div>
</template>

<script>
import Swiper from "../components/Swiper";
import {getAllPatient} from '../api/index';
import contentList from '../components/ContentList'

export default {
  name: 'home',
  components: {
    Swiper,
    contentList
  },
  data () {
    return {
      patientsList: [
        {name:"就诊人", list: []},
      ]
    }
  },
  created () {
   this.getPatient();
  },
  methods: {
      getPatient(){
        getAllPatient().then((res)=>{
          this.patientsList[0].list = res.slice(0,10);
        }).catch((err) => {
          console.log(err);
        })
      }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/home.scss';
</style>
