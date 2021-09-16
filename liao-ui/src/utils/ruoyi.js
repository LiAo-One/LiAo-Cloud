/**
 * 通用js方法封装处理
 * Copyright (c) 2019 ruoyi
 */
import cos from 'cos-js-sdk-v5'

import { image_key } from '@/api/secret_key'

const baseURL = process.env.VUE_APP_BASE_API

// 日期格式化
export function parseTime(time, pattern) {
  if (arguments.length === 0 || !time) {
    return null
  }
  const format = pattern || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if ((typeof time === 'string') && (/^[0-9]+$/.test(time))) {
      time = parseInt(time)
    } else if (typeof time === 'string') {
      time = time.replace(new RegExp(/-/gm), '/')
    }
    if ((typeof time === 'number') && (time.toString().length === 10)) {
      time = time * 1000
    }
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key]
    // Note: getDay() returns 0 on Sunday
    if (key === 'a') {
      return ['日', '一', '二', '三', '四', '五', '六'][value]
    }
    if (result.length > 0 && value < 10) {
      value = '0' + value
    }
    return value || 0
  })
  return time_str
}

// 获取当前时间戳 String 类型
export function getFilePushKey() {
  let d = new Date()
  let year = d.getFullYear()
  let month = change(d.getMonth() + 1)
  let day = change(d.getDate())
  let hour = change(d.getHours())
  let minute = change(d.getMinutes())
  let second = change(d.getSeconds())

  function change(t) {
    if (t < 10) {
      return '0' + t
    } else {
      return t
    }
  }

  let time = year + '-' + month + '-' + day + ' '
    + hour + ':' + minute + ':' + second
  return time
}

// 业务操作类型
export function businessType(type) {
  if (type === 0) {
    return '其它'
  } else if (type === 1) {
    return '新增'
  } else if (type === 2) {
    return '修改'
  } else if (type === 3) {
    return '删除'
  } else if (type === 9) {
    return '清空'
  }
}

// 业务操作类型
export function businessStatus(type) {
  if (type === 0) {
    return '成功'
  } else if (type === 1) {
    return '失败'
  }
}

// 表单重置
export function resetForm(refName) {
  if (this.$refs[refName]) {
    this.$refs[refName].resetFields()
  }
}

// 添加日期范围
export function addDateRange(params, dateRange) {
  var search = params
  search.beginTime = ''
  search.endTime = ''
  if (null != dateRange && '' != dateRange) {
    search.beginTime = dateRange[0]
    search.endTime = dateRange[1]
  }
  return search
}

// 回显数据字典
export function selectDictLabel(datas, value) {
  var actions = []
  Object.keys(datas).some((key) => {
    if (datas[key].dictValue == ('' + value)) {
      actions.push(datas[key].dictLabel)
      return true
    }
  })
  return actions.join('')
}

// 回显数据字典（字符串数组）
export function selectDictLabels(datas, value, separator) {
  var actions = []
  var currentSeparator = undefined === separator ? ',' : separator
  var temp = value.split(currentSeparator)
  Object.keys(value.split(currentSeparator)).some((val) => {
    Object.keys(datas).some((key) => {
      if (datas[key].dictValue == ('' + temp[val])) {
        actions.push(datas[key].dictLabel + currentSeparator)
      }
    })
  })
  return actions.join('').substring(0, actions.join('').length - 1)
}

// 通用下载方法
export function download(fileName) {
  window.location.href = 'http://192.168.2.72:8081' + '/common/download?fileName=' + encodeURI(fileName) + '&delete=' + true
}

// 字符串格式化(%s )
export function sprintf(str) {
  var args = arguments, flag = true, i = 1
  str = str.replace(/%s/g, function() {
    var arg = args[i++]
    if (typeof arg === 'undefined') {
      flag = false
      return ''
    }
    return arg
  })
  return flag ? str : ''
}

// 转换字符串，undefined,null等转化为""
export function praseStrEmpty(str) {
  if (!str || str == 'undefined' || str == 'null') {
    return ''
  }
  return str
}

/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 * @param {*} rootId 根Id 默认 0
 */
export function handleTree(data, id, parentId, children, rootId) {
  id = id || 'id'
  parentId = parentId || 'parentId'
  children = children || 'children'
  rootId = rootId || Math.min.apply(Math, data.map(item => {
    return item[parentId]
  })) || 0
  //对源数据深度克隆
  const cloneData = JSON.parse(JSON.stringify(data))
  //循环所有项
  const treeData = cloneData.filter(father => {
    let branchArr = cloneData.filter(child => {
      //返回每一项的子级数组
      return father[id] === child[parentId].toString()
    })
    branchArr.length > 0 ? father.children = branchArr : ''
    //返回第一层
    return father[parentId] === rootId
  })
  return treeData !== '' ? treeData : data
}

/**
 * 获取 Cos对象
 * @returns {*}
 */
export function imagePush() {

  return image_key().then(res => {
    // 打印秘钥
    return getCos(res)
  })
}

// 初始化 cos对象、并返回
function getCos(data) {
  return new cos({
    getAuthorization: function(options, callback) {
      let credentials = data && data.credentials
      if (!data || !credentials) return console.error('credentials invalid')
      callback({
        TmpSecretId: credentials.tmpSecretId,
        TmpSecretKey: credentials.tmpSecretKey,
        XCosSecurityToken: credentials.sessionToken,
        // 建议返回服务器时间作为签名的开始时间，避免用户浏览器本地时间偏差过大导致签名错误
        StartTime: data.startTime, // 时间戳，单位秒，如：1580000000
        ExpiredTime: data.expiredTime // 时间戳，单位秒，如：1580000900
      })
    }
  })
}

// 示例
function getImgFile(event) {
  let imgFle = event.target.files[0]
  // 执行上传 并获取结果
  imagePush().then(res => {

    res.putObject({
      Bucket: 'pet-1252596634',               /* 存储桶 */
      Region: 'ap-chengdu',                   /* 存储桶所在地域，必须字段 */
      Key: getFilePushKey(),                  /* 文件名，必须字段，必须唯一，时间戳 */
      StorageClass: 'STANDARD',
      Body: imgFle,                           /*上传文件对象*/
      onProgress: function(progressData) {
        /*console.log(JSON.stringify(progressData));*/
      }
    }, function(err, data) {
      // 添加进数组
      console.log('http://' + data.Location)
    })
  })
}

// 文件路径格式化
export function cosFilePath(fileName) {
  return 'http://' + fileName
}

// 文件删除
export function handleRemove(fileName) {
  imagePush().then(res => {
    res.deleteObject({
      Bucket: 'pet-1252596634',               /* 存储桶 */
      Region: 'ap-chengdu',                   /* 存储桶所在地域，必须字段 */
      Key: fileName                           /* 文件名，必须字段，必须唯一，时间戳 */
    }, function(err, data) {
      // 添加进数组
      console.log(err || data)
      console.log(fileName)
    })
  })
}
