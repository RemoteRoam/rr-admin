package tech.remote.base.module.support.codegenerator.service.variable.backend;

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

public class DaoVariableService extends CodeGenerateBaseVariableService {

    @Override
    public boolean isSupport(CodeGeneratorConfigForm form) {
        return true;
    }

    @Override
    public Map<String, Object> getInjectVariablesMap(CodeGeneratorConfigForm form) {
        Map<String, Object> variablesMap = new HashMap<>();

        List<CodeInsertAndUpdateField> updateFieldList = form.getInsertAndUpdate().getFieldList().stream().filter(e -> Boolean.TRUE.equals(e.getInsertFlag())).collect(Collectors.toList());
        List<String> packageList = getPackageList(updateFieldList, form);

        variablesMap.put("packageName", form.getBasic().getJavaPackageName() + ".dao" );
        variablesMap.put("importPackageList", packageList);

        return variablesMap;
    }


    public List<String> getPackageList(List<CodeInsertAndUpdateField> fields, CodeGeneratorConfigForm form) {
        if (CollectionUtils.isEmpty(fields)) {
            return new ArrayList<>();
        }

        HashSet<String> packageSet = new HashSet<>();

        //1、javabean相关的包
        packageSet.addAll(getJavaBeanImportClass(form).stream().filter( e-> e.contains("QueryForm;") || e.contains("VO;")|| e.contains("Entity;")).collect(Collectors.toList()));

        //2、util
        packageSet.add("import java.util.List;");

        //3、 排序一下
        ArrayList<String> packageList = new ArrayList<>(packageSet);
        Collections.sort(packageList);
        return packageList;
    }

}
