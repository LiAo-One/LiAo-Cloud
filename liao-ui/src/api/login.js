import {system_service} from '@/utils/request'

// 登录方法
export function login(adminAccount, adminPassword) {
  return system_service({
    url: '/system/sys-admin/login',
    method: 'post',
    params: {
      adminAccount,
      adminPassword
    }
  })
}

export function logins(username, password, code, uuid) {
  const data = {
    username,
    password,
    code,
    uuid
  }
  return system_service({
    url: '/auth/login',
    headers: {
      isToken: false
    },
    method: 'post',
    data: data
  })
}

// 获取用户详细信息
export function getInfo(token) {
  return system_service({
    url: '/system/token/token-mes-all',
    params: {
      token
    },
    method: 'get'
  })
}

// 获取用户详细信息
export function getInfos() {
  return system_service({
    url: '/auth/getInfo',
    method: 'get',
    params: {}
  })
}

// 退出方法
export function logout(token) {
  return system_service({
    url: '/auth/logout',
    method: 'post',
    params: {
    }
  })
}

// 获取验证码
export function getCodeImg() {
  return system_service({
    url: '/captchaImage',
    headers: {
      isToken: false
    },
    method: 'get',
    timeout: 20000
  })
}
