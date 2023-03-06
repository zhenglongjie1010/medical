<template>
    <div class="table">
        <div class="container">
            <div class="handlebox">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入就诊人名字" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible=true">添加病历</el-button>
            </div>
        </div>
        <el-table size="mini" border style="width:100%" height="680px" :data="tableData" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="id" label="编号" width="120" align="center"></el-table-column>
            <el-table-column prop="pnumber" label="就诊人编号" width="120" align="center"></el-table-column>
            <el-table-column prop="pname" label="就诊人姓名" width="120" align="center"></el-table-column>
            <el-table-column prop="dnumber" label="医生编号" width="120" align="center"></el-table-column>
            <el-table-column prop="dname" label="医生姓名" width="120" align="center"></el-table-column>  
            <el-table-column  label="填写日期" width="120" align="center">
                <template slot-scope="scope">
                    {{attachTime(scope.row.time)}}
                </template>
            </el-table-column>
            <el-table-column  label="症状" >
                <template slot-scope="scope">
                    <p style="height:100px;overflow:scroll">{{scope.row.symptom}}</p>
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

        <el-dialog title="修改病历" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px" :rules="rules">     
                 <el-form-item prop="pnumber" label="就诊人编号" size="mini">
                    <el-input v-model="form.pnumber" placeholder="就诊人编号"></el-input> 
                </el-form-item>
                <el-form-item prop="pname" label="就诊人姓名" size="mini">
                    <el-input v-model="form.pname" placeholder="就诊人姓名"></el-input> 
                </el-form-item>
                <el-form-item prop="dnumber" label="医生编号" size="mini">
                    <el-input v-model="form.dnumber" placeholder="医生编号"></el-input> 
                </el-form-item>
                <el-form-item prop="dname" label="医生姓名" size="mini">
                    <el-input v-model="form.dname" placeholder="医生姓名"></el-input> 
                </el-form-item>
                <el-form-item  label="填写日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.time" style="width:100"></el-date-picker>
                </el-form-item>
                <el-form-item prop="symptom" label="症状描述" size="mini">
                    <el-input v-model="form.symptom" placeholder="症状描述" type="textarea"></el-input> 
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editVisibleVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除病历信息" :visible.sync="delVisible" width="400px" center>
            <div align="center" >删除不可恢复,是否确定删除？</div>            
            <span slot="footer">
                <el-button size="mini" @click="delVisibleVisible = false">取消</el-button>
                <el-button size="mini" @click="deleteRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

import {getAllRecord, setRecord,updateRecord,delRecord} from '../api/index';
import {mixin} from "../mixins/index";

export default {
    mixins:[mixin],
    data(){
        
        return {
            // 手机号码验证
        
            centerDialogVisible:false,
            editVisible:false,  // 编辑弹窗是否显示
            delVisible:false,  //删除弹窗默认不显示
            registerForm: {  //添加框
                pnumber:'',
                pname:'',
                dnumber:'',
                dname:'',
                time:'',
                symptom:'',

            },
            form:{
                id:'',
                pnumber:'',
                pname:'',
                dnumber:'',
                dname:'',
                time:'',
                symptom:'',
            },
            tableData: [],
            tempData: [],
            select_word: '',
            pageSize: 5,//分页每页大小
            currentPage: 1 ,//当前页
            idx:-1,
            multipleSelection: [],  //哪些项目已经选择


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
        //查询所有
        getData(){
            this.tempData = [];
            this.tableData= [];
            getAllRecord().then(res => {
                this.tempData= res;
                this.tableData = res;
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
        //弹出编辑页面
        handleEdit(row){
            this.editVisible = true;
            this.form = {
                id: row.id,
                pnumber:row.pnumber,
                pname:row.pname,
                dnumber:row.dnumber,
                dname:row.dname,
                time:row.time,
                symptom:row.symptom,
            }
        },
        //保存编辑页面修改的数据
        editSave(){
            this.$refs['form'].validate(valid =>{
            if(valid){
            let aa = new Date(this.form.time);
            let datetime = aa.getFullYear()+'-'+ (aa.getMonth()+1) + '-' + aa.getDate();
            let params = new URLSearchParams();
            params.append('id',this.form.id);
            console.log(this.form.id)
            params.append('pnumber',this.form.pnumber);
            params.append('pname',this.form.pname);
            params.append('dnumber',this.form.dnumber);
            params.append('dname',this.form.dname);
            params.append('time',datetime);
            params.append('symptom',this.form.symptom);
            
            updateRecord(params)
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
        //删除药品
        deleteRow(){
          delRecord(this.idx)  
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