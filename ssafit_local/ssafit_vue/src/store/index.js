import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import router from "@/router";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    video: {},
    keyword: "",
    likeVideos: [],
    searchedVideos: [],
    loginUser: null,
    userList: [],
    commentList: [],
    pagenation: {},
  },
  getters: {},
  mutations: {
    SET_VIDEO(state, payload) {
      state.video = payload;
    },
    SEARCH_VIDEO(state, payload) {
      state.searchedVideos = payload;
    },
    SET_LOGIN_USER(state, user) {
      state.loginUser = user;
    },
    LOGOUT(state) {
      state.loginUser = null;
    },
    SET_USER_LIST(state, payload) {
      state.userList = payload;
    },
    SET_COMMENT_LIST(state, payload) {
      state.commentList = [...payload];
    },
    SET_PAGENATION(state, payload) {
      state.pagenation = payload;
    },
    SET_LIKEVIDEOS(state, payload) {
      state.likeVideos = payload;
    },
  },
  actions: {
    insertVideo({ commit }, video) {
      commit;
      const API_URL = `http://localhost:9999/videoapi/videos/` + video.videoId;
      console.log(API_URL);
      axios({
        url: API_URL,
        method: "POST",
        data: video,
      })
        .then((res) => {
          res;
          router.push("/comment/" + video.videoId + "/page/1");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getVideo({ commit }, videoId) {
      const API_URL = `http://localhost:9999/videoapi/videos/` + videoId;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("SET_VIDEO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    insertLikeVideo({ dispatch }, payload) {
      const API_URL = `http://localhost:9999/userapi/like/` + payload.userId;
      let user = payload.user;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          res;
          dispatch("getLikeVideos", user.userId);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteLikeVideo({ dispatch }, payload) {
      const API_URL = `http://localhost:9999/userapi/like/` + payload.userId;
      let user = payload.user;
      axios({
        url: API_URL,
        method: "DELETE",
        data: user,
      })
        .then((res) => {
          res;
          dispatch("getLikeVideos", user.userId);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getLikeVideos({ commit }, userId) {
      const API_URL = `http://localhost:9999/userapi/like/${userId}`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("SET_LIKEVIDEOS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    searchVideo({ commit }, keyword) {
      const API_URL = "https://www.googleapis.com/youtube/v3/search";
      const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
      axios({
        url: API_URL,
        method: "GET",
        params: {
          key: YOUTUBE_KEY,
          part: "snippet",
          q: keyword,
          type: "video",
          maxResults: 5,
        },
      })
        .then((res) => {
          console.log(res.data.items);
          commit("SEARCH_VIDEO", res.data.items);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setLoginUser: function ({ commit }, user) {
      const API_URL = `http://localhost:9999/userapi/login`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          let user = res.data.loginUser;
          if (user) {
            alert("로그인 성공!");
            commit("SET_LOGIN_USER", user);
            sessionStorage.setItem("access-token", res.data["access-token"]);
            sessionStorage.setItem("loginUser", JSON.stringify(user));
            router.push("/").catch(() => {});
          } else {
            alert("로그인 실패");
          }
        })
        .catch((err) => {
          alert("로그인 실패");
          console.log(err);
        });
    },
    registUser({ commit }, user) {
      const API_URL = `http://localhost:9999/userapi/users`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          let result = res.data;
          if (result) {
            sessionStorage.setItem("loginUser", JSON.stringify(user));
            commit("SET_LOGIN_USER", user);
            alert("회원가입 성공!");
            router.push("/").catch(() => {});
          } else {
            alert("회원가입 실패 : 이유 모름");
          }
        })
        .catch((err) => {
          alert("아이디가 중복되었습니다.");
          console.log(err);
        });
    },
    logout({ commit }) {
      commit("LOGOUT");
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("loginUser");
      router.push("/").catch(() => {});
    },
    getUserList({ commit }) {
      const API_URL = `http://localhost:9999/userapi/users`;
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          commit("SET_USER_LIST", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    registComment({ commit }, comment) {
      commit;
      const API_URL = `http://localhost:9999/commentapi/comment`;
      axios({
        url: API_URL,
        method: "POST",
        data: comment,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getComments({ commit }, payload) {
      const API_URL =
        `http://localhost:9999/commentapi/list/` +
        payload.videoId +
        "/pages/" +
        payload.page;
      console.log("url : " + API_URL);
      axios({
        url: API_URL,
        method: "GET",
      })
        .then((res) => {
          let commentList = res.data.commentList;
          let pagenation = res.data.pageNav;
          76;
          commit("SET_COMMENT_LIST", commentList);
          commit("SET_PAGENATION", pagenation);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    modifyComment({ commit }, comment) {
      commit;
      const API_URL = `http://localhost:9999/commentapi/comment`;
      axios({
        url: API_URL,
        method: "PUT",
        data: comment,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteComment({ commit }, comment) {
      commit;
      const API_URL = `http://localhost:9999/commentapi/comment`;
      axios({
        url: API_URL,
        method: "DELETE",
        data: comment,
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    reloadLoginUser({ commit }, user) {
      commit("SET_LOGIN_USER", user);
    },
    modifyUser({ commit }, user) {
      const API_URL = `http://localhost:9999/userapi/users/${user.id}`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
      })
        .then((res) => {
          console.log(res);
          let result = res.data;
          if (result) {
            sessionStorage.setItem("loginUser", JSON.stringify(user));
            commit("SET_LOGIN_USER", user);
            alert("회원정보 수정완료!");
            router.push("/").catch(() => {});
          } else {
            alert("수정 실패 : 이유 모름");
          }
        })
        .catch((err) => {
          alert("수정 실패 : 이유 모름.");
          console.log(err);
        });
    },
    commitUser({ commit }, user) {
      commit;
      let API_URL = `http://localhost:9999/userapi/users/${user.id}`;
      axios({
        url: API_URL,
        method: "PUT",
        data: user,
      })
        .then((res) => {
          let result = res.data;
          if (result) {
            sessionStorage.setItem("loginUser", JSON.stringify(user));
            commit("SET_LOGIN_USER", user);
          } else {
            alert("수정 실패 : 이유 모름");
          }
        })
        .catch((err) => {
          alert("수정 실패 : 이유 모름.");
          console.log(err);
        });
      API_URL = `http://localhost:9999/userapi/recodes/${user.id}`;
      axios({
        url: API_URL,
        method: "POST",
        data: user,
      })
        .then((res) => {
          console.log(res);
          let result = res.data;
          console.log("result는");
          console.log(result);
          if (result) {
            alert("커밋완료!");
          } else {
            alert("커밋 실패 : 이유 모름1");
          }
        })
        .catch((err) => {
          alert("커밋 실패 : 이유 모름2.");
          console.log(err);
        });
    },
  },
  modules: {},
});
