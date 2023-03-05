<template>
  <div>
    <h3><i class="bi bi-search me-2"></i>부위별 영상</h3>
    <div>
      <button class="btn btn-outline-primary mx-2" @click="selectPart('등')">
        등
      </button>
      <button class="btn btn-outline-primary mx-2" @click="selectPart('어깨')">
        어깨
      </button>
      <button class="btn btn-outline-primary mx-2" @click="selectPart('가슴')">
        가슴
      </button>
      <button class="btn btn-outline-primary mx-2" @click="selectPart('하체')">
        하체
      </button>
      <button
        class="btn btn-outline-primary mx-2"
        @click="selectPart('유산소')"
      >
        유산소
      </button>
    </div>
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
  name: "VideoPart",
  components: {
    VideoItem,
  },
  data() {
    return {
      videos: [],
      part: 0,
    };
  },
  created() {
    const API_URL = "https://www.googleapis.com/youtube/v3/search";
    const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
    let keyword = "등";
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
        res.data.items.forEach((ele) => {
          let video = {
            videoId: ele.id.videoId,
            videoTitle: ele.snippet.title,
            exercisePart: 0,
            viewCnt: 0,
          };
          this.videos.push(video);
        });
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    selectPart(part) {
      if (part == "등") {
        this.part = 0;
      } else if (part == "어깨") {
        this.part = 1;
      } else if (part == "가슴") {
        this.part = 2;
      } else if (part == "하체") {
        this.part = 3;
      } else if (part == "유산소") {
        this.part = 4;
      }
      console.log("선택 생성 가자");
      const API_URL = "https://www.googleapis.com/youtube/v3/search";
      const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
      console.log(YOUTUBE_KEY);
      const KAKAO_KEY = process.env.VUE_APP_KAKAO_API_KEY;
      console.log(KAKAO_KEY);
      let keyword = part + "운동";
      axios({
        url: API_URL,
        method: "GET",
        params: {
          key: YOUTUBE_KEY,
          part: "snippet",
          q: keyword,
          type: "video",
          maxResults: 1,
        },
      })
        .then((res) => {
          let videos = [];
          res.data.items.forEach((ele) => {
            let video = {
              videoId: ele.id.videoId,
              videoTitle: ele.snippet.title,
              exercisePart: this.part,
              viewCnt: 0,
            };
            videos.push(video);
          });
          this.videos = videos;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
