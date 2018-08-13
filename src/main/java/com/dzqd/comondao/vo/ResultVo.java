package com.dzqd.comondao.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果集对象
 * @Author: Winston Yang
 * @Date: Create in 10:20 2018/4/26
 * @Modified by:
 *
 */
@Getter
@Setter
public class ResultVo implements java.io.Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private int code;
    private String msg;
    private Object data;

}


