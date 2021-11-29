import {system_service} from '@/utils/request'

// 查询教师列表
export function listTeacher(query) {
  return system_service({
    url: '/system/teacher/list',
    method: 'get',
    params: query
  })
}

// 查询教师详细
export function getTeacher(id) {
  return system_service({
    url: '/system/teacher/' + id,
    method: 'get'
  })
}

// 新增教师
export function addTeacher(data) {
  return system_service({
    url: '/system/teacher',
    method: 'post',
    params: data
  })
}

// 修改教师
export function updateTeacher(data) {
  return system_service({
    url: '/system/teacher',
    method: 'put',
    params: data
  })
}

// 删除教师
export function delTeacher(id) {
  return system_service({
    url: '/system/teacher/' + id,
    method: 'delete'
  })
}

// 导出教师
export function exportTeacher(query) {
  return system_service({
    url: '/system/teacher/export',
    method: 'get',
    params: query
  })
}