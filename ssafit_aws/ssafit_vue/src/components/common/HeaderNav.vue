<template>
  <div>
    <header class="p-3 bg-black">
      <div class="container">
        <div
          class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
        >
          <a
            href="/"
            class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none mx-3"
          >
            <img src="@/assets/logo.png" width="80px" />
          </a>

          <ul
            class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
          >
            <li>
              <a
                @click="checkUser('/commit')"
                class="nav-link px-2 text-secondary"
                ><i class="bi bi-clipboard2-check me-2"></i>나의 운동 현황</a
              >
            </li>
            <li>
              <a
                @click="checkUser('/recommend')"
                class="nav-link px-2 text-secondary"
                ><i class="bi bi-hand-thumbs-up me-2"></i>운동 추천</a
              >
            </li>
            <li>
              <a @click="checkUser('/map')" class="nav-link px-2 text-secondary"
                ><i class="bi bi-geo mx-2"></i>헬스장 찾기</a
              >
            </li>
          </ul>

          <div class="text-end">
            <div v-if="getUser">
              <ul
                class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0"
              >
                <router-link
                  :to="`/user/modify/${getUserId}`"
                  class="nav-link px-2 text-secondary mx-3"
                  ><i class="bi bi-pencil-fill me-2"></i>회원정보
                  수정</router-link
                >
                <a href="#" @click="logout">
                  <button class="btn btn-primary">로그아웃</button></a
                >
              </ul>
            </div>
            <div v-else>
              <button
                type="button"
                class="btn btn-outline-light me-2"
                data-bs-toggle="modal"
                data-bs-target="#modalLogin"
              >
                로그인
              </button>
              <router-link to="/user/regist">
                <button type="button" class="btn btn-warning">회원가입</button>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </header>
    <div class="modal fade" id="modalLogin" tabindex="-1">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-4 border-bottom-0">
            <!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
            <h1 class="fw-bold mb-0 fs-2">로그인</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>

          <div class="modal-body p-5 pt-0">
            <div class="form-floating mb-3">
              <input
                type="text"
                class="form-control rounded-3"
                id="floatingInput"
                placeholder="name@example.com"
                v-model="id"
                @keyup.enter="login"
              />
              <label for="floatingInput">아이디</label>
            </div>
            <div class="form-floating mb-3">
              <input
                type="password"
                class="form-control rounded-3"
                id="floatingPassword"
                placeholder="Password"
                v-model="password"
                @keyup.enter="login"
              />
              <label for="floatingPassword">비밀번호</label>
            </div>
            <button
              class="w-100 mb-2 btn btn-lg rounded-3 btn-primary"
              @click="login"
            >
              Log in
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Modal } from "bootstrap";

export default {
  data() {
    return {
      id: "",
      password: "",
    };
  },
  computed: {
    getUser() {
      if (this.$store.state.loginUser) {
        return true;
      } else {
        return false;
      }
    },
    getUserId() {
      return this.$store.state.loginUser.id;
    },
  },
  methods: {
    login() {
      let user = {
        id: this.id,
        password: this.password,
      };
      this.$store.dispatch("setLoginUser", user);
      this.id = "";
      this.password = "";
      let myModal = Modal.getInstance(document.getElementById("modalLogin"));
      myModal.hide();
    },
    logout() {
      if (confirm("로그아웃 하시겠습니까?")) {
        this.$store.dispatch("logout");
      }
    },
    checkUser(url) {
      if (!this.getUser) {
        alert("로그인이 필요한 서비스입니다.");
      } else {
        this.$router.push(url).catch(() => {});
      }
    },
  },
};
</script>

<style></style>
