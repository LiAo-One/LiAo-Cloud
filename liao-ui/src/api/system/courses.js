import {system_service} from '@/utils/request'

// 查询实训课程列表
export function listCourses(query) {
  return system_service({
    url: '/system/courses/list',
    method: 'get',
    params: query
  })
}

// 查询实训课程详细
export function getCourses(courseId) {
  return system_service({
    url: '/system/courses/' + courseId,
    method: 'get'
  })
}

// 新增实训课程
export function addCourses(data) {
  return system_service({
    url: '/system/courses',
    method: 'post',
    params: data
  })
}

// 修改实训课程
export function updateCourses(data) {
  return system_service({
    url: '/system/courses',
    method: 'put',
    params: data
  })
}

// 删除实训课程
export function delCourses(courseId) {
  return system_service({
    url: '/system/courses/' + courseId,
    method: 'delete'
  })
}

// 导出实训课程
export function exportCourses(query) {
  return system_service({
    url: '/system/courses/export',
    method: 'get',
    params: query
  })
}