<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="3" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="发布课程" />
    </el-steps>

    <div class="ccInfo">
      <img :src="coursePublishVo.cover">
      <div class="main">
        <h2>{{ coursePublishVo.title }}</h2>
        <p class="gray"><span>共{{ coursePublishVo.lessonNum }}课时</span></p>
        <p><span>所属分类：{{ coursePublishVo.subjectLevelOne }} — {{ coursePublishVo.subjectLevelTwo }}</span></p>
        <p>课程讲师：{{ coursePublishVo.teacherName }}</p>
        <h3 class="red">￥{{ coursePublishVo.price }}</h3>
      </div>
    </div>

    <div style="text-align: center;">
      <el-button @click="previous">返回修改</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="publish">发布课程</el-button>
    </div>
  </div>
</template>


<script>
  import course from '@/api/course'
  export default {
    data() {
      return {
        courseId: '',
        saveBtnDisabled: false, // 保存按钮是否禁用
        coursePublishVo: {},//课程发布确认数据

      }
    },

    created() {
      console.log('publish created');
      if (this.$route.params && this.$route.params.id) {
        this.courseId = this.$route.params.id
        // console.log(this.courseId);
        this.getCoursePublish();
      }
    },

    methods: {

      getCoursePublish() {
        course.getCoursePublishById(this.courseId)
          .then(res => {
            this.coursePublishVo = res.data.coursePublishVo;
          })
      },

      previous() {
        console.log('previous')
        this.$router.push({ path: `/course/chapter/${this.courseId}` })
      },

      publish() {
        console.log('publish');
        //调用接口更新状态
        course.publishCourse(this.courseId)
          .then(res => {
            this.$message({
              type: "success",
              message: "发布成功!"
            });
            //路由跳转
            this.$router.push({ path: '/courseList' })

          })


      }
    }
  }
</script>
<style scoped>
  .ccInfo {
    background: #f5f5f5;
    padding: 20px;
    overflow: hidden;
    border: 1px dashed #DDD;
    margin-bottom: 40px;
    position: relative;
    width: 800px;
    margin: 0 auto 40px;
  }

  .ccInfo img {
    background: #d6d6d6;
    width: 400px;
    height: 278px;
    display: block;
    float: left;
    border: none;
  }

  .ccInfo .main {
    margin-left: 480px;
  }

  .ccInfo .main h2 {
    font-size: 28px;
    margin-bottom: 30px;
    line-height: 1;
    font-weight: normal;
  }

  .ccInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
  }

  .ccInfo .main p {
    margin-bottom: 10px;
    word-wrap: break-word;
    line-height: 24px;
    max-height: 48px;
    overflow: hidden;
  }

  .ccInfo .main h3 {
    left: 500px;
    bottom: 20px;
    line-height: 1;
    font-size: 28px;
    color: #d32f24;
    font-weight: normal;
    position: absolute;
  }
</style>