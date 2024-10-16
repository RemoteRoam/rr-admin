package tech.remote.base.module.support.codegenerator.service.variable.backend;

import com.google.common.base.CaseFormat;
import tech.remote.base.common.util.SmartEnumUtil;
import tech.remote.base.module.support.codegenerator.constant.CodeDeleteEnum;
import tech.remote.base.module.support.codegenerator.domain.form.CodeGeneratorConfigForm;
import tech.remote.base.module.support.codegenerator.domain.model.CodeInsertAndUpdateField;
import tech.remote.base.module.support.codegenerator.service.variable.CodeGenerateBaseVariableService;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author 远游工作室-yy
 * @Date 2022/9/29 17:20:41
 *  
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */

public class ControllerVariableService extends CodeGenerateBaseVariableService {

    @Override
    public boolean isSupport(CodeGeneratorConfigForm form) {
        return true;
    }

    @Override
    public Map<String, Object> getInjectVariablesMap(CodeGeneratorConfigForm form) {
        Map<String, Object> variablesMap = new HashMap<>();

        List<CodeInsertAndUpdateField> updateFieldList = form.getInsertAndUpdate().getFieldList().stream().filter(e -> Boolean.TRUE.equals(e.getInsertFlag())).collect(Collectors.toList());

        variablesMap.put("packageName", form.getBasic().getJavaPackageName() + ".controller");

        List<String> packageList = getPackageList(updateFieldList, form);
        variablesMap.put("importPackageList", packageList);

        return variablesMap;
    }


    public List<String> getPackageList(List<CodeInsertAndUpdateField> fields, CodeGeneratorConfigForm form) {
        if (CollectionUtils.isEmpty(fields)) {
            return new ArrayList<>();
        }

        HashSet<String> packageSet = new HashSet<>();

        //1、javabean相关的包
        packageSet.addAll(getJavaBeanImportClass(form).stream().filter(e -> !e.contains("Entity;")).collect(Collectors.toList()));

        //2、其他包
        if (form.getDeleteInfo().getIsSupportDelete()) {

            CodeDeleteEnum codeDeleteEnum = SmartEnumUtil.getEnumByValue(form.getDeleteInfo().getDeleteEnum(), CodeDeleteEnum.class);
            if (codeDeleteEnum == CodeDeleteEnum.BATCH || codeDeleteEnum == CodeDeleteEnum.SINGLE_AND_BATCH) {
                //2、批量删除的话，要导入ValidateList
                packageSet.add("import tech.remote.base.common.domain.ValidateList;");
            }

            if (codeDeleteEnum == CodeDeleteEnum.SINGLE || codeDeleteEnum == CodeDeleteEnum.SINGLE_AND_BATCH) {
                //3、单个删除的话，要导入 @PathVariable
                packageSet.add("import org.springframework.web.bind.annotation.PathVariable;");
                packageSet.add("import org.springframework.web.bind.annotation.GetMapping;");
            }
        }

        packageSet.add("import " + form.getBasic().getJavaPackageName() + ".service." + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, form.getBasic().getModuleName()) + "Service;");

        // 排序一下
        ArrayList<String> packageList = new ArrayList<>(packageSet);
        Collections.sort(packageList);
        return packageList;
    }

}
