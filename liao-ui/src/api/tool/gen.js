import {system_service} from '@/utils/request'

// 查询生成表数据
export function listTable(query) {
  return system_service({
    url: '/tool/gen-table/sel_page',
    method: 'post',
    params: query
  })
}

// 查询db数据库列表
export function listDbTable(query) {
  return system_service({
    url: '/tool/gen-table/db/list',
    method: 'get',
    params: query
  })
}

// 查询表详细信息
export function getGenTable(tableId) {
  return system_service({
    url: '/tool/gen-table/' + tableId,
    method: 'get'
  })
}

// 修改代码生成信息
export function updateGenTable(data) {
  return system_service({
    url: '/tool/gen-table',
    method: 'put',
    data: data
  })
}

// 导入表
export function importTable(data) {
  return system_service({
    url: '/tool/gen-table/importTable',
    method: 'post',
    params: data
  })
}

// 预览生成代码
export function previewTable(tableId) {
  return system_service({
    url: '/tool/gen/preview/' + tableId,
    method: 'get'
  })
}

// 删除表数据
export function delTable(ids) {
  return system_service({
    url: '/tool/gen-table/del_ids',
    method: 'post',
    params: {
      ids
    }
  })
}

// 生成代码（自定义路径）
export function genCode(tableName) {
  return system_service({
    url: '/tool/gen/genCode/' + tableName,
    method: 'get'
  })
}

// 同步数据库
export function synchDb(tableName) {
  return system_service({
    url: '/tool/gen/synchDb/' + tableName,
    method: 'get'
  })
}
