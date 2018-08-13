package com.dzqd.comondao.aspect;

import com.alibaba.druid.pool.DruidDataSource;
import com.dzqd.comondao.dao.DBInfoMapper;
import com.dzqd.comondao.datasource.DataSourceContextHolder;
import com.dzqd.comondao.datasource.DynamicDataSource;
import com.dzqd.comondao.entity.DBInfoDO;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
@Log
public class DataSourceAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    @Autowired
    DBInfoMapper dbInfoMapper;

    @Pointcut("execution(public * com.dzqd.comondao.controller.*Controller.*(..))")
    public void changeDateSource(){}

    @Before("changeDateSource()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //Object[] para=joinPoint.getArgs();
        //访问comondao必须带有dbinfo参数
        if (request.getParameter("dbinfo")==null) throw new RuntimeException(" 拒绝访问! ");

        //获取要使用的数据库名
        String dbName=request.getParameter("dbinfo").toString().trim();
        //切换该次请求的数据源
        setDruidDataSource(dbName);
      // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "changeDateSource()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }

    public void setDruidDataSource(String dbName){
        //DataSourceContextHolder.setDBType("master");
        if (dbName.equalsIgnoreCase("master")) return;
        DBInfoDO dbInfo = dbInfoMapper.getDBInfoByDBName(dbName);
        log.info("dbName is -> " + dbInfo.getDbName() + "; dbIP is  -> " + dbInfo.getDbIp() + "; dbUser is  -> " + dbInfo.getDbUser() + "; dbPasswd is -> " + dbInfo.getDbPasswd());

        DruidDataSource dynamicDataSource = new DruidDataSource();
        dynamicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dynamicDataSource.setUrl("jdbc:mysql://" + dbInfo.getDbIp() + ":" + dbInfo.getDbPort() + "/" + dbInfo.getDbName() + "?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
        dynamicDataSource.setUsername(dbInfo.getDbUser());
        dynamicDataSource.setPassword(dbInfo.getDbPasswd());

        /**
         * 创建动态数据源
         */
        Map<Object, Object> dataSourceMap = DynamicDataSource.getInstance().getDataSourceMap();
        dataSourceMap.put(dbName, dynamicDataSource);
        DynamicDataSource.getInstance().setTargetDataSources(dataSourceMap);
        /**
         * 切换为动态数据源实例，
         */
        DataSourceContextHolder.setDBType(dbName);
    }

}
