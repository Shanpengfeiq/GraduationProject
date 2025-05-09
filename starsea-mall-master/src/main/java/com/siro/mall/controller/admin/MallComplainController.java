package com.siro.mall.controller.admin;

import com.siro.mall.service.ComplainService;
import com.siro.mall.vo.ComplainVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "投诉的Controller")
@Controller
@RequestMapping("/admin")
public class MallComplainController {
@Resource
    private ComplainService complainService;

    @ApiOperation(value = "跳转到商品投诉页面")
    @GetMapping("/complain")
    public String goodsPage(HttpServletRequest request) {
        ComplainVO complainVO = complainService.SelectAllComplain();
        request.setAttribute("path", "mall_complain");
        request.setAttribute("complain",complainVO);
        return "admin/mall_complain";
    }
}
