<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>
      <!-- 讲师头像 -->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacher.avatar"/>
        <!-- 文件上传按钮 -->
        <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
        </el-button>
        <!--
            v-show：是否显示上传组件
            :key：类似于id，如果一个页面多个图片上传控件，可以做区分
            :url：后台上传的url地址
            @close：关闭上传组件
            @crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API+'/eduservice/oss/upload'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacher from '@/api/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'

const defaultForm = {
  name: '',
  sort: 0,
  level: '',
  career: '',
  intro: '',
  avatar: ''
}

export default {
  // 声明使用的额外组件
  components: { ImageCropper, PanThumb },
  data: function() {
    return {
      BASE_API: process.env.BASE_API, // 接口API地址
      imagecropperShow: false,
      imagecropperKey: 0,
      teacher: defaultForm
    }
  },
  watch: {
    $route(to, from) {
      console.log('watch $route')
      this.init()
    }
  },
  created() {
    console.log('created')
    this.init()
  },
  methods: {
    init() {
      // 在页面加载之前,判断路由中是否有id值
      if (this.$route.params && this.$route.params.id) { // 修改数据回显
        const id = this.$route.params.id
        this.getTeacherId(id)
      } else {
        // 添加,表单清空
        // 使用对象拓展运算符，拷贝对象，而不是引用，
        // 否则新增一条记录后，defaultForm就变成了之前新增的teacher的值
        this.teacher = { ...defaultForm }
      }
    },
    // 实现添加和修改都用一个表单
    saveOrUpdate() {
      // 判断路由是否有id
      if (!this.teacher.id) {
        this.saveTeacher()
      } else {
        this.updateTeacher()
      }
    },
    // 添加讲师
    saveTeacher() {
      teacher.addTeacher(this.teacher)
        .then(() => {
          return this.$message({
            type: 'success',
            message: '添加成功!'
          })
        })
        .then(() => {
          this.$router.push({ path: '/teacher' })
        })
        .catch(() => {
          return this.$message({
            type: 'error',
            message: '添加失败!'
          })
        })
    },
    // 根据id查询
    getTeacherId(id) {
      teacher.getTeacherById(id)
        .then(response => {
          this.teacher = response.data.item
        })
        .catch(reason => {
          this.$message({
            type: 'error',
            message: '获取数据失败!'
          })
        })
    },
    // 修改讲师
    updateTeacher() {
      teacher.updateTeacher(this.teacher.id, this.teacher)
        .then(() => {
          return this.$message({
            type: 'success',
            message: '修改成功!'
          })
        }).then(() => {
          this.$router.push({ path: '/teacher' })
        }).catch(() => {
          this.$message({
            type: 'error',
            message: '修改失败!'
          })
        })
    },
    // 上传成功后的回调函数
    cropSuccess(data) {
      console.log(data)
      this.imagecropperShow = false
      // 后台返回的url赋值给avatar
      this.teacher.avatar = data.url
      // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
      this.imagecropperKey = this.imagecropperKey + 1
    },
    // 关闭上传组件
    close() {
      this.imagecropperShow = false
      // 上传失败后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
      this.imagecropperKey = this.imagecropperKey + 1
    }

  }
}
</script>

<style scoped>

</style>
