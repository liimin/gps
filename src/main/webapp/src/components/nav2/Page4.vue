<template>
  <div>
    <h1>vuex 测试1111</h1>
    Clicked: {{ getCount }} times
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
    <Sel></Sel>
    <Sel></Sel>
    <Sel></Sel>
  </div>

</template>

<script>
  import { mapGetters } from 'vuex'
  import { mapActions } from 'vuex'
  import  Sel  from './sel.vue'

  export default {
    computed: {
  	// 使用对象展开运算符将 getters 混入 computed 对象中
    ...mapGetters([
        'getCount'
      // ...
    ])
    },
    methods: {
    ...mapActions([
      'increment', // 映射 this.increment() 为 this.$store.dispatch('increment')
      'decrement'
    ]),
        change:function(){
          this.select2=[{txt:'change'+Math.random(),value:1},{txt:'change2'+Math.random(),value:2}]
        }
      //...mapActions({
      //  add: 'increment' // 映射 this.add() 为 this.$store.dispatch('increment')
      //})
    },
      components: {
          Sel
      }
  }

</script>