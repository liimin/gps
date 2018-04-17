//test
export const getCount = state => {
    return state.count
};
export const sending = state => {
    return state.sending
};
export const tableLoading = state => {
    return state.table_loading
};
export const token = state => {
    return state.token || sessionStorage.getItem('token')
};
export const user = state => {
    return state.user || sessionStorage.getItem('user');
};