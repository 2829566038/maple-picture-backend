package com.maple.maple.controller;

import com.maple.maple.common.BaseResponse;
import com.maple.maple.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    /**
     * 健康检查
     * @return
     */
    @GetMapping
    public BaseResponse<String> health() {
        return ResultUtils.success("ok");
    }
}
