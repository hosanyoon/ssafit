<template>
  <div class="container">
    <Calendar
      :columns="$screens({ default: 1, lg: 4 })"
      :attributes="attributes"
    />
    <br />
    <br />
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import Calendar from "v-calendar/lib/components/calendar.umd";

export default {
  name: "CommitGrass",
  components: {
    Calendar,
  },
  data() {
    return {
      user: null,
      recodes: [],
      commitDates: [],
      attrs: [
        {
          key: "today",
          dates: [
            new Date(2022, 10, 1),
            new Date(2022, 10, 1),
            new Date(2022, 10, 1),
          ],
          // dates: this.dates,
          highlight: true,
        },
      ],
    };
  },
  computed: {
    ...mapState(["loginUser"]),
    attributes() {
      return [
        {
          key: "commitDates",
          highlight: true,
          dates: this.commitDates,
        },
      ];
    },
  },
  created() {
    this.user = this.loginUser;
    let API_URL = `http://localhost:9999/userapi/recodes/${this.user.id}`;
    axios({
      url: API_URL,
      method: "get",
    })
      .then((res) => {
        res.data.forEach((ele) => {
          let recode = {
            date: new Date(ele.date),
            weight: ele.weight,
            recent: ele.recent,
          };
          this.recodes.push(recode);
        });
        console.log("레코트에서 데이터 빼오기");
        this.recodes.forEach((ele) => {
          let year = ele.date.getUTCFullYear();
          let month = ele.date.getUTCMonth();
          let day = ele.date.getUTCDate();
          console.log(year);
          console.log(month);
          console.log(day);
          let date = new Date(year, month, day);
          console.log(date);
          this.commitDates.push(date);
        });
        console.log(this.dates);
      })
      .catch((err) => {
        alert("달력 실패 : 이유 모름2.");
        console.log(err);
      });
  },
};
</script>

<style></style>
