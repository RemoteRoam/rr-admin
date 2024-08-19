/*
 * loading
 *
 * @Author:    YY Studio
 * @Date:      2022-09-06 20:54:50
 * @Wechat:    zhuda1024
 * @Email:     lab1024@163.com
 * @Copyright  YY Studio
 */
import { defineStore } from 'pinia';

export const useSpinStore = defineStore({
  id: 'spin',
  state: () => ({
    loading: false,
  }),

  actions: {
    hide() {
      this.loading = false;
      let spins = document.querySelector('.ant-spin-nested-loading');
      spins.style.zIndex = 999;
    },
    show() {
      this.loading = true;
      let spins = document.querySelector('.ant-spin-nested-loading');
      spins.style.zIndex = 1001;
    },
  },
});
