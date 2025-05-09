package com.siro.mall.controller.common;

import com.siro.mall.common.Constants;
import com.siro.mall.utils.MallUtils;
import com.siro.mall.utils.Result;
import com.siro.mall.utils.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shan
 * @date 2025-03-23
 */
@Api(tags = "图片上传与下载")
@Controller
@RequestMapping("/admin")
public class UploadController {

    @ApiOperation(value = "管理员上传单张图片")
    @PostMapping({"/upload/file"})
    @ResponseBody
    public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) throws URISyntaxException {
// 1. 获取原始文件名（自动过滤路径，仅保留文件名）
        String originalFileName = file.getOriginalFilename();
        // 2. 清理文件名中的非法字符（根据操作系统过滤）
        String safeFileName = sanitizeFileName(originalFileName);
        // 3. 目标文件路径（使用原始文件名）
        File destFile = new File(Constants.FILE_UPLOAD_DIC + safeFileName);
        try {
            // 4. 创建目录（确保路径以斜杠结尾）
            File fileDirectory = destFile.getParentFile();
            if (!fileDirectory.exists() && !fileDirectory.mkdirs()) {
                throw new IOException("目录创建失败: " + fileDirectory);
            }
            // 判断文件是否存在
            if (destFile.exists()) {
                Result resultSuccess = ResultGenerator.genSuccessResult();
                resultSuccess.setData(MallUtils.getHost(new java.net.URI(httpServletRequest.getRequestURL() + "")) + "/goods-img/" + safeFileName);
                return resultSuccess;
            }
            // 5. 保存文件
            file.transferTo(destFile);
            Result resultSuccess = ResultGenerator.genSuccessResult();
            resultSuccess.setData(MallUtils.getHost(new java.net.URI(httpServletRequest.getRequestURL() + "")) + "/goods-img/" + safeFileName);
            return resultSuccess;

        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("文件上传失败: " + e.getMessage());
        }
    }
    private String sanitizeFileName(String fileName) {
        // 匹配所有操作系统的非法字符
        String regex = "[\\\\/:*?\"<>|]"; // Windows 非法字符
        String sanitized = fileName.replaceAll(regex, "_"); // 替换为下划线

        // 额外处理：避免文件名全为空格或点
        if (sanitized.trim().isEmpty() || sanitized.startsWith(".")) {
            sanitized = "untitled_" + System.currentTimeMillis() + ".tmp";
        }
        return sanitized;
    }

    @ApiOperation(value = "管理员上传多张图片")
    @PostMapping({"/upload/files"})
    @ResponseBody
    public Result uploadV2(HttpServletRequest httpServletRequest) throws URISyntaxException {
        List<MultipartFile> multipartFiles = new ArrayList<>(8);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());
        if (multipartResolver.isMultipart(httpServletRequest)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iter = multiRequest.getFileNames();
            int total = 0;
            while (iter.hasNext()) {
                if (total > 5) {
                    return ResultGenerator.genFailResult("最多上传5张图片");
                }
                total += 1;
                MultipartFile file = multiRequest.getFile(iter.next());
                multipartFiles.add(file);
            }
        }
        if (CollectionUtils.isEmpty(multipartFiles)) {
            return ResultGenerator.genFailResult("参数异常");
        }
        if (multipartFiles != null && multipartFiles.size() > 5) {
            return ResultGenerator.genFailResult("最多上传5张图片");
        }
        List<String> fileNames = new ArrayList(multipartFiles.size());
        for (int i = 0; i < multipartFiles.size(); i++) {
            String fileName = multipartFiles.get(i).getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
            String newFileName = tempName.toString();
            System.out.println("newFileName = " + newFileName);
            File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
            //创建文件
            File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
            try {
                if (!fileDirectory.exists()) {
                    if (!fileDirectory.mkdir()) {
                        throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                    }
                }
                multipartFiles.get(i).transferTo(destFile);
                fileNames.add(MallUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultGenerator.genFailResult("文件上传失败");
            }
        }
        Result resultSuccess = ResultGenerator.genSuccessResult();
        resultSuccess.setData(fileNames);
        return resultSuccess;
    }
}
