<template>
  <div class="mainBlogs">
    <Header></Header>
    <div class="block">
      <el-row v-for="(group, index) in blogGroups" :key="index">
        <el-col :span="6" v-for="blog in group" :key="blog.id" class="card-col">
          <el-card class="timeline-card">
            <div class="content-wrapper" @click="goToBlogDetail(blog.id)">
              <img :src="blog.coverImg" alt="Blog Cover" class="blog-cover-img">
              <div>
                <h3>
                  <span class="blog-link">{{ blog.title }}</span>
                </h3>
                <p>{{ blog.description }}</p>
              </div>
            </div>
            <div class="click-times">
              <span>click times</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <!--带有背景色的分页  设置background属性可以为分页按钮添加背景色。-->
      <el-pagination class="mpage" background layout="prev, pager, next" :current-page="currentPage"
        :page-size="pageSize" :total="total" @current-change="page">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import Header from "@/components/Header";

  export default {
    name: "mainBlogs",
    components: {
      Header,
    },
    data() {
      return {
        //得到数据
        blogs: [], //对象
        currentPage: 1, //当前页1 默认第一页
        total: 0, // 一共有多少篇文章
        pageSize: 8, //一页5条
      };
    },
    computed: {
      // 分组后的博客列表
      blogGroups() {
        const groups = [];
        for (let i = 0; i < this.blogs.length; i += 4) {
          groups.push(this.blogs.slice(i, i + 4));
        }
        return groups;
      },
    },
    methods: {
      // 跳转到博客详情页
      goToBlogDetail(blogId) {
        this.$router.push({ name: "BlogDetail", params: { blogId: blogId } });
      },
      //请求结果进行赋值
      page(currentPage) {
        //  pageSize，如果未赋值则默认为 8
        let pageSize = this.pageSize ? this.pageSize : 8;
        this.$axios
          .get("blog/blogs?currentPage=" + currentPage + "&pageSize=" + pageSize)
          .then((res) => {
            this.blogs = res.data.data.records;
            this.currentPage = res.data.data.current;
            this.total = res.data.data.total;
            this.pageSize = res.data.data.size;
          });
      },
    },
    //调用 created方法
    created() {
      this.page(1);
    },
  };
</script>

<style scoped>
 

  .click-times {
    text-align: right;
    margin-bottom: 1px;
    position: relative;
    bottom: 0;
    right: 0;
    /* 为了防止内容溢出card边界，可以增加内边距 */
    padding: 0.5em;
  }

  .click-times span {
    color: gray;
    /* 替换为你想要的颜色 */
  }


  .blog-cover-img {
    max-width: 98%;
    /* 图片最大宽度为父元素的宽度 */
    height: 30%;
    /* 图片高度为卡片高度的一半 */
    object-fit: cover;
    /* 填充整个区域 */
    transform: scale(1.1);
    /* 鼠标悬停时放大 */
  }
  /* 添加到你的样式表中 */

.card-col {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
    
}
.el-row {
  flex-grow:1;
  transition: transform 0.3s ease; /* 添加过渡效果 *//* 设置元素宽度为浏览器显示宽度 */
}
.blog-cover-img {
  max-width: 100%;
  height: auto;
  object-fit: cover;
  width: 100%;
  min-height: 150px; /* 或者设置一个最小高度以保证有图片和无图片的差距不会太大 */
}
 
.timeline-card{
   
  border-style: solid; /* 设置为实线 */
  border-color: rgb(196, 194, 194); /* 设置分隔线颜色 */
  border-width: 1px; /* 设置分隔线宽度 */
}
.timeline-card .content-wrapper {
  height: 40vh;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 10px; /* 根据需要设置内边距 */
}
 
</style>