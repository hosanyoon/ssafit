<template>
  <div>
    <h2>vidoe-result zone</h2>
    <h2>검색 결과</h2>
    <div v-if="searchedVideos.length" class="container">
      <div class="row align-items-start">
        <video-item
          class="col"
          v-for="video in searchedVideos"
          :key="video.id.videoId"
          :video="video"
        ></video-item>
      </div>
      <hr />
    </div>
    <div v-else>검색 결과가 없습니다.</div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import VideoItem from "./VideoItem.vue";

export default {
  name: "VideoResult",
  components: {
    VideoItem,
  },
  computed: {
    ...mapState(["searchedVideos"]),
  },
};
</script>

<style></style>
