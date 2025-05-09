package com.siro.mall.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 分页查询参数
 * @author shan
 * @date 2025-03-23
 */
public class PageQueryUtil extends LinkedHashMap<String, Object> {

    //当前页码
    private int page;
    //每页条数
    private int limit;

    /**
     * 构造函数用于初始化PageQueryUtil实例
     * 该构造函数接收一个包含分页参数的Map，并从中提取分页信息
     *
     * @param params 包含分页参数的Map，必须包含"page"和"limit"键
     */
    public PageQueryUtil(Map<String, Object> params) {
        // 将传入的参数Map全部放入当前实例中
        this.putAll(params);

        // 分页参数
        // 从参数Map中获取当前页码和每页数量，并将其转换为整数类型
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());

        // 计算起始索引，并将其存入当前实例中
        // 起始索引用于数据库查询中确定从哪条记录开始取数据
        this.put("start", (page - 1) * limit);

        // 将当前页码和每页数量存入当前实例中，以便在后续的查询中使用
        this.put("page", page);
        this.put("limit", limit);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
