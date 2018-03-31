<template>
	<div class="amap-page-container">
		<el-amap vid="amapDemo" :zoom="zoom" :center="center" class="amap-demo">
			<el-amap-marker v-for="marker in markers" :key="marker.position[0]" :position="marker.position" :events="marker.events" :visible="marker.visible" :draggable="marker.draggable"></el-amap-marker>
			<el-amap-info-window v-for="(window,index) in windows" :key="window.position[0]" :position="window.position" :content="window.content" :open="window.open"></el-amap-info-window>
		</el-amap>

		<div class="toolbar">
			<button type="button" name="button" @click="toggleVisible">toggle first marker</button>
			<button type="button" name="button" @click="changePosition">change position</button>
			<button type="button" name="button" @click="chnageDraggle">change draggle</button>
			<button type="button" name="button" @click="addMarker">add marker</button>
			<button type="button" name="button" @click="removeMarker">remove marker</button>
		</div>
	</div>
</template>
<style>
	.amap-page-container {
		width: 100%;
		height: 500px;
	}
</style>

<script>
    module.exports = {
        name: 'amap-page',
        data() {
            return {
                zoom: 14,
                center: [121.5273285, 31.21515044],
                markers: [
                    {
                        position: [121.5273285, 31.21515044],
                        events: {
                            click: () => {
                                alert('click marker');
                            },
                            dragend: (e) => {
                                this.markers[0].position = [e.lnglat.lng, e.lnglat.lat];
                            }
                        },
                        visible: true,
                        draggable: false,
                        template: '<span>1</span>'
                    }
                ],
				windows:[
                    {
                        position: [121.5273285, 31.21515044],
                        content: `<div class="prompt">aaaa</div>`,
                        visible: false
                    }
				]
            };
        },
        methods: {
            changePosition() {
                let position = this.markers[0].position;
                this.markers[0].position = [position[0] + 0.002, position[1] - 0.002];
            },
            chnageDraggle() {
                let draggable = this.markers[0].draggable;
                this.markers[0].draggable = !draggable;
            },
            toggleVisible() {
                let visableVar = this.markers[0].visible;
                this.markers[0].visible = !visableVar;
            },
            addMarker() {
                let marker = {
                    position: [121.5273285 + (Math.random() - 0.5) * 0.02, 31.21515044 + (Math.random() - 0.5) * 0.02]
                };
                this.markers.push(marker);
            },
            removeMarker() {
                if (!this.markers.length) return;
                this.markers.splice(this.markers.length - 1, 1);
            }
        }
    };
</script>
