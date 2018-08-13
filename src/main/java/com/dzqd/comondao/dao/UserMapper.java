package com.dzqd.comondao.dao;


import com.dzqd.comondao.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:41 2018/8/6
 * @Modified by:
 */
@Mapper
public interface UserMapper {
    UserDO get(int id);
}
