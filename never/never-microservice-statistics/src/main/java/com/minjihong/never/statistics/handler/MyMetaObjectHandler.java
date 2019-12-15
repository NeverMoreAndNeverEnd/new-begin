package com.minjihong.never.statistics.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("start insert fill");
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        this.setFieldValByName("version", 1L, metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
        this.setFieldValByName("disabled", 0, metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("start update fill");
        this.setFieldValByName("gmtModified", new Date(), metaObject);

    }
}
