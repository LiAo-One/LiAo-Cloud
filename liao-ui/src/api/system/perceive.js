import {system_service} from '@/utils/request'

// 查询设备数据列表
export function listPerceive(query) {
  return system_service({
    url: '/system/perceive/list',
    method: 'get',
    params: query
  })
}

// 查询设备数据详细
export function getPerceive(pId) {
  return system_service({
    url: '/system/perceive/' + pId,
    method: 'get'
  })
}

// 新增设备数据

export function addPerceive(data) {
  return system_service({
    url: '/system/perceive',
    method: 'post',
    params: data
  })
}

// 修改设备数据

export function updatePerceive(data) {
  return system_service({
    url: '/system/perceive',
    method: 'put',
    params: data
  })
}

// 删除设备数据

export function delPerceive(pId) {
  return system_service({
    url: '/system/perceive/' + pId,
    method: 'delete'
  })
}

// 导出设备数据

export function exportPerceive(query) {
  return system_service({
    url: '/system/perceive/export',
    method: 'get',
    params: query
  })
}
