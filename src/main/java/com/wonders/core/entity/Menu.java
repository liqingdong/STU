package com.wonders.core.entity;

import com.wonders.core.base.IDEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_sys_menu")
public class Menu extends IDEntity {
    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 菜单编码
     */
    @Column(name = "MENU_CODE")
    private String menuCode;

    /**
     * 菜单图标
     */
    @Column(name = "MENU_ICON")
    private String menuIcon;

    /**
     * 菜单描述
     */
    @Column(name = "MENU_DESC")
    private String menuDesc;

    /**
     * 是否叶子节点0:非叶子节点 1:叶子节点
     */
    @Column(name = "IS_LEAF")
    private String isLeaf;

    /**
     * 菜单级别
     */
    @Column(name = "MENU_LEVEL")
    private Integer menuLevel;

    /**
     * 父菜单id
     */
    @Column(name = "PARENT_MENU_ID")
    private Long parentMenuId;

    /**
     * 菜单排序
     */
    @Column(name = "SORT_NO")
    private Integer sortNo;

    /**
     * 菜单访问的url
     */
    @Column(name = "URL")
    private String url;

    /**
     * 菜单是否可删除 0:不可删除 1:可删除
     */
    @Column(name = "CAN_DELETE")
    private String canDelete;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 创建人
     */
    @Column(name = "CREATE_BY")
    private String createBy;

    /**
     * 更新人
     */
    @Column(name = "UPDATE_BY")
    private String updateBy;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取菜单名称
     *
     * @return MENU_NAME - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单编码
     *
     * @return MENU_CODE - 菜单编码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单编码
     *
     * @param menuCode 菜单编码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 获取菜单图标
     *
     * @return MENU_ICON - 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标
     *
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * 获取菜单描述
     *
     * @return MENU_DESC - 菜单描述
     */
    public String getMenuDesc() {
        return menuDesc;
    }

    /**
     * 设置菜单描述
     *
     * @param menuDesc 菜单描述
     */
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    /**
     * 获取是否叶子节点0:非叶子节点 1:叶子节点
     *
     * @return IS_LEAF - 是否叶子节点0:非叶子节点 1:叶子节点
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    /**
     * 设置是否叶子节点0:非叶子节点 1:叶子节点
     *
     * @param isLeaf 是否叶子节点0:非叶子节点 1:叶子节点
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 获取菜单级别
     *
     * @return MENU_LEVEL - 菜单级别
     */
    public Integer getMenuLevel() {
        return menuLevel;
    }

    /**
     * 设置菜单级别
     *
     * @param menuLevel 菜单级别
     */
    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    /**
     * 获取父菜单id
     *
     * @return PARENT_MENU_ID - 父菜单id
     */
    public Long getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 设置父菜单id
     *
     * @param parentMenuId 父菜单id
     */
    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    /**
     * 获取菜单排序
     *
     * @return SORT_NO - 菜单排序
     */
    public Integer getSortNo() {
        return sortNo;
    }

    /**
     * 设置菜单排序
     *
     * @param sortNo 菜单排序
     */
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    /**
     * 获取菜单访问的url
     *
     * @return URL - 菜单访问的url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单访问的url
     *
     * @param url 菜单访问的url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取菜单是否可删除 0:不可删除 1:可删除
     *
     * @return CAN_DELETE - 菜单是否可删除 0:不可删除 1:可删除
     */
    public String getCanDelete() {
        return canDelete;
    }

    /**
     * 设置菜单是否可删除 0:不可删除 1:可删除
     *
     * @param canDelete 菜单是否可删除 0:不可删除 1:可删除
     */
    public void setCanDelete(String canDelete) {
        this.canDelete = canDelete;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_DATE - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_DATE - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取创建人
     *
     * @return CREATE_BY - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取更新人
     *
     * @return UPDATE_BY - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}