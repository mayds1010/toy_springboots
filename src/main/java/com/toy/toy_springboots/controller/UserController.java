package com.toy.toy_springboots.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toy.toy_springboots.service.UserListService;

@Controller
@RequestMapping(value = "/survey")
public class UserController {

    @Autowired
    UserListService userListService;

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam Map<String, Object> params,
            ModelAndView modelAndView) {
        Object resultMap = userListService.updateAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("user/userlist");
        return modelAndView;
    }

    @RequestMapping(value = { "/form" }, method = RequestMethod.GET)
    public ModelAndView form(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {

        modelAndView.setViewName("user/useredit"); // edit화면 재사용
        return modelAndView;
    }

    @RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
    public ModelAndView insert(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        Object resultMap = userListService.insertAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("user/userlist");
        return modelAndView;
    }
    // @RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
    // public ModelAndView edit(@RequestParam Map<String, Object> params,
    // @PathVariable String userId, ModelAndView modelAndView) {
    // params.put("USERS_UID", userId);
    // Object resultMap = userListService.getOne(params);
    // modelAndView.addObject("resultMap", resultMap);

    // modelAndView.setViewName("user/useredit");
    // return modelAndView;
    // }

    // @RequestMapping(value = "/update", method = RequestMethod.POST)
    // public ModelAndView update(@RequestParam Map<String, Object> params,
    // ModelAndView modelAndView) {
    // Object resultMap = userListService.updateAndGetList(params);
    // modelAndView.addObject("resultMap", resultMap);

    // modelAndView.setViewName("user/userlist");
    // return modelAndView;
    // }

}