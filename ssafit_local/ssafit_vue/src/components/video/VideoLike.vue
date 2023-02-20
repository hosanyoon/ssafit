<template>
  <div>
    <h3><i class="bi bi-arrow-through-heart-fill me-2"></i>찜한 영상</h3>
    <div v-if="loginUser">
      <div class="d-flex justify-content-start">
        <div class="row align-items-start">
          <video-item
            class="col mx-4"
            v-for="video in likeVideos"
            :key="video.viewCnt"
            :video="video"
          ></video-item>
        </div>
      </div>
    </div>
    <div v-else>찜 목록이 없습니다...</div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoItem from "./VideoItem.vue";

export default {
  name: "VideoLike",
  components: {
    VideoItem,
  },
  computed: {
    ...mapState(["loginUser", "likeVideos"]),
  },
  created() {
    this.$store.dispatch("getLikeVideos", this.$store.state.loginUser.id);
  },
};
</script>

<style></style>
