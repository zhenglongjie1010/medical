<template>
    <div class="content-list">
        <ul class="section-content">
            <li class="content-item" v-for="(item,index) in contentList" :key="index">
                <div class="kuo" @click="goAlbum(item,item.name)">
                    <img class="item-img" :src="attachImageUrl(item.picture)">
                </div>
                <p class="item-name">{{item.name}}</p>
            </li>
        </ul>
    </div>
</template>

<script>
import {mixin} from '../mixins';
export default {
    name: 'content-list',
    mixins: [mixin],
    props: ['contentList'],
    methods:{
        goAlbum(item,type){
            console.log('item', item);
            this.$store.commit("setTempList",item);
            if(type){             //医生
                console.log(item.id);
                this.$router.push({path:`patient-album/${item.id}`});
            }else{

            }
        }
    }
}
</script>

<style scoped>
@import '../assets/css/content-list.scss';
</style>