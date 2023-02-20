<template>
  <div>
    <div class="input-group my-3">
      <input
        type="text"
        class="form-control"
        placeholder="댓글 추가..."
        v-model="content"
        @keyup.enter="registComment"
      />
      <button class="btn btn-outline-secondary" @click="modifyComment">
        수정
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentRegist",
  data() {
    return {
      content: this.comment.content,
    };
  },
  props: ["comment"],
  methods: {
    modifyComment() {
      let comment = {
        commentId: this.comment.commentId,
        videoId: this.comment.videoId,
        writer: this.comment.writer,
        content: this.content,
        parent: this.comment.parent,
      };
      this.$store.dispatch("modifyComment", comment);
      this.$router.go();
    },
  },
};
</script>

<style></style>
