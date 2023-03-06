<template>
    <div>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="6">
                <el-card>
                    <div class="">
                        <div class="grid-content">
                        <div class="grid-num">{{doctorCount}}</div>
                        <div>医生总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
             <el-col :span="6">
                <el-card>
                    <div class="">
                        <div class="grid-content">
                        <div class="grid-num">{{patientCount}}</div>
                        <div>就诊人数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
             <el-col :span="6">
                <el-card>
                    <div class="">
                        <div class="grid-content">
                        <div class="grid-num">10</div>
                        <div>药品总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
             <el-col :span="6">
                <el-card>
                    <div class="">
                        <div class="grid-content">
                        <div class="grid-num">10</div>
                        <div>处方总数</div>
                        </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
               <h3 class="mgb20">医生性别比例</h3>
               <div style="background-color:white">
                   <ve-pie :data="doctorSex" :theme="options"></ve-pie>
               </div>
            </el-col>  
               <el-col :span="12">
               <h3 class="mgb20">人才比例</h3>
               <div style="background-color:white">
                   <ve-histogram :data="doctorTitle" ></ve-histogram>
               </div>
            </el-col>   
        </el-row>
        
    </div>
</template>

<script>
import {getAllDoctor,getAllPatient} from '../api/index';

export default {
    data() {
        return {
            doctorCount:0,  //医生总数
            patientCount:0,      //就诊人数
            doctor: [] ,          //所有医生
            patient:[] ,        //所有就诊人
            doctorSex:{         //按性别分类的医生数
                columns:['性别',"总数"],
                rows: [
                    {'性别': '男','总数': 0},
                    {'性别': '女','总数': 0}
                ]
            },
            options: {
                color: ['#87cefa','#ffc0cb']
            },
            doctorTitle: {         //按医生人才比例  
                columns: ['职称','人数'],
                rows:[
                    {'职称': '主任', '人数': 0},
                    {'职称': '副主任', '人数': 0},
                    {'职称': '主治', '人数': 0},
                    {'职称': '副主治', '人数': 0},
                ]
            }
            
        }
    },
    created(){

    },
    mounted(){
        this.getDoctor();
        this.getPatient();
    },
    methods: {
        getDoctor() {                                 //医生总数
            getAllDoctor().then(res =>{
                this.doctor = res;
                this.doctorCount = res.length;
                this.doctorSex.rows[0]['总数'] = this.setSex(1,this.doctor);
                this.doctorSex.rows[1]['总数'] = this.setSex(0,this.doctor);
                this.setSex(1, this.doctor);
                
                for(let item of res){
                    this.getByTitle(item.title);
                }


            })
        }, 
        setSex(sex, doctor){                                 //根据性别获取医生的数量
            let count = 0;
            for(let item of doctor){
                if(sex == item.sex){
                    count++;
                }
            }
            return count;
        },
         getPatient() {                                //就诊人总数
            getAllPatient().then(res =>{
                this.patient = res;
                this.patientCount = res.length;
            })
        },
        getByTitle(title){           //根据医生职称获取数量
            for(let item of this.doctorTitle.rows){
                if(title.includes(item['职称'])){
                    item['人数']++;
                }
            }
            
        }
    }
}
</script>



<style scoped>
.grid-content{
    color: darkgray;
}
.grid-num{
    font-size: 30px;
    font-weight: bold;
}
</style>