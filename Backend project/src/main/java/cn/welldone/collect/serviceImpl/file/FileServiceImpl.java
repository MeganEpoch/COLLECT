package cn.welldone.collect.serviceImpl.file;

import cn.welldone.collect.exception.MyException;
import cn.welldone.collect.mapperservice.task.TaskMapper;
import cn.welldone.collect.po.task.Task;
import cn.welldone.collect.service.file.FileService;
import cn.welldone.collect.util.Constant;
import cn.welldone.collect.util.FileHelper;
import cn.welldone.collect.vo.file.FileInfoVO;
import cn.welldone.collect.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
//import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @javax.annotation.Resource
    private TaskMapper taskMapper;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${web.file-upload-path}")
    private String path;


    //返回乱码文件名
    @Override
//    public ResultVO<FileInfoVO> uploadFile(MultipartFile file,Integer taskId,Integer fileIndex) {
    public ResultVO<FileInfoVO> uploadFile(MultipartFile file) {
        try {
            FileInfoVO fileInfoVO = FileHelper.save(path, file);
//            TaskFile taskFile=new TaskFile();
//            taskFile.setIndex(fileIndex);
//            taskFile.setName(fileInfoVO.getName());
////            taskFile.setTaskId(taskId);
//
//            Task task=new Task();
//            task.setFileName1();
//           if( taskFileMapper.insert(taskFile)>0) {
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "文件上传成功", fileInfoVO);
//           }
        } catch (MyException myException) {
            logger.error("用于存放上传文件的文件夹不存在或创建失败，请检查路径是否有效", myException);
        } catch (IOException ioException) {
            logger.error("文件复制时出错", ioException);
        }
        return new ResultVO<>(-1, "服务器错误，请联系网站管理员。");
    }

    //originname是乱码,njq版本
    @Override
    public void downloadFile(String originName, String newName, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        response.setContentType("application/x-msdownload");
        try {
            Resource resource = FileHelper.loadFileAsResource(path, originName);
            if (resource == null)
                return;
            inputStream = resource.getInputStream();
            //1.设置文件ContentType类型
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream = response.getOutputStream();
            //2.转码  UTF_8为传入的newName编码的格式 ISO_8859_1为浏览器默认编码
            String convertName = new String(newName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            //3.设置 header  Content-Disposition
            response.setHeader("Content-Disposition", "attachment; filename=" + convertName);
            int b = 0;
            byte[] buffer = new byte[2048];
            while (b != -1) {
                b = inputStream.read(buffer);
                if (b != -1) {
                    outputStream.write(buffer, 0, b);
                }
            }
        } catch (IOException | MyException e) {
            logger.error("文件下载时出错", e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                logger.error("输入流或输出流关闭时出错！", e);
            }
        }
    }


    //根据taskId返回文件名，lym版本
    @Override
    public String preDownloadFile(Integer taskId, Integer fileIndex) { //checked by junit
//        TaskFile taskFile = taskFileMapper.selectByTask(taskId, fileIndex);
//        if (taskFile.getName() != null) {
//            return taskFile.getName();
//        }
//        return "something wrong";
        Task task = taskMapper.selectByPrimaryKey(taskId);
        if (fileIndex == 1) {
            return task.getFileName1();
        } else if (fileIndex == 2) {
            return task.getFileName2();
        } else return "fileIndex is invalid.";
    }


}
