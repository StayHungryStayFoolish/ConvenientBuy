package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbOrderShipping;
import com.convenientbuy.pojo.CbOrderShippingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午10:20 on 16/8/14.
 */
public interface CbOrderShippingMapper {
    int countByExample(CbOrderShippingExample example);

    int deleteByExample(CbOrderShippingExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(CbOrderShipping record);

    int insertSelective(CbOrderShipping record);

    List<CbOrderShipping> selectByExample(CbOrderShippingExample example);

    CbOrderShipping selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") CbOrderShipping record, @Param("example") CbOrderShippingExample example);

    int updateByExample(@Param("record") CbOrderShipping record, @Param("example") CbOrderShippingExample example);

    int updateByPrimaryKeySelective(CbOrderShipping record);

    int updateByPrimaryKey(CbOrderShipping record);
}