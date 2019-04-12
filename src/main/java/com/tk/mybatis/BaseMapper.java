package com.tk.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author TianHeLing
 * @Description 自己的 Mapper,特别注意，该接口不能被扫描到，否则会出错
 * @date 2019/4/12
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
