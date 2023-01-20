package com.toy.toy_springboots.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.toy.toy_springboots.service.SurveyService;

@Controller
@RequestMapping(value = "/survey")
public class SurveyController {

    @Autowired
    SurveyService surveyService;

    // insert,delete,update
    @RequestMapping(value = { "/list", "/", "" }, method = RequestMethod.GET)
    public ModelAndView list(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {
        Object resultMap = surveyService.getList(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("survey/admin_userlist");
        return modelAndView;
    }

    // @RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
    // public ModelAndView insert(@RequestParam Map<String, Object> params,
    // ModelAndView modelAndView) {
    // Object resultMap = surveyService.insertAndGetList(params);
    // modelAndView.addObject("resultMap", resultMap);

    // modelAndView.setViewName("survey/admin_userlist");
    // return modelAndView;
    // }

}
