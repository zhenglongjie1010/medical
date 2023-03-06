<template>
    <div >
        <loginLogo/>
        <div class="signUp">
            <div class="signUp-head">
                    <span>医生注册</span>
            </div>
            
            <el-form :model="registerForm" ref="registerForm" label-width="70px" class="demo-ruleForm" :rules="rules">
                <el-form-item prop="name" label="用户名">
                    <el-input v-model="registerForm.name" placeholder="用户名"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input type="password" v-model="registerForm.password" placeholder="密码"></el-input>
                </el-form-item>
                <el-form-item prop="sex" label="性别">
                    <el-radio-group v-model="registerForm.sex" >
                        <el-radio :label="0">女</el-radio>
                        <el-radio :label="1">男</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="tel" label="联系方式">
                    <el-input v-model="registerForm.tel" placeholder="联系方式"></el-input>
                </el-form-item>
                <el-form-item prop="birth" label="生日">
                    <el-date-picker v-model="registerForm.birth" placeholder="选择日期" style="width:100%;"></el-date-picker>
                </el-form-item>
                <el-form-item prop="title" label="职称">
                    <el-input v-model="registerForm.title" placeholder="职称"></el-input>
                </el-form-item>
                 <el-form-item prop="depart" label="科室" size="mini">
                    <el-input v-model="registerForm.depart" placeholder="科室"></el-input> 
                </el-form-item>
                 <el-form-item prop="number" label="预约数" size="mini">
                    <el-input v-model="registerForm.number" placeholder="预约数" type="预约数"></el-input> 
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input> 
                </el-form-item>
                
                <div class="login-btn">
                    <el-button @click="goback(-1)">取消</el-button>
                    <el-button type="primary" @click="SignUp">确定</el-button>
                </div>
            </el-form> 

        </div>
    </div>
</template>

<script>
import loginLogo from '../components/LoginLogo'
import {mixin} from '../mixins'
import {SignUp} from '../api/index'
export default {
     mixins:[mixin],
    name: 'sign-up',
    components:{
        loginLogo
    },
    data(){
        var validateTel = (rule, value, callback) => {
        const reg = new RegExp(/^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/);
        if (!value.trim()) {
            callback(new Error("请输入手机号码"));
        } else if (!reg.test(value)) {
            callback(new Error("请输入正确格式的11位手机号码"));
        } else {
            callback();
        }
        };
        // 密码验证
        var validatePassword = (rule, value, callback) => {
        const reg = new RegExp(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$/);
        if (!value.trim()) {
            callback(new Error("请输入密码"));
        } else if (!reg.test(value)) {
            callback(new Error("必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-10之间"));
        } else {
            callback();
        }
        };
        return {
            registerForm: {
                name: '',    //用户名
                password: '',    //密码
                sex: '',  //性别
                birth: '', //出生日期
                tel: '', //联系方式
                title: '', //职称
                depart:'',//所属部门
                introduction:'',//简介
                number:'',//预约数量


            },
            rules:{                                         //表单的提交规则
                name: [
                    {required: true, message: '请输入用户名' , trigger: 'blur'}
                ],
                password: [
                    {validator: validatePassword, trigger: 'blur'}
                ],
                tel: [
                    {validator: validateTel, trigger: 'blur'}
                ],
                birth: [
                    {required: true, message: '请选择日期' , trigger: 'blur'}
                ],
            }

        } 
    } ,
    methods:{
        SignUp(){
            let _this = this;
             let aa = this.registerForm.birth;
            let datetime = aa.getFullYear()+'-'+ (aa.getMonth()+1) + '-' + aa.getDate();
            let params = new URLSearchParams();
            params.append('name',this.registerForm.name);
            params.append('password',this.registerForm.password);
            params.append('sex',this.registerForm.sex);
            params.append('birth',datetime);
            params.append('tel',this.registerForm.tel);
            params.append('picture','/imgg/hhh.jpg');
            params.append('title',this.registerForm.title);
            params.append('depart',this.registerForm.depart);
            params.append('introduction',this.registerForm.introduction);
            params.append('number',this.registerForm.number);
            SignUp(params)
                .then(res => {
                    if(res.code ==1 ){
                        
                        _this.notify('注册成功','success');
                        setTimeout(function(){
                            _this.$router.push({path: '/'});
                        },1000);
                    }else{
                        _this.notify('注册失败','error');
                    }
                })
                .catch(err => {
                     _this.notify('注册失败','error');
                })
        },
        goback(index){
            this.$router.go(index);
        }
    },
}
</script>


<style lang="scss" scoped>
@import '../assets/css/sign-up.scss';
</style>