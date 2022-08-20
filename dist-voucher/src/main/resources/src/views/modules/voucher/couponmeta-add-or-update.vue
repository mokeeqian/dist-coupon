<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="标题" prop="title">
      <el-input v-model="dataForm.title" placeholder="标题"></el-input>
    </el-form-item>
    <el-form-item label="类型：满减、直减、折扣" prop="type">
      <el-input v-model="dataForm.type" placeholder="类型：满减、直减、折扣"></el-input>
    </el-form-item>
    <el-form-item label="有效开始时间" prop="validStartTime">
      <el-input v-model="dataForm.validStartTime" placeholder="有效开始时间"></el-input>
    </el-form-item>
    <el-form-item label="有效截止时间" prop="validEndTime">
      <el-input v-model="dataForm.validEndTime" placeholder="有效截止时间"></el-input>
    </el-form-item>
    <el-form-item label="状态：0-不可用， 1-可用" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态：0-不可用， 1-可用"></el-input>
    </el-form-item>
    <el-form-item label="库存量" prop="totalStock">
      <el-input v-model="dataForm.totalStock" placeholder="库存量"></el-input>
    </el-form-item>
    <el-form-item label="领取规则" prop="receiveRule">
      <el-input v-model="dataForm.receiveRule" placeholder="领取规则"></el-input>
    </el-form-item>
    <el-form-item label="消费规则：0-满减 1-直降，2-折扣" prop="consumeRule">
      <el-input v-model="dataForm.consumeRule" placeholder="消费规则：0-满减 1-直降，2-折扣"></el-input>
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
          title: '',
          type: '',
          validStartTime: '',
          validEndTime: '',
          status: '',
          totalStock: '',
          receiveRule: '',
          consumeRule: ''
        },
        dataRule: {
          title: [
            { required: true, message: '标题不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '类型：满减、直减、折扣不能为空', trigger: 'blur' }
          ],
          validStartTime: [
            { required: true, message: '有效开始时间不能为空', trigger: 'blur' }
          ],
          validEndTime: [
            { required: true, message: '有效截止时间不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态：0-不可用， 1-可用不能为空', trigger: 'blur' }
          ],
          totalStock: [
            { required: true, message: '库存量不能为空', trigger: 'blur' }
          ],
          receiveRule: [
            { required: true, message: '领取规则不能为空', trigger: 'blur' }
          ],
          consumeRule: [
            { required: true, message: '消费规则：0-满减 1-直降，2-折扣不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/voucher/couponmeta/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.title = data.couponMeta.title
                this.dataForm.type = data.couponMeta.type
                this.dataForm.validStartTime = data.couponMeta.validStartTime
                this.dataForm.validEndTime = data.couponMeta.validEndTime
                this.dataForm.status = data.couponMeta.status
                this.dataForm.totalStock = data.couponMeta.totalStock
                this.dataForm.receiveRule = data.couponMeta.receiveRule
                this.dataForm.consumeRule = data.couponMeta.consumeRule
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
              url: this.$http.adornUrl(`/voucher/couponmeta/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'title': this.dataForm.title,
                'type': this.dataForm.type,
                'validStartTime': this.dataForm.validStartTime,
                'validEndTime': this.dataForm.validEndTime,
                'status': this.dataForm.status,
                'totalStock': this.dataForm.totalStock,
                'receiveRule': this.dataForm.receiveRule,
                'consumeRule': this.dataForm.consumeRule
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
