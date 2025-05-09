package com.siro.mall.dao;

import com.siro.mall.entity.Complain;

import java.util.List;

public interface ComplainMapper {
    int insertComplain(Complain complain);
    List<Complain> selectAllComplains();
}
