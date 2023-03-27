package cn.welldone.collect.controller.file;

import cn.welldone.collect.service.file.FileService;
import cn.welldone.collect.util.OSSUtil;
import cn.welldone.collect.vo.file.FileInfoVO;
import cn.welldone.collect.vo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    FileService fileService;

    @PostMapping("/upload")
    public ResultVO<FileInfoVO> upload(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/download/{taskId}/{fileIndex}")
    public String preDownload(@PathVariable Integer taskId, @PathVariable Integer fileIndex) {
        return (fileService.preDownloadFile(taskId, fileIndex));
    }
    @GetMapping("/download/{originName:.+}")
    public void download(@PathVariable String originName, @RequestParam String newName, HttpServletResponse response) {
        fileService.downloadFile(originName, newName, response);
    }


    @PostMapping("oss")
    @ResponseBody
    public Map<String,Object> oss(@RequestParam("file")MultipartFile multipartFile) throws IOException {

        Map<String,Object>map=new HashMap<>();
        System.out.println("===========================");
        System.out.println(multipartFile.getOriginalFilename());
        OSSUtil ossUtils = new OSSUtil();
        String url=ossUtils.uploadDocument(multipartFile,"upload");
        //
        System.out.println(url);
        map.put("url",url);
        return map;

    }

}