//  一个页面之间会有多个组件 子组件更新数据 通过数据状态管理  通知每一组件
// 数据状态管理界面  所有的子组件更新交流数据都在此

import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  //配置全局jwt token信息  使其他组件都能是实时获取到更新的数据
  state: {
    token:'',
    //反序列化  将json转换成对象
    userInfo:JSON.parse(sessionStorage.getItem("userInfo"))
    }
  ,
  mutations: {
    //set方法
    SET_TOKEN:(state, token)=>{
      state.token=token
      //浏览器关闭后还能继续存在localstorage里面
      localStorage.setItem("token",token)
    },
    SET_USERINFO:(state, userInfo)=>{
      state.userInfo=userInfo
      //浏览器关闭后session会话可能会消失  sessionstorage不能存对象，只能存字符串
      //JSON.stringify(userInfo) 序列化字符串
      sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },
    REMOVE_INFO:(state)=>{
      state.token=' '
      state.userInfo=''
      localStorage.setItem("token",'')
      sessionStorage.setItem("userInfo",'')
}
    },
  getters:{
    //get方法
    getUser:state => {
      return state.userInfo
    }
  },
  actions: {
  },
  modules: {
  }
})
