<template>
  <div>
    <h1 class="h3 mb-3">
      <i class="bi bi-pencil-fill me-2"></i>SSAFIT 회원가입
    </h1>
    <form
      @submit.prevent="submitForm"
      class="d-flex flex-column align-items-center"
    >
      <div class="col-md-6 mb-4">
        <label for="id" class="form-label">아이디</label>
        <input type="text" class="form-control" id="id" v-model="id" required />
      </div>
      <div class="col-md-6 mb-4">
        <label for="validationDefault02" class="form-label">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="validationDefault02"
          v-model="password"
          required
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="validationDefault02" class="form-label">나이</label>
        <input
          type="number"
          class="form-control"
          id="validationDefault02"
          v-model="age"
          required
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="height" class="form-label">키(cm)</label>
        <input
          type="number"
          class="form-control"
          id="height"
          v-model="height"
          required
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="weight" class="form-label">몸무게(kg)</label>
        <input
          type="number"
          class="form-control"
          id="weight"
          v-model="weight"
          required
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="weight" class="form-label">프로필</label>
        <input
          type="file"
          class="form-control"
          ref="files"
          onchange="checkSize(this)"
          v-on:change="handleFileUpload()"
          multiple
        />
      </div>
      <div class="col-md-6 mb-4">
        <label for="sex" class="form-label">성별</label>
        <select class="form-select" id="sex" v-model="sex" required>
          <option selected disabled value=""></option>
          <option value="1">남</option>
          <option value="0">여</option>
        </select>
      </div>
      <div class="col-md-6 mb-4">
        <label for="preference" class="form-label">운동스타일</label>
        <select
          class="form-select"
          id="preference"
          v-model="preference"
          required
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
            class="form-check-input"
            type="checkbox"
            value=""
            id="agreeCheck"
            required
          />
          <label class="form-check-label" for="agreeCheck">
            개인정보 사용에 동의합니다.
          </label>
        </div>
      </div>
      <div class="col-12">
        <button class="btn btn-primary" type="submit">가입하기</button>
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
      this.registUser();
    },
    registUser() {
      let formData = new FormData();
      formData.append("id", this.id);
      formData.append("password", this.password);
      formData.append("age", this.age);
      formData.append("sex", this.sex);
      formData.append("height", this.height);
      formData.append("preference", this.preference);
      formData.append("weight", this.weight);
      if (this.files) formData.append("files", this.files[0]);
      this.$store.dispatch("registUser", formData);
    },
    handleFileUpload() {
      if(this.$refs.files.files.size < 1024 * 1024){
        this.files = this.$refs.files.files;
      }else{
        alert("파일 사이즈가 1mb를 넘습니다. 제발 아껴주세요ㅠㅠ");
        this.$refs.files.value = null;
      }
    },  
  },
};

</script>

<style></style>
