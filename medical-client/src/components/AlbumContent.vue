<template>
    <div class="content">
        <h1 class="title">
            <slot name="title"></slot>
        </h1>
        <ul>
            <li>
                <div class="song-item">
                    <span class="item-index"></span>
                    <span class="item-name">医生名字</span>
                    <span class="item-title">职称</span>
                    <span class="item-intro">简介</span>
                    <span class="item-title">所属科室</span>
                </div>
            </li>
            <li v-for="(item,index) in doctorList" :key="index">
                <div class="song-item">
                    <span class="item-index">
                        {{index+1}}
                    </span>
                    <span class="item-name">{{item.name}}</span>
                    <span class="item-title">{{item.title}}</span>
                    <span class="item-title">{{item.depart}}</span>
                    <span class="item-intro">{{item.introduction}}</span>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>

import {mapGetters} from "vuex";
import {mixin} from "../mixins";
export default {
    name: 'album-content',
    mixins: [mixin],
    props:[
        'doctorList'
    ]
}
</script>

<style lang="scss" scoped>
@import '../assets/css/album-content.scss';

</style>