package cn.welldone.collect.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OSSUtil {
    private String ENDPOIT = "oss-cn-nanjing.aliyuncs.com";
    private String ACCESSKeyID = "LTAI5tB6og9RLGfY8dfVXSKV";
    private String ACCESSKEYSECRET = "NfHYGBBzZhUDZACPq6ghX0sPcLSGxO";
    private String BUCKETName = "collect-191250";
    private String URLPrefix = "collect-191250.oss-cn-nanjing.aliyuncs.com";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        OSSUtil ossUtils = new OSSUtil();
        ossUtils.getOSSClient();
    }

    public OSSClient getOSSClient() {
        OSSClient ossClient = new OSSClient(ENDPOIT, ACCESSKeyID, ACCESSKEYSECRET);
        if (ossClient.doesBucketExist(BUCKETName)) {
            System.out.println("存在");
        } else {
            System.out.println("不存在，去创建");
            CreateBucketRequest bucketRequest = new CreateBucketRequest(null);
            bucketRequest.setBucketName(BUCKETName);
            bucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(bucketRequest);
            ossClient.shutdown();
        }

        return ossClient;
    }

    public String uploadDocument(MultipartFile multipartFile, String bussessType) throws IOException {

        OSSClient ossClient = this.getOSSClient();
        String ext = multipartFile.getOriginalFilename();
        ext = ext.substring(ext.lastIndexOf("."));
        String date = sdf.format(new Date());

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filename = bussessType + "/" + date + "/" + uuid + ext;
        String url = null;
        ossClient.putObject(BUCKETName, filename, new ByteArrayInputStream(multipartFile.getBytes()));
        url = URLPrefix + "/" + filename;
        ossClient.shutdown();
        return url;


    }
}
