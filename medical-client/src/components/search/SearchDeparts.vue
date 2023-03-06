
<template>
    <div class="search-songs">
        <elbum-content :departList="listOfDeparts"></elbum-content>
    </div>
</template>

<script>
import ElbumContent from '../ElbumContent';
import {mapGetters} from "vuex";
import {mixin} from "../../mixins";


export default {
  
    name: 'search-departs',
    components: { 
        ElbumContent
        
    },
    mixins: [mixin],
    computed:{
        ...mapGetters([
            'listOfDeparts'
        ])
    },
    mounted() {
         this.getDepart();
    }
}
</script>


<style lang="scss" scoped>
@import '../../assets/css/search-songs.scss';
</style>