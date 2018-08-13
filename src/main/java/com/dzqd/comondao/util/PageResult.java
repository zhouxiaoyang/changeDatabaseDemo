package com.dzqd.comondao.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:09 2018/8/6
 * @Modified by:
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class PageResult<T> {
    //当前页
    private int currentPage;
    //总共记录条数
    private int totalSize;
    //结果集
    private List<T> resultList = new ArrayList<T>();

}
