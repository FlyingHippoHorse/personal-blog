<template>
  <div class="calendor">
    <div class="title">
      <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
      <h4>个人博客</h4>
      <el-divider><i class="el-icon-user-solid"></i></el-divider>
    </div>
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in imgOptions" :key="item.id">
        <img :src="item.path" alt="图片" width="300px" />
      </el-carousel-item>
    </el-carousel>
    <div class="block">
      <h2 class="demonstration">对于博主的评级</h2>
      <el-rate v-model="value2" :colors="colors"> </el-rate>
    </div>
    <div class="message">
      <ul>
        <li>
          <el-tag type="success">
            <i class="el-icon-grape"></i>
            qq邮箱:2557523039@qq.com
          </el-tag>
        </li>
        <li>
          <el-tag type="warning">
            <i class="el-icon-ice-tea"></i>
            网易邮箱 qwxxhuchao@163.com
          </el-tag>
        </li>
        <li>
          <el-tag>
            <i class="el-icon-watermelon"></i>
            微信号:wxid_897c5xh9mya312
          </el-tag>
        </li>
        <li>
          <el-tag type="danger">
            <i class="el-icon-cherry"></i>
            电话：18864470086
          </el-tag>
        </li>
      </ul>
    </div>
    <div class="mine">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>关于我</span>
        </div>
        <!-- v-for="o in 1" :key="o"  -->
        <div class="text item">
          可能我已经习惯了一个人，自娱自乐。
        </div>
      </el-card>
    </div>
    <el-calendar v-model="value" width="300px" />
  </div>
</template>

<script>
export default {
  name: "calendor",
  data() {
    return {
      imgOptions: [
        {
          id: 1,
          path: "https://img2.baidu.com/it/u=3527499103,1193383052&fm=26&fmt=auto&gp=0.jpg",
        },
        {
          id: 2,
          path: "https://img2.baidu.com/it/u=1658671278,5902853&fm=26&fmt=auto&gp=0.jpg",
        },
        {
          id: 3,
          path: "https://img1.baidu.com/it/u=2290779245,3922634461&fm=253&fmt=auto&app=120&f=JPEG?w=640&h=400",
        },
      ],
      value: new Date(),
      value2: null,
      colors: ["#99A9BF", "#F7BA2A", "#FF9900"],
    };
  },
};
</script>

<style type="scss" scoped>
.calendor {
  display: flex;
  flex-direction: column;
  font-size: 14px;
}
.title{
  text-align: center;
  margin-top:10px;
}
.title .el-avatar{
  width:100px;
  height:100px;
}
.el-carousel{
  width:100%;
  margin:10px 0px 10px 0px;
}
.el-carousel img{
  /* width:250px; */
  height:180px;
}
 ::v-deep .el-carousel__container{
  height:200px;
}
.block {
  width: 210px;
  margin: 0 auto;
  text-align: center;
  background-color: rgb(223, 109, 33);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.message {
  /* text-align:center; */
}
/* .message ul {

} */
.message ul li {
  width: 210px;
  height: 32px;
  list-style: none;
  margin-top: 10px;
  /* box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1) */
}
.message ul li .el-tag {
  width: 210px;
  height: 32px;
}
.mine{
  width: 210px;
  margin:0 auto;
}
.mine span{
  text-align: center;
  font-weight: 600;
  color: #58666e;
}
.text{
  text-align: center;
}
.el-calendar{
  margin-top:10px;
}
</style>