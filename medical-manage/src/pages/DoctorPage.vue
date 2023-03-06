<template>
    <div class="table">
        <div class="container">
            <div class="handlebox">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入医生名字" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible=true">添加医生</el-button>
            </div>
        </div>
        <el-table size="mini" border style="width:100%" height="680px" :data="data" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column label="医生图片" width="110" align="center">
                <template slot-scope="scope">
                    <div class="doctor-img">
                        <img :src="getUrl(scope.row.picture)" style="width:100%">
                        
                    </div>
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="beforeAvatorUpload" 
                    :on-success="handleAvatorSuccess">
                    <el-button size="mini">更新图片</el-button>
                    </el-upload>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120" align="center"></el-table-column>
             
             <el-table-column prop="sex" label="性别" width="50" align="center">
                <template slot-scope="scope">
                    {{changgeSex(scope.row.sex)}}
                </template>
             </el-table-column>
            <el-table-column  label="生日" width="120" align="center">
                <template slot-scope="scope">
                    {{attachBirth(scope.row.birth)}}
                </template>
            </el-table-column>


            <el-table-column prop="password" label="密码" width="120" align="center"></el-table-column>
            <el-table-column prop="title" label="职称" width="120" align="center"></el-table-column>
            <el-table-column prop="tel" label="联系方式" width="120" align="center"></el-table-column>
            <el-table-column prop="depart" label="科室" width="120" align="center"></el-table-column>
            <el-table-column prop="number" label="预约数量" width="120" align="center"></el-table-column>
            
            <el-table-column  label="简介" >
                <template slot-scope="scope">
                    <p style="height:100px;overflow:scroll">{{scope.row.introduction}}</p>
                </template>
            </el-table-column>
            <el-table-column  label="就诊人管理" >
                <template slot-scope="scope">
                     <el-button size="mini" @click="patientEdit(scope.row.id,scope.row.name)">就诊人管理</el-button>
                </template>
            </el-table-column>

            <el-table-column label="操作" width="150" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                background
                layout = "total,prev,pager,next"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="tableData.length"
                @current-change="handleCurrentChange"
                >
                </el-pagination>
        </div>

        <el-dialog title="添加医生" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" :rules="rules">     
                <el-form-item prop="name" label="名字" size="mini">
                    <el-input v-model="registerForm.name" placeholder="名字"></el-input> 
                </el-form-item>
                <el-form-item prop="password" label="密码" size="mini">
                    <el-input v-model="registerForm.password" placeholder="密码"></el-input> 
                </el-form-item>
                 <el-form-item prop="sex" label="性别" size="mini">
                    <input type="radio" name="sex" value="0" v-model="registerForm.sex">&nbsp;女&nbsp;&nbsp;
                    <input type="radio" name="sex" value="1" v-model="registerForm.sex">&nbsp;男
                </el-form-item>
                <el-form-item prop="birth" label="生日" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.birth" style="width:100"></el-date-picker>
                </el-form-item>
                <el-form-item prop="title" label="职称" size="mini">
                    <el-input v-model="registerForm.title" placeholder="职称"></el-input> 
                </el-form-item>
                <el-form-item prop="tel" label="联系方式" size="mini">
                    <el-input v-model="registerForm.tel" placeholder="联系方式"></el-input> 
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
                
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="addDoctor">确定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改医生" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px" :rules="rules">     
                <el-form-item prop="name" label="名字" size="mini">
                    <el-input v-model="form.name" placeholder="名字"></el-input> 
                </el-form-item>
                <el-form-item prop="password" label="密码" size="mini">
                    <el-input v-model="form.password" placeholder="密码"></el-input> 
                </el-form-item>
                <el-form-item prop="sex" label="性别" size="mini">
                    <input type="radio" name="sex" value="0" v-model="registerForm.sex">&nbsp;女&nbsp;&nbsp;
                    <input type="radio" name="sex" value="1" v-model="registerForm.sex">&nbsp;男
                </el-form-item>
                <el-form-item  label="生日" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.birth" style="width:100"></el-date-picker>
                </el-form-item>
                <el-form-item prop="title" label="职称" size="mini">
                    <el-input v-model="form.title" placeholder="职称"></el-input> 
                </el-form-item>
                <el-form-item prop="tel" label="联系方式" size="mini">
                    <el-input v-model="form.tel" placeholder="联系方式"></el-input> 
                </el-form-item>
                <el-form-item prop="depart" label="科室" size="mini">
                    <el-input v-model="form.depart" placeholder="科室"></el-input> 
                </el-form-item>
                 <el-form-item prop="number" label="预约数" size="mini">
                    <el-input v-model="form.number" placeholder="预约数" type="预约数"></el-input> 
                </el-form-item>
                <el-form-item prop="introduction" label="简介" size="mini">
                    <el-input v-model="form.introduction" placeholder="简介" type="textarea"></el-input> 
                </el-form-item>
                
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editVisibleVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除医生" :visible.sync="delVisible" width="400px" center>
            <div align="center" >删除不可恢复,是否确定删除？</div>            
            <span slot="footer">
                <el-button size="mini" @click="delVisibleVisible = false">取消</el-button>
                <el-button size="mini" @click="deleteRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

