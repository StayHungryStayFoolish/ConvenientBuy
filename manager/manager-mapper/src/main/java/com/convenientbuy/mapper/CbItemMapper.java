package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbItem;
import com.convenientbuy.pojo.CbItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午11:02 on 16/7/31.
 */
public interface CbItemMapper {
    int countByExample(CbItemExample example);

    int deleteByExample(CbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CbItem record);

    int insertSelective(CbItem record);

    List<CbItem> selectByExample(CbItemExample example);

    CbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CbItem record, @Param("example") CbItemExample example);

    int updateByExample(@Param("record") CbItem record, @Param("example") CbItemExample example);

    int updateByPrimaryKeySelective(CbItem record);

    int updateByPrimaryKey(CbItem record);
}