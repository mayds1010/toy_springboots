package com.toy.toy_springboots.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toy.toy_springboots.dao.UserListDao;
import com.toy.toy_springboots.utils.Paginations;

@Service
public class UserListService {

    @Autowired
    UserListDao userListDao;

    public Object getList(Object dataMap) {
        String sqlMapId = "UserList.selectFromUsers_List";
        Object result = userListDao.getList(sqlMapId, dataMap);
        return result;
    }

    public Object getOne(Object dataMap) {
        String sqlMapId = "UserList.selectByUID";
        Object result = userListDao.getOne(sqlMapId, dataMap);
        return result;

    }

    public Object update(Object dataMap) {
        String sqlMapId = "UserList.updateByUID";
        Object result = userListDao.update(sqlMapId, dataMap);
        return result;
    }

    public Object updateAndGetList(Object dataMap) {
        Object result = this.update(dataMap);
        result = this.getList(dataMap);
        return result;

    }

    public Object insert(Object dataMap) {
        String sqlMapId = "UserList.insertWithUID";
        Object result = userListDao.insert(sqlMapId, dataMap);
        return result;
    }

    public Object insertAndGetList(Object dataMap) {
        Object result = this.insert(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object delete(Object dataMap) {
        String sqlMapId = "deleteByUID";
        Object result = userListDao.delete(sqlMapId, dataMap);
        return result;
    }

    public Object deleteAndGetList(Object dataMap) {
        Object result = this.delete(dataMap);
        result = this.getList(dataMap);
        return result;

    }

}