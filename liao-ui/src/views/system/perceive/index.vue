<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="网关" prop="pgate">
        <el-input
          v-model="queryParams.pgate"
          placeholder="请输入网关"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="片区编号" prop="parea">
        <el-input
          v-model="queryParams.parea"
          placeholder="请输入片区编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备名称" prop="pname">
        <el-input
          v-model="queryParams.pname"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编号" prop="pdeviceId">
        <el-input
          v-model="queryParams.pdeviceId"
          placeholder="请输入设备编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="punit">
        <el-input
          v-model="queryParams.punit"
          placeholder="请输入单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备类型" prop="ptype">
        <el-input
          v-model="queryParams.ptype"
          placeholder="请输入单位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="perceiveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="网关" key="pgate" align="center" prop="pgate"/>
      <el-table-column label="片区编号" key="parea" align="center" prop="parea"/>
      <el-table-column label="设备名称" key="pname" align="center" prop="pname"/>
      <el-table-column label="设备编号" key="pdeviceId" align="center" prop="pdeviceId"/>
      <el-table-column label="离散变量 非离散变量" key="pop" align="center" prop="pop"/>
      <el-table-column label="变量数值" key="pvalue" align="center" prop="pvalue"/>
      <el-table-column label="单位" key="punit" align="center" prop="punit"/>
      <el-table-column label="设备类型 0：传感器 2：控制设备" key="ptype" align="center" prop="ptype"/>
      <el-table-column label="备注" key="remark" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
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

    <!-- 添加或修改设备数据
对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网关" prop="pgate">
          <el-input v-model="form.pgate" placeholder="请输入网关"/>
        </el-form-item>

        <el-form-item label="片区编号" prop="parea">
          <el-input v-model="form.parea" placeholder="请输入片区编号"/>
        </el-form-item>

        <el-form-item label="设备名称" prop="pname">
          <el-input v-model="form.pname" placeholder="请输入设备名称"/>
        </el-form-item>

        <el-form-item label="设备编号" prop="pdeviceId">
          <el-input v-model="form.pdeviceId" placeholder="请输入设备编号"/>
        </el-form-item>

        <el-form-item label="离散变量 非离散变量" prop="pop">
          <el-input v-model="form.pop" placeholder="请输入离散变量 非离散变量"/>
        </el-form-item>

        <el-form-item label="变量数值" prop="pvalue">
          <el-input v-model="form.pvalue" placeholder="请输入变量数值"/>
        </el-form-item>

        <el-form-item label="单位" prop="punit">
          <el-input v-model="form.punit" placeholder="请输入单位"/>
        </el-form-item>

        <el-form-item label="设备类型 0：传感器 2：控制设备" prop="ptype">
          <el-select v-model="form.ptype" placeholder="请选择设备类型 0：传感器 2：控制设备">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>

        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listPerceive,
  getPerceive,
  delPerceive,
  addPerceive,
  updatePerceive,
  exportPerceive
} from "@/api/system/perceive";

export default {
  name: "Perceive",
  components: {},
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
      // 设备数据表格数据
      perceiveList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pgate: null,
        parea: null,
        pname: null,
        pdeviceId: null,
        pop: null,
        pvalue: null,
        punit: null,
        ptype: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备数据
     列表 */
    getList() {
      this.loading = true;
      listPerceive(this.queryParams).then(response => {
        this.perceiveList = response.data.records;
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
        pId: null,
        pgate: null,
        parea: null,
        pname: null,
        pdeviceId: null,
        pop: null,
        pvalue: null,
        punit: null,
        ptype: null,
        remark: null,
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
      this.ids = selection.map(item => item.pId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pId = row.pId || this.ids
      getPerceive(pId).then(response => {
        this.form = response.data[0];
        this.open = true;
        this.title = "修改设备数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pId != null) {
            updatePerceive(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPerceive(this.form).then(response => {
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
      const pIds = row.pId || this.ids;
      this.$confirm('是否确认删除设备数据编号为"' + pIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delPerceive(pIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    }
  }
};
</script>
