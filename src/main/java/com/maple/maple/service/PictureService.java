package com.maple.maple.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.maple.model.dto.picture.PictureUploadRequest;
import com.maple.maple.model.entity.Picture;
import com.maple.maple.model.entity.User;
import com.maple.maple.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;


/**
* @author A
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2026-05-09 11:14:10
*/
public interface PictureService extends IService<Picture> {
    /**
     * 上传图片
     *
     * @param multipartFile 图片文件
     * @param pictureUploadRequest 图片上传请求
     * @param loginUser 登录用户
     * @return 图片VO
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);


}
