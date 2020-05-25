package cc.akali.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: dan
 * @Date: 2020/5/14 15:43
 * @Description:
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile multipartFile) throws IOException;
}
