
export const mixin = {
    methods: {

        //提示信息
        notify(title,type){
            this.$notify({
                title:title,
                type:type
            })
        },

        //获取图片地址
        attachImageUrl (srcUrl){
            
            return srcUrl? this.$store.state.configure.HOST+srcUrl : '../assets/img/user.jpg';
        },

        

    }
}