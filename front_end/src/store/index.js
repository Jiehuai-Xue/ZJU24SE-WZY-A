import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
  state: {
    id: null,
  },
  mutations: {
    setId(state, id) {
      state.id = id;
    },
  },
  actions: {
    updateId({ commit }, id) {
      console.log("update id in store:", id);
      commit('setId', id);
    },
  },
  getters: {
    getId: state => state.id,
  },
  plugins: [createPersistedState({
    storage: window.sessionStorage, // 这个插件可以将 Vuex store 的状态自动同步到浏览器的Session Storage。这样，在页面刷新后，Vuex store 可以从存储中恢复状态。
  })],
});
