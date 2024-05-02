package tech.remote.admin.module.business.typenode.domain.vo;

import lombok.Data;

@Data
public class TypeNodeListVO {
    private Long id;
    private Integer projectType;
    private Long nodeId;
    private String nodeCode;
    private String nodeName;
    private Integer nodeLevel;
    private Integer nodeSort;
}
