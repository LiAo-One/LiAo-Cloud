import { system_service } from '@/utils/request'

// 登录方法
export function login(adminAccount, adminPassword) {
  return system_service({
    url: 'system/sys-admin/login',
    method: 'post',
    params: {
      adminAccount,
      adminPassword
    }
  })
}

// 获取用户详细信息
export function getInfo(token) {
  return system_service({
    url: 'system/token/token-mes-all',
    params: {
      token
    },
    method: 'get'
  })
}

// 退出方法
export function logout(token) {
  return system_service({
    url: 'system/sys-admin/logout',
    method: 'post',
    params: {
      token
    }
  })
}

// 获取验证码
export function getCodeImg() {
  return system_service({
    url: 'system/captchaImage',
    method: 'get'
  })
}
