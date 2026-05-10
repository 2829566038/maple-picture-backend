package com.maple.maple.controller;

import com.maple.maple.annotation.AuthCheck;
import com.maple.maple.common.BaseResponse;
import com.maple.maple.common.ResultUtils;
import com.maple.maple.constant.UserConstant;
import com.maple.maple.model.dto.picture.PictureUploadRequest;
import com.maple.maple.model.entity.User;
import com.maple.maple.model.vo.PictureVO;
import com.maple.maple.service.PictureService;
import com.maple.maple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    /**
     * 上传图片（可重新上传）
     */
    @PostMapping("/upload")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<PictureVO> uploadPicture(
            @RequestPart("file") MultipartFile multipartFile,
            PictureUploadRequest pictureUploadRequest,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        PictureVO pictureVO = pictureService.uploadPicture(multipartFile, pictureUploadRequest, loginUser);
        return ResultUtils.success(pictureVO);
    }

}