import {getAllDoctor, setDoctor,updateDoctor,delDoctor} from '../api/index';
import {mixin} from "../mixins/index";

export default {
    mixins:[mixin],
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
            centerDialogVisible:false,
            editVisible:false,  // 编辑弹窗是否显示
            delVisible:false,  //删除弹窗默认不显示
            registerForm: {  //添加框
                password:'',
                name:'',
                sex:'',
                birth:'',
                title:'',
                tel:'', 
                depart:'',
                introduction:'',
                number:''

            },
            form:{
                id:'',
                password:'',
                name:'',
                sex:'',
                birth:'',
                title:'',
                tel:'',
                depart:'',
                introduction:'',
                number:''
            },
            tableData: [],
            tempData: [],
            select_word: '',
            pageSize: 5,//分页每页大小
            currentPage: 1 ,//当前页
            idx:-1,
            multipleSelection: [] , //哪些项目已经选择

            rules:{
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
    },
    computed:{
        //计算当前搜索结果表的数据
        data(){
            return this.tableData.slice((this.currentPage - 1)*this.pageSize,this.currentPage * this.pageSize)
        }
    },
    watch:{
        //收缩框里面的内容发生变化的时候，搜索结果table里面的列表内容会跟着变化
        select_word: function(){
            if(this.select_word == ''){
                this.tableData= this.tempData;
            }else{
                this.tableData = [];
                for(let item of this.tempData){
                    if(item.name.includes(this.select_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    },
    created(){
        this.getData();
    },
    methods:{

        //获取当前页
        handleCurrentChange(val){
            this.currentPage = val;
        },
        //查询所有医生
        getData(){
            this.tempData = [];
            this.tableData= [];
            getAllDoctor().then(res => {
                this.tempData= res;
                this.tableData = res;
            })
        },
        //更新图片
        uploadUrl(id){
            return `${this.$store.state.HOST}/doctor/updatedoctorPic?id=${id}`
        },

        //添加医生
        addDoctor(){
            this.$refs['registerForm'].validate(valid =>{
                if(valid){
            let aa = this.registerForm.birth;
            let datetime = aa.getFullYear()+'-'+ (aa.getMonth()+1) + '-' + aa.getDate();
            let params = new URLSearchParams();
            params.append('name',this.registerForm.name);
            params.append('password',this.registerForm.password);
            params.append('sex',this.registerForm.sex);
            params.append('birth',datetime);
            params.append('title',this.registerForm.title);
            params.append('tel',this.registerForm.tel);
            params.append('introduction',this.registerForm.introduction);
            params.append('depart',this.registerForm.depart);
            params.append('picture','/imgg/hhh.jpg');
            params.append('number',this.registerForm.number);
            
            setDoctor(params)
            .then(res => {
                if(res.code ==1){

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
        //弹出编辑页面
        handleEdit(row){
            this.editVisible = true;
            this.form = {
                id: row.id,
                password:row.password,
                name:row.name,
                sex:row.sex,
                birth:row.birth,
                title:row.title,
                tel:row.tel,
                depart:row.depart,
                introduction:row.introduction,
                number:row.number
            }
        },
        //保存编辑页面修改的数据
        editSave(){
            this.$refs['form'].validate(valid =>{
            if(valid){
            let aa = new Date(this.form.birth);
            let datetime = aa.getFullYear()+'-'+ (aa.getMonth()+1) + '-' + aa.getDate();
            let params = new URLSearchParams();
            params.append('id',this.form.id);
            params.append('name',this.form.name);
            params.append('password',this.form.password);
            params.append('sex',this.form.sex);
            params.append('birth',datetime);
            params.append('title',this.form.title);
            params.append('tel',this.form.tel);         
            params.append('depart',this.form.depart);
            params.append('number',this.form.number);
            params.append('introduction',this.form.introduction);
            
            updateDoctor(params)
            .then(res => {
                if(res.code ==1){
                    this.getData();
                    this.notify("修改成功","success");
                }else{
                    this.notify("修改失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.editVisible=false;
              }
            })
        },
        //弹出删除窗口
        handleDelete(id){
            this.idx=id;
            this.delVisible=true;
        },
        //删除一名医生
        deleteRow(){
          delDoctor(this.idx)  
          .then(res => {
                if(res){
                    this.getData();
                    this.notify("删除成功","success");
                }else{
                    this.notify("删除失败","error");
                }
            })
            .catch(err => {
                console.log(err);
            });
            this.delVisible=false;
        },
        //把已经选择的项目赋值给multipleSelection
        handleSelectionChange(val){
            this.multipleSelection=val;
        },
        //转向该医生下的就诊人管理界面，传入两个参数到就诊人界面
        patientEdit(id,name){
            this.$router.push({path:'Pati',query:{id,name}});
        }
    }
}
</script>

<style scoped>
.handlebox {
    margin-bottom: 20px;
}
.doctor-img {
    width: 100%;
    height: 80px;
    border-radius: 5px;
    margin-bottom: 5px;
    overflow: hidden;
}
.handle-input {
    width: 300px;
    display: inline-block;
}
.pagination{
    display: flex;
    justify-content: center;
}
</style>