package com.toy.toy_springboots.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// @Repository
@Component
public class AttachFileDao { // dao는 공통으로 사용가능
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

}
