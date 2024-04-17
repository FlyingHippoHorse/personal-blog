<template>
  <div class="mainBlogs">
    <Header></Header>
    <div class="block">
      <el-row v-for="(group, index) in blogGroups" :key="index">
        <el-col :span="6" v-for="blog in group" :key="blog.id" class="card-col">
          <el-card class="timeline-card">
            <div>
              <h3>
                <router-link :to="{ name: 'BlogDetail', params: { blogId: blog.id } }">{{ blog.title }}</router-link>
              </h3>
              <p>{{ blog.description }}</p>
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
  .card-col {
    width: 23%;
    /* 每个卡片宽度为25% */
    height: 50%;
    /* 每个卡片高度为10% */
    margin-right: 1%;
    /* 水平方向每个卡片间距为1% */
    margin-bottom: 2%;
    /* 竖直方向每行间距为2% */
    box-sizing: border-box;
    /* 计算宽度时包括边框和填充 */
  }

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

  .el-row {
    display: flex;
    flex-wrap: nowrap;
    /* 换行 */
  }

  .timeline-card {
    width: 20vw;
    /* 每个卡片宽度为25% */
    height: 30vh;
    flex: 1;
    /* 卡片自适应宽度和高度 */
    margin-bottom: 0%;
    margin-right: 0%;
  }
</style>