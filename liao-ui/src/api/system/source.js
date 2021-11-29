import {system_service} from '@/utils/request'

// 查询溯源列表
export function listSource(query) {
  return system_service({
    url: '/system/source/list',
    method: 'get',
    params: query
  })
}

// 查询溯源详细
export function getSource(tId) {
  return system_service({
    url: '/system/source/' + tId,
    method: 'get'
  })
}

// 新增溯源
export function addSource(data) {
  return system_service({
    url: '/system/source',
    method: 'post',
    params: data
  })
}

// 修改溯源
export function updateSource(data) {
  return system_service({
    url: '/system/source',
    method: 'put',
    params: data
  })
}

// 删除溯源
export function delSource(tId) {
  return system_service({
    url: '/system/source/' + tId,
    method: 'delete'
  })
}

// 导出溯源
export function exportSource(query) {
  return system_service({
    url: '/system/source/export',
    method: 'get',
    params: query
  })
}