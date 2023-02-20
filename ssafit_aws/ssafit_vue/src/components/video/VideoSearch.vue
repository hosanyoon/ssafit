<template>
  <div>
    <div class="container">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="제목을 입력하세요"
          aria-label="Recipient's username"
          aria-describedby="button-addon2"
          v-model="keyword"
        />
        <button
          class="btn btn-outline-primary"
          type="button"
          id="button-addon2"
          @click="searchVideo"
        >
          검색
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "VideoSearch",
  data() {
    return {
      keyword: "",
    };
  },
  methods: {
    searchVideo() {
      //동영상 검색 : 액션으로 보냄
      this.$store.dispatch("searchVideo", this.keyword);
      console.log(this.keyword);
    },
  },
};
</script>

<style></style>
