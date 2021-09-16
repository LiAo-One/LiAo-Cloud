import { system_service } from '@/utils/request'

// 获取路由
export const getRouters = (token) => {
  return system_service({
    url: 'system/token/token-mes',
    method: 'get',
    params: {
      name: 'menu',
      token
    }
  })
}
