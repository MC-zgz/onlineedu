<template>

  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px" style="width: 700px;">

      <el-form-item label="课程标题">
        <el-input v-model="courseInfoForm.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写" />
      </el-form-item>

      <!-- 所属分类：级联下拉列表 -->
      <!-- 一级分类 -->
      <el-form-item label="课程类别">
        <el-select @change="getTwoByOneId(courseInfoForm.subjectParentId)" v-model="courseInfoForm.subjectParentId"
          placeholder="一级分类">
          <el-option v-for="subject in oneSubjectList" :key="subject.id" :label="subject.title" :value="subject.id" />
        </el-select>

        <!-- 二级分类 -->
        <el-select v-model="courseInfoForm.subjectId" placeholder="二级分类">
          <el-option v-for="subject in twoSubjectList" :key="subject.value" :label="subject.title"
            :value="subject.id" />
        </el-select>
      </el-form-item>




      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfoForm.teacherId" placeholder="请选择">
          <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id" />
        </el-select>
      </el-form-item>


      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfoForm.lessonNum" controls-position="right"
          placeholder="请填写课程的总课时数" />
      </el-form-item>

      <!-- 课程简介  -->
      <!-- <el-form-item label="课程简介">
        <el-input v-model="courseInfoForm.description"></el-input>
      </el-form-item> -->

      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfoForm.description" />
      </el-form-item>

      <!-- 课程封面-->
      <el-form-item label="课程封面">

        <el-upload :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/ossservice/fileoss/uploadFile'" class="avatar-uploader">
          <!-- <img :src="courseInfoForm.cover"> -->
          <img v-if="courseInfoForm.cover" :src="courseInfoForm.cover" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

      </el-form-item>


      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfoForm.price" controls-position="right" placeholder="免费课程请设置为0元" /> 元
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import course from '@/api/course'
  import teacher from '@/api/teacher'
  import subject from '@/api/subject'
  import Tinymce from '@/components/Tinymce'
  export default {
    components: { Tinymce },
    data() {
      return {
        courseId: '',
        courseInfoForm: {
          subjectId: '',
          cover: ''
        },
        teacherList: [],//讲师下拉选择框
        oneSubjectList: [],//课程一级分类
        twoSubjectList: [],//课程二级分类
        saveBtnDisabled: false, // 保存按钮是否禁用
        BASE_API: process.env.BASE_API, // 接口API地址
      }
    },

    created() {

      if (this.$route.params && this.$route.params.id) {
        this.courseId = this.$route.params.id;
        console.log(this.courseId);
        this.getCourseInfoByCourseId();
      } else {
        this.getAllSubjectInfo();
      }
      this.getTeacherList();
    },

    methods: {

      //数据回显初始化
      getCourseInfoByCourseId() {
        course.getCourseInfoById(this.courseId)
          .then(res => {
            this.courseInfoForm = res.data.courseInfoForm;
            //初始化一级分类
            subject.getAllSubject().then(res => {
              this.oneSubjectList = res.data.allSubjectList;
              //初始化二级分类
              for (let i = 0; i < this.oneSubjectList.length; i++) {
                let oneSubject = this.oneSubjectList[i];
                if (oneSubject.id === this.courseInfoForm.subjectParentId) {
                  this.twoSubjectList = oneSubject.children;
                }
              }
            });
          })
      },

      //讲师下拉框列表
      getTeacherList() {
        teacher.getAllTeacher().then(res => {
          this.teacherList = res.data.list;
        })
      },

      //初始化一级分类
      getAllSubjectInfo() {
        subject.getAllSubject().then(res => {
          this.oneSubjectList = res.data.allSubjectList;
        })
      },
      //根据一级id查询二级分类
      getTwoByOneId(oneId) {
        // console.log("onId:" + oneId);
        for (let i = 0; i < this.oneSubjectList.length; i++) {
          let oneSubject = this.oneSubjectList[i];
          if (oneSubject.id === oneId) {
            this.twoSubjectList = oneSubject.children;
            this.courseInfoForm.subjectId = '';
          }
        }
      },
      //上传成功后方法
      handleAvatarSuccess(res, file) {
        console.log(res)// 上传响应
        console.log(URL.createObjectURL(file.raw))// base64编码
        this.courseInfoForm.cover = res.data.url
      },
      //校验课程封面
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
        const isLt2M = file.size / 1024 / 1024 < 2;
        var isIMG = true;
        if (!isJPG) {
          if (!isPNG) {
            this.$message.error('上传头像图片只能是 JPG 或者 PNG 格式!')
            isIMG = false;
          }
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!')
        }
        return isIMG && isLt2M
      },

      //添加或者修改后跳转下一步
      next() {
        if (this.courseInfoForm.id) {
          console.log("执行修改方法");
          this.updateCourseInfo();
        } else {
          console.log("添加方法");
          this.savaCourseInfo();
        }
      },
      //添加课程信息
      savaCourseInfo() {
        course.addCourseInfo(this.courseInfoForm).then(res => {
          this.$message({
            type: "success",
            message: "添加成功！"
          });
          this.courseId = res.data.courseId;
          this.$router.push({ path: `/course/chapter/${this.courseId}` })
        });
      },
      //修改课程信息
      updateCourseInfo() {
        course.updateCourseInfo(this.courseInfoForm).then(res => {
          this.$message({
            type: "success",
            message: "修改成功！"
          });
          // this.courseId = res.data.courseId;
          this.$router.push({ path: `/course/chapter/${this.courseId}` })
        });
      },

    }
  }
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .tinymce-container {
    line-height: 29px;
  }
</style>