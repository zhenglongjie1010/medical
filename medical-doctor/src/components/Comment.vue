<template>
    <div>
        <div class="comment">
            <h2>会话就诊</h2>
            <div class="comment-msg">
                <div class="comment-img">
                    <img :src="attachImageUrl(this.curDoctorInfo.picture)">
                </div>
                <el-input class="comment-input" type="textarea"  :rows="2" placeholder="请输入发送内容" v-model="textarea">
                </el-input>
            </div>
            <el-button type="primary" class="sub-btn"  @click="centerDialogVisible=true">添加病历</el-button>
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
        <el-dialog title="添加病历信息" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" :rules="rules">     
                <el-form-item prop="pnumber" label="就诊人编号" size="mini">
                    <el-input v-model="registerForm.pnumber" placeholder="就诊人编号"></el-input> 
                </el-form-item>
                <el-form-item prop="pname" label="就诊人姓名" size="mini">
                    <el-input v-model="registerForm.pname" placeholder="就诊人姓名"></el-input> 
                </el-form-item>
                <el-form-item prop="dnumber" label="医生编号" size="mini">
                    <el-input v-model="registerForm.dnumber" placeholder="医生编号"></el-input> 
                </el-form-item>
                <el-form-item prop="dname" label="医生姓名" size="mini">
                    <el-input v-model="registerForm.dname" placeholder="医生姓名"></el-input> 
                </el-form-item>
                <el-form-item prop="time" label="生产日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.time" style="width:100"></el-date-picker>
                </el-form-item>
                <el-form-item prop="symptom" label="症状" size="mini">
                    <el-input v-model="registerForm.symptom" placeholder="症状描述" type="textarea"></el-input> 
                </el-form-item>      
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="addRecord">确定</el-button>
            </span>
        </el-dialog>
    </div>
    
    
</template>

<script>
import { mixin } from '../mixins'
import {mapGetters} from 'vuex'
import {selectByDoctorKey,setComment,getAllCommentByid,setRecord} from '../api/index';


export default {
    name: 'comment',
    mixins:[mixin],
    props:[
        'doctorId',     
        'patientListsId',
    ],
    data(){
        return {
            centerDialogVisible:false,
            curDoctorInfo: {}, // 当前医生信息
            textarea:''  , //存放评论内容
            name:'',
            picture:'',
            commentList:[],  //存放评论列表
            doctorPic:[],
            doctorNa:[],
            registerForm: {  //添加框
                pnumber:'',
                pname:'',
                dnumber:'',
                dname:'',
                time:'',
                symptom:'',

            },
            rules:{
                pnumber: [
                    {required: true, message: '请输入就诊人编号' , trigger: 'blur'}
                ],
                pname: [
                    {required: true, message: '请输入就诊人姓名' , trigger: 'blur'}
                ],
                dnumber: [
                    {required: true, message: '请输入医生编号' , trigger: 'blur'}
                ],
                dname: [
                    {required: true, message: '请输入医生名称' , trigger: 'blur'}
                ],
                time: [
                    {required: true, message: '请输入填写日期' , trigger: 'blur'}
                ],
                symptom: [
                    {required: true, message: '请输入症状描述' , trigger: 'blur'}
                ],
                
            }
        }
    },
   computed:{
         ...mapGetters([
             'loginIn'
     ])
    },
    created(){
        
        selectByDoctorKey(this.doctorId).then(response => {
            
            this.curDoctorInfo = response
            this.name=this.curDoctorInfo.name;
            this.content=this.curDoctorInfo.textarea
            this.picture=this.curDoctorInfo.picture

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
                params.append('doctorId',this.doctorId);
                params.append('patientListsId',this.patientListsId);
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
            
            getAllCommentByid(this.patientListsId,this.doctorId)
                .then( res => {
                    this.commentList = res;
                    console.log(this.commentList)
                })
                .catch( err => {
                    this.notify('评论加载失败','error')
                })
        },
        //添加药品
        addRecord(){

            this.$refs['registerForm'].validate(valid =>{
                if(valid){
                    let aa = this.registerForm.time;
                    let datetime = aa.getFullYear()+'-'+ (aa.getMonth()+1) + '-' + aa.getDate();
                    let params = new URLSearchParams();
                    params.append('pname',this.registerForm.pname);

                    params.append('pnumber',this.registerForm.pnumber);
                    params.append('dnumber',this.registerForm.dnumber);
                    params.append('dname',this.registerForm.dname);
                    params.append('time',datetime);
                    params.append('symptom',this.registerForm.symptom);
                    
                    setRecord(params)
                    .then(res => {
                        if(res.code ==1){
                            this.getData();
                            this.notify("添加成功","success");
                        }else{
                            this.notify("添加失败","error");
                        }
                    })
                    .catch(err => {
                        console.log(err);
                    });
                    this.centerDialogVisible=false;
                }
            })

            
        },
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/comment.scss'
</style>