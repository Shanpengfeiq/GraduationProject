package com.siro.mall.vo;

import java.io.Serializable;

/**
 * 首页轮播图VO
 * @author shan
 * @date 2025-03-23
 */
public class IndexCarouselVO implements Serializable {

    private String carouselUrl;
    private String redirectUrl;

    public String getCarouselUrl() {
        return carouselUrl;
    }

    public void setCarouselUrl(String carouselUrl) {
        this.carouselUrl = carouselUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
