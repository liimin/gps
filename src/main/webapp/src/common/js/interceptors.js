// 引入axios以及element ui中的loading和message组件
import axios from 'axios'
import { Loading, Message } from 'element-ui'
import qs from 'qs';

// 超时时间
axios.defaults.timeout = 5000;
// http请求拦截器
var loadinginstace;
axios.interceptors.request.use(config => {
    // element ui Loading方法
    //loadinginstace = Loading.service({ fullscreen: true });
    /*if (store.state.token) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
        config.headers.Authorization = `token ${store.state.token}`;
    }*/
    if(config.data)
    config.data=qs.stringify(config.data);
    return config
}, error => {
    //loadinginstace.close();
    Message.error({
        message: '加载超时'
    })
    return Promise.reject(error)
})
// http响应拦截器
axios.interceptors.response.use(response  => {// 响应成功关闭loading
    //loadinginstace.close();
    return response
}, error => {
    //loadinginstace.close();
    Message.error({
        message: '加载失败'
    });
    if (error.response) {
        switch (error.response.status) {
            case 401:
                // 返回 401 清除token信息并跳转到登录页面
                //store.commit(types.LOGOUT);
                router.replace({
                    path: 'login',
                    query: {redirect: router.currentRoute.fullPath}
                })
        }
    }
    return Promise.reject(error.response.data || error)   // 返回接口返回的错误信息
    //return Promise.reject(error)
});

export default axios