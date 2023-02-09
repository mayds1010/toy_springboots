package com.toy.toy_springboots.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainsController {
    @GetMapping({ "/" }) // 관리자,일반사용자 접속가능
    public ModelAndView mains(ModelAndView modelAndView) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) { // 로그인이 되어 있을땐 UserDetails
            String username = ((UserDetails) principal).getUsername();
            String password = ((UserDetails) principal).getPassword();
            System.out.println(((UserDetails) principal).getUsername());
            System.out.println(((UserDetails) principal).getPassword());
            System.out.println(((UserDetails) principal).getAuthorities());
            System.out.println(((UserDetails) principal).isAccountNonExpired());
            System.out.println(((UserDetails) principal).isCredentialsNonExpired());
            System.out.println(((UserDetails) principal).isEnabled());
        } else {
            String username = principal.toString(); // 로그인 안되어 있음 toString

        }

        String viewName = "/WEB-INF/views/main.jsp";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}