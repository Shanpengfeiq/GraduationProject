package com.siro.mall.controller.mall;

import com.siro.mall.entity.Complain;
import com.siro.mall.service.ComplainService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * @author shan
 * @date 2025-03-23
 */
@Api(tags = "投诉")
@RestController
public class ComplainController {
    @Resource
    private ComplainService complainService;

    @PostMapping("/insertComplain")
    public int insertComplain(@RequestBody Complain complain){
        System.out.println("---------------"+complain);
       return complainService.InsertComplain(complain);
    }
}
