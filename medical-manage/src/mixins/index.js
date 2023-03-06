export const mixin = {
    methods:{
        //提示信息
        notify(title,type){
            this.$notify({
                title: title,
                type: type
            })
        },
        //根据相对地址获取绝对地址
        getUrl(url){
            
            console.log(`${this.$store.state.HOST}${url}`)
            return `${this.$store.state.HOST}${url}`
        },
        //获取性别中文
        changgeSex(value){
            if(value==0){
                return '女';
            }
            if(value==1){
                return '男';
            }
            return value;
        },
        //获取生日
        attachBirth(val){
            return String(val).substr(0,10);
        },
        //获取生产日期
        attachTime(val){
            return String(val).substr(0,10);
        },
        //上传图片之前的校验
        beforeAvatorUpload(file){
            const isJPG = (file.type ==='image/jpeg')||(file.type==='image/png')
            if(!isJPG){
                this.$message.error('上传图片只能是jpg或png格式');
                return false;
            }
            const isLt2M = (file.size / 1024 /1024) <2;
            if(!isLt2M){
                this.$message.error('上传图片大小不能超过2M');
                return false;
            }
            return true;

        },
        //上传图片之后要做的工作
        handleAvatorSuccess(res){
            let _this = this;
            console.log(res.code)
            if(res.code == 1 ){
                _this.getData();
                _this.$notify({
                    title:'上传成功',
                    type:'success'
                });
            }
            else{
                _this.$notify({
                    
                    title:'上传失败',
                    type:'fail'
                });

            }
        },
        //批量删除已经选择的项目
        delAll(){
            for(let item of this.multipleSelection){
                this.handleDelete(item.id);
                this.deleteRow();
            }

            multipleSelection=[];
        }
    }
}