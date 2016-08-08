package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbOrder;
import com.convenientbuy.pojo.CbOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午11:27 on 16/8/8.
 */
public interface CbOrderMapper {
    int countByExample(CbOrderExample example);

    int deleteByExample(CbOrderExample example);

    int deleteByPrimaryKey(String orderId);

    int insert(CbOrder record);

    int insertSelective(CbOrder record);

    List<CbOrder> selectByExample(CbOrderExample example);

    CbOrder selectByPrimaryKey(String orderId);

    int updateByExampleSelective(@Param("record") CbOrder record, @Param("example") CbOrderExample example);

    int updateByExample(@Param("record") CbOrder record, @Param("example") CbOrderExample example);

    int updateByPrimaryKeySelective(CbOrder record);

    int updateByPrimaryKey(CbOrder record);
}