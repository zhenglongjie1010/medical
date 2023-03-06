<template>
  <div class="the-header"> 
    <div class="header-logo" @click="goHome">
        <svg class="icon">       
            <use xlink:href = "">           
            </use>
        </svg>
        Wise Information Technology of med  &nbsp;&nbsp;&nbsp;&nbsp;智慧医疗
    </div>
    <ul class="navbar">
        <li :class="{active: item.name == activeName}" v-for="item in navMsg " :key="item.path" @click="goPage(item.path,item.name)">
            {{item.name}}
        </li>
        <li>
            <div class="header-search">
                <input type="text" placeholder="搜索医生/科室" @keyup.enter="goSearch()" v-model="keywords">
                <div class="search-btn" @click="goSearch()">
                    <svg class="icon">
                        <use xlink:href= "#icon-sousuo"></use>
                    </svg>
                </div>
            </div>
        </li>
        <li v-show="!loginIn" :class="{active: item.name == activeName}" v-for="item in loginMsg" :key="item.path" @click="goPage(item.path,item.name)">
            {{item.name}}
        </li>
    </ul>
    <div class="header-right" v-show="loginIn">
        <div id='user'>
            <img :src="attachImageUrl(picture)">
        </div>
        <ul class="menu">
            <li v-for="(item,index) in menuList" :key="index" @click="goMenuList(item.path)">{{item.name}}</li>
        </ul>
    </div>
  </div>
</template>

<script>
import {navMsg,loginMsg,menuList}  from '../assets/data/header'
import {mapGetters} from 'vuex'




export default {
  name: 'the-header',
  
  data() {
      return{

          navMsg:[], //导航栏
          keywords:'' ,      //搜索关键字
          loginMsg: [] , //右侧导航栏
          menuList:[], //用户下拉菜单
          
      }
  },
  components: {
    
  },
  computed:{
      ...mapGetters([
          'activeName',
          'loginIn',
          'picture'
      ])
  },
  created() {
      this.navMsg = navMsg;
      this.loginMsg = loginMsg ;
      this.menuList = menuList;

  },
  mounted(){
      document.querySelector('#user').addEventListener('click',function(e){
          document.querySelector('.menu').classList.add("show");
          e.stopPropagation()          //关键在于阻止冒泡
      },false);
      document.querySelector('.menu').addEventListener('click',function(e){
          e.stopPropagation()         //点击菜单内部时，阻止时间冒泡，这样点击内部的时候，菜单不会关闭
      },false);
      document.addEventListener('click',function(){
          document.querySelector('.menu').classList.remove('show');
      },false);


  },
  methods:{
      goHome(){
        this.$router.push({path: "/"});
      },
      goPage(path,name){
          this.$store.commit('setActiveName',name);
          this.$router.push({path: path});
      },
      goSearch(){
          this.$router.push({path:'/search',query:{keywords: this.keywords}})
      },
      //获取图片地址
      attachImageUrl (srcUrl){
          console.log(this.$store.state.configure.HOST)
          return srcUrl? this.$store.state.configure.HOST+srcUrl : '../assets/img/user.jpg';
      },
      goMenuList(path){
          if(path == 0 ){
              this.$store.commit('setLoginIn',false);
              this.$router.go(0);
          }else{
              this.$router.push({path:path})
          }
      }
  }
}
</script>

<style  lang="scss" scoped>
@import '../assets/css/the-header.scss';
</style>
