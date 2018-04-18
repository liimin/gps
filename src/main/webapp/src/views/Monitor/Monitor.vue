<script src="../../common/js/options/maps/amap.js"></script>
<template>

    <el-container v-bind:style="{ height: containerHeight + 'px' }" class="monitor-wrap">
        <!---->
        <div class="corner" @click="mapChange"><!--<i  v-bind:class="[ mapOptions.gmap ? 'fa-map' : 'fa-google','fa','mapMode']" aria-hidden="true"  ></i>--></div>
        <el-button icon="el-icon-menu"
                   v-bind:style="{ top: (containerHeight/2) -20 + 'px' }"
                   v-bind:class="[ left ? 'switch-pull-left'  : 'switch-pull-right','switch']"
                   @click.native.prevent="side">
        </el-button>
        <el-aside width="200px" v-bind:class="[ left ? 'pull-left'  : 'pull-right']">
            <el-input
                    placeholder="输入关键字进行过滤"
                    v-model="filterText">
            </el-input>
            <el-tree
                    class="device-tree"
                    :data="treeOptions.deviceData"
                    :props="treeOptions.defaultProps"
                    show-checkbox
                    node-key="sn"
                    highlight-current
                    :filter-node-method="filterNode"
                    @check-change="treeNodeCheckChange"
                    ref="devicetree">
            </el-tree>
        </el-aside>
        <el-container>
        <el-main>
            <div class="mode-wrap">
                <el-radio-group v-model="mode" size="mini">
                    <el-radio label="1" border>实时</el-radio>
                    <el-radio label="2" border v-popover:popover2 >轨迹</el-radio>
                </el-radio-group>
                <el-tag type="info" size="mini">标签三</el-tag>
                <el-popover
                        ref="popover2"
                        placement="bottom"
                        title=""
                        width="400"
                        trigger="focus"
                        manual="true"
                        >
                    <el-date-picker
                            v-model="hisTimerange"
                            type="datetimerange"
                            :picker-options="datepicker.options"
                            :range-separator="datepicker.rangeSeparator"
                            :start-placeholder="datepicker.startPlaceholder"
                            :end-placeholder="datepicker.endPlaceholder"
                            align="right">
                    </el-date-picker>
                </el-popover>
            </div>
            <gmap-map
                    :center="map.options.center"
                    :zoom="map.options.zoom"
                    style="width: 100%; height: 100%;"
                    ref="gmap"
                    v-if="map.name==='gmap'">
                <gmap-marker
                        :key="index"
                        v-for="(m, index) in mapOptions.markers"
                        :position="m.position"
                        :clickable="true"
                        :draggable="true"
                        @click="center=m.position"
                ></gmap-marker>
                <GmapPolyline v-if="mapOptions.polyline.length" :path="mapOptions.polyline" :options="map.polyline"/>
            </gmap-map>
            <el-amap vid="amap" ref="amap" :zoom="map.options.zoom"  :amap-manager="map.options.amapManager" v-if="map.name==='amap'">
                <el-amap-marker  :key="index" v-for="(marker,index) in mapOptions.markers" :position="marker.position"></el-amap-marker>
                <el-amap-polyline :path="mapOptions.polyline" :strokeColor="map.options.polyline.strokeColor"></el-amap-polyline>
            </el-amap>
        </el-main>
        <div :class="[ up ? 'device-info-wrap-up'  : 'device-info-wrap-down','device-info-wrap']" v-if=" mode==1 "  >
            <el-table
                    :data="tableDeviceInfo"
                    height="120"
                    @click.native.prevent="foot">
                <el-table-column prop="sn" label="SN" ></el-table-column>
                <el-table-column prop="address" label="ADDRESS" width="460">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            <p>LATITUDE: {{ scope.row.latitude }}</p>
                            <p>LONGITUDE: {{ scope.row.longitude }}</p>
                            <div slot="reference" class="name-wrapper">
                                <el-tag size="medium">{{ scope.row.address }}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column prop="gpstime" label="GPSTIME" > </el-table-column>
                <el-table-column prop="speed" label="SPEED" > </el-table-column>
            </el-table>
        </div>
        <div :class="[ up ? 'device-info-wrap-up'  : 'device-info-wrap-down','device-info-wrap']"  v-if=" mode==2 " >
            <el-table
                    :data="tableHisInfo"
                    height="120"
                    @click.native.prevent="foot">
                <el-table-column prop="sn" label="SN" ></el-table-column>
                <el-table-column prop="address" label="ADDRESS" width="460">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            <p>LATITUDE: {{ scope.row.latitude }}</p>
                            <p>LONGITUDE: {{ scope.row.longitude }}</p>
                            <div slot="reference" class="name-wrapper">
                                <el-tag size="medium">{{ scope.row.address }}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column prop="gpstime" label="GPSTIME" > </el-table-column>
                <el-table-column prop="speed" label="SPEED" > </el-table-column>
                <div slot="append">
                    <el-button  @click.native.prevent="loadMore" class="loadmore" >load more...
                    </el-button>
                    <el-button  @click.native.prevent="loadAll" class="loadmore" >load all...
                    </el-button>
                </div>
            </el-table>

        </div>
        </el-container>
    </el-container>
