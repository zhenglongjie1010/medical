<template>
  <div id="app"> 
    <the-header></the-header>
    <router-view class="music-content"/>   
    <scroll-top/>
    <the-footer/>
  </div>
</template>

<script>
import ScrollTop from './components/ScrollTop';
import TheFooter from './components/TheFooter';
import TheHeader from './components/TheHeader';



export default {
  name: 'App',
  components: {
    TheHeader,
    ScrollTop,
    TheFooter
    
  }
}
</script>

<style  lang="scss" scoped>
@import './assets/css/app.scss';
</style>
