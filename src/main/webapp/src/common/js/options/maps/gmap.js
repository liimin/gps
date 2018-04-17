import convert from '../../coordinateConvert.js'
export default {
    name:'gmap',
    options:{
        zoom:10,
        center: {lng:113.973800, lat:22.538006},
        polyline:{
            geodesic: true,
            strokeColor: '#FF0000',
            strokeOpacity: 1.0,
            strokeWeight: 2
        }
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
        return { marker:{position:{lat:alat,lng:alng},sn:sn},data:{latitude:alat,longitude:alng,sn:sn,gpstime:gpstime,speed:speed}};
    },

    /**
     * 格式化多个marker数据
     * @param arrMarkers
     * @returns {Array}
     */
    formatMarkers(arrMarkers){
        let __arrMarkers=[],__oMarker;
        for(let __objMarker of arrMarkers){
            __oMarker=formatMarker(__objMarker);
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
        let latlng=convert.gcj_encrypt(latitude,longitude);
        alat=latlng.lat;
        alng=latlng.lon;
        return {position:{lat: alat, lng: alng},obj:{latitude:alat,longitude:alng,sn:sn,gpstime:gpstime,speed:speed}};
    },

    /**
     * 格式化多个折线数据
     * @param arrPolyLines
     * @returns {Array}
     */
    formatPolyLines(arrPolyLines){
        let __arrPolyLines=[],__oPolyLine,arrObj=[];
        for(let __objPolyLine of arrPolyLines){
            __oPolyLine=formatPolyLine(__objPolyLine);
            __arrPolyLines.push(__oPolyLine.position);
            arrObj.push(__oPolyLine.obj);
        }
        return { polylines:__arrPolyLines,origin:arrObj};
    },

    fit($$refs,markers){
        const bounds = new google.maps.LatLngBounds()
        for (let m of markers) {
            bounds.extend(m.position)
        }
        $$refs.gmap.$mapObject.fitBounds(bounds)
    }
};
