<!--（博客列表）-->
<template>
  <div class="mcontaner">
    <!-- <div class="left">
      <slideBar/>
    </div> -->
    <div class="fixed-navbar">
      <span class="blog-name">FlyingHippo的博客</span>
      <a href="https://github.com/FlyingHippoHorse" target="_blank" class="github-link">
        <img src="@/assets/GitHub-Mark.png" alt="GitHub">
      </a>
    </div>
    <div class="center">
      <mainBlogs />
    </div>
    <!-- <div class="right">
      <calendor/>
    </div> -->
  </div>
</template>

<script>
  import mainBlogs from "./mainBlog"
  import BlogDetail from './BlogDetail'
  import slideBar from "@/components/slideBar"
  import calendor from '@/components/calendor'
  export default {
    name: "Blogs.vue",
    components: { slideBar, calendor, mainBlogs, BlogDetail },
    data() {
      return {
        flag: "false"
      }
    },
    methods: {

    },

  };
</script>

<style scoped>
  .mcontaner {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    width: 100%;
    height: 100%;
    text-align: center;
  }

  .fixed-navbar {
    position: fixed;
    margin-left: 10px;
    /* 添加10像素的左边距 */
    text-align: left;
    /* 添加这条是为了确保内容左对齐 */
    top: 0;
    left: 0;
    background-color: whitesmoke;
    height: 40px;
    width: 100%;
    z-index: 1000;
    /* 可选，用于控制元素在堆叠顺序上的显示 */
  }

  .blog-name {
    /* 让文本左对齐 */
    margin-left: 0;
    text-align: left;
    font-family: 'Your Font Name', sans-serif;
    /* 选择字体 */
    font-size: 24px;
    /* 根据需要调整字体大小 */
    font-weight: bold;
    /* 可选的字体粗细 */
    color: #333;
    /* 根据需要调整文字颜色 */
  }

  /* .left {
  width:170px;
  height:870px;
  position: fixed;
  top:0;
  left:0;
} */
  .center {
    width: 90%;
    /* margin-left:170px;*/
    background-color: white;
  }

  .github-link {
    display: inline-block;
    position: fixed;
    right: 0;
    top: 0;
    margin-top: 4px;
    margin-right: 20px;
    height: 40px;
    /* 调整为与.navbar相同的高度 */
  }

  .github-link img {
    height: 30px;
    /* 图片高度与父元素高度相同 */
    width: 30px;
    /* 自动调整宽度以保持比例 */
    vertical-align: middle;
    /* 使图片垂直居中 */
  }

  /* .right{
  width:30%;
} */
</style>