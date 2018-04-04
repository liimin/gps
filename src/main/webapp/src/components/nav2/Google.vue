<template>
	<gmap-map
			:center="center"
			:zoom="7"
			style="width: 100%; height: 800px"
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
</template>

<script>
    /////////////////////////////////////////
    // New in 0.4.0
    import * as VueGoogleMaps from 'vue2.1-google-maps';
    import Vue from 'vue';

    Vue.use(VueGoogleMaps, {
        load: {
            key: 'AIzaSyCpW1TwuC5cXs2E8eoDOuv3VIplN6_I_FA',
            v: '3',
            // libraries: 'places', //// If you need to use place input
        }
    });

    export default {
        data () {
            return {
                center: {lat: 10.0, lng: 10.0},
                markers: [{
                    position: {lat: 10.0, lng: 10.0}
                }, {
                    position: {lat: 11.0, lng: 11.0}
                }]
            }
        }
    }
</script>