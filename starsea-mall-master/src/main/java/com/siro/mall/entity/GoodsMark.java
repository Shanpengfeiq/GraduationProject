package com.siro.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ApiModel(value = "评论")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsMark {
    private Long goodsMarkId;
    private Long goodsId;
    private Long userId;
    private Long mark;
    private String reviewContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reviewTime;
}
