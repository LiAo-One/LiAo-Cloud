import { system_service } from '@/utils/request'

// 查询参数列表
export function listConfig(query) {
  return system_service({
    url: '/system/config/list',
    method: 'get',
    params: query
  })
}

// 查询参数详细
export function getConfig(configId) {
  return system_service({
    url: '/system/config/' + configId,
    method: 'get'
  })
}

// 根据参数键名查询参数值
export function getConfigKey(configKey) {
  return system_service({
    url: '/system/config/configKey/' + configKey,
    method: 'get'
  })
}

// 新增参数配置
export function addConfig(data) {
  return system_service({
    url: '/system/config',
    method: 'post',
    data: data
  })
}

// 修改参数配置
export function updateConfig(data) {
  return system_service({
    url: '/system/config',
    method: 'put',
    data: data
  })
}

// 删除参数配置
export function delConfig(configId) {
  return system_service({
    url: '/system/config/' + configId,
    method: 'delete'
  })
}

// 清理参数缓存
export function clearCache() {
  return system_service({
    url: '/system/config/clearCache',
    method: 'delete'
  })
}

// 导出参数
export function exportConfig(query) {
  return system_service({
    url: '/system/config/export',
    method: 'get',
    params: query
  })
}
