package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbOrderItem;
import com.convenientbuy.pojo.CbOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午10:11 on 16/8/8.
 */
public interface CbOrderItemMapper {
    int countByExample(CbOrderItemExample example);

    int deleteByExample(CbOrderItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(CbOrderItem record);

    int insertSelective(CbOrderItem record);

    List<CbOrderItem> selectByExample(CbOrderItemExample example);

    CbOrderItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") CbOrderItem record, @Param("example") CbOrderItemExample example);

    int updateByExample(@Param("record") CbOrderItem record, @Param("example") CbOrderItemExample example);

    int updateByPrimaryKeySelective(CbOrderItem record);

    int updateByPrimaryKey(CbOrderItem record);
}