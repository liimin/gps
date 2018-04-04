<template>
    <el-form  :model="addForm" :rules="addFormRules" ref="addForm" label-width="80px" @submit.prevent="onSubmit"
             style="margin:20px;width:60%;min-width:600px;">
        <el-form-item label="角色名称" prop="rolename">
            <el-input v-model="addForm.rolename" clearable ></el-input>
        </el-form-item>
        <el-form-item label="备注">
            <el-input type="textarea" v-model="addForm.remark" clearable></el-input>
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
            <el-button type="primary" @click.native="handleAdd" :loading="sending">立即保存</el-button>
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
    import { getDeviceList,addRole } from '../../api/api';
    import { mapGetters } from 'vuex';
    export default {
        computed: {
            ...mapGetters(['sending'])
        },
        data() {
            const generateData = _ => {
                const data = [];
                for (let i = 1; i <= 15; i++) {
                    data.push({
                        key: i,
                        label: `设备 ${ i }`,
                        disabled: i % 4 === 0
                    });
                }
                return data;
            };
            return {
                deviceData:[],//generateData(),
                addForm: {
                    rolename: '',
                    remark: '',
                    sns:''
                },
                result: [],

                addFormRules: {
                    rolename: [
                        { required: true, message: '请输入角色名', trigger: 'blur' }
                    ]
                }
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
                getDeviceList(para).then((res) => {
                    for (let device of res.data.data) {
                        this.deviceData.push({
                            key: device.sn,
                            label: `SN  ${ device.sn }`
                        });
                    }
                });
            },

            handleAdd(){
                let para =Object.assign({}, this.addForm);
                para.sns=this.result.map(item => item).toString();
                addRole(para).then((res) => {
                    this.$message({
                        message: '新增成功',
                        type: 'success'
                    });
                    this.$router.push({ path: '/role' });
                });
            }
        },
        mounted() {
            this.getDevices();
        }
    };

</script>