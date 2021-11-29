import {system_service} from '@/utils/request'

// 查询溯源产品评价列表
export function listCommit(query) {
  return system_service({
    url: '/system/commit/list',
    method: 'get',
    params: query
  })
}

// 查询溯源产品评价详细
export function getCommit(cId) {
  return system_service({
    url: '/system/commit/' + cId,
    method: 'get'
  })
}

// 新增溯源产品评价
export function addCommit(data) {
  return system_service({
    url: '/system/commit',
    method: 'post',
    params: data
  })
}

// 修改溯源产品评价
export function updateCommit(data) {
  return system_service({
    url: '/system/commit',
    method: 'put',
    params: data
  })
}

// 删除溯源产品评价
export function delCommit(cId) {
  return system_service({
    url: '/system/commit/' + cId,
    method: 'delete'
  })
}

// 导出溯源产品评价
export function exportCommit(query) {
  return system_service({
    url: '/system/commit/export',
    method: 'get',
    params: query
  })
}