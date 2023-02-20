<template>
  <div>
    <h1 class="h3 mb-3 fw-normal">SSAFIT 회원가입</h1>
    <form
      @submit.prevent="submitForm"
      class="d-flex flex-column align-items-center"
    >
      <div class="col-md-6 mb-4">
        <label for="validationDefault01" class="form-label">id</label>
        <input
          type="text"
          class="form-control"
          id="validationDefault01"
          value="Mark"
          required
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="validationDefault02" class="form-label">Last name</label>
        <input
          type="text"
          class="form-control"
          id="validationDefault02"
          value="Otto"
          required
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="validationDefaultUsername" class="form-label"
          >Username</label
        >
        <div class="input-group">
          <span class="input-group-text" id="inputGroupPrepend2">@</span>
          <input
            type="text"
            class="form-control"
            id="validationDefaultUsername"
            aria-describedby="inputGroupPrepend2"
            required
          />
        </div>
      </div>
      <div class="col-md-6">
        <label for="validationDefault03" class="form-label">City</label>
        <input
          type="text"
          class="form-control"
          id="validationDefault03"
          required
        />
      </div>
      <div class="col-md-3">
        <label for="validationDefault04" class="form-label">State</label>
        <select class="form-select" id="validationDefault04" required>
          <option selected disabled value="">Choose...</option>
          <option>...</option>
        </select>
      </div>
      <div class="col-md-3">
        <label for="validationDefault05" class="form-label">Zip</label>
        <input
          type="text"
          class="form-control"
          id="validationDefault05"
          required
        />
      </div>
      <div class="col-12">
        <div class="form-check">
          <input
            class="form-check-input"
            type="checkbox"
            value=""
            id="invalidCheck2"
            required
          />
          <label class="form-check-label" for="invalidCheck2">
            Agree to terms and conditions
          </label>
        </div>
      </div>
      <div class="col-12">
        <button class="btn btn-primary" type="submit">Submit form</button>
      </div>
    </form>

    <form class="d-flex flex-column align-items-center">
      <div class="col-md-6 mb-4">
        <label for="id" class="form-label">id</label>
        <input
          type="text"
          class="form-control is-invalid"
          id="id"
          required
          v-model="id"
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="password" class="form-label">password</label>
        <input
          type="password"
          class="form-control is-invalid"
          id="password"
          required
          v-model="password"
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="age" class="form-label">나이</label>
        <input
          type="number"
          class="form-control is-invalid"
          id="age"
          aria-describedby="validationServer03Feedback"
          required
          v-model="age"
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="height" class="form-label">키(cm)</label>
        <input
          type="number"
          class="form-control is-invalid"
          id="height"
          aria-describedby="validationServer03Feedback"
          required
          v-model="height"
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="weight" class="form-label">몸무게(kg)</label>
        <input
          type="number"
          class="form-control is-invalid"
          id="weight"
          aria-describedby="validationServer03Feedback"
          required
          v-model="weight"
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="sex" class="form-label">성별</label>
        <select
          class="form-select"
          id="sex"
          aria-describedby="validationServer04Feedback"
          required
          v-model="sex"
        >
          <option selected disabled value=""></option>
          <option value="1">남</option>
          <option value="0">여</option>
        </select>
      </div>
      <div class="col-md-6 mb-4">
        <label for="preference" class="form-label">운동 스타일</label>
        <select
          class="form-select is-invalid"
          id="preference"
          aria-describedby="validationServer04Feedback"
          required
          v-model="preference"
        >
          <option selected disabled value=""></option>
          <option value="0">웨이트</option>
          <option value="1">필라테스</option>
          <option value="2">크로스핏</option>
          <option value="3">홈트</option>
        </select>
      </div>
      <div class="col-md-3 mb-4">
        <div class="form-check">
          <input
            class="form-check-input is-invalid"
            type="checkbox"
            value=""
            id="agreeCheck"
            aria-describedby="invalidCheck3Feedback"
            required
          />
          <label class="form-check-label" for="agreeCheck">
            개인정보 사용에 동의합니다.
          </label>
        </div>
      </div>
      <div class="col-12">
        <button class="btn btn-primary" type="submit" @click="registUser">
          가입하기
        </button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "UserRegist",
  data() {
    return {
      id: null,
      password: null,
      age: null,
      sex: null,
      height: null,
      preference: null,
      weight: null,
    };
  },
  methods: {
    submitForm() {
      console.log("막았어");
    },
    registUser() {
      console.log("되나?");
      let user = {
        id: this.id,
        password: this.password,
        age: this.age,
        sex: this.sex,
        height: this.hegiht,
        preference: this.preference,
        weight: this.weight,
      };

      console.log(user);

      this.$store.dispatch("registUser", user);
    },
  },
};
</script>

<style></style>
