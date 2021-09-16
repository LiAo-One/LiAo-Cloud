<template>
  <el-row>
    <el-col :span="24">
      <el-select v-model="copyValue" filterable clearable placeholder="请选择" @change="updateValue"
                 :style="'width:100%;min-width:250px'">
        <el-option
          v-for="item in dataList"
          :key="item.dnId"
          :label="item.dnName"
          :value="item.dnId">
        </el-option>
        <el-col :span="24">
          <div style="float: right;margin-right:10px;padding-bottom: 10px">
            <el-pagination
              @current-change="handleCurrentChange"
              :current-page="pageInfo.pageNum"
              :page-size="pageInfo.pageSize"
              layout="total, prev, pager, next"
              :total="pageInfo.total">
            </el-pagination>
          </div>
        </el-col>
      </el-select>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "SelectPage",
  props: {
    //绑定值
    value: String,
    //下拉列表
    dataList: Array,
    //option的label在列表中对应的key
    labelKey: String,
    //option的value在列表中对应的key
    valueKey: String,
    //分页信息
    pageInfo: Object
  },
  methods: {
    //更新值
    updateValue() {
      this.$emit('updateValue', this.copyValue);
    },
    //翻页
    handleCurrentChange(val) {
      this.$emit('selectPageChange', val);
    },
  },
  data() {
    return {
      copyValue: undefined
    };
  }
}
</script>

<style scoped>

</style>
