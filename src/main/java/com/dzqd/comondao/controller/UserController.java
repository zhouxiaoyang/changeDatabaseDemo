package com.dzqd.comondao.controller;

import com.dzqd.comondao.dao.UserMapper;
import com.dzqd.comondao.entity.UserDO;
import com.dzqd.comondao.util.ResultVoUtil;
import com.dzqd.comondao.vo.ResultVo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 11:02 2018/8/10
 * @Modified by:
 */
@RestController
@RequestMapping("/user")
@Log
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/get/{id}")
    public ResultVo get(@PathVariable("id") Integer id){
        UserDO userDo=userMapper.get(id);
        log.info("userDO = "+userDo.toString());
        return ResultVoUtil.success(userDo);
    }


}
