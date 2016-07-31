package com.convenientbuy.mapper;

import com.convenientbuy.pojo.CbItemDesc;
import com.convenientbuy.pojo.CbItemDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by bonismo@hotmail.com
 * 下午10:32 on 16/7/31.
 */
public interface CbItemDescMapper {
    int countByExample(CbItemDescExample example);

    int deleteByExample(CbItemDescExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(CbItemDesc record);

    int insertSelective(CbItemDesc record);

    List<CbItemDesc> selectByExampleWithBLOBs(CbItemDescExample example);

    List<CbItemDesc> selectByExample(CbItemDescExample example);

    CbItemDesc selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") CbItemDesc record, @Param("example") CbItemDescExample example);

    int updateByExampleWithBLOBs(@Param("record") CbItemDesc record, @Param("example") CbItemDescExample example);

    int updateByExample(@Param("record") CbItemDesc record, @Param("example") CbItemDescExample example);

    int updateByPrimaryKeySelective(CbItemDesc record);

    int updateByPrimaryKeyWithBLOBs(CbItemDesc record);

    int updateByPrimaryKey(CbItemDesc record);
}