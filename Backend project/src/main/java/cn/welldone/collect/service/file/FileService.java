package cn.welldone.collect.service.file;

import cn.welldone.collect.vo.file.FileInfoVO;
import cn.welldone.collect.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    ResultVO<FileInfoVO> uploadFile(MultipartFile file);

    void downloadFile(String originName, String newName, HttpServletResponse response);

    String preDownloadFile(Integer taskId, Integer fileIndex);
//    String preDownloadFile(Integer taskId, Integer fileIndex, HttpServletResponse response);
}
