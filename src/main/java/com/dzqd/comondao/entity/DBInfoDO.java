package com.dzqd.comondao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:41 2018/8/6
 * @Modified by:
 */
@Getter
@Setter
@ToString
public class DBInfoDO {
    private int id;
    private String dbName;
    private String dbIp;
    private int dbPort;
    private String dbUser;
    private String dbPasswd;


}
