package com.wonders.web.controller;

import com.wonders.core.dto.MenuDto;
import com.wonders.core.entity.User;
import com.wonders.core.service.MenuService;
import com.wonders.core.utils.MatrixCodeUtil;
import com.wonders.core.utils.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/index")
    public void index(Model model) throws Exception {
        User user = SecurityContextUtil.getCurrentUser();
        List<MenuDto> menus = menuService.getUserTopMenus(user.getId());

        for (MenuDto topMenu : menus) {
            List<MenuDto> childMenus = menuService.getUserMenusByParentMenuId(user.getId(), topMenu.getId());
            topMenu.setChildMenus(childMenus);
        }

        model.addAttribute("menus", menus);
    }

    @RequestMapping("/welcome")
    public void welcome(Model model) throws Exception {
    }

    @ResponseBody
    @RequestMapping(value = "/randomCode")
    public MatrixCodeUtil.RandomCodeVo createRandomCode(Integer width, Integer height) throws Exception {
        return MatrixCodeUtil.createRandomCode(width, height);
    }

    @ResponseBody
    @RequestMapping("/test")
    public String test(Model model) throws Exception {
        return "我很成功";
    }
}
