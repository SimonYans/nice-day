package top.niceday.yan.domain.base;

import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class AuditUser {

    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 所属部门名称
     */
    private String orgName;
    /**
     * 所属部门ID
     */
    private Long sysOrgId;

    /**
     * 角色ID列表
     */
    private List<String> roleNameList;

    private List<String> roleIdList;

    private Integer userType;

}
