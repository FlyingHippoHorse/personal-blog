<template>
  <div class="m-content"> 
    <div style="margin-top: 50px;"></div>
    <div class="block">
      <el-avatar :size="50" :src="require('@/assets/flyhippo.png')"></el-avatar>
      <div>Flying Hippo</div>
      <div class="maction">
        <span>
          <el-link type="info" href="/blogs">主页</el-link>
        </span>
        <el-divider direction="vertical" v-if="hasLogin"></el-divider>
        <span v-show="hasLogin">
           <el-link type="success" href="/blog/add">发表博客</el-link>
        </span>
        
        <el-divider direction="vertical" v-if="hasLogin"></el-divider>
        <span v-show="hasLogin">
          <el-link type="danger" @click="logout">退出</el-link>
        </span>
      </div>
    </div>

  </div>
</template>

<script> 
export default {
  name: "Header",
  data(){
    return{
      user:{
        username:'请先登录',
        
      },
      hasLogin:false
    }
  },
  methods:{
    logout() {
      // 后端的jwt信息是无状态的  没有存到redis进行一个状态的处理  直接将localstorage删除无需请求后端  如果是有状态的需要请求后端
      const _this =this
      this.$axios.get('http://localhost:8081/logout', {
        headers: {
          //获取token信息
          "Authorization": localStorage.getItem("token")
        }
      }).then((res) => {    //删除信息
        _this.$store.commit('REMOVE_INFO')
        _this.$router.push('/login')
      });
    }
  },
  created() {
    if(this.$store.getters.getUser.username) {    //如果用户名不为空的话  进行回显
      this.user.username = this.$store.getters.getUser.username
      this.user.avatar = this.$store.getters.getUser.avatar
      this.hasLogin = true    //信息不为空
    }
  }
}
</script>

<style scoped>
.m-content{
  /* max-width: 960px; */
  margin: 0 auto;
  text-align: center;
}
.maction{
  margin: 10px 0;
}
</style>