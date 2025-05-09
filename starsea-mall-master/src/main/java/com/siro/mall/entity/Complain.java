package com.siro.mall.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "投诉")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complain {
    private Integer complainId;
    private String complainContent;
//    private String complainName;
}
