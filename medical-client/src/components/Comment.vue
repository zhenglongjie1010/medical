<template>
    <div>
        <div class="comment">
            <h2>会话就诊</h2>
            <div class="comment-msg">
                <div class="comment-img">
                    <img :src="attachImageUrl(this.curPatientInfo.picture)">
                </div>
                <el-input class="comment-input" type="textarea"  :rows="2" placeholder="请输入发送内容" v-model="textarea">
                </el-input>
            </div>
            <el-button type="primary" class="sub-btn" @click="postComment">发送</el-button>
        </div>
        <p>会话记录：共{{commentList.length}}条记录</p>
        <ul class="popular" v-for="(item,index) in commentList" :key="index">
            <li>
                <div class="popular-img">
                    <img :src="attachImageUrl(item.picture)">
                </div>
                <div class="popular-msg">
                    <ul>
                        <li class="name">{{item.name}}</li>
                        <li class="time">{{item.time}}</li>
                        <li class="content">{{item.content}}</li>
                    </ul>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>

import { mixin } from '../mixins'
import {mapGetters} from 'vuex'
import {selectByPatientKey,setComment,getAllCommentByid} from '../api/index';

export default {
    name: 'comment',
    mixins:[mixin],
    props:[
        'patientId',     //进行评论的就诊人的id
        'doctorListsId',
    ],
    data(){
        return {
            curPatientInfo: {}, // 当前病人信息
            textarea:''  , //存放评论内容
            name:'',
            picture:'',
            commentList:[],  //存放评论列表
            patientPic:[],
            patientNa:[],
        }
    },
    computed:{
         ...mapGetters([
             'loginIn'
     ])
    },
    created(){
        
       selectByPatientKey(this.patientId,this.doctorListsId).then(response => {
            this.curPatientInfo = response
            this.name=this.curPatientInfo.name;
            this.content=this.curPatientInfo.textarea
            this.picture=this.curPatientInfo.picture

        })
        
       
    },
    mounted(){
        this.getComment();
    },
    methods:{
        //提交评论
        postComment(){
            if(this.loginIn){
                let params = new URLSearchParams();
                params.append('patientId',this.patientId);
                
                params.append('doctorListsId',this.doctorListsId);
                console.log(this.doctorListsId)
                params.append('name',this.name);
                params.append('picture',this.picture);
                params.append('content',this.textarea);

                setComment(params)
                    .then(res => {
                        if(res.code ==1 ){
                            this.notify('留言成功','success');

                            this.textarea='';
                            this.getComment();
                        }else{
                            this.notify('留言失败','error');
                        }
                    })
                    .catch(err =>{
                        this.notify('留言失败','error');
                    })
            }else{
                 this.notify('请先登录','warning');
            }
        },
        //留言列表
        getComment(){
            console.log(this.patientId)
            getAllCommentByid(this.patientId,this.doctorListsId)
                .then( res => {
                    this.commentList = res;
                    //for(let item of res){
                    //    getPatients(item.patientId);
                    //}
                    console.log(this.commentList)
                    
                })
                .catch( err => {
                    this.notify('评论加载失败','error')
                })
        },
       

    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/comment.scss'
</style>