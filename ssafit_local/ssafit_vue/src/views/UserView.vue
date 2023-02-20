<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "UserView",
  computed: {
    ...mapState(["loginUser"]),
  },
};
</script>

<style></style>
