<template>

</template>

<script>// eslint-disable-next-line no-unused-vars
import teacher from '@/api/teacher'
export default {
  // name: 'index'
  // 定义变量和初始值
  data() {
    return {
      list: null,
      total: 0,
      page: 1,
      limit: 10,
      searchObj: {}
    }
  },
  // 在页面渲染之前调用,调用具体的某个方法
  created() {
    this.getListTeacher()
  },
  // 写具体的方法调用
  methods: {
    getListTeacher(page = 1) {
      this.page = page
      teacher.getTeacherPageList(this.page, this.limit, this.searchObj)
        .then(response => {
          this.list = response.rows.items
          this.total = response.rows.total
          console.log(this.list)
          console.log(this.total)
        })
        .catch(response => {

        })
    }
  }

}
</script>

<style scoped>

</style>
