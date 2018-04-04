import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Main from './views/Main.vue'
import Table from './views/User/User.vue'
import Form from './components/nav1/Form.vue'
import User from './views/User/User.vue'
import Role from './views/Role/Role.vue'
import Page4 from './components/nav2/Page4.vue'
import AMap from './components/nav2/AMap.vue'
import Google from './components/nav2/Google.vue'
//import Bing from './components/nav2/Bing.vue'
/*import Page6 from './components/nav3/Page6.vue'*/
//import echarts from './components/charts/echarts.vue'
import RoleEdit from './views/Role/RoleEdit.vue'
import RoleAdd from './views/Role/RoleAdd.vue'
import Monitor from './views/Monitor/Monitor.vue'
let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: 'HOME',
        iconCls: 'fa fa-home',//图标样式class
        children: [
            { path: '/main', component: Main, name: '主页'},
        ]
    },
    {
        path: '/',
        component: Home,
        name: 'SYSTEM',
        iconCls: 'fa fa-cogs',
        children: [
            { path: '/user', component: User, name: 'User' },
            { path: '/role', component: Role, name: 'Role' },
            { path: '/addRole', component: RoleAdd, name: 'RoleAdd', hidden: true  },
            { path: '/editRole', component: RoleEdit, name: 'RoleEdit', hidden: true  },

        ]
    },
    {
        path: '/',
        component: Home,
        name: 'OTHERS',
        iconCls: 'fa fa-address-card',
        //leaf: true,//只有一个节点
        children: [
            /*{ path: '/page6', component: Page6, name: '导航三' },*/
            { path: '/table', component: Table, name: 'Table' },
            { path: '/form', component: Form, name: 'Form' },
            { path: '/page4', component: Page4, name: '页面4' },
            { path: '/amap', component: AMap, name: 'AMap',iconCls:'el-icon-message' },
            { path: '/google', component: Google, name: 'Google',iconCls:'el-icon-message' },
            { path: '/monitor', component: Monitor, name: 'Monitor',iconCls:'el-icon-message' },
            /*{ path: '/bing', component: Bing, name: 'Bing',iconCls:'el-icon-message' }*/
        ]
    },
    {
        path: '/',
        component: Home,
        name: 'Charts',
        iconCls: 'fa fa-bar-chart',
        children: [
           // { path: '/echarts', component: echarts, name: 'echarts' }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;