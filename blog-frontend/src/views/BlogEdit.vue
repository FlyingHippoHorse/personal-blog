<!--（编辑博客）-->
<template>
  <div>
    <Header></Header>
    <div class="m-content">
      <el-form ref="editForm" status-icon :model="editForm" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-upload 
          :action="uploadImageUrl" 
          :headers="headers" 
          :on-success="handleSuccess" 
          :on-error="handleError" 
          :limit="1"
          :on-exceed="handleExceed"
          :before-upload="handleBeforeUpload" 
          :on-progress="handleProgress">
            <el-button type="primary" size="medium">上传图片</el-button>
          </el-upload>
          <!--
          action: 图片上传的地址
          show-file-list: 是否显示文件上传列表
          accept: 可接受的上传类型，image/*为图片
          headers: 头部信息
          on-success: 上传成功事件
          on-error: 上传失败事件
          before-upload: 上传前处理事件，返回一个值，值为false将阻止上传
          on-progress: 上传中事件
          -->
        </el-form-item>
        <el-form-item label="摘要" prop="description">
          <el-input type="textarea" v-model="editForm.description"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">


          <mavon-editor ref=md @imgAdd="imgAdd" v-model="editForm.content" :toolbars="toolbars" />


        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm()">立即创建</el-button>
          <el-button @click="goback()">取消</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  import Header from "@/components/Header";
  export default {
    name: "BlogEdit.vue",
    components: { Header },
    data() {
      return {
        headers: {
          Authorization:  localStorage.getItem("token")
        },
        uploadImageUrl: process.env.VUE_APP_UPLOAD_IMAGE_URL || 'http://localhost:8081/api/img/upload', // 若有环境变量则使用环境变量，否则使用默认值
        imageList: [],
        coverImg:'',
        toolbars: {
          bold: true, // 粗体
          italic: true, // 斜体
          header: true, // 标题
          underline: true, // 下划线
          strikethrough: true, // 中划线
          mark: false, // 标记
          superscript: false, // 上角标
          subscript: false, // 下角标
          quote: true, // 引用
          ol: true, // 有序列表
          ul: true, // 无序列表
          link: true, // 链接
          imagelink: true, // 图片链接
          code: true, // code
          table: true, // 表格
          fullscreen: true, // 全屏编辑
          readmodel: false, // 沉浸式阅读
          htmlcode: true, // 展示html源码
          help: false, // 帮助
          /* 1.3.5 */
          undo: true, // 上一步
          redo: false, // 下一步
          trash: false, // 清空
          save: false, // 保存（触发events中的save事件）
          /* 1.4.2 */
          navigation: false, // 导航目录
          /* 2.1.8 */
          alignleft: true, // 左对齐
          aligncenter: true, // 居中
          alignright: true, // 右对齐
          /* 2.2.1 */
          subfield: true, // 单双栏模式
          preview: true, // 预览
          boxShadow: false
        },
        editForm: {
          id: null,
          title: '',
          description: '',
          content: ''
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' },
            { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '请输入摘要', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      const blogId = this.$route.params.blogId
      const _this = this
      if (blogId) {
        this.$axios.get('blog/blog/' + blogId).then((res) => {
          const blog = res.data.data
          _this.editForm.id = blog.id
          _this.editForm.title = blog.title
          _this.editForm.description = blog.description
          _this.editForm.content = blog.content
        });
      }
    },
    methods: {
      handleSuccess(response, file, fileList) {
        this.coverImg=response.data; 
        this.$message.success("上传成功");
      },
      handleError() {
        this.$message.error("上传失败,请重新上传图片!");
      },
      handleBeforeUpload(file) {
        const isJPGOrPNG = file.type === 'image/jpeg' || file.type === 'image/png';
        if (!isJPGOrPNG) {
          this.$message.error('只能上传 JPG/PNG 格式的图片!');
        }
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error("上传图片大小不能超过 2MB!");
        }
        return isJPGOrPNG && isLt2M;
      },
      handleProgress(event, file, fileList) {
        this.loading = true;  //  上传时执行loading事件
      },
      handleExceed(files, fileList) {
      this.$message.warning(`最多只能上传 1 张图片`);
    },
      beforeUpload(file) {
        const isJPGOrPNG = file.type === 'image/jpeg' || file.type === 'image/png';
        const isLt500K = file.size / 1024 < 500;

        if (!isJPGOrPNG) {
          this.$message.error('只能上传 JPG/PNG 格式的图片!');
          return false;
        }
        if (!isLt500K) {
          this.$message.error('图片大小不能超过 500KB!');
          return false;
        }
        // 添加 Authorization 请求头
        this.headers.Authorization = localStorage.getItem("token");
        // 校验通过，允许上传
        return true;
      },
      handleExceed(files, fileList) {
        this.$message.warning(`最多只能上传 3 张图片`);
      }, 
      imgAdd(pos, file) {
        // 上传图片
        var formData = new FormData()
        formData.append('image', file)
        this.$axios.post('/api/img/upload', formData, {
          headers: {
            "Authorization": localStorage.getItem("token"),
            'Content-Type': 'multipart/form-data'
          }
        }).then((data) => {
          // console.log(JSON.stringify(url))
          // 第二步.将返回的url替换到文本原位置![...](./0) -> ![...](url)
          /**
           * $vm 指为mavonEditor实例，可以通过如下两种方式获取
           * 1.  通过引入对象获取: `import {mavonEditor} from ...` 等方式引入后，`$vm`为`mavonEditor`
           * 2. 通过$refs获取: html声明ref : `<mavon-editor ref=md ></mavon-editor>，`$vm`为 `this.$refs.md`
          * 3. 由于vue运行访问的路径只能在static下，so，我就把图片保存到它这里了
           */
          let url = data.data.data;
          console.log(url)
          this.$refs.md.$img2Url(pos, url)
        })
      }
      ,
      goback() {
        this.$router.go(-1);
      },
      submitForm() {
        const _this = this
        this.$refs.editForm.validate((valid) => {
          if (valid) {
            this.$axios.post('blog/blog/edit', this.editForm, {
              headers: {
                "Authorization": localStorage.getItem("token")
              }
            }).then((res) => {
              _this.$alert('操作成功', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push("/blogs")
                }
              });
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        })
      }
    }
  }

</script>

<style scoped>
  .m-content {
    text-align: center;
  }
</style>