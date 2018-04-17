<template>
	<el-container>
		<el-header>
			<el-row>
				<el-col :span="8" v-for="data in datas" :key="data.value">
					<el-card class="box-card">
						<div slot="header" class="clearfix">
							<span><i v-bind:class="data.icon" aria-hidden="true"></i>&nbsp;{{data.title}}</span>
							<el-button  type="text">操作按钮</el-button>
						</div>
						<div class="data-value">
							{{data.value}}
						</div>
					</el-card>
				</el-col>
			</el-row>
		</el-header>
		<el-main>
			<chart :options="chartOptions" />
		</el-main>
	</el-container>
</template>

<style scoped lang="scss">
	@import '~scss_vars';
	.el-main{
		margin-top: 110px;
	}
	.echarts {
		height: 500px;
		width: 100%;
	}
	.data-value {
		font:italic bold 60px/60px arial,sans-serif;
		color: $color-primary;;
	}
	.el-col{
		.el-button{
			float: right;
			padding: 3px 0;
		}
		.box-card{
			margin: 15px;
		}
	}
</style>

<script>
    import Vue from 'vue'
   	import ECharts from 'vue-echarts/components/ECharts.vue'

   // 手动引入 ECharts 各模块来减小打包体积
    import 'echarts/lib/chart/bar'
    import 'echarts/lib/chart/line'
    import 'echarts/lib/chart/pie'
    import 'echarts/lib/component/tooltip'
    import 'echarts/lib/component/legend'
    import 'echarts/lib/component/title'
    // 注册组件后即可使用
    Vue.component('chart', ECharts)


    import { getDeviceStatics } from '../api/api';
    export default {
        data() {
            return {
                datas:[
					{title:'总数',value:'0',icon:"fa fa-users" },
                    {title:'在线数',value:'0',icon:"fa fa-toggle-on"},
                    {title:'离线数',value:'0',icon:"fa fa-toggle-off"}
				],
				count:1,
				chartOptions:{
                    title: {
                        text: '动态数据',
                        subtext: '纯属虚构',
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross',
                            label: {
                                backgroundColor: '#283b56'
                            }
                        }
                    },
                    legend: {
                        data:['最新成交价', '预购队列'],
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            dataView: {readOnly: false},
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    dataZoom: {
                        show: false,
                        start: 0,
                        end: 100
                    },
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: true,
                            data:[]
                        },
                        {
                            type: 'category',
                            boundaryGap: true,
                            data: []
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            min: 0,
                            scale: true,
                            name: '价格',
                           /* max: 30,*/
                            boundaryGap: [0.2, 0.2]
                        },
                        {
                            type: 'value',
                            scale: true,
                            name: '预购量',
                           /* max: 1200,*/
                            min: 0,
                            boundaryGap: [0.2, 0.2]
                        }
                    ],
                    series: [
                        {
                            name:'预购队列',
                            type:'bar',
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            data:[]
                        },
                        {
                            name:'最新成交价',
                            type:'line',
                            data:[]
                        }
                    ]
				}
            }
        },
		methods:{
            creatData(){
				let axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
				let data0 = this.chartOptions.series[0].data;
				let data1 = this.chartOptions.series[1].data;
				let xAxis0= this.chartOptions.xAxis[0].data;
				let xAxis1= this.chartOptions.xAxis[1].data;
				if( this.count > 12){
					data0.shift();
					data1.shift();
					xAxis0.shift();
					xAxis1.shift();
				}
				data0.push(Math.round(Math.random() * 1000));
				data1.push((Math.random() * 10 + 5).toFixed(1) - 0);
				xAxis0.push(axisData);
				xAxis1.push(this.count++);
			},
            getDeviceStatics(){
                getDeviceStatics({}).then((res) => {
                    let {total, online ,offline }=res.data.data;
                    this.datas[0].value=total;
                    this.datas[1].value=online;
                    this.datas[2].value=offline;
                });
			}
		},
		mounted () {
            this.creatData();
            this.getDeviceStatics();
            setInterval(this.creatData, 2100);
        }
    }
</script>