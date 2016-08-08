package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbItemParam;
import com.convenientbuy.pojo.CbItemParamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午9:32 on 16/8/8.
 */
public interface CbItemParamMapper {
    int countByExample(CbItemParamExample example);

    int deleteByExample(CbItemParamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CbItemParam record);

    int insertSelective(CbItemParam record);

    List<CbItemParam> selectByExampleWithBLOBs(CbItemParamExample example);

    List<CbItemParam> selectByExample(CbItemParamExample example);

    CbItemParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CbItemParam record, @Param("example") CbItemParamExample example);

    int updateByExampleWithBLOBs(@Param("record") CbItemParam record, @Param("example") CbItemParamExample example);

    int updateByExample(@Param("record") CbItemParam record, @Param("example") CbItemParamExample example);

    int updateByPrimaryKeySelective(CbItemParam record);

    int updateByPrimaryKeyWithBLOBs(CbItemParam record);

    int updateByPrimaryKey(CbItemParam record);
}