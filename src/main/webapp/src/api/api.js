import axios from 'axios';

let base = '/api/gps';


export const requestLogin = params => { return axios.post(`${base}/loginPost`, params).then(res => res.data); };

export const getUserList = params => { return axios.post(`${base}/user/getUserList`, params)};

export const getRoleList = params => { return axios.post(`${base}/role/getRoleList`, params); };

//export const getUserListPage = params => { return axios.get(`${base}/user/listpage`, { params: params }); };

export const removeUser = params => { return axios.post(`${base}/user/batchremove`, params); };

export const editUser = params => { return axios.post(`${base}/user/edit`, params); };

export const addUser = params => { return axios.post(`${base}/user/add`, params); };


// role
export const getAllRoles = params => { return axios.post(`${base}/role/getAllRoles`, params); };

export const addRole = params => { return axios.post(`${base}/role/add`, params); };

export const editRole = params => { return axios.post(`${base}/role/edit`, params); };

export const removeRole = params => { return axios.post(`${base}/role/remove`, params); };

//device
export const getDeviceList = params => { return axios.post(`${base}/device/getDeviceList`, params); };

//gps
export const getRtBySn = params => { return axios.post(`${base}/track/getRtBySn`, params); };
export const getHisBySn = params => { return axios.post(`${base}/track/getHisBySn`, params); };
