<template>
  <div>
    <video-detail></video-detail>
    <comment-list></comment-list>
  </div>
</template>

<script>
import VideoDetail from "@/components/comment/VideoDetail.vue";
import CommentList from "@/components/comment/CommentList.vue";

export default {
  name: "CommentView",
  components: {
    VideoDetail,
    CommentList,
  },
};
</script>

<style></style>
