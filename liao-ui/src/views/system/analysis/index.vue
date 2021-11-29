<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="语音类型" prop="vtype">
        <el-select v-model="queryParams.vtype" placeholder="请选择语音类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="analysisList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
  <el-table-column label="语音" key="vvoice" align="center" prop="vvoice" />
  <el-table-column label="语音描述" key="vdesc" align="center" prop="vdesc" />
  <el-table-column label="语音分析" key="vanalysis" align="center" prop="vanalysis" />
  <el-table-column label="语音检索" key="vsel" align="center" prop="vsel" />
<!--  <el-table-column label="语音类型" key="vtype" align="center" prop="vtype" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改海量语音分析数据检索对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="语音" prop="vvoice">
          <el-input v-model="form.vvoice" type="textarea" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="语音描述" prop="vdesc">
          <el-input v-model="form.vdesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="语音分析" prop="vanalysis">
          <el-input v-model="form.vanalysis" type="textarea" placeholder="请输入内容" />
        </el-form-item>

        <el-form-item label="语音检索" prop="vsel">
          <el-input v-model="form.vsel" type="textarea" placeholder="请输入内容" />
        </el-form-item>

<!--        <el-form-item label="语音类型" prop="vtype">
          <el-input v-model="form.vtype" type="textarea" placeholder="语音类型" />
        </el-form-item>-->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAnalysis, getAnalysis, delAnalysis, addAnalysis, updateAnalysis, exportAnalysis } from "@/api/system/analysis";

export default {
  name: "Analysis",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 海量语音分析数据检索表格数据
      analysisList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vvoice: null,
        vdesc: null,
        vanalysis: null,
        vsel: null,
        vtype: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询海量语音分析数据检索列表 */
    getList() {
      this.loading = true;
      listAnalysis(this.queryParams).then(response => {
        this.analysisList = response.data.records;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        vId: null,
        vvoice: null,
        vdesc: null,
        vanalysis: null,
        vsel: null,
        vtype: null,
        version: null,
        deleted: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.vId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加海量语音分析数据检索";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const vId = row.vId || this.ids
      getAnalysis(vId).then(response => {
        this.form = response.data[0];
        this.open = true;
        this.title = "修改海量语音分析数据检索";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.vId != null) {
            updateAnalysis(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnalysis(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const vIds = row.vId || this.ids;
      this.$confirm('是否确认删除海量语音分析数据检索编号为"' + vIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAnalysis(vIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    }
  }
};
</script>
