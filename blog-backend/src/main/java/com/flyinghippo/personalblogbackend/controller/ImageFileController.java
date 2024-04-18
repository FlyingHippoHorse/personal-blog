package com.flyinghippo.personalblogbackend.controller;


import cn.hutool.core.io.FileUtil;
import com.flyinghippo.personalblogbackend.entity.Result;
import com.flyinghippo.personalblogbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author FlyHippo
 * @since 2024-04-16
 */
@RestController
@Slf4j
@RequestMapping("/api/img")
public class ImageFileController {
    @Value("${upload.directory}")
    private String fileSavePath;
    /**
     * 时间格式化
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd/");

    @PostMapping("/upload")
    public Result<Object> handleImageUpload(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
        //后半段目录：  2020/03/15
        String directory = simpleDateFormat.format(new Date());
        /**
         * 文件保存目录  E:/images/2020/03/15/
         * 如果目录不存在，则创建
         */

        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        log.info("图片上传，保存位置：" + fileSavePath + directory);
        //给文件重新设置一个名字
        //后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

        //创建这个新文件
        File newFile = new File(fileSavePath + directory + newFileName);
        //复制操作
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newFileName;
            log.info("图片上传，访问URL：" + url);
            return Result.succ("上传成功！", url);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail("IO异常！");
        }

    }

    // 可选：提供一个方法来服务已上传的文件，用于前端访问
    @GetMapping("/uploaded/{filename:.+}")
    public ResponseEntity<Resource> serveUploadedFile(@PathVariable String filename) throws MalformedURLException {
        // 根据文件名从上传目录加载文件
        Path filePath = Paths.get(fileSavePath, filename);
        Resource file = new UrlResource(filePath.toUri());

        if (!file.exists() || !file.isReadable()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(FileTypeMap.getDefaultFileTypeMap().getContentType(filePath.toFile())))
                .body(file);
    }
}
