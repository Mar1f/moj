import { StoreOptions } from "vuex";

// initial state
export default {
  namespaced: true,
  state: {
    theme: "dark",
  },
  actions: {},
  mutations: {
    toggleTheme(state, payload) {
      state.theme = payload;
    },
  },
} as StoreOptions<any>;
