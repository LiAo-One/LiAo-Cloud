import {system_service} from '@/utils/request'

// 查询字典数据列表
export function listData(query) {
  return system_service({
    url: '/system/sys-dict-data/sel_page',
    method: 'post',
    params: query
  })
}

// 查询字典数据详细
export function getData(id) {
  return system_service({
    url: '/system/sys-dict-data/sel_id',
    method: 'post',
    params: {
      id
    }
  })
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return system_service({
    url: '/system/sys-dict-type/type/' + dictType,
    method: 'get'
  })
}

// 新增字典数据
export function addData(data) {
  return system_service({
    url: '/system/sys-dict-data/add',
    method: 'post',
    params: data
  })
}

// 修改字典数据
export function updateData(data) {
  return system_service({
    url: '/system/sys-dict-data/upd_id',
    method: 'post',
    params: data
  })
}

// 删除字典数据
export function deleteData(ids) {
  return system_service({
    url: '/system/sys-dict-data/del_ids',
    method: 'post',
    params: {
      ids
    }
  })
}

