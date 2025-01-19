/*
 * 心跳
 *
 * @Author:    YY Studio
 * @Date:      2022-09-03 21:55:47
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */
import { postRequest } from '/src/lib/axios';

export const heartBeatApi = {
  // 分页查询 @author 卓大
  queryList: (param) => {
    return postRequest('/support/heartBeat/query', param);
  },
};
