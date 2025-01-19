package tech.remote.admin.constant;

import tech.remote.base.constant.SwaggerTagConst;

/**
 * swagger
 *
 * @Author 远游工作室:罗伊
 * @Date 2022-01-07 18:59:22
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */
public class AdminSwaggerTagConst extends SwaggerTagConst {

    public static class BaseManagement {
        public static final String CUSTOMER_MANAGEMENT = "基础管理-客户管理";
        public static final String THIRD_PARTY = "基础管理-三方管理";
    }

    public static class ProjectManagement {
        public static final String SYSTEM_CERTIFICATION = "项目管理-体系认证";
        public static final String MEASUREMENT = "项目管理-仪器计量";
    }

    public static class Business {
        public static final String MANAGER_CATEGORY = "ERP进销存-分类管理";

        public static final String MANAGER_GOODS = "ERP进销存-商品管理";

        public static final String OA_BANK = "OA办公-银行卡信息";

        public static final String OA_ENTERPRISE = "OA办公-企业";

        public static final String OA_INVOICE = "OA办公-发票信息";

        public static final String OA_NOTICE = "OA办公-通知公告";
    }


    public static class System {

        public static final String SYSTEM_LOGIN = "系统-员工登录";

        public static final String SYSTEM_EMPLOYEE = "系统-员工管理";

        public static final String SYSTEM_DEPARTMENT = "系统-部门管理";

        public static final String SYSTEM_MENU = "系统-菜单";

        public static final String SYSTEM_DATA_SCOPE = "系统-系统-数据范围";

        public static final String SYSTEM_ROLE = "系统-角色";

        public static final String SYSTEM_ROLE_DATA_SCOPE = "系统-角色-数据范围";

        public static final String SYSTEM_ROLE_EMPLOYEE = "系统-角色-员工";

        public static final String SYSTEM_ROLE_MENU = "系统-角色-菜单";

    }


}
