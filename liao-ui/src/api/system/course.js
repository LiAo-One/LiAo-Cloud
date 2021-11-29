import {system_service} from '@/utils/request'

// 查询课程列表
export function listCourse(query) {
  return system_service({
    url: '/system/course/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getCourse(cno) {
  return system_service({
    url: '/system/course/' + cno,
    method: 'get'
  })
}

// 新增课程
export function addCourse(data) {
  return system_service({
    url: '/system/course',
    method: 'post',
    params: data
  })
}

// 修改课程
export function updateCourse(data) {
  return system_service({
    url: '/system/course',
    method: 'put',
    params: data
  })
}

// 删除课程
export function delCourse(cno) {
  return system_service({
    url: '/system/course/' + cno,
    method: 'delete'
  })
}

// 导出课程
export function exportCourse(query) {
  return system_service({
    url: '/system/course/export',
    method: 'get',
    params: query
  })
}