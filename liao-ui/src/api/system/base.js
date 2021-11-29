import {system_service} from '@/utils/request'

// 查询实训基地列表
export function listBase(query) {
  return system_service({
    url: '/system/base/list',
    method: 'get',
    params: query
  })
}

// 查询实训基地详细
export function getBase(baseId) {
  return system_service({
    url: '/system/base/' + baseId,
    method: 'get'
  })
}

// 新增实训基地
export function addBase(data) {
  return system_service({
    url: '/system/base',
    method: 'post',
    params: data
  })
}

// 修改实训基地
export function updateBase(data) {
  return system_service({
    url: '/system/base',
    method: 'put',
    params: data
  })
}

// 删除实训基地
export function delBase(baseId) {
  return system_service({
    url: '/system/base/' + baseId,
    method: 'delete'
  })
}

// 导出实训基地
export function exportBase(query) {
  return system_service({
    url: '/system/base/export',
    method: 'get',
    params: query
  })
}