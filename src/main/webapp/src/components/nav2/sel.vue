<template>
  <div>
    <h1>select 测试</h1>
    <select  @change="change" >
      <option value="" v-for="item in select1">{{item.txt}}</option>
    </select>
    <select  >
      <option value="" v-for="item in select2">{{item.txt}}</option>
    </select>
  </div>
</template>

<script>
    export default {
      data(){
          return{
              select1: [{txt:'change1',value:1},{txt:'change2',value:2}],
              select2:[],
          }
      },
    methods: {
        change:function(){
          this.select2=[{txt:'change'+Math.random(),value:1},{txt:'change2'+Math.random(),value:2}]
        }
    }
  }
</script>