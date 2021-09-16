import {system_service} from '@/utils/request'

// 查询字典类型列表
export function listType(query) {
  return system_service({
    url: '/system/sys-dict-type/sel_page',
    method: 'post',
    params: query
  })
}

// 查询字典类型详细
export function getType(ids) {
  return system_service({
    url: '/system/sys-dict-type/sel_ids',
    method: 'post',
    params: {
      ids
    }
  })
}

// 新增字典类型
export function addType(data) {
  return system_service({
    url: '/system/sys-dict-type/add',
    method: 'post',
    params: data
  })
}

// 修改字典类型
export function updateType(data) {
  return system_service({
    url: '/system/sys-dict-type/upd_id',
    method: 'post',
    params: data
  })
}

// 删除字典类型
export function delType(ids) {
  return system_service({
    url: '/system/sys-dict-type/del_ids',
    method: 'post',
    params: {
      ids
    }
  })
}

// 清理参数缓存
export function clearCache() {
  return request({
    url: '/system/system/dict/type/clearCache',
    method: 'delete'
  })
}

// 导出字典类型
export function exportType(query) {
  return request({
    url: '/system/dict/type/export',
    method: 'get',
    params: query
  })
}

// 获取字典选择框列表
export function optionselect() {
  return system_service({
    url: '/system/sys-dict-type/optionselect',
    method: 'get'
  })
}
