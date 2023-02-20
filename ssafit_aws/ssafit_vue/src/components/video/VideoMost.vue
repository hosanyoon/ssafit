<template>
  <div>
    <h3><i class="bi bi-fire"></i> 인기 동영상</h3>
    <div class="container">
      <div class="row align-items-start">
        <video-item
          class="col"
          v-for="video in videos"
          :key="video.viewCnt"
          :video="video"
        ></video-item>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import VideoItem from "./VideoItem.vue";

export default {
  name: "VideoMost",
  components: {
    VideoItem,
  },
  data() {
    return {
      videoURL: "https://www.youtube.com/embed/CHp0Kaidr14",
      videos: [],
    };
  },
  created() {
    const API_URL = "http://ec2-15-164-231-78.ap-northeast-2.compute.amazonaws.com:8080/videoapi/list/most";
    axios({
      url: API_URL,
      method: "GET",
    })
      .then((res) => {
        res.data.forEach((ele) => {
          let video = {
            videoId: ele.videoId,
            videoTitle: ele.videoTitle,
            exercisePart: ele.exercisePart,
            viewCnt: ele.viewCnt,
          };
          this.videos.push(video);
        });
        console.log(this.videos);
      })
      .catch((err) => {
        console.log(err);
      });
  },
};
</script>

<style></style>
