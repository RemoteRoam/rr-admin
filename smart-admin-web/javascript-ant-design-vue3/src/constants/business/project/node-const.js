/**
 * 节点常量，对应t_node表
 */

// 提取的数据
const data = [
  { id: 1, node_code: "first_payment", node_name: "确认首款" },
  { id: 2, node_code: "create_task", node_name: "新建任务" },
  { id: 3, node_code: "enter_contract", node_name: "录入合同" },
  { id: 4, node_code: "send_data", node_name: "资料发送" },
  { id: 5, node_code: "receive_data", node_name: "资料接收" },
  { id: 6, node_code: "pay_experiment_fee", node_name: "实验费付款" },
  { id: 7, node_code: "assign_task", node_name: "下达任务" },
  { id: 8, node_code: "estimate_completion", node_name: "预计完成" },
  { id: 9, node_code: "experiment_check", node_name: "实验检测" },
  { id: 10, node_code: "lab_report", node_name: "试验室上报" },
  { id: 11, node_code: "self_declaration", node_name: "自我声明" },
  { id: 12, node_code: "factory_audit_task", node_name: "工厂审核任务" },
  { id: 13, node_code: "factory_audit", node_name: "工厂审核" },
  { id: 14, node_code: "non_conformity_correction", node_name: "不符合项整改" },
  { id: 15, node_code: "submit_certification_fee", node_name: "交认证费" },
  { id: 16, node_code: "certificate", node_name: "证书" },
  { id: 17, node_code: "final_payment", node_name: "确认尾款" },
  { id: 18, node_code: "invoice", node_name: "开票" },
  { id: 19, node_code: "archive", node_name: "归档" },
  { id: 20, node_code: "mail", node_name: "邮寄" },
  { id: 21, node_code: "submit_data", node_name: "资料上报" },
  { id: 22, node_code: "review_plan", node_name: "审核计划" },
  { id: 23, node_code: "submit_paper", node_name: "交卷" },
  { id: 24, node_code: "assessment_date", node_name: "评定日期" },
  { id: 25, node_code: "rectification_date", node_name: "整改日期" },
  { id: 26, node_code: "measurement_completion", node_name: "计量完成" },
  { id: 27, node_code: "receive_payment", node_name: "收款" },
  { id: 28, node_code: "sampling_test", node_name: "抽检实验" },
  { id: 29, node_code: "pre_data_transfer", node_name: "前期资料传递" },
  { id: 30, node_code: "system_file_transfer", node_name: "体系文件传递" },
];

// 定义常量
const NODE_CONST = {};
data.forEach(item => {
  NODE_CONST[item.node_code] = item.id;
});

// 导出常量
export default NODE_CONST;
