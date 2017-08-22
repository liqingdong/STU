package com.wonders.core.entity;

import com.wonders.core.base.IDEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "t_sys_role")
public class Role extends IDEntity {
    /**
     * 角色代码
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "ROLE_DESC")
    private String roleDesc;

    /**
     * 是否可删除:0-不可删除;1-可删除
     */
    @Column(name = "CAN_DELETE")
    private String canDelete;

    /**
     * 获取角色代码
     *
     * @return ROLE_CODE - 角色代码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色代码
     *
     * @param roleCode 角色代码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色描述
     *
     * @return ROLE_DESC - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    /**
     * 获取是否可删除:0-不可删除;1-可删除
     *
     * @return CAN_DELETE - 是否可删除:0-不可删除;1-可删除
     */
    public String getCanDelete() {
        return canDelete;
    }

    /**
     * 设置是否可删除:0-不可删除;1-可删除
     *
     * @param canDelete 是否可删除:0-不可删除;1-可删除
     */
    public void setCanDelete(String canDelete) {
        this.canDelete = canDelete;
    }

}