package com.toy.toy_springboots.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toy.toy_springboots.dao.UserListDao;
import com.toy.toy_springboots.service.UserListService;

@Controller
public class MainController {

    @Autowired
    UserListService userListService;

    @RequestMapping(value = { "/main", "/", "" }, method = RequestMethod.GET)
    public ModelAndView main(ModelAndView modelAndView) {

        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ModelAndView userList(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        Object resultMap = userListService.getList(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("user/userlist");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam Map<String, Object> params,
            @PathVariable String userId, ModelAndView modelAndView) {
        params.put("USERS_UID", userId);
        Object resultMap = userListService.getOne(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("user/useredit");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam Map<String, Object> params,
            @PathVariable String userId, ModelAndView modelAndView) {
        params.put("USERS_UID", userId);
        Object resultMap = userListService.deleteAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);
        modelAndView.setViewName("user/userlist");
        return modelAndView;

    }

    public void listPagination(@RequestParam Map<String, Object> params,
            @PathVariable String currentPage, ModelAndView modelAndView) {
        params.put("currentPage", Integer.parseInt(currentPage));
        params.put("pageScale", 10);
    }
}
