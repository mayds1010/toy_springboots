package com.toy.toy_springboots.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.toy.toy_springboots.service.CommonCodeOurService;
import com.toy.toy_springboots.utils.CommonUtils;

@Controller
@RequestMapping
public class CommonCodeOurController {

    @Autowired
    CommonCodeOurService commonCodeOurService;

    @Autowired
    CommonUtils commonUtils;

    @RequestMapping(value = { "/insert" }, method = RequestMethod.POST)
    public ModelAndView insert(MultipartHttpServletRequest multipartHttpServletRequest,
            @RequestParam Map<String, Object> params,
            ModelAndView modelAndView) throws IOException {

        String registerSeq = multipartHttpServletRequest.getParameter("REGISTER_SEQ");

        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file_first");
        String fileName = multipartFile.getOriginalFilename();

        String relativePath = "src\\main\\resources\\static\\files\\";
        // file 저장
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(relativePath + fileName));
        bufferedWriter.write(new String(multipartFile.getBytes()));
        bufferedWriter.flush();

        commonCodeOurService.insertAndGetList(params);
        // modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("commonCode_our/list");
        return modelAndView;
    }

    @RequestMapping(value = { "/updateMulti" }, method = RequestMethod.POST)
    public ModelAndView updateMulti(MultipartHttpServletRequest multipartHttpServletRequest,
            @RequestParam Map<String, Object> params,
            ModelAndView modelAndView) throws IOException {

        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();

        while (fileNames.hasNext()) {
            String value = (String) params.get(fileNames.next());
            System.out.print(value);// DB가 저장되어 있다.
            if (value != null) {
                // originalFilename 있는지 여부 확인
            }
        }
        modelAndView.setViewName("commonCode_our/list");
        return modelAndView;
    }

    @RequestMapping(value = { "/insertMulti" }, method = RequestMethod.POST)
    public ModelAndView insertMulti(MultipartHttpServletRequest multipartHttpServletRequest,
            @RequestParam Map<String, Object> params,
            ModelAndView modelAndView) throws IOException {

        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
        String absolutePath = commonUtils.getRelativeToAbsolutePath("src/main/resources/static/files/");

        Map attachfile = null;
        List attachfiles = new ArrayList();
        String pysicalfileName = commonUtils.getUniqueSequence();
        String storePath = absolutePath + pysicalfileName + File.separator;
        File newPath = new File(storePath);
        newPath.mkdir(); // create directory
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile multipartFile = multipartHttpServletRequest.getFile(fileName);
            String originalFilename = multipartFile.getOriginalFilename();

            if (originalFilename != null && multipartFile.getSize() > 0) { // 방어코드
                String storePathFileName = storePath + originalFilename;
                multipartFile.transferTo(new File(storePathFileName)); // 경로설정

                // add SOURCE_UNIQUE_SEQ, ORGINALFILE_NAME, PHYSICALFILE_NAME in hashMap
                attachfile = new HashMap<>();
                attachfile.put("ATTACHFILE_SEQ", commonUtils.getUniqueSequence());
                attachfile.put("SOURCE_UNIQUE_SEQ", params.get("COMMON_CODE_ID"));
                attachfile.put("ORGINALFILE_NAME", originalFilename);
                attachfile.put("PHYSICALFILE_NAME", pysicalfileName);
                attachfile.put("REGISTER_SEQ", params.get("REGISTER_SEQ"));
                attachfile.put("MODIFIER_SEQ", params.get("MODIFIER_SEQ"));

                attachfiles.add(attachfile);
            }
        }
        params.put("attachfiles", attachfiles);

        Object resultMap = commonCodeOurService.insertWithFilesAndGetList(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("commonCode_our/list");
        return modelAndView;
    }

    @RequestMapping(value = { "/formMulti" }, method = RequestMethod.GET)
    public ModelAndView formMulti(@RequestParam Map<String, Object> params, ModelAndView modelAndView) {

        modelAndView.setViewName("commonCode_our/editMulti"); // edit화면 재사용
        return modelAndView;
    }

    @RequestMapping(value = { "/deleteMulti" }, method = RequestMethod.POST)
    public ModelAndView deleteMulti(HttpServletRequest httpServletRequest, @RequestParam Map<String, Object> params,
            ModelAndView modelAndView) {
        // modelAndView.addObject("resultMap", resultMap);
        String[] deleteMultis = httpServletRequest.getParameterMap().get("COMMON_CODE_ID");
        params.put("deleteMultis", deleteMultis);
        Object resultMap = commonCodeOurService.deleteMulti(params);

        modelAndView.setViewName("commonCode_our/list");
        return modelAndView;
    }

    @RequestMapping(value = { "/listPagination/{currentPage}" }, method = RequestMethod.GET)
    public ModelAndView listPagination(@RequestParam Map<String, Object> params,
            @PathVariable String currentPage, ModelAndView modelAndView) {
        params.put("currentPage", Integer.parseInt(currentPage)); // string을 integer로
        params.put("pageScale", 10);
        Object resultMap = commonCodeOurService.getListWithPagination(params); // resultMap key:total,resultList
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("commonCode_our/list_pagination");
        return modelAndView;
    }

    @RequestMapping(value = { "/editMulti/{uniqueId}" }, method = RequestMethod.GET)
    public ModelAndView editMulti(@RequestParam Map<String, Object> params,
            @PathVariable String uniqueId, ModelAndView modelAndView) {
        params.put("COMMON_CODE_ID", uniqueId);
        params.put("SOURCE_UNIQUE_SEQ", uniqueId);
        Object resultMap = commonCodeOurService.getOneWithAttachFiles(params);
        modelAndView.addObject("resultMap", resultMap);

        modelAndView.setViewName("commonCode_our/editMulti");
        return modelAndView;
    }
}
