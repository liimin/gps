import babelpolyfill from 'babel-polyfill'
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
/*import 'element-ui/lib/theme-chalk/index.css'*/
import './assets/theme/theme-dark/index.css'
import store from './vuex/store'
import Vuex from 'vuex'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import router from './routes'
//import Mock from './mock'
//Mock.bootstrap();
import 'font-awesome/css/font-awesome.min.css'
import './common/js/interceptors'
import DrawerLayout from 'vue-drawer-layout'

Vue.use(DrawerLayout)
Vue.use(ElementUI);
new Vue({
  //el: '#app',
  //template: '<App/>',
  router,
  store,
  //components: { App }
  render: h => h(App)
}).$mount('#app')
