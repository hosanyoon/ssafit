import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "https://www.googleapis.com/youtube/v3/search",
  headers: {
    "Content-type": "application/json",
  },
});
