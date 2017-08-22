package com.wonders.web.security;

import com.wonders.core.entity.User;
import com.wonders.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class myUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Collection<GrantedAuthority> auths = new ArrayList<>();

        if (user.getStatus().equals("0")) {
            return new MyUserDetail(username, user.getPassword(), true, true, true, false, auths);
        }

        /**
         * 后期可具体实现菜单的具体权限控制
         * @see DefaultSecurityMetadataSource#getAttributes(Object)
         */
//        List<Role> roles = roleService.queryRolesByUserName(username);
//            GrantedAuthority authority = new SimpleGrantedAuthority(role.getId().toString());
//        for (Role role : roles){
//            auths.add(authority);
//        }

        auths.add(new SimpleGrantedAuthority("ROLE_ALL"));

        return new MyUserDetail(username, user.getPassword(), true, true, true, true, auths,user);
    }
}
