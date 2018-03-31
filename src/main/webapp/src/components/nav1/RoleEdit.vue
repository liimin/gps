<template>
    <el-form :model="editForm" :rules="editFormRules" ref="editForm" label-width="80px" @submit.prevent="onSubmit"
             style="margin:20px;width:60%;min-width:600px;">
        <el-form-item label="角色名称" prop="rolename">
            <el-input v-model="editForm.rolename" clearable></el-input>
        </el-form-item>
        <el-form-item label="备注">
            <el-input type="textarea" v-model="editForm.remark" clearable></el-input>
        </el-form-item>
        <el-form-item label="设备">
            <el-transfer
                    v-model="result"
                    filterable
                    :left-default-checked="[2, 3]"
                    :right-default-checked="[1]"
                    :titles="['设备', '结果']"
                    :button-texts="['删除', '添加']"
                    @change="handleChange"
                    :data="deviceData">
                <!--<el-button class="transfer-footer" slot="left-footer" size="small">操作</el-button>
                <el-button class="transfer-footer" slot="right-footer" size="small">操作</el-button>-->
            </el-transfer>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click.native="handleEdit" :loading="saving">立即保存</el-button>
            <el-button @click.native.prevent>取消</el-button>
        </el-form-item>
    </el-form>
</template>
<style>
    .el-transfer-panel{
        width: 35% !important;
        height: 400px;
    }
    .el-transfer-panel__list.is-filterable{
        height:288px;
    }
    .transfer-footer {
        margin-left: 20px;
        padding: 6px 5px;
    }
</style>
<script>
    import { editRole,getDeviceList } from '../../api/api';
    export default {
        data() {
            return {
                saving:false,
                deviceData:[],
                editForm: {
                    rolename: '',
                    remark: '',
                    sns:''
                },
                editFormRules: {
                    rolename: [
                        { required: true, message: '请输入角色名', trigger: 'blur' }
                    ]
                },
                result: []
               /* renderFunc(h, option) {
                    return `<span>${option.key } - ${option.label }</span>`;
                }*/
            };
        },

        methods: {
            handleChange(value, direction, movedKeys) {
                //console.log(this.result.map(item => item).toString());
            },
            //获取设备列表
            getDevices() {
                let para = {
                    currentPage: this.page,
                    pageSize:this.pageSize
                };
                //NProgress.start();
                getDeviceList(para).then((res) => {
                    var aDevices=res.data.data;
                    var obj=null;
                    for (let i = 0,j=aDevices.length; i <j ; i++) {
                        obj=aDevices[i];
                        this.deviceData.push({
                            key: obj.sn,
                            label: `SN  ${ obj.sn }`
                        });
                    }
                    //NProgress.done();
                });
            },

            handleEdit(){
                this.saving = true;
                let para =Object.assign({}, this.editForm);
                para.sns=this.result.map(item => item).toString();
                editRole(para).then((res) => {
                    this.saving = false;
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });

                });
            }
        },
        mounted() {
            this.getDevices();
        }
    };

</script>