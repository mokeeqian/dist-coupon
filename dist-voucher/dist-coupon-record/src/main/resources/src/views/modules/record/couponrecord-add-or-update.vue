<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="卷模板ID" prop="metaId">
      <el-input v-model="dataForm.metaId" placeholder="卷模板ID"></el-input>
    </el-form-item>
    <el-form-item label="用户ID" prop="userId">
      <el-input v-model="dataForm.userId" placeholder="用户ID"></el-input>
    </el-form-item>
    <el-form-item label="订单号：仅在使用时才和订单关联" prop="businessId">
      <el-input v-model="dataForm.businessId" placeholder="订单号：仅在使用时才和订单关联"></el-input>
    </el-form-item>
    <el-form-item label="券状态：0-未使用，1-已使用，2-已过期，3-已冻结" prop="status">
      <el-input v-model="dataForm.status" placeholder="券状态：0-未使用，1-已使用，2-已过期，3-已冻结"></el-input>
    </el-form-item>
    <el-form-item label="领取日期" prop="receiveTime">
      <el-input v-model="dataForm.receiveTime" placeholder="领取日期"></el-input>
    </el-form-item>
    <el-form-item label="截止日期" prop="endTime">
      <el-input v-model="dataForm.endTime" placeholder="截止日期"></el-input>
    </el-form-item>
    <el-form-item label="消费日期" prop="consumeTime">
      <el-input v-model="dataForm.consumeTime" placeholder="消费日期"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          metaId: '',
          userId: '',
          businessId: '',
          status: '',
          receiveTime: '',
          endTime: '',
          consumeTime: ''
        },
        dataRule: {
          metaId: [
            { required: true, message: '卷模板ID不能为空', trigger: 'blur' }
          ],
          userId: [
            { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          businessId: [
            { required: true, message: '订单号：仅在使用时才和订单关联不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '券状态：0-未使用，1-已使用，2-已过期，3-已冻结不能为空', trigger: 'blur' }
          ],
          receiveTime: [
            { required: true, message: '领取日期不能为空', trigger: 'blur' }
          ],
          endTime: [
            { required: true, message: '截止日期不能为空', trigger: 'blur' }
          ],
          consumeTime: [
            { required: true, message: '消费日期不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/record/couponrecord/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.metaId = data.couponRecord.metaId
                this.dataForm.userId = data.couponRecord.userId
                this.dataForm.businessId = data.couponRecord.businessId
                this.dataForm.status = data.couponRecord.status
                this.dataForm.receiveTime = data.couponRecord.receiveTime
                this.dataForm.endTime = data.couponRecord.endTime
                this.dataForm.consumeTime = data.couponRecord.consumeTime
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/record/couponrecord/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'metaId': this.dataForm.metaId,
                'userId': this.dataForm.userId,
                'businessId': this.dataForm.businessId,
                'status': this.dataForm.status,
                'receiveTime': this.dataForm.receiveTime,
                'endTime': this.dataForm.endTime,
                'consumeTime': this.dataForm.consumeTime
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
