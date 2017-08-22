package com.wonders.core.mapper;

import com.wonders.core.base.MapperExtension;
import com.wonders.core.dto.MenuDto;
import com.wonders.core.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends MapperExtension<Menu> {
    
    List<MenuDto> getTopMenus();

    List<MenuDto> getRoleTopMenus(Long roleId);

    List<MenuDto> getUserTopMenus(Long userId);

    List<MenuDto> getMenusByParentMenuId(@Param("menuId") Long menuId);

    List<MenuDto> getRoleMenusByParentMenuId(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    List<MenuDto> getUserMenusByParentMenuId(@Param("userId") Long userId, @Param("menuId") Long menuId);

    List<Menu> queryMenus(Menu menu);
}