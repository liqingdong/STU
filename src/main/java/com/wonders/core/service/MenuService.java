package com.wonders.core.service;

import com.github.pagehelper.PageHelper;
import com.wonders.core.base.BaseService;
import com.wonders.core.dto.MenuDto;
import com.wonders.core.entity.Menu;
import com.wonders.core.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService extends BaseService<Menu,MenuMapper> {
    public List<MenuDto> getTopMenus() {
        return this.mapper.getTopMenus();
    }

    public List<MenuDto> getRoleTopMenus(Long roleId) {
        return this.mapper.getRoleTopMenus(roleId);
    }

    public List<MenuDto> getUserTopMenus(Long userId) {
        return this.mapper.getUserTopMenus(userId);
    }

    public List<MenuDto> getMenusByParentMenuId(Long menuId) {
        return this.mapper.getMenusByParentMenuId(menuId);
    }

    public List<MenuDto> getRoleMenusByParentMenuId(Long roleId, Long menuId) {
        return this.mapper.getRoleMenusByParentMenuId(roleId, menuId);
    }

    public List<MenuDto> getUserMenusByParentMenuId(Long userId, Long menuId) {
        return this.mapper.getUserMenusByParentMenuId(userId, menuId);
    }

    public List<Menu> queryMenusWithPage(Integer pageNo, Integer pageSize, Menu menu) {
        PageHelper.startPage(pageNo, pageSize);
        return this.mapper.queryMenus(menu);
    }
}
