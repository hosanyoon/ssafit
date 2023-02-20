<template>
  <div>
    <h3><i class="bi bi-clipboard2-check me-2"></i>나의 운동 현황</h3>
    <br />
    <br />
    <form
      @submit.prevent="submitForm"
      class="row g-3 justify-content-center align-items-center"
    >
      <div class="col-md-3">
        <label for="weight" class="form-label">오늘의 체중</label>
        <input
          type="text"
          class="form-control"
          id="weight"
          v-model="user.weight"
          required
        />
      </div>
      <div class="col-md-3">
        <label for="recent" class="form-label">오늘의 부위</label>
        <select class="form-select" id="recent" v-model="user.recent" required>
          <option selected disabled value=""></option>
          <option value="0">등</option>
          <option value="1">어깨</option>
          <option value="2">가슴</option>
          <option value="3">하체</option>
          <option value="4">유산소</option>
        </select>
      </div>
      <div class="col-md-1 ml-5 mt-5">
        <button class="btn btn-primary" type="submit">commit</button>
      </div>
    </form>

    <br />
    <br />
    <div>
      <div class="fs-5">
        현재 내 몸상태 : 키: {{ user.height }}cm, 몸무게 : {{ user.weight }}kg
      </div>
      <div class="fs-5 text-primary">
        신체질량지수(BMI) : {{ bmi }} {{ bmiStatus }}
      </div>
    </div>
    <br />
    <br />
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "CommitExercise",
  data() {
    return {
      user: null,
    };
  },
  methods: {
    submitForm() {
      if (confirm("커밋하시겠습니까? ")) {
        this.$store.dispatch("commitUser", this.user);
      }
    },
  },
  computed: {
    ...mapState(["loginUser"]),
    bmi() {
      return Math.round(this.user.weight / (this.user.height * 0.01) ** 2);
    },
    bmiStatus() {
      let bmi = this.user.weight / (this.user.height * 0.01) ** 2;
      if (bmi <= 20) return "저체중";
      else if (bmi <= 24) return "정상";
      else if (bmi <= 29) return "과체중";
      else return "비만";
    },
  },
  created() {
    this.user = this.loginUser;
  },
};
</script>

<style></style>
