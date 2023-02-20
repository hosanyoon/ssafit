<template>
  <div>
    <video-most></video-most>
    <hr />
    <video-part></video-part>
    <hr />
    <video-like></video-like>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoPart from "@/components/video/VideoPart.vue";
import VideoLike from "@/components/video/VideoLike.vue";
import VideoMost from "@/components/video/VideoMost.vue";

export default {
  name: "VideoView",
  components: {
    VideoLike,
    VideoPart,
    VideoMost,
  },
  computed: {
    ...mapState(["searchedVideos"]),
  },
  created() {
    this.searchedVideos = [];
  },
};
</script>

<style></style>
