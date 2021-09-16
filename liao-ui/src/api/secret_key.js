import { file_upload } from '@/utils/request'

/**
 * 获取秘钥
 * @returns {*}
 */
export function image_key() {
  return file_upload({
    url: 'tencent/cos_client'
  })
}
