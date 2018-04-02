<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.username" placeholder="姓名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getUsers">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="users"  highlight-current-row v-loading="tableLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55" :selectable='selectable'>
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="username" label="用户名" width="120">
				<template slot-scope="scope">
					<i class="fa fa-user-o" aria-hidden="true" v-if="scope.row.userid==10000"></i>
					<span style="margin-left: 5px">{{ scope.row.username }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="mobile" label="电话" width="160">
			</el-table-column>
			<el-table-column prop="createtime" label="创建时间" width="200" sortable>
				<template slot-scope="scope">
					<i class="el-icon-time" ></i>
					<span style="margin-left: 5px">{{ scope.row.createtime }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="roleNames" label="角色" width="160" sortable>
			</el-table-column>

			<el-table-column prop="createtime" label="更新时间" width="200" sortable>
				<template slot-scope="scope">
					<i class="el-icon-time" ></i>
					<span style="margin-left: 5px">{{ scope.row.updatetime }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="remark" label="备注" min-width="120" sortable>
			</el-table-column>
			<el-table-column label="操作" width="160">
				<template slot-scope="scope">
					<el-button type="primary" icon="el-icon-edit"  @click="handleEdit(scope.$index, scope.row)" :disabled="scope.row.userid=='10000'"></el-button>
					<el-button type="danger" icon="el-icon-delete"  @click="handleDel(scope.$index, scope.row)" :disabled="scope.row.userid=='10000'"></el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0" :loading="sending">批量删除</el-button>
			<el-pagination layout="total,prev, pager, next" @current-change="handleCurrentChange" :page-size="pageSize" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑界面-->
		<el-dialog title="编辑" :visible.sync="editFormVisible"  :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="formRules" ref="editForm">
				<el-form-item label="用户名" prop="username">
					<el-input v-model="editForm.username" auto-complete="off" :disabled="true"></el-input>
				</el-form-item>
				<el-form-item label="电话" prop="mobile">
					<el-input v-model="editForm.mobile" auto-complete="off" clearable></el-input>
				</el-form-item>

				<el-form-item label="角色" prop="roleIds">
					<el-radio-group v-model="editForm.roleIds">
						<el-radio v-for="(role,index) in roles" :key="index" :label="role.roleid" border>{{role.rolename}}</el-radio>
					</el-radio-group>
				</el-form-item>

				<el-form-item label="备注">
					<el-input type="textarea" v-model="editForm.remark" clearable></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="sending">提交</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="formRules" ref="addForm">
				<el-form-item label="用户名" prop="username">
					<el-input v-model="addForm.username" auto-complete="off" clearable ></el-input>
				</el-form-item>
				<el-form-item label="密码" prop="password">
					<el-input v-model="addForm.password" auto-complete="off" clearable type="password"></el-input>
				</el-form-item>
				<el-form-item label="确认密码" prop="password1">
					<el-input v-model="addForm.password1" auto-complete="off" clearable type="password"></el-input>
				</el-form-item>
				<el-form-item label="电话" prop="mobile">
					<el-input v-model="addForm.mobile" auto-complete="off" clearable ></el-input>
				</el-form-item>

				<el-form-item label="角色" prop="roleIds">
					<el-radio-group v-model="addForm.roleIds">
						<el-radio v-for="(role,index) in roles" :key="index" :label="role.roleid" border>{{role.rolename}}</el-radio>
					</el-radio-group>
				</el-form-item>

				<el-form-item label="备注">
					<el-input type="textarea" v-model="addForm.remark" clearable></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="sending">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	import util from '../../common/js/util'
	import { getUserList, removeUser, editUser, addUser,getAllRoles } from '../../api/api';
    import { mapGetters } from 'vuex'

	export default {
        computed: {
            // 使用对象展开运算符将 getters 混入 computed 对象中
            ...mapGetters(['sending','tableLoading'])
        },
		data() {
            var confirmPassword = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.addForm.password !== '') {
                        this.$refs.addForm.validateField('password1');
                    }
                    callback();
                }
            };
            var confirmPassword2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.addForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
			return {
				filters: {
					username: ''
				},
				users: [],
				total: 0,
				currentPage: 1,
				pageSize:10,
				sels: [],//列表选中列
				editFormVisible: false,//编辑界面是否显示
				//编辑界面数据
				editForm: {
					userId: 0,
					username: '',
					roleIds:0,
					remark: '',
					mobile: ''
				},

                roles:[],

				addFormVisible: false,//新增界面是否显示
				addLoading: false,
				formRules: {
                    username: [
						{ required: true, message: '请输入用户名', trigger: 'blur' }
					],
					password:[
                        {required: true, trigger: 'blur', validator: confirmPassword },
                        {pattern:/^(\w){6,20}$/ , message: '只能输入6-20个字母、数字、下划线 ！', trigger: 'blur'}
					],
                    password1:[
                        {required: true, trigger: 'blur', validator: confirmPassword2 }
                    ]
				},
				//新增界面数据
				addForm: {
                    username: '',
                    roleIds:0,
                    remark: '',
					password:'',
					password1:'',
                    mobile: ''
				}

			}
		},
      /*  watch:{
			roles:{
				handler(curVal,oldVal){
					this.addForm.roleIds=parseInt(this.roles[0].roleId);
				}
			}
    	},*/
		methods: {
            selectable(row,index){
				return row.userid!='10000';
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getUsers();
			},
			//获取用户列表
			getUsers() {
				let para = {
					currentPage: this.page,
					name: this.filters.name,
					pageSize:this.pageSize
				};
                getUserList(para).then((res) => {
					this.total = res.data.total;
					this.users = res.data.data;
				});
			},

			//获取所有角色列表
            getAllRoles(call) {
                if (this.roles.length){
                   	call && call();
                    return
                };
                getAllRoles({}).then((res) => {
                    this.roles = res.data.data;
                    this.listLoading = false;
                    call && call();
                });
			},

			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let para = { ids: row.userid };
					removeUser(para).then((res) => {
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.editFormVisible = true;
                this.editForm = Object.assign({}, row);
                this.editForm.roleIds=parseInt(this.editForm.roleIds);
				this.getAllRoles();
			},
			//显示新增界面
			handleAdd: function () {
				this.addFormVisible = true;
                this.addForm = {
                    username: '',
                    roleIds:'',
                    remark: '',
                    mobile: ''
                };
				this.getAllRoles(function(){
                    this.addForm.roleIds=this.roles[0].roleid;
				}.bind(this));
			},
			//编辑
			editSubmit: function () {
				this.$refs.editForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.editLoading = true;
							let para = Object.assign({}, this.editForm);
							return;
                            delete para.createtime;
                            delete para.password;
                            delete para.roleNames;
                            delete para.status;
							editUser(para).then((res) => {
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['editForm'].resetFields();
								this.editFormVisible = false;
								this.getUsers();
							}).catch(err=>{
                            });
						});
					}
				});
			},
			//新增
			addSubmit: function () {
				this.$refs.addForm.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							let para = Object.assign({}, this.addForm);
							addUser(para).then((res) => {
								this.addLoading = false;
								this.$message({
									message: '提交成功',
									type: 'success'
								});
								this.$refs['addForm'].resetFields();
								this.addFormVisible = false;
								this.getUsers();
							}).catch(err=>{
                            });
						});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.userid).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					let para = { ids: ids };
					removeUser(para).then((res) => {
						this.listLoading = false;
						this.$message({
							message: '删除成功',
							type: 'success'
						});
						this.getUsers();
					});
				}).catch(() => {

				});
			}
		},
		mounted() {
			this.getUsers();
		}
	}

</script>

<style scoped>
	.el-button + .el-button{
		margin-left:2px;
	}
	.demo-table-expand {
		font-size: 0;
	}
	.demo-table-expand label {
		width: 90px;
		color: #99a9bf;
	}
	.demo-table-expand .el-form-item {
		margin-right: 0;
		margin-bottom: 0;
		width: 50%;
	}
</style>