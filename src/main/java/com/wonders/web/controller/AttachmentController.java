package com.wonders.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by c_liqingdong on 2017/5/23.
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String rootPath = "E:/测试";

    @RequestMapping("/index")
    public void index(Model model) {

    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {
        StringBuilder resp = new StringBuilder();
        response.setStatus(HttpStatus.OK.value());          // 设置状态码
        response.setContentType("text/html;charset=UTF-8"); // 设置返回值
        response.setCharacterEncoding("UTF-8");             // 设置编码

        File targetParentFile = new File(rootPath);
        if (!targetParentFile.exists()) targetParentFile.mkdirs();
        File targetFile = new File(targetParentFile, file.getOriginalFilename());
        if (targetFile.exists()) targetFile.delete();
        file.transferTo(targetFile);

        resp.append("success");
        logger.info("上传成功");
        response.getWriter().print(resp.toString());
    }


}
