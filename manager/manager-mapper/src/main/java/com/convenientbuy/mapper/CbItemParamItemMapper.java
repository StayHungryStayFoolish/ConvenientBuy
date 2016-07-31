package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbItemParamItem;
import com.convenientbuy.pojo.CbItemParamItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午11:29 on 16/7/31.
 */
public interface CbItemParamItemMapper {
    int countByExample(CbItemParamItemExample example);

    int deleteByExample(CbItemParamItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CbItemParamItem record);

    int insertSelective(CbItemParamItem record);

    List<CbItemParamItem> selectByExampleWithBLOBs(CbItemParamItemExample example);

    List<CbItemParamItem> selectByExample(CbItemParamItemExample example);

    CbItemParamItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CbItemParamItem record, @Param("example") CbItemParamItemExample example);

    int updateByExampleWithBLOBs(@Param("record") CbItemParamItem record, @Param("example") CbItemParamItemExample example);

    int updateByExample(@Param("record") CbItemParamItem record, @Param("example") CbItemParamItemExample example);

    int updateByPrimaryKeySelective(CbItemParamItem record);

    int updateByPrimaryKeyWithBLOBs(CbItemParamItem record);

    int updateByPrimaryKey(CbItemParamItem record);
}