<template>
  <div class="home">
    <swiper />
    <div class="section" v-for="(item,index) in doctorsList" :key="index">
      <div class="section-title">{{item.name}}</div>
      <content-list :contentList="item.list"></content-list>
    </div>
  </div>
</template>

<script>

import Swiper from "../components/Swiper";
import {getAllDoctor} from '../api/index';
import contentList from '../components/ContentList'
export default {
  name: 'home',
  components: {
    Swiper,
    contentList
  },
  data () {
    return {
      doctorsList: [
        {name:"医生", list: []},
      ]
    }
  },
  created () {
   this.getDoctor();
  },
  methods: {
      getDoctor(){
        getAllDoctor().then((res)=>{
          this.doctorsList[0].list = res.slice(0,10);
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
