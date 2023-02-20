<template>
  <div class="d-flex flex-start mt-4">
    <div v-if="comment.commentId != comment.parent" class="col-2"></div>
    <img
      class="rounded-circle shadow-1-strong me-3"
      :src="comment.image"
      alt="avatar"
      width="65"
      height="65"
    />
    <div class="flex-grow-1 flex-shrink-1">
      <div>
        <div class="d-flex justify-content-between align-items-center">
          <p class="mb-1" style="font-weight: 900">
            <span class="me-3">{{ comment.writer }}</span>
            <i class="bi bi-calendar3"></i>
            <span class="small ms-1" style="font-weight: 200">
              {{ comment.registDay }}</span
            >
          </p>
          <div>
            <span v-if="comment.writer == this.$store.state.loginUser.id">
              <span style="cursor: pointer">
                <i class="bi bi-pencil-fill"></i
                ><span
                  class="small me-1"
                  v-on:click="
                    reply = false;
                    modify = !modify;
                  "
                >
                  수정</span
                >
              </span>
              <span style="cursor: pointer">
                <i class="bi bi-trash3-fill"></i
                ><span class="small me-1" data-bs-toggle="modal" :data-bs-target="'#comment'+`${comment.commentId}`">  
                  삭제</span
                >
              </span>
            </span>
            <span
              v-if="comment.commentId == comment.parent"
              style="cursor: pointer"
            >
              <i class="bi bi-reply"></i
              ><span
                class="small"
                v-on:click="
                  reply = !reply;
                  modify = false;
                "
              >
                답글</span
              >
            </span>
          </div>
        </div>
        <p
          class="d-flex justify-content-start mb-2 mx-3"
          style="text-align: left"
        >
          {{ comment.content }}
        </p>
      </div>
      <comment-regist
        :parent="comment.commentId"
        v-show="reply"
      ></comment-regist>
      <comment-modify :comment="comment" v-show="modify"></comment-modify>
    </div>
    <div class="modal fade" :id="'comment'+`${comment.commentId}`" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">댓글 삭제</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        정말로 삭제하시겠습니까?<br>
        답글이 있는 경우, 답글의 보존을 위해 글쓴이와 내용만이 삭제됩니다. 
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" v-on:click="deleteComment">삭제</button>
      </div>
    </div>
  </div>
</div>
  </div>
</template>

<script>
import CommentRegist from "@/components/comment/CommentRegist.vue";
import CommentModify from "@/components/comment/CommentModify.vue";

export default {
  name: "CommentItem",
  data() {
    return {
      reply: false,
      modify: false,
    };
  },
  props: ["comment"],
  components: {
    CommentRegist,
    CommentModify,
  },
  methods: {
    deleteComment() {
      console.log(this.comment);
      this.$store.dispatch("deleteComment", this.comment);
      this.$router.go();
    },
  },
};
</script>

<style></style>
