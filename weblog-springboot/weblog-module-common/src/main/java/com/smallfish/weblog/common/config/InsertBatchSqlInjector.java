package com.smallfish.weblog.common.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 添加sql注入器
 **/
public class InsertBatchSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        // super.getMethodList() 保留自带的方法
        List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);

        // 添加自定义方法 批量插入 方法名 insertBatchSomeColumn
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}
