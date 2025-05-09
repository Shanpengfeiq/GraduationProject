package com.siro.mall.service;

import com.siro.mall.entity.Complain;
import com.siro.mall.vo.ComplainVO;

import java.util.List;

public interface ComplainService {
    int InsertComplain(Complain complain);

    ComplainVO SelectAllComplain();
}
