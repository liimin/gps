<template>

    <el-container v-bind:style="{ height: containerHeight + 'px' }">
        <el-button icon="el-icon-menu"
                   circle
                   v-bind:style="{ top: (containerHeight/2) -20 + 'px' }"
                   v-bind:class="[ left ? 'switch-pull-left'  : 'switch-pull-right','switch']"
                   @click.native.prevent="click">
        </el-button>
        <el-aside width="220px" v-bind:class="[ left ? 'pull-left'  : 'pull-right' ]" >
            <el-input
                    placeholder="输入关键字进行过滤"
                    v-model="filterText">
            </el-input>
            <el-tree
                    class="device-tree"
                    :data="deviceData"
                    :props="defaultProps"
                    show-checkbox
                    default-expand-all
                    :filter-node-method="filterNode"
                    ref="devicetree">
            </el-tree>
        </el-aside>
        <el-main>
            <div class="mode-wrap">
                <el-radio-group v-model="mode" size="mini">
                    <el-radio label="1" border>实时</el-radio>
                    <el-radio label="2" border>轨迹</el-radio>
                </el-radio-group>
            </div>
            <gmap-map
                    :center="center"
                    :zoom="7"
                    style="width: 100%; height: 100%;"
            >
                <gmap-marker
                        :key="index"
                        v-for="(m, index) in markers"
                        :position="m.position"
                        :clickable="true"
                        :draggable="true"
                        @click="center=m.position"
                ></gmap-marker>
            </gmap-map>
        </el-main>
    </el-container>
</template>

<script>
    /////////////////////////////////////////
    // New in 0.4.0
    import * as VueGoogleMaps from 'vue2-google-maps';
    import Vue from 'vue';

    Vue.use(VueGoogleMaps, {
        load: {
            key: 'AIzaSyCpW1TwuC5cXs2E8eoDOuv3VIplN6_I_FA',
            v: '3',
            // libraries: 'places', //// If you need to use place input
        }
    });
    import { getDeviceList } from '../../api/api';
    export default {
        watch: {
            filterText(val) {
                this.$refs.devicetree.filter(val);
            },
            fullHeight (val) {
                if(!this.timer) {
                    this.fullHeight = val
                    this.timer = true
                    let that = this
                    setTimeout(function (){
                        that.timer = false
                    },400)
                }
            }
        },
        data() {
            return {
                containerHeight: 500,
                //map
                center: {lat: 10.0, lng: 10.0},
                markers: [{
                    position: {lat: 10.0, lng: 10.0}
                }, {
                    position: {lat: 11.0, lng: 11.0}
                }],
                //控制侧边栏
                left: true,
                //切换轨迹与实时
                mode:'1',
                //SN树
                filterText: '',
                deviceData: [],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                }
            };
        },
        methods: {
            click() {
                this.left = !this.left;
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            //获取设备列表
            getDevices() {
                let para = {
                    currentPage: 1,
                    pageSize:10000
                };
                getDeviceList(para).then((res) => {
                    let aDevices=res.data.data;
                    let obj=null;
                    for (let i = 0,j=aDevices.length; i <j ; i++) {
                        obj=aDevices[i];
                        this.deviceData.push({
                            id: obj.sn,
                            label: `SN  ${ obj.sn }`
                        });
                    }
                });
            },
            computeHeight(){
                this.containerHeight = document.getElementsByClassName('content-container')[0].offsetHeight-56;
            }
        },
        mounted() {
            this.getDevices();
            this.computeHeight();
            window.onresize = () => {
                return this.computeHeight()
            }
        }
    }
</script>
<style scoped lang="scss">
    $aside-width:220px;
    $container-height:600px;
    $switch-size:40px;
    $animate-mode:.2s linear forwards;
    $switch-left:200px;
    $base-color:#545c64;
    $border-style:1px solid $base-color;

    .device-tree{
        margin-top: 20px;
    }
    .el-container {
        height: $container-height;
        position: relative;
        .el-main {
            text-align: center;
            border: $border-style;
            position: relative;
            padding: 0;
            overflow:hidden;
            .mode-wrap{
                position: absolute;
                top: 10px;
                left: 50%;
                z-index: 819;
            }
        }
        .el-aside {
            text-align: center;
            margin-left: $aside-width;
            opacity: 0;
            z-index: 818;
            float: left;
            border: $border-style;
            border-right: none;
            width: $aside-width;
        }
        .switch {
            height: $switch-size;
            width: $switch-size/2;
            position: absolute;
            top: $container-height/2-20px;
            z-index: 819;
            border-color: $base-color;
            color: $base-color;
        }
    }


    .pull-right {
        animation: pull-right $animate-mode;
    }

    .pull-left {
        animation: pull-left $animate-mode;
    }

    .switch-pull-right {
        animation: switch-pull-right $animate-mode;
    }

    .switch-pull-left {
        animation: switch-pull-left $animate-mode;
    }



    @keyframes switch-pull-right {
        from {
            width: $switch-size / 2;
            left: 0;
            border-radius: 0 $switch-size / 2 $switch-size / 2 0;
            padding: 12px;

        }
        to {
            width: $switch-size;
            left: $switch-left;
            border-radius: 50%;
            border-left: $border-style;
        }
    }

    @keyframes switch-pull-left {
        from {
            width: $switch-size;
            left: $switch-left;
            border-radius: 50%;
        }
        to {
            width: $switch-size / 2;
            left: 0;
            border-radius: 0 $switch-size / 2 $switch-size / 2 0;
            padding: 0;
            border-left: none;
        }
    }

    @keyframes pull-right {
        from {
            margin-left: -$aside-width;
            opacity: 0
        }
        to {
            margin-left: 0;
            opacity: 1;
            width: $aside-width;
        }
    }

    @keyframes pull-left {
        from {
            margin-left: 0px;
            opacity: 1;
            width: $aside-width;
        }
        to {
            margin-left: -$aside-width;
            opacity: 0
        }
        /*0%{
            left: 0px;opacity: 1;width: 200px;
        }
        50%{
            opacity: 0;
        }
        100%{
            left: -200px;opacity: 0;width:0;
        }*/
    }
</style>