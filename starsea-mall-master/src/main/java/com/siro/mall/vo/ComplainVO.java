package com.siro.mall.vo;

import com.siro.mall.entity.Complain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplainVO {
    private int complainCount;
    private List<Complain> complains;
}
