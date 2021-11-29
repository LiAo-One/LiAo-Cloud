import {system_service} from '@/utils/request'

// 查询海量语音分析数据检索列表
export function listAnalysis(query) {
  return system_service({
    url: '/system/analysis/list',
    method: 'get',
    params: query
  })
}

// 查询海量语音分析数据检索详细
export function getAnalysis(vId) {
  return system_service({
    url: '/system/analysis/' + vId,
    method: 'get'
  })
}

// 新增海量语音分析数据检索
export function addAnalysis(data) {
  return system_service({
    url: '/system/analysis',
    method: 'post',
    params: data
  })
}

// 修改海量语音分析数据检索
export function updateAnalysis(data) {
  return system_service({
    url: '/system/analysis',
    method: 'put',
    params: data
  })
}

// 删除海量语音分析数据检索
export function delAnalysis(vId) {
  return system_service({
    url: '/system/analysis/' + vId,
    method: 'delete'
  })
}

// 导出海量语音分析数据检索
export function exportAnalysis(query) {
  return system_service({
    url: '/system/analysis/export',
    method: 'get',
    params: query
  })
}