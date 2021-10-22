import {system_service} from '@/utils/request'

// 查询用户列表
export function listAdmin(query) {
  return system_service({
    url: '/system/sys-admin/sel_page',
    method: 'post',
    params: query
  })
}

// 查询用户详细
export function getAdmin(id) {
  return system_service({
    url: '/system/sys-admin/sel_id',
    method: 'post',
    params: {
      id
    }
  })
}

// 修改用户
export function updateAdmin(data) {
  return system_service({
    url: '/system/sys-admin/upd_id',
    method: 'post',
    params: data
  })
}

// 添加用户
export function addAdmin(data) {
  return system_service({
    url: '/system/sys-admin/add',
    method: 'post',
    params: data
  })
}

// 删除单个用户
export function deleteAdmin(ids) {
  return system_service({
    url: '/system/sys-admin/del_ids',
    method: 'post',
    params: {
      ids
    }
  })
}

// 下载用户导入模板
export function importTemplate() {
  return system_service({
    url: '/system/sys-admin/importTemplate',
    method: 'get'
  })
}

// 导出用户
export function exportUser(query) {
  return system_service({
    url: '/system/sys-admin/export',
    method: 'get',
    params: query
  })
}

