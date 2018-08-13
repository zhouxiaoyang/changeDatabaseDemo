package com.dzqd.comondao.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 分页查询参数统一处理
 * @Author: Winston Yang
 * @Date: Create in 10:17 2018/8/7
 * @Modified by:
 */
public class PageQuery extends LinkedHashMap<String, Object> {
    //需要跳转的页码
    private int pageNumber;
    // 每页条数
    private int pageSize;


    public PageQuery(Map<String, Object> params) {
        this.putAll(params);
        this.pageNumber = params.get("pageNumber") == null ? 1 : Integer.parseInt(params.get("pageNumber").toString());
        // 分页参数
        // this.pageNumber = Integer.parseInt(params.get("pageNumber").toString());
        this.pageSize = params.get("pageSize") == null ? 10 : Integer.parseInt(params.get("pageSize").toString());
        // this.pageSize = Integer.parseInt(params.get("pageSize").toString());
        this.put("pageNumber",pageNumber);
        this.put("offset", (pageNumber - 1) * pageSize);
        this.put("pageSize",pageSize);

        // this.put("limit", limit);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
