import {system_service} from '@/utils/request'

// 查询操作日志列表
export function list(query) {
  return system_service({
    url: '/system/sys-open-log/sel_page',
    method: 'post',
    params: query
  })
}

// 删除操作日志
export function delOperlog(ids) {
  return system_service({
    url: '/system/sys-open-log/del_ids/',
    method: 'post',
    params: {
      ids
    }
  })
}

// 清空操作日志
export function cleanOperlog() {
  return system_service({
    url: '/system/sys-open-log/clean',
    method: 'delete'
  })
}

// 导出操作日志
export function exportOperlog(query) {
  return system_service({
    url: '/monitor/operlog/export',
    method: 'get',
    params: query
  })
}
