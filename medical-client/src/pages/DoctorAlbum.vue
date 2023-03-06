<template>
    <div class="song-list-album">
        <div class="album-slide">
            <div class="album-img">
                <img :src="attachImageUrl(curDoctorInfo.picture)">
            </div>
            <div class="album-info">
                <h2>医生简介</h2>
                <span>
                    名字：{{curDoctorInfo.name}}
                </span>
                <hr>
                <span>
                    职称：{{curDoctorInfo.title}}
                </span>
                 <hr>
                <span>
                    科室：{{curDoctorInfo.depart}}
                </span>
                 <hr>
                 <span>
                    介绍：{{curDoctorInfo.introduction}}
                </span>
                 <hr>
            </div>
        </div>
        
        <div class="album-content">
            <div class="songs-body">
                <comment :patientId="patientId" :doctorListsId="doctorListsId"></comment>
                
            </div>
        </div>

    </div>
        
    
</template>


<script>
import {mixin} from '../mixins';
import {mapGetters} from 'vuex';
import {selectByKey} from '../api/index';
import comment from '../components/Comment'


export default {
    name: 'doctor-album',
    mixins: [mixin],
    components:{
        comment
    },
    data(){
        return {
            // listOfDoctors:'',
            doctorListsId: '',   //前面传来医生编号
            curDoctorInfo: {}, // 当前医生信息
        }

    },
    computed:{
         ...mapGetters([
             'patientId'
     ])
    },
    created(){
        
        this.doctorListsId = this.$route.params.id;   //
        console.log('doctorListsId',this.doctorListsId)
        selectByKey(this.doctorListsId).then(response => {
            this.curDoctorInfo = response
        })
    },
    mounted() {
    },
    methods:{
       

    }
}
</script>


<style lang="scss" scoped>
@import '../assets/css/song-list-album.scss'
</style>