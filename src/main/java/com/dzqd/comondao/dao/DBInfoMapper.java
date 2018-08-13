package com.dzqd.comondao.dao;


import com.dzqd.comondao.entity.DBInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:41 2018/8/6
 * @Modified by:
 */
@Mapper
public interface DBInfoMapper {
    DBInfoDO getDBInfoByDBName(String dbName);
    DBInfoDO getDBInfoByID(int id);
}
