<template>
  <div>
    <commit-regist></commit-regist>
    <commit-grass></commit-grass>
  </div>
</template>

<script>
// import CommitExercise from "@/components/commit/CommitExercise.vue";
// import CommitStatus from "@/components/commit/CommitStatus.vue";
import CommitGrass from "@/components/commit/CommitGrass.vue";
import CommitRegist from "@/components/commit/CommitRegist.vue";

export default {
  name: "CommitView",
  components: {
    // CommitExercise,
    // CommitStatus,
    CommitGrass,
    CommitRegist,
  },
};
</script>

<style></style>
