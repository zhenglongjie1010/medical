<template>
    <div class="content">
        <h1 class="title">
            <slot name="title"></slot>
        </h1>
        <ul>
            <li>
                <div class="song-item">
                    <span class="item-index"></span>
                    <span class="item-name">姓名</span>
                    <span class="item-name">科室</span>
                     
                    
                </div>
            </li>
            <li v-for="(item,index) in departList" :key="index">
                <div class="song-item">
                    <span class="item-index">
                        {{index+1}}
                    </span>
                    <span class="item-name">{{item.name}}</span>
                    <span class="item-intro">{{item.depart}}</span>
                    
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import {mapGetters} from "vuex";
import {mixin} from "../mixins";
export default {
    name: 'elbum-content',
    mixins: [mixin],
    props:[
        'departList'
    ]
}
</script>

<style lang="scss" scoped>
@import '../assets/css/album-content.scss';

</style>