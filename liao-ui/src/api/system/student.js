import {system_service} from '@/utils/request'

// 查询学生列表
export function listStudent(query) {
  return system_service({
    url: '/system/student/list',
    method: 'get',
    params: query
  })
}

// 查询学生详细
export function getStudent(studentId) {
  return system_service({
    url: '/system/student/' + studentId,
    method: 'get'
  })
}

// 新增学生
export function addStudent(data) {
  return system_service({
    url: '/system/student',
    method: 'post',
    params: data
  })
}

// 修改学生
export function updateStudent(data) {
  return system_service({
    url: '/system/student',
    method: 'put',
    params: data
  })
}

// 删除学生
export function delStudent(studentId) {
  return system_service({
    url: '/system/student/' + studentId,
    method: 'delete'
  })
}

// 导出学生
export function exportStudent(query) {
  return system_service({
    url: '/system/student/export',
    method: 'get',
    params: query
  })
}