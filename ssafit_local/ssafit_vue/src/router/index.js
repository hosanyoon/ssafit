import Vue from "vue";
import VueRouter from "vue-router";
import VideoView from "../views/VideoView.vue";
import UserView from "../views/UserView.vue";
import CommentView from "../views/CommentView.vue";
import CommitView from "../views/CommitView.vue";
import RecommendView from "../views/RecommendView.vue";
import MapView from "../views/MapView.vue";
import UserRegist from "@/components/user/UserRegist";
import UserModify from "@/components/user/UserModify";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "video",
    component: VideoView,
  },
  {
    path: "/user",
    component: UserView,
    children: [
      {
        path: "regist",
        name: "Regist",
        component: UserRegist,
      },
      {
        path: "modify/:id",
        name: "UserModify",
        component: UserModify,
      },
    ],
  },
  {
    path: "/comment/:id/page/:page",
    name: "Comment",
    component: CommentView,
  },
  {
    path: "/commit",
    name: "commit",
    component: CommitView,
  },
  {
    path: "/recommend",
    name: "recommend",
    component: RecommendView,
  },
  {
    path: "/map",
    name: "map",
    component: MapView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
