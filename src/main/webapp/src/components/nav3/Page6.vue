<template>
	<div class="amap-page-container">
		<el-alert
				title="高德原生API"
				type="success">
		</el-alert>
		<el-amap vid="amapDemo"  :center="center" :amap-manager="amapManager" :zoom="zoom" :events="events" class="amap-demo">
		</el-amap>

		<div class="toolbar">
			<el-button @click="showWindow()">Show InfoWindow</el-button>
		</div>
	</div>
</template>

<style>
	.amap-demo {
		height: 300px;
	}
</style>

<script>

    // NPM 方式
	import { AMapManager } from 'vue-amap';
    // CDN 方式
    let amapManager = new AMapManager();
    module.exports = {
        data: function() {
            return {
                zoom: 12,
                center: [121.59996, 31.197646],
                amapManager,
                events: {
                    init(o) {
                        let marker = new AMap.Marker({
                            position: [121.59996, 31.197646]
                        });

                        marker.setMap(o);
                    }
                }
            };
        },

        methods: {
            add() {
                let o = amapManager.getMap();
                let marker = new AMap.Marker({
                    position: [121.59996, 31.177646]
                });

                marker.setMap(o);
            },

			showWindow(){
                let o = amapManager.getMap();
                var info = [];
                info.push("<div><div><img style=\"float:left;\" src=\" http://webapi.amap.com/images/autonavi.png \"/></div> ");
                info.push("<div style=\"padding:0px 0px 0px 4px;\"><b>高德软件</b>");
                info.push("电话 : 010-84107000   邮编 : 100102");
                info.push("地址 :北京市朝阳区望京阜荣街10号首开广场4层</div></div>");
                let infoWindow = new AMap.InfoWindow({
                    content: info.join("<br/>"),  //使用默认信息窗体框样式，显示信息内容
                    offset:{x:-10,y:-10}
                });
                infoWindow.open(o, o.getCenter());
			}
        },

		mounted(){
		}
    };
</script>