// import request from '@/utils/request'

export function getCourse(courseType) {

    // return request({
    //     url: '/course/course/listByType?type=' + courseType,
    //     method: 'GET'
    // }).catch(error => {
    //     // 处理捕获的异常
    //     console.error('请求失败', error);
    // })
}

export function getCourseByName(name, type) {

    // return request({
    //     url: '/course/course/listByName?name=' + name + "&type=" + type,
    //     method: 'GET'
    // }).catch(error => {
    //     // 处理捕获的异常
    //     console.error('请求失败', error);
    // })
}

export function getCourseBySelect() {

    // return request({
    //     url: '/course/course/listBySelect',
    //     method: 'GET'
    // }).catch(error => {
    //     // 处理捕获的异常
    //     console.error('请求失败', error);
    // })
}


// 新增用户
export function addCourseSelect(data) {
    // return request({
    //     url: '/course/courseSelect/add',
    //     method: 'post',
    //     data: data
    // })
}

// 新增用户
export function deleteCourseSelect(data) {
    // return request({
    //     url: '/course/courseSelect/delete',
    //     method: 'post',
    //     data: data
    // })
}