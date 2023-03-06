import { DoctorOfName, DoctorOfDepart } from "../api/index";

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

        //根据医生名字模糊搜索
        getDoctor(){
            if(!this.$route.query.keywords){
                this.$store.commit('setListOfDoctors',[]);
                this.notify('您输入的内容为空','warning');
            }else{
                DoctorOfName(this.$route.query.keywords).then(res =>{
                    if(!res.length){
                        this.$store.commit('setListOfDoctors',[]);
                        this.notify('系统暂无符合的医生','warning');
                    }else{
                        this.$store.commit('setListOfDoctors', res);
                    }
                }).catch(err =>{
                    console.log(err)
                })
            }

        },

        //根据医生部门模糊搜索
        getDepart(){
            if(!this.$route.query.keywords){
                this.$store.commit('setListOfDeparts',[]);
                this.notify('您输入的内容为空','warning');
            }else{
                DoctorOfDepart(this.$route.query.keywords).then(res =>{
                    if(!res.length){
                        this.$store.commit('setListOfDeparts',[]);
                        this.notify('系统暂无符合的科室','warning');
                    }else{
                        this.$store.commit('setListOfDeparts', res);
                    }
                }).catch(err =>{
                    console.log(err)
                })
            }

        }

    }
}