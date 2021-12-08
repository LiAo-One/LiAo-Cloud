import {system_service} from '@/utils/request'

// 获取路由
export const getRouters = (token) => {
  return system_service({
    url: '/system/token/get_routers',
    method: 'get',
    params: {
      token
    }
  })
}

export const getRouter = (token) => {
  return system_service({
    url: 'system/token/getRouters',
    method: 'get',
    params: {}
  })
}
