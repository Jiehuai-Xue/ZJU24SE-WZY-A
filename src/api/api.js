import axios from "axios";
import {error} from "vue-resource/src/util";

axios.defaults.baseURL = 'http://localhost:8080';//后端开发环境地址
axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';//配置请求头信息。

export const getUserInfoById = (user_id) => {
    let data;
    axios.get('/api/forum/users/id/' + user_id)
        .then(function (response) {
            data = response.data;
        }).catch((error) => {
            console.log(error);
        });
    return data;
};

export const getUserInfoByName = (name) => {
    return axios.get('/api/forum/users/username/' + name);
};

export const getAnnouncementById = (announcement_id,params) => {
    return axios.get('/api/forum/announcements/' + announcement_id, { params: params });
};

export const deleteAnnouncementById = (announcement_id) => {
    axios.delete('/api/forum/announcements/' + announcement_id)
        .then(response => console.log(response))
        .catch(error => console.log(error));
};

export const createAnnouncement = (announcement) => {
    axios.post('/api/forum/announcements', announcement)
        .then(response => console.log(response))
        .catch(error => console.log(error));
};

