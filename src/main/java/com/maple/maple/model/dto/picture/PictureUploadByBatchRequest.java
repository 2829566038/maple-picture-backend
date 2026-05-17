package com.maple.maple.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * 批量上传图片请求
 * */
@Data
public class PictureUploadByBatchRequest implements Serializable {

    /**
     * 图片名称（用于修改）
     */
    private String name;

    /**
     * 上传数量（默认10张）
     */
    private Integer count = 10;

    private static final long serialVersionUID = 1L;
}
