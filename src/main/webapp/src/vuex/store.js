import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'

Vue.use(Vuex)

// 应用初始状态
const state = {
    count: 1,
    color:"#fff",
    sending:false,
    table_loading:false
};

// 定义所需的 mutations
const mutations = {
    INCREMENT(state) {
        state.count++
    },
    DECREMENT(state) {
        state.count--
    },
    BGCOLOR(state,color){
        state.color=color;
    },
    SET_SENDING(state,isSending){
        state.sending=isSending;
    },
    SET_TABLE_LOADING(state,isLoading){
        state.table_loading=isLoading;
    }
}

// 创建 store 实例
export default new Vuex.Store({
    actions,
    getters,
    state,
    mutations
})