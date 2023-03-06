<template>
    <div class="song-list-album">
        <div class="album-slide">
           <div class="album-img">
                <img :src="attachImageUrl(curPatientInfo.picture)">
            </div>
            <div class="album-info">
                <h2>就诊人信息</h2>
                <span>
                    名字：{{curPatientInfo.name}}
                </span>
                <hr>
                <span>
                    出生日期：{{curPatientInfo.birth}}
                </span>
                <hr>
                
            </div>
        </div>
        <div class="album-content">
                    <div class="songs-body">
                        <comment :doctorId="doctorId" :patientListsId="patientListsId"></comment>
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
    name: 'patient-album',
    mixins: [mixin],
    components:{
        comment
    },
    computed:{
         ...mapGetters([
             'doctorId'
     ])
    },
    data(){
        return {
            
            patientListsId: '',   
            curPatientInfo: {}, 
        }

    },
    created(){
        this.patientListsId = this.$route.params.id;   //
        console.log('patientListsId',this.patientListsId)
        selectByKey(this.patientListsId).then(response => {
            this.curPatientInfo = response
           
        })
        
    },
}
</script>


<style lang="scss" scoped>
@import '../assets/css/song-list-album.scss'
</style>