<template>
    <div class="search-doctors">
        <album-content :doctorList="listOfDoctors"></album-content>
    </div>
</template>

<script>
import AlbumContent from '../AlbumContent';
import {mapGetters} from "vuex";
import {mixin} from "../../mixins";

export default {
  
    name: 'search-doctors',
    components: { 
        AlbumContent 
    },
    mixins: [mixin],
    computed:{
        ...mapGetters([
            'listOfDoctors'
        ])
    },
    mounted() {
         this.getDoctor();
    }
}
</script>


<style lang="scss" scoped>
@import '../../assets/css/search-songs.scss';
</style>