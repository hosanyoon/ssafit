<template>
  <section class="gradient-custom">
    <div class="container my-5 py-5">
      <div class="row d-flex justify-content-center">
        <div class="col-md-12 col-lg-10 col-xl-8">
          <div class="card">
            <div class="card-body p-4">
              <h4 class="text-center mb-4 pb-2">
                댓글 {{ commentList.length }}개
              </h4>
              <comment-regist></comment-regist>
              <div
                class="row"
                v-for="(comment, index) in commentList"
                :key="index"
              >
                <div class="col" style="display: flexbox">
                  <comment-item :comment="comment"></comment-item>
                </div>
              </div>
            </div>
          </div>
          <div class="my-2">
            <ul class="pagination justify-content-center">
              <li class="page-item">
                <router-link
                  class="page-link"
                  :class="{ disabled: !isPrev }"
                  :to="`/comment/${videoId}/page/${
                    pagenation.firstPageNum - 10
                  }`"
                >
                  <span class="sr-only">이전</span>
                </router-link>
              </li>
              <span
                v-for="i in pagenation.lastPageNum -
                pagenation.firstPageNum +
                1"
                :key="i"
              >
                <li class="page-item">
                  <router-link
                    class="page-link"
                    :to="`/comment/${videoId}/page/${
                      i + pagenation.firstPageNum - 1
                    }`"
                    @click.native="getComments"
                    >{{ i + pagenation.firstPageNum - 1 }}</router-link
                  >
                </li>
              </span>
              <li class="page-item">
                <router-link
                  class="page-link"
                  :class="{ disabled: !isPrev }"
                  :to="`/comment/${videoId}/page/${
                    pagenation.firstPageNum + 10
                  }`"
                >
                  <span class="sr-only">다음</span>
                </router-link>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import CommentItem from "./CommentItem.vue";
import { mapState } from "vuex";
import CommentRegist from "@/components/comment/CommentRegist.vue";

export default {
  name: "CommentList",
  components: {
    CommentItem,
    CommentRegist,
  },
  computed: {
    videoId() {
      return this.$route.params.id;
    },
    isPrev() {
      return this.pagenation.prevBtnActivation;
    },
    isNext() {
      return this.pagenation.nextBtnActivation;
    },
    ...mapState(["pagenation", "commentList"]),
  },
  created() {
    this.getComments();
  },
  methods: {
    getComments() {
      let payload = {
        videoId: this.$route.params.id,
        page: this.$route.params.page,
      };
      this.$store.dispatch("getComments", payload);
    },
  },
};
</script>

<style></style>
