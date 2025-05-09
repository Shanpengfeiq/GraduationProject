package com.siro.mall.service.impl;

import com.siro.mall.dao.ComplainMapper;
import com.siro.mall.entity.Complain;
import com.siro.mall.service.ComplainService;
import com.siro.mall.vo.ComplainVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ComplainServiceImpl implements ComplainService {
    @Resource
    private ComplainMapper complainMapper;

    @Override
    public int InsertComplain(Complain complain) {
        return complainMapper.insertComplain(complain);
    }

    @Override
    public ComplainVO SelectAllComplain() {
        List<Complain> complains = complainMapper.selectAllComplains();
        ComplainVO complainVO=new ComplainVO();
        complainVO.setComplains(complains);
        complainVO.setComplainCount(complains.size());
        return complainVO;
    }
}
