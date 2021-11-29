import {system_service} from '@/utils/request'

// 查询服务器详细
export function getServer() {
  return system_service({
    url: '/monitor/server',
    method: 'get'
  })
}
