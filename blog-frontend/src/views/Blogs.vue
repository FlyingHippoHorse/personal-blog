<!--（博客列表）-->
<template>
  <div class="mcontaner">
    <!-- <div class="left">
      <slideBar/>
    </div> -->
    <div class="center">
      <mainBlogs/>
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
  components: {slideBar,calendor,mainBlogs,BlogDetail},
  data() {
    return {
     flag:"false"
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
  text-align:center;
}
/* .left {
  width:170px;
  height:870px;
  position: fixed;
  top:0;
  left:0;
} */
.center {
  width:90%; 
  /* margin-left:170px;*/
  background-color: white; 
}
/* .right{
  width:30%;
} */

</style>