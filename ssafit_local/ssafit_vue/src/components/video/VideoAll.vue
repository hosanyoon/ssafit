<template>
  <div>
    <h3>인기 동영상</h3>
    <div class="container">
      <div class="row align-items-start">
        <video-item
          class="col"
          v-for="video in videos"
          :key="video.id.videoId"
          :video="video"
        ></video-item>
      </div>
      <hr />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import VideoItem from "./VideoItem.vue";

export default {
  name: "VideoAll",
  components: {
    VideoItem,
  },
  data() {
    return {
      videos: [],
    };
  },
  created() {
    console.log("all 생성 가자");
    const API_URL = "https://www.googleapis.com/youtube/v3/search";
    const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
    let keyword = "헬스";
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
        this.videos = res.data.items;
        console.log("결과-------------");
        console(this.videos);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style></style>
