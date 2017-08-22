package com.wonders.core.dto;

import com.wonders.core.entity.Menu;

import java.util.List;

public class MenuDto extends Menu {

    // 子菜单集合
    private List<MenuDto> childMenus;

    public List<MenuDto> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<MenuDto> childMenus) {
        this.childMenus = childMenus;
    }

    // 是否选中
    private boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
