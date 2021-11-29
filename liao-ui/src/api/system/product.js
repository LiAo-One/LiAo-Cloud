import {system_service} from '@/utils/request'

// 查询溯源产品列表
export function listProduct(query) {
  return system_service({
    url: '/system/product/list',
    method: 'get',
    params: query
  })
}

// 查询溯源产品详细
export function getProduct(pId) {
  return system_service({
    url: '/system/product/' + pId,
    method: 'get'
  })
}

// 新增溯源产品
export function addProduct(data) {
  return system_service({
    url: '/system/product',
    method: 'post',
    params: data
  })
}

// 修改溯源产品
export function updateProduct(data) {
  return system_service({
    url: '/system/product',
    method: 'put',
    params: data
  })
}

// 删除溯源产品
export function delProduct(pId) {
  return system_service({
    url: '/system/product/' + pId,
    method: 'delete'
  })
}

// 导出溯源产品
export function exportProduct(query) {
  return system_service({
    url: '/system/product/export',
    method: 'get',
    params: query
  })
}