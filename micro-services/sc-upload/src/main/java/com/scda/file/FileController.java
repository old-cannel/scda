package com.scda.file;

import com.scda.common.response.ResponseVo;
import com.scda.common.utils.IdUtils;
import com.scda.util.FileUtil;
import com.scda.vo.Base64File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liuqi
 * @Date: 2019/4/2 15:20
 * @Description: 文件上传
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private FileUtil fileUtil;

    /**
     * 上传文件
     *
     * @param files 多个文件
     * @return
     */
    @PostMapping("/files")
    public ResponseVo upload(@RequestParam(value = "file", required = false) MultipartFile[] files) {

        if (files == null || files.length < 1) {
            return ResponseVo.validFail("文件不能为空或参数错误");
        }
        List<String> ids = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            if (multipartFile.isEmpty()) {
                return ResponseVo.validFail("文件不能为空或参数错误");
            }
            ids.add(saveFile(multipartFile));
        }
        return ResponseVo.success(ids);
    }

    /**
     * Base64文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/files/base64")
    public ResponseVo uploadByBase64(@RequestBody Base64File file) {
        if (file == null || file.getSize() < 1) {
            return ResponseVo.validFail("文件不能为空或参数错误");
        }
        if (StringUtils.isEmpty(file.getFileType())) {
            file.setFileType("jpg");
        }

        return ResponseVo.success(saveFileByBase64(file.getBase64string(), file.getFileType()));
    }

    /**
     * 保存文件到服务器
     *
     * @param multipartFile
     * @return
     */
    private String saveFile(MultipartFile multipartFile) {


        String fileName = IdUtils.getUUID() + multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        try {
            return fileUtil.saveFile(multipartFile.getInputStream(), fileName);
        } catch (IOException e) {
            log.error("文件读取流异常，详情：{}", e.getMessage());
            throw new RuntimeException("文件读取流异常,请重试！");
        }

    }

    /**
     * 保存文件到服务器
     *
     * @param file      base64编码的文件
     * @param extension 扩展名
     * @return
     */
    private String saveFileByBase64(String file, String extension) {
        String fileName = IdUtils.getUUID() + "." + extension;
        return fileUtil.saveFileByBase64(file, fileName);
    }

}
