package com.scda.util;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: liuqi
 * @Date: 2019/4/2 17:00
 * @Description: 文件工具类
 */
@Slf4j
@Component
public class FileUtil {
    //服务器文件地址
    @Value("${file.local.path}")
    private String localPath;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyyMMdd");

    private static BASE64Decoder decoder = new BASE64Decoder();

    /**
     * 把输入流保存到指定文件
     *
     * @param source   源文件输入流
     * @param fileName 文件名称
     */
    public String saveFile(InputStream source, String fileName) {
        checkRootPath();
        String parentDir = DateUtil.format(new Date(), simpleDateFormat);
        mkdirs(localPath + parentDir);
        String filePath = localPath + parentDir + "/" + fileName;
        if (isExists(filePath)) {
            log.error("保存文件，文件{}已经存在！", filePath);
            throw new RuntimeException("文件已经存在");

        }
        InputStream input = null;
        OutputStream output = null;
        try {
            input = source;
            output = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > -1) {
                output.write(buf, 0, bytesRead);
            }
            output.close();
            input.close();
            return parentDir + "/" + fileName;
        } catch (Exception e) {
            log.error("文件保存异常，详情：{}", e.getMessage());
            throw new RuntimeException("文件保存异常，请重试");
        }
    }

    /**
     * 文件base64 保存到服务器
     *
     * @param base64   文件base64
     * @param fileName 文件名称
     */
    public String saveFileByBase64(String base64, String fileName) {

        checkRootPath();
        String parentDir = DateUtil.format(new Date(), simpleDateFormat);
        mkdirs(localPath + parentDir);
        String filePath = localPath + parentDir + "/" + fileName;
        if (isExists(filePath)) {
            log.error("保存文件，文件{}已经存在！", filePath);
            throw new RuntimeException("文件已经存在");

        }
        OutputStream output = null;
        try {
            output = new FileOutputStream(new File(filePath));
            byte[] result = decoder.decodeBuffer(base64);//解码
            for (int i = 0; i < result.length; ++i) {
                if (result[i] < 0) {// 调整异常数据
                    result[i] += 256;
                }
            }
            output.write(result);
            output.flush();
            output.close();
            return parentDir + "/" + fileName;
        } catch (Exception e) {
            log.error("文件保存异常，详情：{}", e.getMessage());
            throw new RuntimeException("文件保存异常，请重试");
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                log.error("文件复制异常，详情：{}", e.getMessage());
                throw new RuntimeException("文件复制异常，详情：" + e.getMessage());
            }
        }
    }


    /*判断文件是否存在*/
    public boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /*判断是否是文件夹*/
    public boolean isDir(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file.isDirectory();
        } else {
            return false;
        }
    }

    /**
     * 文件或者目录重命名
     *
     * @param oldFilePath 旧文件路径
     * @param newName     新的文件名,可以是单个文件名和绝对路径
     * @return
     */
    public boolean renameTo(String oldFilePath, String newName) {
        try {
            File oldFile = new File(oldFilePath);
            //若文件存在
            if (oldFile.exists()) {
                //判断是全路径还是文件名
                if (newName.indexOf("/") < 0 && newName.indexOf("\\") < 0) {
                    //单文件名，判断是windows还是Linux系统
                    String absolutePath = oldFile.getAbsolutePath();
                    if (newName.indexOf("/") > 0) {
                        //Linux系统
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("/") + 1) + newName;
                    } else {
                        newName = absolutePath.substring(0, absolutePath.lastIndexOf("\\") + 1) + newName;
                    }
                }
                File file = new File(newName);
                //判断重命名后的文件是否存在
                if (file.exists()) {
                    log.warn("该文件已存在,不能重命名");
                } else {
                    //不存在，重命名
                    return oldFile.renameTo(file);
                }
            } else {
                log.warn("原该文件不存在,不能重命名");
                throw new RuntimeException("原该文件不存在,不能重命名");
            }
        } catch (Exception e) {
            log.error("文件重命名异常，详情：{}", e.getMessage());
            throw new RuntimeException("文件重命名异常，详情：" + e.getMessage());
        }
        return false;
    }


    /*文件拷贝操作*/
    public void copy(String sourceFile, String targetFile) {
        File source = new File(sourceFile);
        File target = new File(targetFile);
        target.getParentFile().mkdirs();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            in = fis.getChannel();//得到对应的文件通道
            out = fos.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            log.error("文件复制异常，详情：{}", e.getMessage());
            throw new RuntimeException("文件复制异常，详情：" + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                log.error("文件复制异常，详情：{}", e.getMessage());
                throw new RuntimeException("文件复制异常，详情：" + e.getMessage());
            }
        }
    }

    /*读取Text文件操作*/
    public String readText(String filePath) {
        String lines = "";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                lines += line + "\n";
            }
        } catch (Exception e) {
            log.error("文件读取异常，详情：{}", e.getMessage());
            throw new RuntimeException("文件读取异常，详情：" + e.getMessage());
        }
        return lines;
    }

    /*写入Text文件操作*/
    public void writeText(String filePath, String content, boolean isAppend) {
        FileOutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = new FileOutputStream(filePath, isAppend);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
        } catch (IOException e) {
            log.error("文件写入异常，详情：{}", e.getMessage());
            throw new RuntimeException("文件写入异常，详情：" + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                log.error("文件写入异常，详情：{}", e.getMessage());
                throw new RuntimeException("文件写入异常，详情：" + e.getMessage());
            }
        }
    }


    /**
     * 通过上一层目录和目录名得到最后的目录层次
     *
     * @param previousDir 上一层目录
     * @param dirName     当前目录名
     * @return
     */
    public String getSaveDir(String previousDir, String dirName) {
        if (StringUtils.isEmpty(previousDir)) {
            dirName = previousDir + "/" + dirName + "/";
        } else {
            dirName = dirName + "/";
        }
        return dirName;
    }

    /**
     * 如果目录不存在，就创建文件
     *
     * @param dirPath
     * @return
     */
    public String mkdirs(String dirPath) {
        try {
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            log.error("目录创建异常，详情：{}", e.getMessage());
            throw new RuntimeException("创建目录异常，详情：" + e.getMessage());
        }
        return dirPath;
    }


    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */

    public boolean delete(String fileName) {
        try {
            File sourceFile = new File(fileName);
            if (sourceFile.isDirectory()) {
                for (File listFile : sourceFile.listFiles()) {
                    delete(listFile.getAbsolutePath());
                }
            }
            return sourceFile.delete();
        } catch (Exception e) {
            log.error("文件删除异常，详情：{}", e.getMessage());

        }
        return false;
    }


    private void checkRootPath() {
        if (StringUtils.isEmpty(localPath)) {
            throw new RuntimeException("服务器文件保存目录没有配置，请联系管理员");
        }
    }

}
