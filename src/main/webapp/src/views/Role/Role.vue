<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.rolename" placeholder="角色名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getRoleList" icon="el-icon-search">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd"icon="el-icon-plus">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="roleList"  highlight-current-row v-loading="tableLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55" :selectable='selectable'>
			</el-table-column>
			<el-table-column type="index" width="60">
			</el-table-column>
			<el-table-column prop="rolename" label="角色名" width="260">
				<template slot-scope="scope">
					<i class="fa fa-user-o" aria-hidden="true" v-if="scope.row.roleid==10000"></i>
					<span style="margin-left: 5px">{{ scope.row.rolename }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="createtime" label="创建时间" width="200" sortable>
				<template slot-scope="scope">
					<i class="el-icon-time"></i>
					<span style="margin-left: 5px">{{ scope.row.createtime }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="createtime" label="更新时间" width="200" sortable>
				<template slot-scope="scope">
					<i class="el-icon-time" ></i>
					<span style="margin-left: 5px">{{ scope.row.updatetime }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="remark" label="备注" min-width="220" sortable>
			</el-table-column>
			<el-table-column label="操作" width="160">
				<template slot-scope="scope">
					<el-button type="primary"  @click="handleEdit(scope.$index, scope.row)" icon="el-icon-edit" :disabled="scope.row.roleid=='10000'" ></el-button>
					<el-button type="danger" icon="el-icon-delete"  @click="handleDel(scope.$index, scope.row)" :loading="sending" :disabled="scope.row.roleid=='10000'"></el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0" :loading="sending" icon="el-icon-delete">批量删除</el-button>
			<el-pagination layout="total,prev, pager, next" @current-change="handleCurrentChange" :page-size="pageSize" :total="total" style="float:right;">
			</el-pagination>
		</el-col>
	</section>
</template>

<script>
	import util from '../../common/js/util'
	import { getRoleList, removeRole } from '../../api/api';
    import { mapGetters } from 'vuex';
	export default {
        computed: {
            ...mapGetters(['sending','tableLoading'])
        },
		data() {
			return {
				filters: {
                    rolename: ''
				},
                sels:[],
                roleList: [],
				total: 0,
				page: 1,
				pageSize:10,
			}
		},
		methods: {
            selectable(row,index){
                return row.roleid!='10000';
            },
            selsChange: function (sels) {
                this.sels = sels;
            },
			handleCurrentChange(val) {
				this.page = val;
				this.getRoleList();
			},
			//获取用户列表
            getRoleList() {
				let para = {
					page: this.page,
                    rolename: this.filters.rolename,
					pageSize:this.pageSize
				};
                getRoleList(para).then((res) => {
					this.total = res.data.total;
					this.roleList = res.data.data;
				});
			},

			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let para = { ids: row.roleid };
                    removeRole(para).then((res) => {
						this.$message({
							message: '删除成功',
							type: 'success'
						});
                        this.getRoleList();
					});
				}).catch(() => {

				});
			},
			//跳转编辑界面
			handleEdit: function (index, row) {
                this.$router.push({ path: '/editRole' });
			},
			//跳转新增界面
			handleAdd: function () {
                this.$router.push({ path: '/addRole' });
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.roleid).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					let para = { ids: ids };
                    removeRole(para).then((res) => {
						this.$message({
							message: '删除成功',
							type: 'success'
						});
                        this.getRoleList();
					});
				}).catch(() => {

				});
			}
		},
		mounted() {
			this.getRoleList();

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