<template>
	<el-container>
		<el-header>
			<el-row>
				<el-col :span="8" v-for="data in datas" :key="data">
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
			<!--<chart
				:options="pie"
				:init-options="initOptions"
				auto-resize
			/>-->
		</el-main>
	</el-container>
</template>

<style scoped lang="scss">
	@import '~scss_vars';
	.echarts {
		height: 500px;
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
    import 'echarts/lib/component/polar'
    import pie from '../mock/data/main/pie'
    // 注册组件后即可使用
    Vue.component('chart', ECharts)
    export default {
        data: function () {
            return {
                datas:[
					{title:'总数',value:'88888888',icon:"fa fa-users" },
                    {title:'在线数',value:'55555588',icon:"fa fa-toggle-on"},
                    {title:'离线数',value:'33333300',icon:"fa fa-toggle-off"}
				],
                initOptions: {
                    //renderer: options.renderer || 'canvas'
                },
				pie:{
                    backgroundColor: '#fff',
                    title: {
                        text: 'Customized Pie',
                        left: 'center',
                        top: 20,
                        textStyle: {
                            color: '#ccc'
                        }
                    },

                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },

                    visualMap: {
                        show: false,
                        min: 80,
                        max: 600,
                        inRange: {
                            colorLightness: [0, 1]
                        }
                    },
                    series : [
                        {
                            name:'访问来源',
                            type:'pie',
                            radius : '55%',
                            center: ['50%', '50%'],
                            data:[
                                {value:335, name:'直接访问'},
                                {value:310, name:'邮件营销'},
                            ].sort(function (a, b) { return a.value - b.value; }),
                            roseType: 'radius',
                            label: {
                                normal: {
                                    textStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    lineStyle: {
                                        color: 'rgba(255, 255, 255, 0.3)'
                                    },
                                    smooth: 0.2,
                                    length: 10,
                                    length2: 20
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: '#c23531',
                                    /*shadowBlur: 200,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'*/
                                }
                            },

                            animationType: 'scale',
                            animationEasing: 'elasticOut',
                            animationDelay: function (idx) {
                                return Math.random() * 200;
                            }
                        }
                    ]
				}
            }
        },mounted () {
        }
    }
</script>