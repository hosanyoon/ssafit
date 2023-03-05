<template>
  <div>
    <h3><i class="bi bi-hand-thumbs-up me-2"></i>운동추천</h3>
    <br />
    <div>
      '{{ loginUser.id }}' 님의 추천영상 : '{{ loginUser.id }}' 님은 현재 '{{
        getRecentPart
      }}' 운동을 하셨고, 다음 운동부위는 '{{ getNextPart }}' 입니다. 다음은 '{{
        getPreference
      }}' 타입의 '{{ getNextPart }}' 운동 검색 결과입니다.
    </div>
    <br />
    <br />
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
import { mapState } from "vuex";
import VideoItem from "@/components/video/VideoItem.vue";

export default {
  name: "RecommendView.vue",
  data() {
    return {
      videos: [],
    };
  },
  components: {
    VideoItem,
  },
  computed: {
    ...mapState(["loginUser"]),
    getRecentPart() {
      let part = this.loginUser.recent;
      if (part == 0) {
        return "등";
      } else if (part == 1) {
        return "어깨";
      } else if (part == 2) {
        return "가슴";
      } else if (part == 3) {
        return "하체";
      } else {
        return "유산소";
      }
    },
    getNextPart() {
      let part = this.loginUser.recent;
      if (part == 0) {
        return "어깨";
      } else if (part == 1) {
        return "가슴";
      } else if (part == 2) {
        return "하체";
      } else if (part == 3) {
        return "유산소";
      } else {
        return "등";
      }
    },
    getPreference() {
      let preference = this.loginUser.Preference;
      if (preference == 0) {
        return "웨이트";
      } else if (preference == 1) {
        return "필라테스";
      } else if (preference == 2) {
        return "크로스핏";
      } else {
        return "홈트";
      }
    },
  },
  created() {
    const API_URL = "https://www.googleapis.com/youtube/v3/search";
    const YOUTUBE_KEY = process.env.VUE_APP_YOUTUBE_API_KEY;
    let keyword = this.getPreference + " " + this.getNextPart + "운동";
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
        console.log(res);
        res.data.items.forEach((ele) => {
          let video = {
            videoId: ele.id.videoId,
            videoTitle: ele.snippet.title,
            exercisePart: (this.loginUser.recent + 1) % 5,
            viewCnt: 0,
          };
          console.log(video);
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
