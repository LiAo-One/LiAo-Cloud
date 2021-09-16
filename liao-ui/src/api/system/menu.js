import { system_service } from '@/utils/request'

// 查询菜单列表
export function listMenu(query) {
  return system_service({
    url: '/system/sys-menu/sel_page',
    method: 'post',
    params: query
  })
}

// 查询菜单详细
export function getMenu(id) {
  return system_service({
    url: '/system/sys-menu/sel_id/',
    method: 'post',
    params: {
      id
    }
  })
}

// 查询菜单下拉树结构
export function treeselect() {
  return system_service({
    url: '/system/sys-menu/menu_tree_select',
    method: 'get'
  })
}

// 根据角色ID查询菜单下拉树结构
export function roleMenuTreeselect(roleId) {
  return system_service({
    url: '/system/sys-menu/role_menu_tree_select/',
    method: 'post',
    params: {
      roleId
    }
  })
}

// 新增菜单
export function addMenu(data) {
  return system_service({
    url: '/system/sys-menu/add',
    method: 'post',
    params: data
  })
}

// 修改菜单
export function updateMenu(data) {
  return system_service({
    url: '/system/sys-menu/upd_id',
    method: 'post',
    params: data
  })
}

// 删除菜单
export function delMenu(id) {
  return system_service({
    url: '/system/sys-menu/del_id/',
    method: 'post',
    params: {
      id
    }
  })
}
