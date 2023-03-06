<template>
    <div class="table">
        <div class="container">
            <div class="handlebox">
                <el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
                <el-input v-model="select_word" size="mini" placeholder="请输入药品名称" class="handle-input"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible=true">添加药品</el-button>
            </div>
        </div>
        <el-table size="mini" border style="width:100%" height="680px" :data="tableData" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="40"></el-table-column>
            <el-table-column prop="id" label="编号" width="120" align="center"></el-table-column>
            <el-table-column prop="name" label="药品名称" width="120" align="center"></el-table-column>
            <el-table-column prop="take" label="服用方式" width="120" align="center"></el-table-column>
            <el-table-column prop="number" label="数量" width="120" align="center"></el-table-column>
             
            <el-table-column  label="生产日期" width="120" align="center">
                <template slot-scope="scope">
                    {{attachTime(scope.row.time)}}
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

        <el-dialog title="添加药品" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" :rules="rules">     
                <el-form-item prop="name" label="药品名称" size="mini">
                    <el-input v-model="registerForm.name" placeholder="药品名称"></el-input> 
                </el-form-item>
                <el-form-item prop="take" label="服用方式" size="mini">
                    <el-input v-model="registerForm.take" placeholder="服用方式"></el-input> 
                </el-form-item>
                <el-form-item prop="specify" label="规格" size="mini">
                    <el-input v-model="registerForm.specify" placeholder="规格"></el-input> 
                </el-form-item>
                <el-form-item prop="number" label="数量" size="mini">
                    <el-input v-model="registerForm.number" placeholder="数量"></el-input> 
                </el-form-item>
                <el-form-item prop="time" label="生产日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.time" style="width:100"></el-date-picker>
                </el-form-item>
                         
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="addDrug">确定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="修改药品" :visible.sync="editVisible" width="400px" center>
            <el-form :model="form" ref="form" label-width="80px" :rules="rules">     
                 <el-form-item prop="name" label="药品名称" size="mini">
                    <el-input v-model="form.name" placeholder="药品名称"></el-input> 
                </el-form-item>
                <el-form-item prop="take" label="服用方式" size="mini">
                    <el-input v-model="form.take" placeholder="服用方式"></el-input> 
                </el-form-item>
                <el-form-item prop="specify" label="规格" size="mini">
                    <el-input v-model="form.specify" placeholder="规格"></el-input> 
                </el-form-item>
                <el-form-item prop="number" label="数量" size="mini">
                    <el-input v-model="form.number" placeholder="数量"></el-input> 
                </el-form-item>
                <el-form-item  label="生产日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="form.time" style="width:100"></el-date-picker>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editVisibleVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除药品" :visible.sync="delVisible" width="400px" center>
            <div align="center" >删除不可恢复,是否确定删除？</div>            
            <span slot="footer">
                <el-button size="mini" @click="delVisibleVisible = false">取消</el-button>
                <el-button size="mini" @click="deleteRow">确定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>

import {getAllDrug, setDrug,updateDrug,delDrug} from '../api/index';
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
                name:'',
                take:'',
                specify:'',
                number:'',
                time:'',

            },
            form:{
                id:'',
                name:'',
                take:'',
                specify:'',
                number:'',
                time:'',
            },
            tableData: [],
            tempData: [],
            select_word: '',
            pageSize: 5,//分页每页大小
            currentPage: 1 ,//当前页
            idx:-1,
            multipleSelection: [],  //哪些项目已经选择


            rules:{
                name: [
                    {required: true, message: '请输入药品名称' , trigger: 'blur'}
                ],
                take: [
                    {required: true, message: '请输入服用方式' , trigger: 'blur'}
                ],
                specify: [
                    {required: true, message: '请输入药品规格' , trigger: 'blur'}
                ],
                number: [
                    {required: true, message: '请输入药品数量' , trigger: 'blur'}
                ],
                time: [
                    {required: true, message: '请输入生产日期' , trigger: 'blur'}
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
        //查询所有药品
        getData(){
            this.tempData = [];
            this.tableData= [];
            getAllDrug().then(res => {
                this.tempData= res;
                this.tableData = res;
            })
        },
        
        //添加药品
        addDrug(){

            this.$refs['registerForm'].validate(valid =>{
                if(valid){
                    let aa = this.registerForm.time;
                    let datetime = aa.getFullYear()+'-'+ (aa.getMonth()+1) + '-' + aa.getDate();
                    let params = new URLSearchParams();
                    params.append('name',this.registerForm.name);
                    params.append('take',this.registerForm.take);
                    params.append('specify',this.registerForm.specify);
                    params.append('number',this.registerForm.number);
                    params.append('time',datetime);
                    
                    setDrug(params)
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
                name:row.name,
                take:row.take,
                specify:row.specify,
                number:row.number,
                time:row.time,
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
            params.append('name',this.form.name);
            params.append('take',this.form.take);
            params.append('specify',this.form.specify);
            params.append('number',this.form.number);
            params.append('time',datetime);
            
            updateDrug(params)
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
          delDrug(this.idx)  
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