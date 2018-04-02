<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="姓名"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" v-on:click="getUser">查询</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <template>
            <el-table :data="users" border highlight-current-row v-loading="loading" style="width: 100%;">
                <el-table-column type="index" width="60">
                </el-table-column>
                <el-table-column prop="userName" label="用户名" width="220">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            <p>用户名: {{ scope.row.userName }}</p>
                            <p>角色: {{ scope.row.roleNames }}</p>
                            <div slot="reference" class="name-wrapper">
                                <el-tag size="medium">{{ scope.row.userName }}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column prop="mobile" label="电话" width="200">
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="200" sortable>
                </el-table-column>
                <el-table-column prop="roleNames" label="角色" width="220" sortable>
                </el-table-column>
                <el-table-column prop="remark" label="备注" min-width="280" sortable>
                </el-table-column>
            </el-table>
        </template>

    </section>
</template>
<script>
    import {getUserList} from '../../api/api';
    export default {
        data() {
            return {
                filters: {
                    name: ''
                },
                loading: false,
                users: []
            }
        },
        methods: {
            //性别显示转换
            formatSex: function (row, column) {
                return row.sex == 1 ? '男' : row.sex == 0 ? '女' : '未知';
            },
            //获取用户列表
            getUser: function () {
                let para = {
                    name: this.filters.name
                };
                this.loading = true;
                getUserList(para).then((res) => {
                    this.users = res.data.data;
                    this.loading = false;
                });
            }
        },
        mounted() {
            this.getUser();
        }
    };

</script>

<style scoped>

</style>