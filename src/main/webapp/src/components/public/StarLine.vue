<template>
    <div>
        <slot></slot>
    </div>
</template>

<script>
    export default {
        data(){
            return {
                bgColor:'#fff',
                ctx:null,
                w:0,
                h:0,
                arr:[],
                num:60
            }
        },
        methods:{
            init(){
                this.$store.state.color = this.bgColor;
                let  canvas = document.createElement('canvas');
                canvas.width=window.innerWidth;
                canvas.height=window.innerHeight;
                this.ctx=canvas.getContext('2d');
                this.w=canvas.width;
                this.h=canvas.height;
                for(var i=0; i<this.num; i++){
                    this.arr[i]={
                        rX:Math.random()*6-3,
                        rY:Math.random()*6-3,
                        x:Math.random()*this.w,
                        y:Math.random()*this.h
                    };
                    this.drawCircle(this.arr[i].x,this.arr[i].y);
                }
                canvas.style.display="block";
                canvas.style.background=this.$store.state.color;
                document.body.appendChild(canvas);
                document.body.style.background = "url('"+canvas.toDataURL()+"')";
            },
            drawCircle(x,y){
                var ctx=this.ctx;
                ctx.fillStyle='pink';
                ctx.beginPath();
                ctx.arc(x,y,1,0,Math.PI*2,false);
                ctx.closePath();
                ctx.fill();
            },
            starMove(){
                var ctx=this.ctx;
                ctx.clearRect(0,0,this.w,this.h);
                var star=null;
                var starNext=null;
                for(var i=0; i<this.num; i++){
                    star=this.arr[i];
                    star.x+=star.rX;
                    star.y+=star.rY;
                    if(star.x>this.w || star.x<0){
                        star.rX=-star.rX;
                    }
                    if(star.y>this.h || star.y<0){
                        star.rY=-star.rY;
                    }
                    this.drawCircle(star.x,star.y);
                    for(var j=i+1;j<this.num; j++){
                        starNext=this.arr[j];
                        if((star.x-starNext.x)*(star.x-starNext.x) + (star.y-starNext.y)*(star.y-starNext.y)<=50*50){
                            this.drawLine(star.x,star.y,starNext.x,starNext.y);
                        }
                    }
                }
            },
            drawLine(x1,y1,x2,y2,isMouse){
                var ctx=this.ctx;
                var color=ctx.createLinearGradient(x1,y1,x2,y2);
                if(isMouse){
                    color.addColorStop(0,'yellow');
                    color.addColorStop(1,'green');
                }else{
                    color.addColorStop(0,'#fff');
                    color.addColorStop(1,'#333');
                }
                ctx.save();
                ctx.strokeStyle=color;
                ctx.beginPath();
                ctx.moveTo(x1,y1);
                ctx.lineTo(x2,y2);
                ctx.closePath();
                ctx.stroke();
                ctx.restore();
            }

        },
        mounted(){
            this.init();
            setInterval(function(){
                this.starMove();
            }.bind(this),60);

            document.addEventListener('mousemove',function(e){
                var star=null;
                for(var i=0;i<this.num; i++){
                    star=this.arr[i];
                    if((star.x- e.clientX)*(star.x- e.clientX) + (star.y- e.clientY)*(star.y- e.clientY)<=80*80){
                        this.drawLine(star.x,star.y, e.clientX, e.clientY,true);
                    }
                }
            }.bind(this));
        }
    }

</script>

<style lang="css" scoped>
    .canvas{
        width: 100%;
        background: #000;
        display:block;
    }
</style>