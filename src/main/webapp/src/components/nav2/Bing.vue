
<template id="child-Outmap">
	<div>
		<div id='printoutPanel'></div>
		<div id='myMap' :style="style"></div>
	</div>
</template>

<script>
    export default{
        data(){
            return{
                style:{
                    width:'85vw',
                    height:'82vh'
                },
                data_info:[],
				map:null
            }
        },
        /*props:{		//里面存放的也是数据，与data里面的数据不同的是，这里的数据是从其他地方得到的数据
          height:{
            type:Number,
            default:300
          },
          longitude:{//定义经度
            type:Number,
            default:116.404
          },
          latitude:{//定义纬度
            type:Number,
            default:39.915
          }
        },*/
        props:['om'],    //通过props属性，将从父级得到的数据传给子级
        mounted(){
            this.feathData();
        },
        methods:{
			importMap(){
                if(!this.map){
                    let head= document.getElementsByTagName("head")[0];
                    let script= document.createElement("script");
                    script.type= "text/javascript";
                    script.src= "https://www.bing.com/api/maps/mapcontrol?key=AtJ00V-1zLyoUyjQlSrJrKcPe__agGVYwrulR0x5EGhXojMfIWha7rCW6tlGh56R";
                    head.appendChild(script);
                    script.onload = ()=>{
                        this.feathData();
                    };
                }else {
                    this.feathData();
                }
			},
            feathData(){
                var _this=this;
                //this.$http.get('src/data/OutMap.txt').then((res)=>{
                    var map = new Microsoft.Maps.Map(document.getElementById('myMap'), {});
                   /* map.centerAndZoom(new BMap.Point(_this.om.longitude,_this.om.latitude), 15);
                    var opts = {
                        width : 250,     // 信息窗口宽度
                        height: 80,     // 信息窗口高度
                        title : "信息窗口" , // 信息窗口标题
                        enableMessage:true//设置允许信息窗发送短息
                    };
                    for(var i=0;i<_this.data_info.length;i++){
                        var marker = new BMap.Marker(new BMap.Point(_this.data_info[i].point1,_this.data_info[i].point2));  // 创建标注
                        var content = _this.data_info[i].address;
                        map.addOverlay(marker);               // 将标注添加到地图中
                        addClickHandler(content,marker);
                    }
                    function addClickHandler(content,marker){
                        marker.addEventListener("mouseover",function(e){
                            openInfo(content,e)}
                        );
                    };
                    function openInfo(content,e){
                        var p = e.target;
                        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
                        var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象
                        map.openInfoWindow(infoWindow,point); //开启信息窗口
                    };*/
               // }).catch((err)=>{
                //    console.log(err);
               // });
            }
        }
    }
</script>