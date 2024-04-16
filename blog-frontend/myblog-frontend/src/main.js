import { createApp } from 'vue'
import App from './App.vue'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import axios from 'axios'
Vue.prototype.$axios = axios //
Vue.use(Element)
createApp(App).mount('#app')
