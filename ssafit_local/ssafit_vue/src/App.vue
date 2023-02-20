<template>
  <div id="app">
    <header-nav></header-nav>
    <div class="container">
      <br />
      <router-view />
    </div>
  </div>
</template>

<script>
import HeaderNav from "@/components/common/HeaderNav.vue";

export default {
  name: "App",
  components: {
    HeaderNav,
  },
  created() {
    let user = JSON.parse(sessionStorage.getItem("loginUser"));
    console.log("app.vue 생성시 유저 세션에서 가져오기");
    console.log(user);
    this.$store.dispatch("reloadLoginUser", user);
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
