package com.scl.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author shengchenglong
 * @version 1.0
 * @since 17/9/12 09:04 JDK 1.8
 */
@Controller
public class FileUploadController {

    @RequestMapping("test")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File dest = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, dest);
        return dest.getAbsolutePath();
    }
}
