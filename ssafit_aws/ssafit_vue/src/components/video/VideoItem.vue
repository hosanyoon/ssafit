<template>
  <div class="container my-3 px-1">
    <!--카드로-->
    <div class="card px-1" style="width: 12rem">
      <img
        :src="`https://i.ytimg.com/vi/${video.videoId}/default.jpg`"
        class="card-img-top img-thumbnail"
      />
      <div class="card-body">
        <div
          class="row text-start"
          style="text-decoration: none; color: black"
          @click="insertVideo"
        >
          {{ video.videoTitle }}
        </div>
        <p class="card-text text-end">
          <small class="text-muted">{{ video.viewCnt }} views</small>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "VideoItem",
  props: {
    video: {
      type: Object,
      required: true,
    },
  },
  computed: {
    getUser() {
      if (this.$store.state.loginUser) {
        return true;
      } else {
        return false;
      }
    },
  },
  methods: {
    insertVideo() {
      if (!this.getUser) {
        alert("로그인이 필요한 서비스입니다.");
      } else {
        this.$store.dispatch("insertVideo", this.video);
      }
    },
  },
};
</script>

<style></style>
