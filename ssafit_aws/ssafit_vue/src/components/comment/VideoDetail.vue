<template>
  <div class="container">
    <div>
      <iframe
        width="560"
        height="315"
        :src="videoURL"
        title="YouTube video player"
        frameborder="0"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowfullscreen
      ></iframe>
      <h3 class="my-3" style="font-weight: 800">{{ video.videoTitle }}</h3>
      <div class="row justify-content-between">
        <div class="col">조회수: {{ video.viewCnt }}</div>
        <div class="col">
          <button
            class="col btn btn-light my-2 mx-1"
            v-show="!check"
            @click="insertLikeVideo"
          >
            찜하기 <i class="bi bi-heart"></i>
          </button>
          <button
            class="col btn btn-light my-2 mx-1"
            v-show="check"
            @click="deleteLikeVideo"
          >
            찜 취소 <i class="bi bi-heart-fill"></i>
          </button>
          <button
          class="col btn btn-light my-2 mx-1"
          v-if="this.$store.state.loginUser.id == 'Admin'"
          @click="deleteVideo"
          >
            삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "VideoDetail",
  data() {
    return {
      videoURL: "https://www.youtube.com/embed/" + this.$route.params.id,
      check: false,
    };
  },
  computed: {
    ...mapState(["video", "isLiked"]),
  },
  watch: {
    isLiked() {
      this.check = this.isLiked;
    },
  },
  created() {
    this.getVideo();
    this.checkLiked();
  },
  methods: {
    getVideo() {
      this.$store.dispatch("getVideo", this.$route.params.id);
    },
    insertLikeVideo() {
      let payload = {
        userId: this.$store.state.loginUser.id,
        user: {
          likedVideo: this.video.videoId,
        },
      };
      this.$store.dispatch("insertLikeVideo", payload);
      this.check = true;
    },
    deleteLikeVideo() {
      let payload = {
        userId: this.$store.state.loginUser.id,
        user: {
          likedVideo: this.video.videoId,
        },
      };
      this.$store.dispatch("deleteLikeVideo", payload);
      this.check = false;
    },
    checkLiked() {
      let payload = {
        userId: this.$store.state.loginUser.id,
        videoId: this.$route.params.id,
      };
      this.$store.dispatch("checkLiked", payload);
    },
    deleteVideo(){
      this.$store.dispatch("deleteVideo",this.video.videoId);
    }
  },
};
</script>

<style></style>
