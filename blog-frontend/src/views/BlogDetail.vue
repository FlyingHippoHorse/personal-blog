<!--（博客详情页）-->
<template>
  <div class="detail">
    <div class="left">
      <slideBar />
    </div>
    <div class="center">
      <Header></Header>
      <div class="mblog">
        <h2>{{ blog.title }}</h2>
        <el-link icon="el-icon-edit" v-if="ownBlog">
          <router-link :to="{ name: 'BlogEdit', params: { blogId: blog.id } }"
            >编辑</router-link
          >
        </el-link>
        <el-divider></el-divider>
        <div class="content markdown-body" v-html="blog.content"></div>
      </div>
      <!-- <el-autocomplete
        popper-class="my-autocomplete"
        v-model="state"
        :fetch-suggestions="querySearch"
        placeholder="请输入内容"
        @select="handleSelect"
      >
        <i
          class="el-icon-edit el-input__icon"
          slot="suffix"
          @click="handleIconClick"
        >
        </i>
        <template slot-scope="{ item }">
          <div class="name">{{ item.value }}</div>
          <span class="addr">{{ item.address }}</span>
        </template>
      </el-autocomplete> -->
    </div>
    <div class="right">
      <calendor/>
    </div>
  </div>
</template>

<script>
import slideBar from "@/components/slideBar";
import calendor from "@/components/calendor";
import "github-markdown-css/github-markdown.css";
import Header from "@/components/Header";
export default {
  name: "BlogDetail",
  components: { Header, slideBar, calendor },
  data() {
    return {
      blog: {
        userId: null,
        title: "",
        description: "",
        content: "",
      },
      ownBlog: false,
    };
  },
  methods: {
    getBlog() {
      const blogId = this.$route.params.blogId;
      const _this = this;
      this.$axios.get("blog/blog/" + blogId).then((res) => {
        console.log(res);
        console.log(res.data.data);
        _this.blog = res.data.data;
        var MarkdownIt = require("markdown-it"), //返回的博客详情content通过markdown-it工具进行渲染。
          md = new MarkdownIt();
        var result = md.render(_this.blog.content);
        _this.blog.content = result;
        // 判断是否是自己的文章，能否编辑
        _this.ownBlog = _this.blog.userId === _this.$store.getters.getUser.id;
      });
    },
  },
  created() {
    this.getBlog();
  },
};
</script>

<style scoped>
.detail{
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  width: 100%;
  height: 100%;
  text-align: center;
}
.left {
  width:170px;
  height:870px;
  position: fixed;
  top:0;
  left:0;
}
.center {
  width:70%;
  margin-left:170px;
  background-color: bisque;
  text-align: center;
}
.center .mblog {
  background-color: beige;
  width:80%;
  margin-left:8%;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  min-height: 700px;
  padding: 20px 15px;
}
.right{
  width:30%;
}
</style>