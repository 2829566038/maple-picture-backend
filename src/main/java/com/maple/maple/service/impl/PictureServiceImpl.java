package com.maple.maple.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.maple.exception.ErrorCode;
import com.maple.maple.exception.ThrowUtils;
import com.maple.maple.manager.FileManager;
import com.maple.maple.mapper.PictureMapper;
import com.maple.maple.model.dto.file.UploadPictureResult;
import com.maple.maple.model.dto.picture.PictureUploadRequest;
import com.maple.maple.model.entity.Picture;
import com.maple.maple.model.entity.User;
import com.maple.maple.model.vo.PictureVO;
import com.maple.maple.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author A
 * @description 针对表【picture(图片)】的数据库操作Service实现
 * @createDate 2026-05-09 11:14:10
 */
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
        implements PictureService {

    @Resource
    private FileManager fileManager;

    @Override
    public PictureVO uploadPicture(MultipartFile multipartFile, PictureUploadRequest pictureUploadRequest, User loginUser) {
        //校验参数
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NO_AUTH_ERROR);
        //判断新增还是删除
        Long pictureId = null;
        if (pictureUploadRequest != null) {
            pictureId = pictureUploadRequest.getId();
        }
        //如果是更新，则判断图片是否存在
        if (pictureId != null) {
            boolean existsts = this.lambdaQuery().eq(Picture::getId, pictureId).exists();
            ThrowUtils.throwIf(!existsts, ErrorCode.NOT_FOUND_ERROR, "图片不存在");
        }
        //上传图片，得到图片信息
        //按照用户id划分目录
        String uploadPathPrefix = String.format("public/%s", loginUser.getId());
        UploadPictureResult uploadPictureResult = fileManager.uploadPicture(multipartFile, uploadPathPrefix);
        //构造要入库的图片信息
        Picture picture = new Picture();
        picture.setUrl(uploadPictureResult.getUrl());
        picture.setName(uploadPictureResult.getPicName());
        picture.setPicSize(uploadPictureResult.getPicSize());
        picture.setPicWidth(uploadPictureResult.getPicWidth());
        picture.setPicHeight(uploadPictureResult.getPicHeight());
        picture.setPicScale(uploadPictureResult.getPicScale());
        picture.setPicFormat(uploadPictureResult.getPicFormat());
        picture.setUserId(loginUser.getId());
        //操作数据库
        //如果pictureId不为空表示更新，否则新增
        if(pictureId != null) {
            picture.setId(pictureId);
            picture.setEditTime(new Date());
        }
        boolean result = this.saveOrUpdate(picture);
        ThrowUtils.throwIf(!result, ErrorCode.SYSTEM_ERROR, "上传失败，数据库错误");
        return PictureVO.objToVo(picture);
    }
}



