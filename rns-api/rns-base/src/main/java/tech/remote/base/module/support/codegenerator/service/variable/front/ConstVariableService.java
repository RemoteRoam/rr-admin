package tech.remote.base.module.support.codegenerator.service.variable.front;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.base.CaseFormat;
import tech.remote.base.common.util.SmartStringUtil;
import tech.remote.base.module.support.codegenerator.domain.form.CodeGeneratorConfigForm;
import tech.remote.base.module.support.codegenerator.domain.model.CodeField;
import tech.remote.base.module.support.codegenerator.service.variable.CodeGenerateBaseVariableService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 远游工作室-yy
 * @Date 2022/9/29 17:20:41
 *
 *  
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */

public class ConstVariableService extends CodeGenerateBaseVariableService {

    @Override
    public boolean isSupport(CodeGeneratorConfigForm form) {
        return true;
    }

    @Override
    public Map<String, Object> getInjectVariablesMap(CodeGeneratorConfigForm form) {
        Map<String, Object> variablesMap = new HashMap<>();
        List<Map<String, Object>> enumList = new ArrayList<>();
        List<CodeField> enumFiledList = form.getFields().stream().filter(e -> SmartStringUtil.isNotBlank(e.getEnumName())).collect(Collectors.toList());
        for (CodeField codeField : enumFiledList) {
            Map<String, Object> beanToMap = BeanUtil.beanToMap(codeField);
            String upperUnderscoreEnum = CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, codeField.getEnumName());
            beanToMap.put("upperUnderscoreEnum", upperUnderscoreEnum);
            enumList.add(beanToMap);
        }
        variablesMap.put("enumList", enumList);
        return variablesMap;
    }
}