</template>

<script>
    /////////////////////////////////////////
    // New in 0.4.0
    import * as VueGoogleMaps from 'vue2.1-google-maps';
    import Vue from 'vue';
    import moment from 'moment'
        Vue.use(VueGoogleMaps, {
        load: {
            key: 'AIzaSyCpW1TwuC5cXs2E8eoDOuv3VIplN6_I_FA',
            v: '3',
            // libraries: 'places', //// If you need to use place input
        }
    });
    import VueAMap from 'vue-amap';
    import amap from '../../common/js/options/maps/amap.js';
    import gmap from '../../common/js/options/maps/gmap.js';
    import datepicker from '../../common/js/options/datepicker.js';
    import treeOpts from '../../common/js/options/tree.js';

    Vue.use(VueAMap);
    amap.options.amapManager = new VueAMap.AMapManager();
    // 初始化vue-amap
    VueAMap.initAMapApiLoader({
        // 高德的key
        key: 'bd290dc27d0945959280753215ddda71',
        // 插件集合
        plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor','Geocoder'],
        // 高德 sdk 版本，默认为 1.4.4
        v: '1.4.5'
    });
    // 引入css，推荐将css放入main入口中引入一次即可。
    import { getDeviceList,getRtBySn,getHisBySn } from '../../api/api';
    import infiniteScroll from 'vue-infinite-scroll'
    Vue.use(infiniteScroll)

    export default {
        watch: {
            filterText(val) {
                this.$refs.devicetree.filter(val);
            },
            containerHeight (val) {
                if(!this.timer) {
                    this.containerHeight = val
                    this.timer = true
                    setTimeout(function (){
                        this.timer = false
                    },400);
                }
            },
            'mapOptions.markers'(markers) {
                this.map.fit(this.$refs,markers)
            },
            'hisTimerange'(value) {
                let begin=moment(value[0]).format("YYYY-MM-DD HH:mm:ss");
                let end=moment(value[0]).format("YYYY-MM-DD HH:mm:ss");
            },
            'mode'(value) {
                if(value==2){
                    this.$refs.devicetree.setCheckedKeys([]);
                }
            }
        },
        data() {
            return {
                //实时轨迹列表
                tableDeviceInfo:[],
                //历史轨迹列表
                tableHisInfo:[],
                containerHeight: 500,
                //控制侧边栏
                left: true,
                up:true,
                //切换轨迹与实时
                mode:'1',
                map:amap,
                datepicker:datepicker,
                filterText: '',
                treeOptions:{
                    //SN树
                    deviceData: [],
                    defaultProps: treeOpts.defaultProps
                },
                hisTimerange:'',
                mapOptions:{
                    markers: [],
                    polyline:[]
                },
                busy:false,
                temPolyline:[],
                pageSize:10,
                page:1
            };
        },
        methods: {
            loadMore: function() {
                this.page++;
                let list= this.temPolyline.filter((u, index) => index < this.pageSize * this.page && index >= this.pageSize * (this.page - 1));
                this.tableHisInfo.push.apply(this.tableHisInfo,list);
            },
            loadAll: function() {
                while (1){
                    this.page++;
                    let list= this.temPolyline.filter((u, index) => index < this.pageSize * this.page && index >= this.pageSize * (this.page - 1));
                    if(!list ||!list.length){
                        break;
                    }
                    this.tableHisInfo.push.apply(this.tableHisInfo,list);
                }
            },
            mapChange(){
                this.map = this.map.name==='amap'? gmap : amap;
            },
            side() {
                this.left = !this.left;
            },
            foot(){
                //this.up =!this.up;
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            treeNodeCheckChange(obj,isChecked,isChildChecked){
                if(isChecked){
                    if(this.mode =='2'){
                        this.$refs.devicetree.setCheckedKeys([]);
                        this.$refs.devicetree.setCheckedKeys([obj.id]);
                    }
                    this.getRT(obj.id);
                    this.getHis(obj.id);
                }else{
                    this.removeMarker(obj.id);
                    this.removePolyline();
                }
            },
            //获取设备列表
            getDevices() {
                let para = {
                    currentPage: 1,
                    pageSize:10000
                };
                getDeviceList(para).then((res) => {
                    for (let obj of res.data.data) {
                        this.treeOptions.deviceData.push({
                            id: obj.sn,
                            label: `SN  ${ obj.sn }`
                        });
                    }
                });
            },
            getRT(sn) {
                let para = {
                    sn: sn
                };
                getRtBySn(para).then((res) => {
                    if(res.data.data){
                        this.pushMarker(res.data.data,info=>{
                            this.tableDeviceInfo.push(info)
                        });
                    }
                })
            },
            getHis(sn) {
                let para = {
                    sn: sn
                };
                getHisBySn(para).then((res) => {
                    let { data } =res.data;
                    let start=data[0];
                    let end=data[data.length-1];
                    this.pushMarkers([start,end]);
                    this.pushPolyLine(data);

                });
            },
            computeHeight(){
                this.containerHeight = document.getElementsByClassName('content-container')[0].offsetHeight-56;
            },

            removeMarker(objId){
                splice(objId,this.mapOptions.markers);
                splice(objId,this.tableDeviceInfo);
            },

            pushMarkers(data,callback) {
                let aMarkers=this.map.formatMarkers(data);
                this.mapOptions.markers.concat(aMarkers)
                callback && callback();
            },

            pushMarker(data,callback){
                let objMarker=this.map.formatMarker(data);
                this.mapOptions.markers.push(objMarker.marker);
                callback && callback(objMarker.data);
            },

            pushPolyLine(data){
                let lines=this.map.formatPolyLines(data);
                this.mapOptions.polyline=lines.polylines;
                this.temPolyline=lines.data;
                this.tableHisInfo=
                    this.temPolyline.filter((u, index) => index < this.pageSize * this.page && index >= this.pageSize * (this.page - 1));
            },

            removePolyline(){
                this.mapOptions.polyline=[];
                this.tableHisInfo=[];
            },

            clear(){
               this.removeMarker();
               this.removePolyline();
            }

        },
        mounted() {
            this.computeHeight();
            this.getDevices();
            window.onresize = () => {
                return this.computeHeight()
            }
            this.getRT();
        }
    }

    function splice(objId,arr){
        if(!objId){
            arr=[];
            return;
        }
        let _i = arr.findIndex(item => item.sn === objId);
        _i > -1 && arr.splice(_i, 1);
    }
</script>
<style scoped lang="scss">
    @import '~scss_vars';
    $aside-width:200px;
    $container-height:600px;
    $switch-size:20px;
    $animate-mode:.2s ease forwards;
    $switch-left:190px;
    $border-style:1px solid $color-primary;

    .el-radio{
        background-color: rgba(255,255,255,0.8);
    }
    .device-tree{
        margin-top: 20px;
    }
    .el-container {
        overflow: hidden;
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
            z-index: 819;
            border-color: $color-primary;
            color: $color-primary;
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
    .corner{
        background:-webkit-linear-gradient(45deg, #2e2e2e 24%, #5F5F5F 40%,#6f6f6f 47%,#5F5F5F 46%,#2F2F2F 50%,#fff 50%,#fff);
        background:-moz-linear-gradient(45deg, #2e2e2e 24%, #5F5F5F 40%,#6f6f6f 47%,#5F5F5F 46%,#2F2F2F 50%,#fff 50%,#fff);
        background:-o-linear-gradient(45deg, #2e2e2e 24%, #5F5F5F 40%,#6f6f6f 47%,#5F5F5F 46%,#2F2F2F 50%,#fff 50%,#fff);
        background:-ms-linear-gradient(45deg, #2e2e2e 24%, #5F5F5F 40%,#6f6f6f 47%,#5F5F5F 46%,#2F2F2F 50%,#fff 50%,#fff);
        background:linear-gradient(45deg, #2e2e2e 10%, #5F5F5F 20%, #f3f3f3 30%, #5F5F5F 47%, #2F2F2F 47%, #fff 50%, #fff);
        width:10px;
        height:10px;
        border-radius:0 0 0px 90px / 0 0 0 0px;
        -webkit-transform:rotate(0deg);
        -moz-transform:rotate(0deg);
        -o-transform:rotate(0deg);
        -ms-transform:rotate(0deg);
        transform:rotate(0deg);
        position:absolute;
        right:0px;
        top:0px;
        box-shadow:-2px 3px 5px black;
        overflow:hidden;
        -webkit-transition:all 0.3s linear 0s;
        -moz-transition:all 0.3s linear 0s;
        -o-transition:all 0.3s linear 0s;
        -ms-transition:all 0.3s linear 0s;
        transition:all 0.3s linear 0s;
        z-index: 200;
        animation: rollback 20s infinite;
        transition: all 1s linear;

    }

    .corner:hover{
        animation:roll 1s ease forwards;
        cursor: pointer;

    }

    .mapMode{
        height: 30px;
        width: 30px;
        position: absolute;
        top: 0;
        right: 0;
        border: 1px solid $color-primary;
        transform: rotate(90deg);
    }

    @keyframes rollback {
        80%{
            width: 10px;
            height: 10px;
            border-radius:0 0 0px 90px / 0 0 0 10px;
        }
        90%{
            width: 30px;
            height: 30px;
            border-radius:0 0 0px 90px / 0 0 0 30px;
        }
        100%{
            width: 10px;
            height: 10px;
            border-radius:0 0 0px 90px / 0 0 0 10px;
        }
    }
    @keyframes roll {
        0%{
            width: 10px;
            height: 10px;
            border-radius:0 0 0px 90px / 0 0 0 10px;
        }
        100%{
            width: 30px;
            height: 30px;
            border-radius:0 0 0px 90px / 0 0 0 30px;
        }
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
            bottom: $aside-width;
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
    }
</style>
<style lang="scss">
    $table-bg:rgba(84,92,100,.3);
    $tr-height:20px;
    $wrap-height:120px;
    .monitor-wrap .el-button{padding :0}
    .device-info-wrap{
        position: absolute;
        bottom:0;
        width: calc(100% - 2px);
        height: $wrap-height;
        overflow-y: auto;
        transition:bottom .3s ease;
        margin:auto;
    left:0;
    right:0;
        .el-table{
            background-color: $table-bg;
            width:100%;
            maxheight:$wrap-height;
            cursor:pointer;

            th,tr{
                background-color: $table-bg;
                color: #fff;
            }
            td,th{
                padding: 1px 0;
            }
            .el-table__empty-text{
                color:#fff;
                background-color: #666;
                padding: 2px 3px;
            }
        }
        .el-table__expanded-cell{
            background-color: $table-bg;
        }
        .el-table--enable-row-hover .el-table__body tr:hover>td{
            background-color:rgba(84,92,100,.5);
        }
        .el-table__append-wrapper{
            text-align: center;
            .loadmore{
                color:#fff;
                background-color: #666;
                padding: 2px 3px;
                border: 0;
                color: #fff;
                font-size: 14px;
                font-weight: bold;
            }
        }
        .el-tag{
            color: #fff;
            background-color: rgba(0,0,0,.3);
        }
    },
    .device-info-wrap:after{
        content: '';

    }
    .device-info-wrap-down{
        bottom: $wrap-height*-1  + $tr-height
    }
    .device-info-wrap-up{
        bottom: 1px;
    }
</style>