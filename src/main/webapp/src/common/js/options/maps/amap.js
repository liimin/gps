import convert from '../../coordinateConvert.js'
export default {

    name:'amap',
    options:{
        zoom:10,
        center: [113,973800, 22,538006],
        amapManager:'',
        polyline:{
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 2
        },
        plugin: [{
            pName: 'Geolocation',
            events: {
                init(o) {
                    // o 是高德地图定位插件实例
                    o.getCurrentPosition((status, result) => {
                        if (result && result.position) {
                            /*self.lng = result.position.lng;
                            self.lat = result.position.lat;
                            self.center = [self.lng, self.lat];
                            self.loaded = true;
                            self.$nextTick();*/
                            console.log(result.position)
                        }
                    });
                }
            }
        }]
    },
    getMap(){
      return this.options.amapManager.getMap();
    },
    /**
     * 格式化单个marker数据
     * @param objMarker
     * @returns {{position: [null,null], sn: *}}
     */
    formatMarker(objMarker){
        let { latitude,longitude,sn ,gpstime,speed,alat,alng }=objMarker;
        latitude=latitude/1000000;
        longitude=longitude/1000000;
        let latlng=convert.gcj_encrypt(latitude,longitude)
        alat=latlng.lat;
        alng=latlng.lon;
        return {marker:{position:[alng,alat],sn:sn},data:{latitude:alat,longitude:alng,sn:sn,gpstime:gpstime,speed:speed}};
    },

    /**
     * 格式化多个marker数据
     * @param arrMarkers
     * @returns {Array}
     */
    formatMarkers(arrMarkers){
        let __arrMarkers=[],__oMarker;
        for(let __objMarker of arrMarkers){
            __oMarker=this.formatMarker(__objMarker);
            __arrMarkers.push(__oMarker.marker)
        }
        return __arrMarkers;
    },

    /**
     * 格式化单个折线数据
     * @param objPoliLine
     * @returns {[null,null]}
     */
    formatPolyLine(objPoliLine){
        let { latitude,longitude,sn ,gpstime,speed,alat,alng }=objPoliLine;
        latitude=latitude/1000000;
        longitude=longitude/1000000;
        let latlng=convert.gcj_encrypt(latitude,longitude)
        alat=latlng.lat;
        alng=latlng.lon;
        return { position:[alng, alat],data:{latitude:alat,longitude:alng,sn:sn,gpstime:gpstime,speed:speed}};
    },

    /**
     * 格式化多个折线数据
     * @param arrPolyLines
     * @returns {Array}
     */
    formatPolyLines(arrPolyLines){
        let __arrPolyLines=[],__oPolyLine,arrObj=[];
        for(let __objPolyLine of arrPolyLines){
            __oPolyLine=this.formatPolyLine(__objPolyLine);
            __arrPolyLines.push(__oPolyLine.position)
            arrObj.push(__oPolyLine.data);
        }
        return { polylines:__arrPolyLines,data:arrObj};
    },

    fit($$refs,markers){
        this.getMap().setFitView();
    }
};
