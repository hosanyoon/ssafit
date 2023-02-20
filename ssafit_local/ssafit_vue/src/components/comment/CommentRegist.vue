<template>
  <div>
    <div class="input-group my-3">
      <input
        type="text"
        class="form-control"
        placeholder="댓글 추가..."
        v-model="content"
      />
      <button class="btn btn-outline-secondary" @click="registComment">
        등록
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentRegist",
  data() {
    return {
      content: "",
    };
  },
  props: ["parent"],
  methods: {
    registComment() {
      let comment = {
        videoId: this.$store.state.video.videoId,
        writer: this.$store.state.loginUser.id,
        content: this.content,
        parent: this.parent,
      };
      this.$store.dispatch("registComment", comment);
      this.$router.go();
    },
  },
};
</script>

<style></style>
