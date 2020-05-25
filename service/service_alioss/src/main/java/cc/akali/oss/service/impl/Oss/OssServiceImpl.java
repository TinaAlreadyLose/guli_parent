package cc.akali.oss.service.impl.Oss;

import cc.akali.oss.service.OssService;
import cc.akali.oss.utils.ConstantPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: dan
 * @Date: 2020/5/14 15:44
 * @Description:
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile multipartFile) {

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String url = null;
//        System.out.println(endpoint);
//        System.out.println(accessKeyId);
//        System.out.println(accessKeySecret);
//        System.out.println(bucketName);
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            InputStream inputStream = multipartFile.getInputStream();
            //获取文件值

            String filename = multipartFile.getOriginalFilename();
//            System.out.println(filename);
            //生成一个为一个uuid
            String uuid = UUID.randomUUID().toString().replace("-", "_");
            filename = uuid + filename;
            //把文件按照日期分类
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            filename = dataPath + "/" + filename;
            ossClient.putObject(bucketName, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            //上传文件之后的路径返回
            //https://edu--0001.oss-cn-qingdao.aliyuncs.com/
            url = "https://" + bucketName + "." + endpoint + "/" + filename;
            log.info(url);
        } catch (IOException e) {
            e.printStackTrace();
            url = "上传失败";
        }
        return url;
    }

}

