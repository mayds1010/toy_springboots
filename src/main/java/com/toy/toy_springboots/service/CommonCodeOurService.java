package com.toy.toy_springboots.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toy.toy_springboots.dao.CommonCodeOurDao;
import com.toy.toy_springboots.utils.Paginations;

@Service
public class CommonCodeOurService {
    @Autowired
    CommonCodeOurDao commonCodeOurDao;

    @Autowired
    AttachFileService attachFileService; // controller에 연결하지 않고 여기에 연결함

    public Object getOneWithAttachFiles(Object dataMap) {
        // Attach files ArrayList<Map>, getOne은 Map
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("attachFiles", attachFileService.getList(dataMap));

        // 기존 값 보존을 위해 사용
        result.putAll((Map<String, Object>) this.getOne(dataMap));// putAll: result에 키,값을 자동으로 넣어 줌
        return result;
    }

    public Object deleteAndGetList(Object dataMap) {
        Object result = this.delete(dataMap); // 아래 delete function 불러오기
        result = this.getList(dataMap);
        return result;
    }

    public Object insertWithFilesAndGetList(Object dataMap) {
        // insert files
        Object result = attachFileService.insertMulti(dataMap);
        result = this.insert(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object updateAndGetList(Object dataMap) {
        Object result = this.update(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object insertAndGetList(Object dataMap) {
        Object result = this.insert(dataMap);
        result = this.getList(dataMap);
        return result;
    }

    public Object getListWithPagination(Object dataMap) { // 묶음 두개로
        Map<String, Object> result = new HashMap<String, Object>();
        int totalCount = (int) this.getTotal(dataMap);
        int currentPage = (int) ((Map<String, Object>) dataMap).get("currentPage");
        Paginations paginations = new Paginations(totalCount, currentPage);
        result.put("paginations", paginations);
        ((Map<String, Object>) dataMap).put("pageBegin", paginations.getPageBegin()); // 파라미터추가
        result.put("resultList", this.getList(dataMap));
        return result;
    }

    public Object getTotal(Object dataMap) {
        String sqlMapId = "CommonCodeOur.selectTotal";
        Object result = commonCodeOurDao.getOne(sqlMapId, dataMap);
        return result;
    }

    public Object getList(Object dataMap) { // 중간단계 컨트롤러로 감
        String sqlMapId = "CommonCodeOur.selectListByUID";
        Object result = commonCodeOurDao.getList(sqlMapId, dataMap);
        return result;
    }

    public Object getOne(Object dataMap) {
        String sqlMapId = "CommonCodeOur.selectByUID";
        Object result = commonCodeOurDao.getOne(sqlMapId, dataMap);
        return result;
    }

    public Object update(Object dataMap) {
        String sqlMapId = "CommonCodeOur.updateByUID";

        Object result = commonCodeOurDao.update(sqlMapId, dataMap);
        return result;
    }

    public Object delete(Object dataMap) {
        String sqlMapId = "CommonCodeOur.deleteByUID";
        Object result = commonCodeOurDao.delete(sqlMapId, dataMap);
        return result;
    }

    public Object deleteMulti(Object dataMap) {
        String sqlMapId = "CommonCodeOur.deleteMultiByUIDs";
        Object result = commonCodeOurDao.delete(sqlMapId, dataMap);
        return result;
    }

    public Object insert(Object dataMap) {
        String sqlMapId = "CommonCodeOur.insertWithUID";

        Object result = commonCodeOurDao.insert(sqlMapId, dataMap);
        return result;
    }
}
