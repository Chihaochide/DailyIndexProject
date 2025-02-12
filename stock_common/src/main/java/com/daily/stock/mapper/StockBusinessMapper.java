package com.daily.stock.mapper;

import com.daily.stock.pojo.entity.StockBusiness;

/**
* @author 刘轩赫
* @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
* @createDate 2025-01-27 01:37:20
* @Entity com.daily.sotck.pojo.entity.StockBusiness
*/
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

}
