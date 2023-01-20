package com.toy.toy_springboots.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toy.toy_springboots.dao.SurveyDao;

@Service
public class SurveyService {

    @Autowired
    SurveyDao surveyDao;

    public Object getList(Object dataMap) {
        String sqlMapId = "Survey.selectListByUID";
        Object result = surveyDao.getList(sqlMapId, dataMap);
        return result;
    }
    // public Object insertAndGetList(Object dataMap) {
    // Object result = this.insert(dataMap);
    // result = this.getList(dataMap);
    // return result;
    // }

    // public Object insert(Object dataMap){

    // Object result = surveyDao.insert(sqlMapId, dataMap);
    // return result;
    // }

}
