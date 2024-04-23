package tech.remote.base.module.support.codegenerator.service.variable.front;

import tech.remote.base.module.support.codegenerator.domain.form.CodeGeneratorConfigForm;
import tech.remote.base.module.support.codegenerator.service.variable.CodeGenerateBaseVariableService;

import java.util.*;

/**
 * @Author 远游工作室-yy
 * @Date 2022/9/29 17:20:41
 *
 *
 * @Copyright  <a href="http://www.remotenomad.tech">远游工作室</a>
 */

public class ApiVariableService extends CodeGenerateBaseVariableService {

    @Override
    public boolean isSupport(CodeGeneratorConfigForm form) {
        return true;
    }

    @Override
    public Map<String, Object> getInjectVariablesMap(CodeGeneratorConfigForm form) {
        Map<String, Object> variablesMap = new HashMap<>();

        return variablesMap;
    }
}
