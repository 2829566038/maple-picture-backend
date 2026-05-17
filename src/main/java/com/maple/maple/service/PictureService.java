package com.maple.maple.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.maple.model.dto.picture.PictureQueryRequest;
import com.maple.maple.model.dto.picture.PictureReviewRequest;
import com.maple.maple.model.dto.picture.PictureUploadRequest;
import com.maple.maple.model.entity.Picture;
import com.maple.maple.model.entity.User;
import com.maple.maple.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * 图片服务接口
 */
public interface PictureService extends IService<Picture> {
    /**
     * 校验图片实体
     * @param picture 图片实体
     */
    void validPicture(Picture picture);

    /**
     * 上传图片
     *
     * @param inputSource 图片文件
     * @param pictureUploadRequest 图片上传请求
     * @param loginUser 登录用户
     * @return 图片VO
     */
    PictureVO uploadPicture(Object inputSource,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 获取图片包装类(单条)
     * @param picture 图片实体
     * @param request 请求对象
     * @return 图片VO
     */
    PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 获取图片包装类(分页)
     * @param picturePage 图片分页实体
     * @param request 请求对象
     * @return 图片VO页实体
     */
    Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 获取图片查询包装类
     * @param pictureQueryRequest 图片查询请求
     * @return 图片查询包装类
     */
    QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 图片审核
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    void fillReviewParams(Picture picture, User loginUser);
}
